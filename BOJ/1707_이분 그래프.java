/*
https://www.acmicpc.net/problem/1707
[����]
�׷����� ������ ������ �ѷ� �����Ͽ�, �� ���տ� ���� ���������� ���� �������� �ʵ��� ������ �� ���� ��, �׷��� �׷����� Ư���� �̺� �׷��� (Bipartite Graph) �� �θ���.

�׷����� �Է����� �־����� ��, �� �׷����� �̺� �׷������� �ƴ��� �Ǻ��ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
�Է��� ���� ���� �׽�Ʈ ���̽��� �����Ǿ� �ִµ�, ù° �ٿ� �׽�Ʈ ���̽��� ���� K(2��K��5)�� �־�����. 
�� �׽�Ʈ ���̽��� ù° �ٿ��� �׷����� ������ ���� V(1��V��20,000)�� ������ ���� E(1��E��200,000)�� �� ĭ�� ���̿� �ΰ� ������� �־�����. �� �������� 1���� V���� ���ʷ� ��ȣ�� �پ� �ִ�. �̾ ��° �ٺ��� E���� �ٿ� ���� ������ ���� ������ �־����µ�, �� �ٿ� ������ �� ������ ��ȣ�� �� ĭ�� ���̿� �ΰ� �־�����.

[���]
K���� �ٿ� ���� �Է����� �־��� �׷����� �̺� �׷����̸� YES, �ƴϸ� NO�� ������� ����Ѵ�.

[Ǯ��]
1. �̺� �׷����� ������ ������ �� ���� �����θ� ĥ�� �� �ִ� �׷����̴�.
2. BFS�� Ž���ϸ鼭 �� ���� �� �� �ϳ��� ��ĥ�Ѵ�.
3. Ž���� ������ �� ������ ���� ���� ���̶�� �̺б׷����� �ƴϴ�.
4. ��� ��쿡 ���ؼ� 3�� ��찡 ������ �̺б׷����̴�. 


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
