/*
https://www.acmicpc.net/problem/4386
[����]
�����̴� ������ ���̴�. ���� �����̴� �ƹ����Գ� �κ귯�� �ִ� n���� ������ �̾ ���ڸ��� �ϳ� ���� ���̴�. ���ڸ��� ������ ������ ����.

���ڸ��� �̷�� ���� ���� �ٸ� �� ���� ���������� ���� �����̴�.
��� ������ ���ڸ� ���� ���� ���� ���� ��/���������� �̾��� �־�� �Ѵ�.
������ 2���� ��� ���� ���� �ִ�. ���� �ϳ� ���� ������ �� �� ������ �Ÿ���ŭ�� ����� ��ٰ� �� ��, ���ڸ��� ����� �ּ� ����� ���Ͻÿ�.

[�Է�]
ù° �ٿ� ���� ���� n�� �־�����. (1 �� n �� 100)

��° �ٺ��� n���� �ٿ� ���� �� ���� x, y��ǥ�� �Ǽ� ���·� �־�����, �ִ� �Ҽ��� ��°�ڸ����� �־�����. ��ǥ�� 1000�� ���� �ʴ� ���� �Ǽ��̴�.

[���]
ù° �ٿ� ������ ����Ѵ�. ����/��� ������ 10-2���� ����Ѵ�.

[Ǯ��]
������ �Է¹ް�, �Է¹��� ����� ��������Ʈ�� ������ �ִ´�.
��������Ʈ�� ������������ �����ϰ�, ������ ���̰� ���� �������� ������ ���� ����Ǿ��ִ��� Ȯ���Ѵ�.
���� ����Ǿ� ���� �ʴٸ� ���� �����ϰ�, �����ϴ� �Ÿ��� �����ش�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static ArrayList<Node> node;
	static ArrayList<Edge> edge;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		node = new ArrayList<Node>(n);
		edge = new ArrayList<Edge>(n * (n - 1) / 2);
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		StringTokenizer st = null;
		double res = 0d;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			node.add(new Node(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
		}
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				edge.add(new Edge(i, j, distance(node.get(i), node.get(j))));
			}
		}
		
		Collections.sort(edge);
		
		for (Edge e: edge) {
			if (find(e.n1) != find(e.n2)) {
				union(e.n1, e.n2);
				res += e.d;
			}
		}

		bw.write(String.format("%.2f", res));
		bw.close();
		br.close();
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
	
	private static double distance(Node n1, Node n2) {
		return Math.sqrt(Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2));
	}
	
}

class Node {
	
	double x, y;

	public Node(double x, double y) {
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