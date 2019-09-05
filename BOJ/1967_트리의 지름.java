/*
https://www.acmicpc.net/problem/1967
[����]
Ʈ��(tree)�� ����Ŭ�� ���� ������ �׷����̴�. Ʈ�������� � �� ��带 �����ص� �� ���̿� ��ΰ� �׻� �ϳ��� �����ϰ� �ȴ�. 
Ʈ������ � �� ��带 �����ؼ� �������� �� ��� ��, ���� ��� �þ�� ��찡 ���� ���̴�. 
�̷� �� Ʈ���� ��� ������ �� �� ��带 ������ �� ������ �ϴ� �� �ȿ� ���� �ȴ�.



�̷� �� ��� ������ ����� ���̸� Ʈ���� �����̶�� �Ѵ�. ��Ȯ�� �������ڸ� Ʈ���� �����ϴ� ��� ��ε� �߿��� ���� �� ���� ���̸� ���Ѵ�.

�Է����� ��Ʈ�� �ִ� Ʈ���� ����ġ�� �ִ� ������� �� ��, Ʈ���� ������ ���ؼ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�. �Ʒ��� ���� Ʈ���� �־����ٸ� Ʈ���� ������ 45�� �ȴ�.



 

[�Է�]
������ ù ��° ���� ����� ���� n(1 �� n �� 10,000)�̴�. ��° �ٺ��� n��° �ٱ��� �� ������ ���� ������ ���´�. 
������ ���� ������ �� ���� ������ �̷���� �ִ�. ù ��° ������ ������ �����ϴ� �� ��� �� �θ� ����� ��ȣ�� ��Ÿ����, �� ��° ������ �ڽ� ��带, �� ��° ������ ������ ����ġ�� ��Ÿ����. ������ ���� ������ �θ� ����� ��ȣ�� ���� ���� ���� �Էµǰ�, �θ� ����� ��ȣ�� ������ �ڽ� ����� ��ȣ�� ���� ���� ���� �Էµȴ�. ��Ʈ ����� ��ȣ�� �׻� 1�̶�� �����ϸ�, ������ ����ġ�� 100���� ũ�� ���� ���� �����̴�.

[���]
ù° �ٿ� Ʈ���� ������ ����Ѵ�.

[Ǯ��]
Ʈ���� ������ �� ������ �� ������ DFS �Ǵ� BFS�� �ϰ�, �� �� ������ �ٽ� �������� DFS �Ǵ� BFS�� �ϸ� ���� �� �ִ�.


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