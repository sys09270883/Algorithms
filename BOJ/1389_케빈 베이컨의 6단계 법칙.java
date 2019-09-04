/*
https://www.acmicpc.net/problem/1389
[����]
�ɺ� �������� 6�ܰ� ��Ģ�� ���ϸ� ������ �ִ� ��� ������� �ִ� 6�ܰ� �̳����� ���� �ƴ� ������� ����� �� �ִ�. 
�ɺ� ������ ������ ������ �� ����� �ּ� �� �ܰ� ���� �̾��� �� �ִ��� ����ϴ� �����̴�.

���� ���, ���� ������� �� ���� ���ϴ��б��� �̰�ȣ�� �������б��� �μ���� �� �ܰ踸�� �̾��� �� ������?

õ��ȣ�� �̰�ȣ�� ���� �б��� �ٴϴ� �����̴�. õ��ȣ�� �ֹ����� Baekjoon Online Judge�� ���� �˰� �Ǿ���. 
�ֹ��ذ� �輱���� ���� Startlink�� â���ߴ�. �輱���� �赵���� ���� �б� ���Ƹ� �Ҽ��̴�. �赵���� �μ���� ���� �б��� �ٴϴ� ���̷� ���� �˰� �ִ�. ��, �̰�ȣ-õ��ȣ-�ֹ���-�輱��-�赵��-�μ��� �� ���� 5�ܰ踸 ��ġ�� �ȴ�.

�ɺ� �������� �̱� �渮��� ��ȭ���� ���� �ɺ� ������ ������ ������ ������ �ܰ��� �� ���� ���� ���� ����̶�� �Ѵ�.

������ Baekjoon Online Judge�� ���� �߿��� �ɺ� �������� ���� ���� ���� ����� ã������ �Ѵ�. �ɺ� ������ ���� ��� ����� �ɺ� ������ ������ ���� ��, ������ �ܰ��� ���̴�.

���� ���, BOJ�� ������ 5���̰�, 1�� 3, 1�� 4, 2�� 3, 3�� 4, 4�� 5�� ģ���� ��츦 �����غ���.

1�� 2���� 3�� ���� 2�ܰ� ����, 3���� 1�ܰ�, 4���� 1�ܰ�, 5���� 4�� ���ؼ� 2�ܰ� ���� �� �� �ִ�. ����, �ɺ� �������� ���� 2+1+1+2 = 6�̴�.

2�� 1���� 3�� ���ؼ� 2�ܰ� ����, 3���� 1�ܰ� ����, 4���� 3�� ���ؼ� 2�ܰ� ����, 5���� 3�� 4�� ���ؼ� 3�ܰ� ���� �� �� �ִ�. ����, �ɺ� �������� ���� 2+1+2+3 = 8�̴�.

3�� 1���� 1�ܰ�, 2���� 1�ܰ�, 4���� 1�ܰ�, 5���� 4�� ���� 2�ܰ� ���� �� �� �ִ�. ����, �ɺ� �������� ���� 1+1+1+2 = 5�̴�.

4�� 1���� 1�ܰ�, 2���� 3�� ���� 2�ܰ�, 3���� 1�ܰ�, 5���� 1�ܰ� ���� �� �� �ִ�. 4�� �ɺ� �������� ���� 1+2+1+1 = 5�� �ȴ�.

���������� 5�� 1���� 4�� ���� 2�ܰ�, 2���� 4�� 3�� ���� 3�ܰ�, 3���� 4�� ���� 2�ܰ�, 4���� 1�ܰ� ���� �� �� �ִ�. 5�� �ɺ� �������� ���� 2+3+2+1 = 8�̴�.

5���� ���� �߿��� �ɺ� �������� ���� ���� ���� ����� 3�� 4�̴�.

BOJ ������ ���� ģ�� ���谡 �Է����� �־����� ��, �ɺ� �������� ���� ���� ���� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ �� N (2 �� N �� 100)�� ģ�� ������ �� M (1 �� M �� 5,000)�� �־�����. ��° �ٺ��� M���� �ٿ��� ģ�� ���谡 �־�����. 
ģ�� ����� A�� B�� �̷���� ������, A�� B�� ģ����� ���̴�. A�� B�� ģ���̸�, B�� A�� ģ���̸�, A�� B�� ���� ���� ����. 
ģ�� ����� �ߺ��Ǿ� ���� ���� ������, ģ���� �� �� ���� ����� ����. ��, ��� ����� ģ�� ����� ����Ǿ��� �ִ�.

[���]
ù° �ٿ� BOJ�� ���� �߿��� �ɺ� �������� ���� ���� ���� ����� ����Ѵ�. �׷� ����� ���� ���� ��쿡�� ��ȣ�� ���� ���� ����� ����Ѵ�.

[Ǯ��]
BFS�� i��°���� j��°������ ���� sum �迭�� ����Ѵ�. (j != i)
sum�迭�� ��ȸ�ϸ鼭 ���� ���� ���� ������ �ε����� ����Ѵ�.

 + DFS�ε� ����

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static int[] sum;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>(N + 1);
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		sum = new int[N + 1];
		int A, B;
		Node res = new Node(-1, Integer.MAX_VALUE);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			addEdge(A, B);
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (j == i)
					continue;
				
				sum[i] += BFS(i, j);
				Arrays.fill(visited, false);
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (res.cnt > sum[i]) {
				res.cnt = sum[i];
				res.idx = i;
			}
		}
		
		bw.write(String.valueOf(res.idx));
		bw.close();
		br.close();
	}

	private static void addEdge(int A, int B) {
		adj.get(A).add(B);
		adj.get(B).add(A);
	}

	private static int BFS(int start, int end) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start, 0));
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int curIdx = cur.idx;
			int curCnt = cur.cnt;
			
			if (curIdx == end)
				return curCnt;
			
			for (int i : adj.get(curIdx)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, curCnt + 1));
				}
			}
		}

		return Integer.MIN_VALUE;
	}

}

class Node {
	int idx, cnt;

	public Node(int idx, int cnt) {
		this.idx = idx;
		this.cnt = cnt;
	}
}