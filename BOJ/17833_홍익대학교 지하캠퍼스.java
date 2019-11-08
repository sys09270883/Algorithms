/*
https://www.acmicpc.net/problem/17833
[문제]
홍익대학교 서울 캠퍼스의 건물들은 정문부터 후문까지 연결되어 있지만 건물마다 연결되어 있는 층이 제각각이다. 
예를 들어 정문인 R동의 3층으로 나오면 K동의 1층이 있고, L동의 8층으로 나오면 I동의 1층이 있기도 하다. 
이렇듯 캠퍼스가 작지만 복잡하게 연결되어 있어 학생들 사이에선 '홍그와트'라는 별명으로 불리고 있다.

건축공학과 학생인 기열이는 이러한 특징이 잘 드러나도록 정문부터 후문까지 연결된 홍익대학교의 지하 캠퍼스를 설계하여 과제로 제출해야 한다. 
하지만 기열이는 자기 손으로 과제를 끝내기에 턱없이 시간이 모자란다고 생각하고 포기하기 직전에 놓여있다. 절망에 빠진 기열이를 위해 희망을 주도록 하자.

기열이를 위해 유명한 건물 모델 사이트에서 M개의 모델을 가져왔다. 
해당 모델들은 바로 3D프린터로 출력할 수 있으며, 이 모델들을 이어 붙이기만 하면 과제를 완성할 수 있도록 되어있다. 
또한 모든 모델은 좌우로 뒤집어 사용할 수 있도록 만들어졌다. (단 위아래가 바뀌도록 뒤집을 수는 없다.)

 과제에 주어진 규격 때문에 지하 1층부터 지하 N층까지밖에 건물을 배치할 수 없다.
 모든 건물은 양 옆면에 출입구가 한 개 씩 있으며 다른 건물과 연결될 때 그림과 같이 출입구끼리 연결되어야한다.
 각 모델에는 건물의 층 수 Hi, 출입구가 위치한 층인 Ei1, Ei2가 정해져 있다. 또한 해당 모델을 3D프린터로 출력하는 시간 Ti 또한 주어진다.
 같은 모델을 여러 번 사용하는 것도 가능하지만, 모델을 사용할 때마다 동일한 시간이 든다.
 정문의 R층과 후문의 D층에는 지하 캠퍼스와 연결되는 출입구가 존재한다. 이를 각각 시작점과 끝점으로 하여 홍익대학교 지하캠퍼스를 설계한다
샘플의 높이를 맘대로 바꿀 수는 없지만 너비를 바꾸는 것은 매우 쉬운 일이기 때문에 건물을 몇 개나 사용하는지는 중요하지 않다고 할 때, 
캠퍼스를 설계할 때 드는 최소 시간을 구해서 기열이에게 알려주자.



위와 같이 3개의 모델이 주어진다고 했을 때, 각각 H, T, E1, E2값은 각각 모델 아래 박스 안의 값과 같다.

N=9, R=3, D=2 라고 할 때, 위 세 모델로 캠퍼스를 설계한다면 시간을 최소로 하는 설계 방법은 아래 그림과 같다.



이 때 걸리는 시간은 38이다.

[입력]
첫 째 줄에는 건물을 배치할 수 있는 지하 층 수에 해당하는 자연수 N이 주어진다. ( N은 2 ≤ N ≤ 2,000 이다. )

둘 째 줄에는 각각 정문과 후문에 출입구가 위치한 층 R과 D가 공백을 사이에 두고 주어진다. ( R과 D는 각각 1 ≤ R ≤ N, 1 ≤ D ≤ N 인 자연수다. )

셋 째 줄에는 샘플의 개수 M이 주어진다. ( M은 1 ≤ M ≤ 2,000 인 자연수다. )

넷 째 줄부터 M+3번째 줄까지 각 줄에는 샘플의 꼭대기 층 Hi, 수정하는데 걸리는 시간 Ti, 출입구가 있는 층 Ei1, Ei2가 각각 공백을 사이에 두고 주어진다.

( 이 때 위의 입력은 각각 2 ≤ Hi ≤ N, 1 ≤Ti ≤ 1,000,000, 1 ≤ Ei1 ≤ Hi , 1 ≤ Ei2 ≤ Hi  를 만족하는 자연수이다. 단, Ei1 ≠ Ei2 이다. )

[출력]
첫 째 줄에 과제를 끝마치기 위해 필요한 최소 시간을 출력하라.

단, 주어진 모델로 캠퍼스를 설계할 수 없다면 첫 째 줄에 -1을 출력하라.

[풀이]
R에서 D까지의 최소 시간을 구하는 문제이므로 다익스트라로 접근한다.
건물들의 좌, 우를 바꿔서 배치할 수 있으므로 양방향 그래프로 연결한다. 연결할 때 높이 제한을 유의한다.
다익스트라로 D까지의 최소 시간을 구한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	final static int INF = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Node>> adj; 
	static ArrayList<Building> buildings;
	static int[] times;
	static int N, R, D, M;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		R = io.nextInt();
		D = io.nextInt();
		M = io.nextInt();
		adj = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
		buildings = new ArrayList<Building>();
		times = new int[N + 1];
		Arrays.fill(times, INF);
		
		for (int i = 0; i < M; i++) {
			buildings.add(new Building(io.nextInt(), io.nextInt(), io.nextInt(), io.nextInt()));
		}
				
		addEdge();
		dijkstra();
		
		io.write(times[D] == INF ? -1 : times[D]);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(R, 0));
		times[R] = 0;
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int cur = tmp.idx;
			int curTime = tmp.time;
			
			if (curTime > times[cur])
				continue;
			
			for (Node n : adj.get(cur)) {
				int next = n.idx;
				int nextTime = n.time;
				
				if (times[next] > times[cur] + nextTime) {
					times[next] = times[cur] + nextTime;
					pq.add(new Node(next, times[next]));
				}
			}
		}
	}
	
	private static void addEdge() {
		for (Building b : buildings) {
			for (int j = 0; j <= N - b.h; j++) {
				adj.get(b.e1 + j).add(new Node(b.e2 + j, b.t));
				adj.get(b.e2 + j).add(new Node(b.e1 + j, b.t));
			}
		}
	}
	
}

class Node implements Comparable<Node>{
	int idx, time;

	public Node(int idx, int time) {
		super();
		this.idx = idx;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}
	
}

class Building {
	int h, t, e1, e2;

	public Building(int h, int t, int e1, int e2) {
		super();
		this.h = h;
		this.t = t;
		this.e1 = e1;
		this.e2 = e2;
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
