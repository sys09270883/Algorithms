/*
https://www.acmicpc.net/problem/11558
[����]
�����̿� �ְ��̴� The Game of Death�� �����Ѵ�.

The Game of Death ��Ģ:

�÷��̾�� ���� �� �� ������ �Ѵ�(�ڽŵ� ����).
ó�� �����ϴ� ����� ������ �ڿ��� K�� ���Ѵ�.
������ ������� ������ ����� ���ʴ�� ���󰡴ٰ� K��° ������� ����� �ɸ��� �ȴ�.
�����̴� �����̺��� �� ������ ������ �� �� ���ӿ��� �ݵ�� �ְ��̸� �ݵ��! �ɸ��� �ϰ� �ʹ�. �ְ��̰� �ɸ� �� �ֵ��� �����̸� ��������.

[�Է�]
ù �ٿ��� �׽�Ʈ ���̽��� ���� T�� �־�����, �̾ T���� ���� �׽�Ʈ ���̽����� �־�����.

�� �׽�Ʈ ���̽��� ù �ٿ��� �÷��̾��� ���� N(1 �� N �� 10,000)�� �־�����.

�̾ N�ٿ� ���� �� �÷��̾ ������ ����� ���� Ai(1 �� Ai �� N, 1 �� i �� N)�� �־�����.

�����̴� 1��, �ְ��̴� N���̴�,

[���]
�� �׽�Ʈ ���̽����� �� �ٿ� ���� �����̰� �ְ��̸� �ɸ��� �ϰ� ���� �� �ҷ��� �ϴ� �ּ� ���� K�� ����Ѵ�.

���� � ���ڸ� ���ص� �ְ��̰� �ɸ��� �ʴ´ٸ� 0�� ����ؾ� �Ѵ�.

[Ǯ��]
BFS�� N�� ���� �� ���� cnt�� ������Ű�鼭 Ž���Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			adj = new ArrayList<ArrayList<Integer>>(N + 1);
			for (int i = 0; i < N + 1; i++) {
				adj.add(new ArrayList<>());
			}
			visited = new boolean[N + 1];
			
			for (int i = 1; i <= N; i++) {
				adj.get(i).add(Integer.parseInt(br.readLine()));
			}
			
			res.append(BFS()).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int cur = tmp.num;
			int cnt = tmp.cnt;
			
			if (cur == N)
				return cnt;
			
			for (int i : adj.get(cur)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
		
		return 0;
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