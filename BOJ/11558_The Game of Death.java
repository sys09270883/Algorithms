/*
https://www.acmicpc.net/problem/11558
[문제]
희현이와 주경이는 The Game of Death를 좋아한다.

The Game of Death 규칙:

플레이어는 각자 한 명씩 지목을 한다(자신도 가능).
처음 시작하는 사람은 임의의 자연수 K를 말한다.
시작한 사람부터 지목한 사람을 차례대로 따라가다가 K번째 지목당한 사람이 걸리게 된다.
희현이는 희현이부터 이 게임을 시작할 때 이 게임에서 반드시 주경이를 반드시! 걸리게 하고 싶다. 주경이가 걸릴 수 있도록 희현이를 도와주자.

[입력]
첫 줄에는 테스트 케이스의 숫자 T가 주어지며, 이어서 T번에 걸쳐 테스트 케이스들이 주어진다.

매 테스트 케이스의 첫 줄에는 플레이어의 숫자 N(1 ≤ N ≤ 10,000)이 주어진다.

이어서 N줄에 걸쳐 각 플레이어가 지명한 사람의 숫자 Ai(1 ≤ Ai ≤ N, 1 ≤ i ≤ N)이 주어진다.

희현이는 1번, 주경이는 N번이다,

[출력]
매 테스트 케이스마다 한 줄에 걸쳐 희현이가 주경이를 걸리게 하고 싶을 때 불러야 하는 최소 숫자 K를 출력한다.

만약 어떤 숫자를 말해도 주경이가 걸리지 않는다면 0을 출력해야 한다.

[풀이]
BFS로 N이 나올 때 까지 cnt를 증가시키면서 탐색한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			adj = new ArrayList<ArrayList<Integer>>(N + 1);
			for (int i = 0; i < N + 1; i++) {
				adj.add(new ArrayList<>());
			}
			visited = new boolean[N + 1];
			
			for (int i = 1; i <= N; i++) {
				adj.get(i).add(Integer.parseInt(br.readLine()));
			}
			
			res.append(BFS()).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int cur = tmp.num;
			int cnt = tmp.cnt;
			
			if (cur == N)
				return cnt;
			
			for (int i : adj.get(cur)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
		
		return 0;
	}
	
}

class Node {
	int num, cnt;

	public Node(int num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
	
}