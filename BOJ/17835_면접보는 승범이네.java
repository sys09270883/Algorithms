/*
https://www.acmicpc.net/problem/17835
[문제]
마포구에는 모든 대학생이 입사를 희망하는 굴지의 대기업 ㈜승범이네 본사가 자리를 잡고 있다. 
승범이는 ㈜승범이네의 사장인데, 일을 못 하는 직원들에게 화가 난 나머지 전 직원을 해고하고 신입사원을 뽑으려 한다. 
1차 서류전형이 끝난 뒤 합격자들은 면접을 준비하게 되었다.

면접자들은 서로 다른 N개의 도시에 거주한다. 승범이는 면접자들의 편의를 위해 거주 중인 N개 도시 중 K개의 도시에 면접장을 배치했다. 
도시끼리는 단방향 도로로 연결되며, 거리는 서로 다를 수 있다. 어떤 두 도시 사이에는 도로가 없을 수도, 여러 개가 있을 수도 있다. 
또한 어떤 도시에서든 적어도 하나의 면접장까지 갈 수 있는 경로가 항상 존재한다.

모든 면접자는 본인의 도시에서 출발하여 가장 가까운 면접장으로 찾아갈 예정이다. 
즉, 아래에서 언급되는 '면접장까지의 거리'란 그 도시에서 도달 가능한 가장 가까운 면접장까지의 최단 거리를 뜻한다.

속초 출신 승범이는 지방의 서러움을 알기에 각 도시에서 면접장까지의 거리 중, 그 거리가 가장 먼 도시에서 오는 면접자에게 교통비를 주려고 한다.

승범이를 위해 면접장까지의 거리가 가장 먼 도시와 그 거리를 구해보도록 하자.

[입력]
첫째 줄에 도시의 수 N(2 ≤ N ≤ 100,000), 도로의 수 M(1 ≤ M ≤ 500,000), 면접장의 수 K(1 ≤ K ≤ N)가 공백을 두고 주어진다. 도시는 1번부터 N번까지의 고유한 번호가 매겨진다.

다음 M개의 줄에 걸쳐 한 줄마다 도시의 번호 U, V(U ≠ V)와 도로의 길이 C(1 ≤ C ≤ 100,000)가 공백을 두고 순서대로 주어진다. 이는 도시 U에서 V로 갈 수 있는 도로가 존재하고, 그 거리가 C라는 뜻이다.

마지막 줄에 면접장이 배치된 도시의 번호 K개가 공백을 두고 주어진다.

[출력]
첫째 줄에 면접장까지 거리가 가장 먼 도시의 번호를 출력한다. 만약 그런 도시가 여러 곳이면 가장 작은 번호를 출력한다.

둘째 줄에 해당 도시에서 면접장까지의 거리를 출력한다.

[풀이]
주어진 입력이 U에서 V로 가는 도로의 길이들이고, 도시에서 정거장까지의 최단거리를 구해야 한다. 
이럴 경우 간선 정보를 역방향으로 연결하고 정거장에서 도시들까지의 최단거리를 생각하면 된다.
그리고 다익스트라를 수행할 때 여러 개의 정거장에 대해서 다익스트라를 하지 않고 한 번의 다익스트라에 모든 정거장을 넣고 다익스트라를 한다.
O(NMlogM) -> O(MlogM)으로 시간복잡도 측면에서 매우 향상된다. 
 + N이 100000, 모든 C가 100000이며 1->2, 2->3, ... 999999->100000일 경우 오버플로우가 발생한다. 따라서 넉넉히 long으로 잡고 한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	final static long INF = Long.MAX_VALUE;
	static int N, M, K, U, V, C;
	static ArrayList<ArrayList<Node>> adj;
	static ArrayList<Integer> station;
	static long[] dists;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		K = io.nextInt();
		adj = new ArrayList<ArrayList<Node>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
		station = new ArrayList<Integer>(K);
		dists = new long[N + 1];
		Arrays.fill(dists, INF);
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			U = io.nextInt();
			V = io.nextInt();
			C = io.nextInt();
			adj.get(V).add(new Node(U, C));
		}
		
		for (int i = 0; i < K; i++) {
			station.add(io.nextInt());
		}
		
		dijkstra();
		
		long max = 0l, maxIdx = -1;
		
		for (int i = 1; i < N + 1; i++) {
			if (dists[i] < INF) {
				if (max < dists[i]) {
					maxIdx = i;
					max = dists[i];
				}
			}
		}
		
		res.append(maxIdx).append('\n').append(max);
		io.write(res);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < K; i++) {
			int s = station.get(i);
			pq.add(new Node(s, 0l));
			dists[s] = 0l;
		}
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int cur = tmp.idx;
			long curDist = tmp.dist;
			
			if (curDist > dists[cur])
				continue;
			
			for (Node n : adj.get(cur)) {
				int next = n.idx;
				long nextDist = n.dist;
				
				if (dists[next] > dists[cur] + nextDist) {
					dists[next] = dists[cur] + nextDist;
					pq.add(new Node(next, dists[next]));
				}
			}
		}
	}
	
}

class Node implements Comparable<Node>{
	int idx;
	long dist;

	public Node(int idx, long dist) {
		super();
		this.idx = idx;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Long.compare(this.dist, o.dist);
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
