/*
https://www.acmicpc.net/problem/9205
[����]
�۵��� ��� ����̿� ģ������ �۵����� ������ ��Ÿ��Ʈ �� �佺Ƽ���� ������ �Ѵ�. 
���ش� ���ָ� ���ø鼭 �ɾ��� �ߴ�. ����� ����̳� ������ �ϰ�, ���� �� �ڽ��� ��� ����Ѵ�. 
���� �� �ڽ����� ���ְ� 20�� ����ִ�. ���� ������ �ȵǱ� ������ 50���Ϳ� �� ���� ���÷��� �Ѵ�.

������� ������ �佺Ƽ���� ������ ���� �ſ� �� �Ÿ��̴�. ����, ���ָ� �� �����ؾ� �� ���� �ִ�. 
�̸� ���ͳ����� ���縦 �غ��� �������� ���ָ� �Ĵ� �������� �ִ�. �������� ����� ��, �� ���� ������ �� ���� ���� �� �� �ִ�. 
������, �ڽ��� ����ִ� ���ִ� 20���� ���� �� ����.

������, ����̳� ��, ��Ÿ��Ʈ �� �佺Ƽ���� ��ǥ�� �־�����. ����̿� ģ������ �ູ�ϰ� �佺Ƽ���� ������ �� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� t�� �־�����. (t �� 50)

�� �׽�Ʈ ���̽��� ù° �ٿ��� ���ָ� �Ĵ� �������� ���� n�� �־�����. (0 �� n �� 100).

���� n+2�� �ٿ��� ����̳� ��, ������, ��Ÿ��Ʈ �� �佺Ƽ�� ��ǥ�� �־�����. �� ��ǥ�� �� ���� x�� y�� �̷���� �ִ�. (�� �� ��� ����, -32768 �� x, y �� 32767)

�۵��� ���簢�� ������� ���� �����̴�. �� ��ǥ ������ �Ÿ��� x ��ǥ�� ���� + y ��ǥ�� ���� �̴�. (����ư �Ÿ�)

[���]
�� �׽�Ʈ ���̽��� ���ؼ� ����̿� ģ������ �ູ�ϰ� �佺Ƽ���� �� �� ������ "happy", �߰��� ���ְ� �ٴڳ��� "sad"�� ����Ѵ�.

[Ǯ��]
����Ʈ�� ��, ������, �� �佺Ƽ���� ��ǥ�� �־��ش�.
�湮���� �ʾҰ�, ���� �������� �Ÿ��� 1000������ ������ ��ǥ�鿡 ���ؼ� BFS�� �����Ѵ�.
�� �佺Ƽ��(n + 1)���� ������ �� ������ true, �������� �� �Ѵٸ� false�� �����Ѵ�.   

*/
import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<Node> list;
	static boolean[] visited;
	static int n;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder res = new StringBuilder();

		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			list = new ArrayList<Node>(n + 2);
			visited = new boolean[n + 2];

			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new Node(x, y));
			}

			if (BFS())
				res.append("happy\n");
			
			else
				res.append("sad\n");
		}

		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}

	private static boolean distance(Node n1, Node n2) {
		if (Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y) > 1000)
			return false;

		return true;
	}

	private static boolean BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		visited[0] = true;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			if (cur == n + 1)
				return true;
			
			for (int i = 1; i < n + 2; i++) {
				if (!visited[i] && distance(list.get(cur), list.get(i))) {
					visited[i] = true;
					queue.add(i);
				}
			}
		}
		
		return false;
	}

}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
