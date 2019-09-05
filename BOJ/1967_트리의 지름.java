/*
https://www.acmicpc.net/problem/1967
[문제]
트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 
트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 
이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.



이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.

입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.



 

[입력]
파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n번째 줄까지 각 간선에 대한 정보가 들어온다. 
간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.

[출력]
첫째 줄에 트리의 지름을 출력한다.

[풀이]
트리의 지름은 한 점에서 끝 점까지 DFS 또는 BFS를 하고, 그 끝 점에서 다시 끝점으로 DFS 또는 BFS를 하면 구할 수 있다.


*/
import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<ArrayList<Node>> adj;
	static boolean[] visited;
	static int ans = 0;
	static int end = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		adj = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
		visited = new boolean[n + 1];
		
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			addEdge(p, c, d);
		}
		
		BFS(1);
		
		Arrays.fill(visited, false);
		
		BFS(end);
		
		bw.write(String.valueOf(ans));
		bw.close();
		br.close();
	}
	
	private static void addEdge(int p, int c, int d) {
		adj.get(p).add(new Node(c, d));
		adj.get(c).add(new Node(p, d));
	}
	
	private static void BFS(int idx) {
		Queue<Node> queue = new LinkedList<Node>(); 
		queue.add(new Node(idx, 0));
		visited[idx] = true;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int v = cur.v;
			int dist = cur.dist;
			
			if (ans < dist) {
				ans = dist;
				end = v;
			}
			
			for (Node w : adj.get(v)) {
				if (!visited[w.v]) {
					visited[w.v] = true;
					queue.add(new Node(w.v, dist + w.dist));
				}
			}
		}
	}
}

class Node {
	int v, dist;
	
	public Node(int v, int dist) {
		this.v = v;
		this.dist = dist;
	}
}