/*
https://www.acmicpc.net/problem/17835
[����]
���������� ��� ���л��� �Ի縦 ����ϴ� ������ ���� �߽¹��̳� ���簡 �ڸ��� ��� �ִ�. 
�¹��̴� �߽¹��̳��� �����ε�, ���� �� �ϴ� �����鿡�� ȭ�� �� ������ �� ������ �ذ��ϰ� ���Ի���� ������ �Ѵ�. 
1�� ���������� ���� �� �հ��ڵ��� ������ �غ��ϰ� �Ǿ���.

�����ڵ��� ���� �ٸ� N���� ���ÿ� �����Ѵ�. �¹��̴� �����ڵ��� ���Ǹ� ���� ���� ���� N�� ���� �� K���� ���ÿ� �������� ��ġ�ߴ�. 
���ó����� �ܹ��� ���η� ����Ǹ�, �Ÿ��� ���� �ٸ� �� �ִ�. � �� ���� ���̿��� ���ΰ� ���� ����, ���� ���� ���� ���� �ִ�. 
���� � ���ÿ����� ��� �ϳ��� ��������� �� �� �ִ� ��ΰ� �׻� �����Ѵ�.

��� �����ڴ� ������ ���ÿ��� ����Ͽ� ���� ����� ���������� ã�ư� �����̴�. 
��, �Ʒ����� ��޵Ǵ� '����������� �Ÿ�'�� �� ���ÿ��� ���� ������ ���� ����� ����������� �ִ� �Ÿ��� ���Ѵ�.

���� ��� �¹��̴� ������ �������� �˱⿡ �� ���ÿ��� ����������� �Ÿ� ��, �� �Ÿ��� ���� �� ���ÿ��� ���� �����ڿ��� ����� �ַ��� �Ѵ�.

�¹��̸� ���� ����������� �Ÿ��� ���� �� ���ÿ� �� �Ÿ��� ���غ����� ����.

[�Է�]
ù° �ٿ� ������ �� N(2 �� N �� 100,000), ������ �� M(1 �� M �� 500,000), �������� �� K(1 �� K �� N)�� ������ �ΰ� �־�����. ���ô� 1������ N�������� ������ ��ȣ�� �Ű�����.

���� M���� �ٿ� ���� �� �ٸ��� ������ ��ȣ U, V(U �� V)�� ������ ���� C(1 �� C �� 100,000)�� ������ �ΰ� ������� �־�����. �̴� ���� U���� V�� �� �� �ִ� ���ΰ� �����ϰ�, �� �Ÿ��� C��� ���̴�.

������ �ٿ� �������� ��ġ�� ������ ��ȣ K���� ������ �ΰ� �־�����.

[���]
ù° �ٿ� ��������� �Ÿ��� ���� �� ������ ��ȣ�� ����Ѵ�. ���� �׷� ���ð� ���� ���̸� ���� ���� ��ȣ�� ����Ѵ�.

��° �ٿ� �ش� ���ÿ��� ����������� �Ÿ��� ����Ѵ�.

[Ǯ��]
�־��� �Է��� U���� V�� ���� ������ ���̵��̰�, ���ÿ��� ����������� �ִܰŸ��� ���ؾ� �Ѵ�. 
�̷� ��� ���� ������ ���������� �����ϰ� �����忡�� ���õ������ �ִܰŸ��� �����ϸ� �ȴ�.
�׸��� ���ͽ�Ʈ�� ������ �� ���� ���� �����忡 ���ؼ� ���ͽ�Ʈ�� ���� �ʰ� �� ���� ���ͽ�Ʈ�� ��� �������� �ְ� ���ͽ�Ʈ�� �Ѵ�.
O(NMlogM) -> O(MlogM)���� �ð����⵵ ���鿡�� �ſ� ���ȴ�. 
 + N�� 100000, ��� C�� 100000�̸� 1->2, 2->3, ... 999999->100000�� ��� �����÷ο찡 �߻��Ѵ�. ���� �˳��� long���� ��� �Ѵ�.

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
