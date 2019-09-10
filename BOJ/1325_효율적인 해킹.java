/*
https://www.acmicpc.net/problem/1325
[����]
��Ŀ �������� �� �˷��� ��� ȸ�縦 ��ŷ�Ϸ��� �Ѵ�. �� ȸ��� N���� ��ǻ�ͷ� �̷���� �ִ�. 
�������� ������ ������, �� ���� ��ŷ���� ���� ���� ��ǻ�͸� ��ŷ �� �� �ִ� ��ǻ�͸� ��ŷ�Ϸ��� �Ѵ�.

�� ȸ���� ��ǻ�ʹ� �ŷ��ϴ� �����, �ŷ����� �ʴ� ����� �̷���� �ִµ�, A�� B�� �ŷ��ϴ� ��쿡�� B�� ��ŷ�ϸ�, A�� ��ŷ�� �� �ִٴ� �Ҹ���.

�� ȸ���� ��ǻ���� �ŷ��ϴ� ���谡 �־����� ��, �� ���� ���� ���� ��ǻ�͸� ��ŷ�� �� �ִ� ��ǻ���� ��ȣ�� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ�, N�� M�� ���´�. N�� 10,000���� �۰ų� ���� �ڿ���, M�� 100,000���� �۰ų� ���� �ڿ����̴�. 
��° �ٺ��� M���� �ٿ� �ŷ��ϴ� ���谡 A B�� ���� �������� ������, "A�� B�� �ŷ��Ѵ�"�� �ǹ��Ѵ�. ��ǻ�ʹ� 1������ N������ ��ȣ�� �ϳ��� �Ű��� �ִ�.

[���]
ù° �ٿ�, �������� �� ���� ���� ���� ��ǻ�͸� ��ŷ�� �� �ִ� ��ǻ���� ��ȣ�� ������������ ����Ѵ�.

[Ǯ��]
BFS�� �ϸ鼭 res �迭�� ����Ѵ�.
res�� �ִ밪�� ���� �ε����� ����Ѵ�
 + ����Ŭ�� ����� ��쿡 ���ؼ� ����ó���ؾ��Ѵ�...

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[] res;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		res = new int[N + 1];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			addEdge(A, B);
		}
		
		for (int i = 1; i < N + 1; i++) {
			BFS(i);
			Arrays.fill(visited, false);
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 1; i < N + 1; i++) {
			if (max < res[i])
				max = res[i];
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (max == res[i])
				sb.append(i).append(' ');
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void addEdge(int A, int B) {
		adj.get(A).add(B);
	}
	
	private static void BFS(int n) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(n);
		visited[n] = true;
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(i);
					res[i]++;
				}
			}
		}
	}
}
