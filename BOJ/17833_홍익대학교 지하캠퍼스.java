/*
https://www.acmicpc.net/problem/17833
[����]
ȫ�ʹ��б� ���� ķ�۽��� �ǹ����� �������� �Ĺ����� ����Ǿ� ������ �ǹ����� ����Ǿ� �ִ� ���� �������̴�. 
���� ��� ������ R���� 3������ ������ K���� 1���� �ְ�, L���� 8������ ������ I���� 1���� �ֱ⵵ �ϴ�. 
�̷��� ķ�۽��� ������ �����ϰ� ����Ǿ� �־� �л��� ���̿��� 'ȫ�׿�Ʈ'��� �������� �Ҹ��� �ִ�.

������а� �л��� �⿭�̴� �̷��� Ư¡�� �� �巯������ �������� �Ĺ����� ����� ȫ�ʹ��б��� ���� ķ�۽��� �����Ͽ� ������ �����ؾ� �Ѵ�. 
������ �⿭�̴� �ڱ� ������ ������ �����⿡ �ξ��� �ð��� ���ڶ��ٰ� �����ϰ� �����ϱ� ������ �����ִ�. ������ ���� �⿭�̸� ���� ����� �ֵ��� ����.

�⿭�̸� ���� ������ �ǹ� �� ����Ʈ���� M���� ���� �����Դ�. 
�ش� �𵨵��� �ٷ� 3D�����ͷ� ����� �� ������, �� �𵨵��� �̾� ���̱⸸ �ϸ� ������ �ϼ��� �� �ֵ��� �Ǿ��ִ�. 
���� ��� ���� �¿�� ������ ����� �� �ֵ��� ���������. (�� ���Ʒ��� �ٲ�� ������ ���� ����.)

 ������ �־��� �԰� ������ ���� 1������ ���� N�������ۿ� �ǹ��� ��ġ�� �� ����.
 ��� �ǹ��� �� ���鿡 ���Ա��� �� �� �� ������ �ٸ� �ǹ��� ����� �� �׸��� ���� ���Ա����� ����Ǿ���Ѵ�.
 �� �𵨿��� �ǹ��� �� �� Hi, ���Ա��� ��ġ�� ���� Ei1, Ei2�� ������ �ִ�. ���� �ش� ���� 3D�����ͷ� ����ϴ� �ð� Ti ���� �־�����.
 ���� ���� ���� �� ����ϴ� �͵� ����������, ���� ����� ������ ������ �ð��� ���.
 ������ R���� �Ĺ��� D������ ���� ķ�۽��� ����Ǵ� ���Ա��� �����Ѵ�. �̸� ���� �������� �������� �Ͽ� ȫ�ʹ��б� ����ķ�۽��� �����Ѵ�
������ ���̸� ����� �ٲ� ���� ������ �ʺ� �ٲٴ� ���� �ſ� ���� ���̱� ������ �ǹ��� �� ���� ����ϴ����� �߿����� �ʴٰ� �� ��, 
ķ�۽��� ������ �� ��� �ּ� �ð��� ���ؼ� �⿭�̿��� �˷�����.



���� ���� 3���� ���� �־����ٰ� ���� ��, ���� H, T, E1, E2���� ���� �� �Ʒ� �ڽ� ���� ���� ����.

N=9, R=3, D=2 ��� �� ��, �� �� �𵨷� ķ�۽��� �����Ѵٸ� �ð��� �ּҷ� �ϴ� ���� ����� �Ʒ� �׸��� ����.



�� �� �ɸ��� �ð��� 38�̴�.

[�Է�]
ù ° �ٿ��� �ǹ��� ��ġ�� �� �ִ� ���� �� ���� �ش��ϴ� �ڿ��� N�� �־�����. ( N�� 2 �� N �� 2,000 �̴�. )

�� ° �ٿ��� ���� ������ �Ĺ��� ���Ա��� ��ġ�� �� R�� D�� ������ ���̿� �ΰ� �־�����. ( R�� D�� ���� 1 �� R �� N, 1 �� D �� N �� �ڿ�����. )

�� ° �ٿ��� ������ ���� M�� �־�����. ( M�� 1 �� M �� 2,000 �� �ڿ�����. )

�� ° �ٺ��� M+3��° �ٱ��� �� �ٿ��� ������ ����� �� Hi, �����ϴµ� �ɸ��� �ð� Ti, ���Ա��� �ִ� �� Ei1, Ei2�� ���� ������ ���̿� �ΰ� �־�����.

( �� �� ���� �Է��� ���� 2 �� Hi �� N, 1 ��Ti �� 1,000,000, 1 �� Ei1 �� Hi , 1 �� Ei2 �� Hi  �� �����ϴ� �ڿ����̴�. ��, Ei1 �� Ei2 �̴�. )

[���]
ù ° �ٿ� ������ ����ġ�� ���� �ʿ��� �ּ� �ð��� ����϶�.

��, �־��� �𵨷� ķ�۽��� ������ �� ���ٸ� ù ° �ٿ� -1�� ����϶�.

[Ǯ��]
R���� D������ �ּ� �ð��� ���ϴ� �����̹Ƿ� ���ͽ�Ʈ��� �����Ѵ�.
�ǹ����� ��, �츦 �ٲ㼭 ��ġ�� �� �����Ƿ� ����� �׷����� �����Ѵ�. ������ �� ���� ������ �����Ѵ�.
���ͽ�Ʈ��� D������ �ּ� �ð��� ���Ѵ�.

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
