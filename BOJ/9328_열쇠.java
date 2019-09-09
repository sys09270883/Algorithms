/*
[����]
����̴� 1�� ������ ħ���� �ſ� �߿��� ������ ���Ŀ����� �Ѵ�. ����̰� ������ �ִ� ��鵵���� ������ ��ġ�� ��� ��Ÿ�� �ִ�. 
������ ���� ��� ����ֱ� ������, ���� ������ ���谡 �ʿ��ϴ�. ����̴� �Ϻ� ���踦 �̹� ������ �ְ�, �Ϻ� ����� ������ �ٴڿ� ������ �ִ�.

����̰� ��ĥ �� �ִ� ������ �ִ� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ������ �־�����. �׽�Ʈ ���̽��� ���� 100���� ���� �ʴ´�.

�� �׽�Ʈ ���̽��� ù° �ٿ��� ������ ���̿� �ʺ� h�� w (2 �� h, w �� 100)�� �־�����. 
���� h�� �ٿ��� ������ ��Ÿ���� w���� ���ڰ� �־�����, �� ���ڴ� ���� �� �ϳ��̴�.

'.'�� �� ������ ��Ÿ����.
'*'�� ���� ��Ÿ����, ����̴� ���� ����� �� ����.
'$'�� ����̰� ���ľ��ϴ� �����̴�.
���ĺ� �빮�ڴ� ���� ��Ÿ����.
���ĺ� �ҹ��ڴ� ���踦 ��Ÿ����, �� ������ �빮���� ��� ���� �� �� �ִ�.
������ �ٿ��� ����̰� �̹� ������ �ִ� ���谡 ������� �־�����. ����, ���踦 �ϳ��� ������ ���� �ʴ� ��쿡�� "0"�� �־�����.

����̴� ���� ������ ���� �� �ִ�. 
������ ���� ���ؼ�, �� ���� �� �� �ִ� ������ ������ 0��, 1��, �Ǵ� �� �̻��̰�, ������ ���迡 ���ؼ�, 
�� ����� �� �� �ִ� ���� ������ 0��, 1��, �Ǵ� �� �̻��̴�. ����� ���� �� ����� �� �ִ�.

[���]
�� �׽�Ʈ ���̽� ����, ����̰� ��ĥ �� �ִ� ������ �ִ� ������ ����Ѵ�.

[Ǯ��]
1. ��𼭵� ������ �� �ְ� �ϱ� ���� ���� �׵θ��� '.'�� �ʱ�ȭ��Ų��.
2. BFS �߿� Ű�� ��� ���� ���ϴ� ���� doors�� �����Ѵ�.
3. BFS �߿� Ű�� ����� ������ ���� Ű�� �� �� �ִ� ���� doors�� ��ȸ�ϸ鼭 ť�� �־��ش�.
4. ť�� �� ������ �� ������ �ݺ��ϰ� ť�� ��� cnt�� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int T, h, w;
	static char[][] map;
	static boolean[][] visited;
	static HashSet<Character> keys;
	static HashSet<Node> doors;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		String input = null;
		StringBuilder res = new StringBuilder();

		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h + 2][w + 2];
			for (char[] row : map) {
				Arrays.fill(row, '.');
			}
			doors = new HashSet<Node>();
			visited = new boolean[h + 2][w + 2];
			keys = new HashSet<Character>();
			cnt = 0;

			// input map
			for (int i = 1; i < h + 1; i++) {
				input = br.readLine();
				for (int j = 1; j < w + 1; j++) {
					map[i][j] = input.charAt(j - 1);
				}
			}

			// input key
			if (!(input = br.readLine()).equals("0")) {
				for (int i = 0; i < input.length(); i++) {
					keys.add(input.charAt(i));
				}
			}

			BFS();
			res.append(cnt).append('\n');
		}

		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int y = cur.y;
			int x = cur.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny > h + 1 || nx > w + 1)
					continue;

				if (visited[ny][nx] || map[ny][nx] == '*')
					continue;
				
				if ('A' <= map[ny][nx] && map[ny][nx] <= 'Z') {
					boolean flag = false;
					for (char c : keys) {
						if (map[ny][nx] == c - 32) {
							flag = true;
							break;
						}
					}
					
					if (!flag) {
						doors.add(new Node(ny, nx));
						continue;
					}
				}
				
				else if ('a' <= map[ny][nx] && map[ny][nx] <= 'z') {
					keys.add(map[ny][nx]);
					for (Node node : doors) {
						if (map[node.y][node.x] == map[ny][nx] - 32) {
							if (visited[node.y][node.x])
								continue;
							
							visited[node.y][node.x] = true;
							queue.add(new Node(node.y, node.x));
						}
					}
				}
				
				else if (map[ny][nx] == '$')
					cnt++;
				
				visited[ny][nx] = true;
				queue.add(new Node(ny, nx));
			}
		}
	}

}

class Node {
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}