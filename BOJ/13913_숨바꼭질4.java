/*
https://www.acmicpc.net/problem/13913
[����]
�����̴� ������ ���ٲ����� �ϰ� �ִ�. �����̴� ���� �� N(0 �� N �� 100,000)�� �ְ�, ������ �� K(0 �� K �� 100,000)�� �ִ�. 
�����̴� �Ȱų� �����̵��� �� �� �ִ�. ����, �������� ��ġ�� X�� �� �ȴ´ٸ� 1�� �Ŀ� X-1 �Ǵ� X+1�� �̵��ϰ� �ȴ�. 
�����̵��� �ϴ� ��쿡�� 1�� �Ŀ� 2*X�� ��ġ�� �̵��ϰ� �ȴ�.

�����̿� ������ ��ġ�� �־����� ��, �����̰� ������ ã�� �� �ִ� ���� ���� �ð��� �� �� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù ��° �ٿ� �����̰� �ִ� ��ġ N�� ������ �ִ� ��ġ K�� �־�����. N�� K�� �����̴�.

[���]
ù° �ٿ� �����̰� ������ ã�� ���� ���� �ð��� ����Ѵ�.

��° �ٿ� ��� �̵��ؾ� �ϴ��� �������� ������ ����Ѵ�.

[Ǯ��]
�������� BFS�� ��θ� �����ϴ� �迭�� �߰��Ѵ�.
K�� �������� ��, ��θ� ������ Ž���Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static int MAX = 100001; 
	static int N, K;
	static int[] map;
	static boolean[] visited;
	static int[] path;
	static StringBuilder res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[MAX];
		visited = new boolean[MAX];
		path = new int[MAX];
		res = new StringBuilder();

		BFS();
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int cur = tmp.cur;
			int cnt = tmp.cnt;
			
			if (cur == K) {
				res.append(cnt).append('\n');
				
				Stack<Integer> stack = new Stack<Integer>();
				while (cur != N) {
					stack.add(cur);
					cur = path[cur];
				}
				stack.add(N);
				while (!stack.isEmpty()) {
					res.append(stack.pop()).append(' ');
				}
				
				return;
			}
			
			for (int i = 0; i < 3; i++) {
				int next = move(cur, i);
				
				if (next < 0 || next >= MAX)
					continue;
				
				if (visited[next])
					continue;
				
				visited[next] = true;
				path[next] = cur;
				queue.add(new Node(next, cnt + 1));
			}
		}
	}
	
	private static int move(int cur, int i) {
		int res = 0;
		
		switch (i) {
		case 0:
			res = cur + 1;
			break;
		case 1:
			res = cur - 1;
			break;
		case 2:
			res = cur * 2;
			break;
		}
		
		return res;
	}
	
}

class Node {
	int cur, cnt;

	public Node(int cur, int cnt) {
		super();
		this.cur = cur;
		this.cnt = cnt;
	}
	
}