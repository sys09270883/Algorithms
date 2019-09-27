/*
https://www.acmicpc.net/problem/1774
[문제]
황선자씨는 우주신과 교감을 할수 있는 채널러 이다. 
하지만 우주신은 하나만 있는 것이 아니기때문에 황선자 씨는 매번 여럿의 우주신과 교감하느라 힘이 든다.
 이러던 와중에 새로운 우주신들이 황선자씨를 이용하게 되었다.

하지만 위대한 우주신들은 바로 황선자씨와 연결될 필요가 없다. 
이미 황선자씨와 혹은 이미 우주신끼리 교감할 수 있는 우주신들이 있기 때문에 새로운 우주신들은 그 우주신들을 거쳐서 황선자 씨와 교감을 할 수 있다.

우주신들과의 교감은 우주신들과 황선자씨 혹은 우주신들 끼리 이어진 정신적인 통로를 통해 이루어 진다. 
하지만 우주신들과 교감하는 것은 힘든 일이기 때문에 황선자씨는 이런 통로들이 긴 것을  좋아하지 않는다. 왜냐하면 통로들이 길 수록 더 힘이 들기 때문이다.

또한 우리들은 3차원 좌표계로 나타낼 수 있는 세상에 살고 있지만 우주신들과 황선자씨는 2차원 좌표계로 나타낼 수 있는 세상에 살고 있다. 
통로들의 길이는 2차원 좌표계상의 거리와 같다.

이미 황선자씨와 연결된, 혹은 우주신들과 연결된 통로들이 존재한다. 
우리는 황선자 씨를 도와 아직 연결이 되지 않은 우주신들을 연결해 드려야 한다. 
새로 만들어야 할 정신적인 통로의 길이들이 합이 최소가 되게 통로를 만들어 “빵상”을 외칠수 있게 도와주자.

[입력]
첫째 줄에 우주신들의 개수(N<=1,000) 이미 연결된 신들과의 통로의 개수(M<=1,000)가 주어진다.

두 번째 줄부터 N개의 줄에는 황선자를 포함하여 우주신들의 좌표가 (0<= X<=1,000,000), (0<=Y<=1,000,000)가 주어진다. 
그 밑으로 M개의 줄에는 이미 연결된 통로가 주어진다. 번호는 위의 입력받은 좌표들의 순서라고 생각하면 된다. 좌표는 정수이다.

[출력]
첫째 줄에 만들어야 할 최소의 통로 길이를 출력하라. 출력은 소수점 둘째짜리까지 출력하여라.

[풀이]
1~N까지의 노드를 입력받고, 간선을 추가한다. 추가적으로 길이가 0이 되는 간선도 추가한다.
간선이 겹치긴 하지만, 오름차순으로 정렬 후 길이가 짧은 간선부터 뽑을 때 노드가 겹치더라도 작은 길이부터 추출하므로 상관없다.
간선을 합쳐주면서 합을 갱신한다.

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