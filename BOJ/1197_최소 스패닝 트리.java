/*
https://www.acmicpc.net/problem/1197
[����]
�׷����� �־����� ��, �� �׷����� �ּ� ���д� Ʈ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�ּ� ���д� Ʈ����, �־��� �׷����� ��� �������� �����ϴ� �κ� �׷��� �߿��� �� ����ġ�� ���� �ּ��� Ʈ���� ���Ѵ�.

[�Է�]
ù° �ٿ� ������ ���� V(1 �� V �� 10,000)�� ������ ���� E(1 �� E �� 100,000)�� �־�����. ���� E���� �ٿ��� �� ������ ���� ������ ��Ÿ���� �� ���� A, B, C�� �־�����. �̴� A�� ������ B�� ������ ����ġ C�� �������� ����Ǿ� �ִٴ� �ǹ��̴�. C�� ������ ���� ������, ������ 1,000,000�� ���� �ʴ´�.

�ּ� ���д� Ʈ���� ����ġ�� -2147483648���� ũ�ų� ����, 2147483647���� �۰ų� ���� �����͸� �Է����� �־�����.

[���]
ù° �ٿ� �ּ� ���д� Ʈ���� ����ġ�� ����Ѵ�.

[Ǯ��]
ũ�罺Į �˰������� �ּ� ���д� Ʈ���� ���Ѵ�.
�������� ������������ ������ ��, ������ ���� ����Ǿ����� �ʴٸ�(findSameParent) ���� �����Ѵ�.(union-find)
�׸��� �� ������ �ִ� �ڽ�Ʈ�� �����ش�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static int MAX_SIZE = 10001;
	static int V, E;
	static ArrayList<Edge> edge;
	static int[] parent;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edge = new ArrayList<Edge>();
		parent = new int[MAX_SIZE];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A, B, C;
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			edge.add(new Edge(A, B, C));
		}
		
		Collections.sort(edge, (o1, o2) -> o1.cost - o2.cost);
		
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			int from = edge.get(i).from;
			int to = edge.get(i).to;
			int cost = edge.get(i).cost;
			
			if (!isSameParent(from, to)) {
				union(from, to);
				res += cost; 
			}
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (parent[x] == x)
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y)
			parent[y] = x;
	}
	
	private static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y)
			return true;
		
		return false;
	}
}

class Edge {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
}