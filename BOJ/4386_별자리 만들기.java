/*
https://www.acmicpc.net/problem/4386
[문제]
도현이는 우주의 신이다. 이제 도현이는 아무렇게나 널브러져 있는 n개의 별들을 이어서 별자리를 하나 만들 것이다. 별자리의 조건은 다음과 같다.

별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.
별들이 2차원 평면 위에 놓여 있다. 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다고 할 때, 별자리를 만드는 최소 비용을 구하시오.

[입력]
첫째 줄에 별의 개수 n이 주어진다. (1 ≤ n ≤ 100)

둘째 줄부터 n개의 줄에 걸쳐 각 별의 x, y좌표가 실수 형태로 주어지며, 최대 소수점 둘째자리까지 주어진다. 좌표는 1000을 넘지 않는 양의 실수이다.

[출력]
첫째 줄에 정답을 출력한다. 절대/상대 오차는 10-2까지 허용한다.

[풀이]
점들을 입력받고, 입력받은 점들로 간선리스트에 정보를 넣는다.
간선리스트를 오름차순으로 정렬하고, 간선의 길이가 작은 간선부터 노드들이 서로 연결되어있는지 확인한다.
만약 연결되어 있지 않다면 서로 연결하고, 연결하는 거리를 더해준다.

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