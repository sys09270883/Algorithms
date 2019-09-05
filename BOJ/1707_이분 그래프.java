/*
https://www.acmicpc.net/problem/1707
[문제]
그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

[입력]
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K(2≤K≤5)가 주어진다. 
각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V(1≤V≤20,000)와 간선의 개수 E(1≤E≤200,000)가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호가 빈 칸을 사이에 두고 주어진다.

[출력]
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

[풀이]
1. 이분 그래프는 인접한 정점을 두 가지 색으로만 칠할 수 있는 그래프이다.
2. BFS로 탐색하면서 두 가지 색 중 하나를 색칠한다.
3. 탐색을 진행할 때 인접한 점이 같은 색이라면 이분그래프가 아니다.
4. 모든 경우에 대해서 3의 경우가 없으면 이분그래프이다. 


*/
import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<ArrayList<Integer>> adj;
	static int[] colors;
	static final int RED = 1;
	static final int BLUE = -1;
	static boolean isBipartite;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int K = Integer.parseInt(br.readLine()), V, E;
		StringTokenizer st = null;
		StringBuilder res = new StringBuilder();
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			adj = new ArrayList<ArrayList<Integer>>(V + 1);
			for (int i = 0; i < V + 1; i++) {
				adj.add(new ArrayList<Integer>());
			}
			colors = new int[V + 1];
			isBipartite = true;
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				addEdge(A, B, adj);
				
			}
			
			for (int i = 1; i < V + 1; i++) {
				if (!isBipartite)
					break;
				
				if (colors[i] == 0)
					BFS(i, RED);
			}
			
			res.append(isBipartite ? "YES" : "NO").append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void addEdge(int A, int B, ArrayList<ArrayList<Integer>> adj) {
		adj.get(A).add(B);
		adj.get(B).add(A);
	}
	
	private static void BFS(int idx, int color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(idx);
		colors[idx] = color;
		
		while (!queue.isEmpty() && isBipartite) {
			int cur = queue.poll();
			
			
			for (int i : adj.get(cur)) {
				if (colors[i] == 0) {
					colors[i] = colors[cur] * -1;
					queue.add(i);
				}
				
				else if (colors[i] == colors[cur]) {
					isBipartite = false;
					return;
				}
			}
		}
	}
	
}
