/*
https://www.acmicpc.net/problem/11724
[����]
���� ���� �׷����� �־����� ��, ���� ��� (Connected Component)�� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� N�� ������ ���� M�� �־�����. (1 �� N �� 1,000, 0 �� M �� N��(N-1)/2) ��° �ٺ��� M���� �ٿ� ������ �� ���� u�� v�� �־�����. (1 �� u, v �� N, u �� v) ���� ������ �� ���� �־�����.

[���]
ù° �ٿ� ���� ����� ������ ����Ѵ�.

[Ǯ��]
DFS �Ǵ� BFS�� ī�����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		visited = new boolean[N + 1];
		
		int a, b, cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			if(!visited[i]) {
				DFS(i);
//				BFS(i);
				cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
	
	private static void DFS(int v) {
		visited[v] = true;
		
		for (int i : adj.get(v)) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(i);
			}
		}
	}
	
	private static void BFS(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		visited[v] = true;
		queue.add(v);
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			
			for (int i : adj.get(tmp)) {
				if(!visited[i]) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}

