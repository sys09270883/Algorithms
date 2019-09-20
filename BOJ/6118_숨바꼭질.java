/*
https://www.acmicpc.net/problem/6118
[����]
�缭��� �����Ͽ� ���� ���忡�� ���ٲ����� �ϰ� �ִ�. ���忡�� �갣�� ���� �η��ְ� �缮�̴� �� �߿� �ϳ��� ����� �Ѵ�. 
�갣�� ������ N(2 <= N <= 20,000)���̸�, 1 ���� ���ٰ� ����.  

�缭��� �����ϰ� 1�� �갣���� ã�� ���� �˰� �ִ�. ��� �갣�� M(1<= M <= 50,000)���� ����� ��� �̾��� �ְ�, 
�� �� ���� A_i �� B_i(1<= A_i <= N; 1 <= B_i <= N; A_i != B_i)�� ��Ÿ����. ���� � �갣���� �ٸ� �갣���δ� ������ ���� �����ϴٰ� �����ص� ����. 

�缭��� �߳����� �����ϱ� ������ �ִ��� ������ �ȳ��� ���� ��Ҹ� ã���� �Ѵ�. 
������ 1�� �갣������ �Ÿ�(���⼭ �Ÿ��� ���� ������ �ϴ� ���� �ּ� �����̴�)�� �־������� �����Ѵٰ� �Ѵ�. �缭���� �߳����� �ִ��� ���� �� �ִ� �갣�� ã�� �� �ְ� ��������!

[�Է�]
ù ��° �ٿ��� N�� M�� ������ ���̿� �ΰ� �־�����.

���� M�ٿ� ���ļ� A_i�� B_i�� ������ ���̿� �ΰ� �־�����.

 

[���]
����� ���ٷ� �̷������, �� ���� ���� �������� �������� ����ؾ��Ѵ�. 

ù ��°�� ����� �ϴ� �갣 ��ȣ��(���� �Ÿ��� ���� �갣�� �������� ���� ���� �갣 ��ȣ�� ����Ѵ�), �� ��°�� �� �갣������ �Ÿ���, 
�� ��°�� �� �갣�� ���� �Ÿ��� ���� �갣�� ������ ����ؾ��Ѵ�.

[Ǯ��]
ù ��° BFS���� �ִ�Ÿ��� ���Ѵ�.
�� ��° BFS���� �ִ�Ÿ��� ���� �Ÿ��� �ִ� �갣���� �켱���� ť�� �־��ش�.
�켱���� ť�� peek()�� �ִ�Ÿ��� �켱���� ť�� ũ�⸦ ���ʴ�� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		pq = new PriorityQueue<Integer>();
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
					
			adj.get(A).add(B);
			adj.get(B).add(A);
		}
		
		int maxDist = BFS();
		Arrays.fill(visited, false);
		BFS(maxDist);
		
		res.append(pq.peek()).append(" ").append(maxDist).append(" ").append(pq.size());
		
		bw.write(res.toString());
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		int maxDist = Integer.MIN_VALUE;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int num = tmp.num;
			int cnt = tmp.cnt;
			maxDist = cnt;
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
		
		return maxDist;
	}
	
	private static void BFS(int maxDist) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int num = tmp.num;
			int cnt = tmp.cnt;
			
			if (cnt == maxDist)
				pq.add(num);
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
	}
	
}

class Node {
	int num, cnt;

	public Node(int num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
	
}