/*
https://www.acmicpc.net/problem/1774
[����]
Ȳ���ھ��� ���ֽŰ� ������ �Ҽ� �ִ� ä�η� �̴�. 
������ ���ֽ��� �ϳ��� �ִ� ���� �ƴϱ⶧���� Ȳ���� ���� �Ź� ������ ���ֽŰ� �����ϴ��� ���� ���.
 �̷��� ���߿� ���ο� ���ֽŵ��� Ȳ���ھ��� �̿��ϰ� �Ǿ���.

������ ������ ���ֽŵ��� �ٷ� Ȳ���ھ��� ����� �ʿ䰡 ����. 
�̹� Ȳ���ھ��� Ȥ�� �̹� ���ֽų��� ������ �� �ִ� ���ֽŵ��� �ֱ� ������ ���ο� ���ֽŵ��� �� ���ֽŵ��� ���ļ� Ȳ���� ���� ������ �� �� �ִ�.

���ֽŵ���� ������ ���ֽŵ�� Ȳ���ھ� Ȥ�� ���ֽŵ� ���� �̾��� �������� ��θ� ���� �̷�� ����. 
������ ���ֽŵ�� �����ϴ� ���� ���� ���̱� ������ Ȳ���ھ��� �̷� ��ε��� �� ����  �������� �ʴ´�. �ֳ��ϸ� ��ε��� �� ���� �� ���� ��� �����̴�.

���� �츮���� 3���� ��ǥ��� ��Ÿ�� �� �ִ� ���� ��� ������ ���ֽŵ�� Ȳ���ھ��� 2���� ��ǥ��� ��Ÿ�� �� �ִ� ���� ��� �ִ�. 
��ε��� ���̴� 2���� ��ǥ����� �Ÿ��� ����.

�̹� Ȳ���ھ��� �����, Ȥ�� ���ֽŵ�� ����� ��ε��� �����Ѵ�. 
�츮�� Ȳ���� ���� ���� ���� ������ ���� ���� ���ֽŵ��� ������ ����� �Ѵ�. 
���� ������ �� �������� ����� ���̵��� ���� �ּҰ� �ǰ� ��θ� ����� �������� ��ĥ�� �ְ� ��������.

[�Է�]
ù° �ٿ� ���ֽŵ��� ����(N<=1,000) �̹� ����� �ŵ���� ����� ����(M<=1,000)�� �־�����.

�� ��° �ٺ��� N���� �ٿ��� Ȳ���ڸ� �����Ͽ� ���ֽŵ��� ��ǥ�� (0<= X<=1,000,000), (0<=Y<=1,000,000)�� �־�����. 
�� ������ M���� �ٿ��� �̹� ����� ��ΰ� �־�����. ��ȣ�� ���� �Է¹��� ��ǥ���� ������� �����ϸ� �ȴ�. ��ǥ�� �����̴�.

[���]
ù° �ٿ� ������ �� �ּ��� ��� ���̸� ����϶�. ����� �Ҽ��� ��°¥������ ����Ͽ���.

[Ǯ��]
1~N������ ��带 �Է¹ް�, ������ �߰��Ѵ�. �߰������� ���̰� 0�� �Ǵ� ������ �߰��Ѵ�.
������ ��ġ�� ������, ������������ ���� �� ���̰� ª�� �������� ���� �� ��尡 ��ġ���� ���� ���̺��� �����ϹǷ� �������.
������ �����ָ鼭 ���� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<Node> node;
	static ArrayList<Edge> edge;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		node = new ArrayList<Node>(N + 1);
		edge = new ArrayList<Edge>(N * (N + 1) * 2);
		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		double res = 0d;
		
		node.add(new Node(0, 0));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			node.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edge.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
		}
		
		for (int i = 1; i <= N - 1; i++) {
			for (int j = i + 1; j <= N; j++) {
				edge.add(new Edge(i, j, distance(node.get(i), node.get(j))));
			}
		}
		
		Collections.sort(edge);
		
		for (Edge e : edge) {
			if (find(e.n1) != find(e.n2)) {
				union(e.n1, e.n2);
				res += e.d;
			}
		}
		
		bw.write(String.format("%.2f", res));
		bw.close();
		br.close();
	}
	
	private static double distance(Node n1, Node n2) {
		return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		parent[x] = y;
	}
	
}

class Node {
	
	int x, y;
	
	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}

class Edge implements Comparable<Edge>{
	
	int n1, n2;
	double d;
	
	public Edge(int n1, int n2, double d) {
		super();
		this.n1 = n1;
		this.n2 = n2;
		this.d = d;
	}

	@Override
	public int compareTo(Edge e) {
		// TODO Auto-generated method stub
		return Double.compare(this.d, e.d);
	}
	
}