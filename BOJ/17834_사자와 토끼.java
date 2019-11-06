/*
https://www.acmicpc.net/problem/17834
[문제]
사자와 토끼는 전국적으로 인기를 끌고 있는 재밌는 보드게임이다. 사자와 토끼를 즐기기 위해서는 2명의 플레이어와 1명의 심판이 필요하다. 
보드판은 N개의 수풀과 M개의 오솔길로 이루어져 있다. 
오솔길은 서로 다른 두 수풀을 양방향으로 연결하며, 어떤 수풀에서 다른 수풀까지 1개 이상의 오솔길을 통하면 반드시 도달 할 수 있다.

게임은 다음과 같은 순서로 이루어진다.

  

심판이 사자와 토끼의 초기 위치를 각각 어느 수풀로 할지 정한다. 
사자와 토끼의 초기 위치는 같을 수 없으며, 사자의 위치는 사자 플레이어에게만, 토끼의 위치는 토끼 플레이어에게만 알려준다.

매 턴마다, 사자와 토끼는 현재 위치한 수풀에서 오솔길 1개를 따라 이동해야 한다. 두 플레이어는 자신의 말을 이동할 위치를 심판에게만 말한다. 이동하지 않을 수는 없다.

이동한 후, 사자와 토끼가 같은 수풀에 있다면 사자가 토끼를 잡아먹어 게임이 끝난다. 
그렇지 않다면, 다시 2로 돌아가 턴을 계속하여 진행한다. 물론 게임이 끝나지 않는 이상, 이동 후에도 두 플레이어는 상대 말의 위치를 알 수 없다. 
또한, 사자는 오솔길 위에서는 토끼를 볼 수 없기 때문에, 같은 오솔길을 통해 이동하여도 서로 다른 수풀에 도착했다면 게임이 끝나지 않는다.

이렇게 서로 심리전을 통해 토끼는 사자에게서 도망가고, 사자는 토끼를 찾아내는 게임이다. 
그런데 보드의 모양과 사자와 토끼의 초기 위치에 따라서, 어떻게 움직여도 영원히 게임이 끝나지 않는 경우가 있다는 것을 발견했다. 
예를 들어, 위의 그림과 같은 보드판에서는 게임이 끝나지 않는 (사자의 초기 위치, 토끼의 초기 위치)의 조합은 다음과 같이 8가지가 존재한다 : 
(1,2) (1,4) (2,1) (2,3) (3,2) (3,4) (4,1) (4,3). 이런 경우에는, 심지어 두 플레이어가 서로의 위치를 알고 일부러 게임을 끝내려고 해도 끝낼 수 없다!

보드판의 모양이 주어질 때, 어떻게 움직여도 영원히 게임이 끝나지 않는 초기 위치의 경우의 수가 몇 가지가 있을지 구해보자.

[입력]
첫 번째 줄에 수풀의 수 N(2 ≤ N ≤ 50,000)과 오솔길의 수 M(1 ≤ M ≤ 500,000)이 공백으로 구분되어 주어진다.

두 번째 줄부터 M개의 줄에 걸쳐, u, v(1 ≤ u,v ≤ N, u ≠ v)가 공백으로 구분되어 주어진다. 이는 u번 수풀과 v번 수풀이 오솔길로 연결되어 있음을 의미한다.

[출력]
첫 번째 줄에 어떻게 움직여도 영원히 게임이 끝나지 않는 초기 위치의 경우의 수를 출력한다.

답이 32-bit형 정수(int)의 최대값보다 클 수 있음에 주의하자.

[풀이]
게임이 끝나지 않으려면 그래프를 이분그래프로 나누었을 때, 턴마다 서로 반대 색깔의 정점에 위치해야 한다.
따라서 이 문제는 이분그래프로 풀 수 있다.
이분그래프일 때, redCnt * blueCnt * 2가 선택할 수 있는 총 가지수이다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M, u, v;
	static long redCnt, blueCnt;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] colors;
	static boolean isBipartite;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		colors = new int[N + 1];
		isBipartite = true;
		
		for (int i = 0; i < M; i++) {
			u = io.nextInt();
			v = io.nextInt();
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (!isBipartite)
				break;
			
			if (colors[i] == 0)
				BFS(i, 1);
		}
		
		io.write(isBipartite ? redCnt * blueCnt * 2l : 0l);
	}
	
	private static void BFS(int idx, int color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(idx);
		colors[idx] = color;
		redCnt++;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : adj.get(cur)) {
				if (colors[next] == 0) {
					colors[next] = -colors[cur];
					
					if (colors[next] == 1)
						redCnt++;
					
					else
						blueCnt++;
					
					queue.add(next);
				}
				
				else if (colors[cur] == colors[next]){
					isBipartite = false;
					return;
				}
			}
		}
	}
	
	
}

class FastIO {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	FastIO() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return st.nextToken();
	}
	
	int nextInt() {
		return Integer.parseInt(next());
	}
	
	long nextLong() {
		return Long.parseLong(next());
	}
	
	double nextDouble() {
		return Double.parseDouble(next());
	}
	
	String nextLine() {
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	void write(double d) throws IOException { 
		bw.write(String.valueOf(d));
		close();
	}
	
	void write(int i) throws IOException {
		bw.write(String.valueOf(i));
		close();
	}
	
	void write(long l) throws IOException {
		bw.write(String.valueOf(l));
		close();
	}
	
	void write(StringBuilder sb) throws IOException {
		bw.write(sb.toString().trim());
		close();
	}
	
	void write(String str) throws IOException {
		bw.write(str.trim());
		close();
	}
	
	void close() throws IOException {
		bw.close();
		br.close();
	}
}
