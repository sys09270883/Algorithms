/*
https://www.acmicpc.net/problem/2234
[����]

�뷫 ���� �׸��� ���� ���� ������ �ִ�. ���� ���� ���� ��Ÿ����, ������ ���� ��� �����ٴ� �� �ִ� ��θ� ��Ÿ����. 
�̷��� ������ ���� ������ �Է¹޾Ƽ� ������ ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

  1. �� ���� �ִ� ���� ����
  2. ���� ���� ���� ����
  3. �ϳ��� ���� �����Ͽ� ���� �� �ִ� ���� ���� ���� ũ��
���� �������� ���� 5����, ���� ū ���� 9���� ĭ���� �̷���� ������, ���� �׸����� ȭ��ǥ�� ����Ű�� ���� �����ϸ� 16�� ũ���� ���� ���� �� �ִ�.

���� m��n(1 �� m, n �� 50)���� ���簢�� ĭ���� �̷������. ������ �ּ� �� ���� ���� �־, �׻� �ϳ��� ���� �����Ͽ� �� ���� ��ġ�� ��찡 �ִ�.

[�Է�]
ù° �ٿ� �� ���� n, m�� �־�����. ���� m���� �ٿ��� n���� ������ ���� ���� ������ �־�����. 
���� ���� ������ �� ������ �־����µ�, ���ʿ� ���� ���� ���� 1��, ���ʿ� ���� ���� ���� 2��, ���ʿ� ���� ���� ���� 4��, ���ʿ� ���� ���� ���� 8�� ���� ���� �־�����.
 ����� �������� �� ��Ʈ�� �����ϸ� ����. ���� �� ���� 0���� 15������ ���� �ȿ� �ִ�.

[���]
ù° �ٿ� 1�� ����, ��° �ٿ� 2�� ����, ��° �ٿ� 3�� ���� ����Ѵ�.

[Ǯ��]
BFS + ��Ʈ����ŷ ��������.
1���� 2���� ���� �� ���� BFS�� ���Ѵ�.
ť�� y, x�� �ְ� ���ϵ��� ������ �� �� �ִ��� ���θ� Ȯ���Ѵ�.
�� �� �ִ����� ���� ���δ� ���� ��ġ�� ���� �� ���� (pow(2, i))�� & �������� Ȯ���Ѵ�.

3���� ���� ���� ��ȸ�ϸ鼭 ���� �־� ���� ���� ��� ���� �μ��� BFS�� �����Ѵ�.
���� �μ��� ����� map[i][j]���� �ش� ������ �ǹ��ϴ� ���� ���ָ� �ȴ�. (W = 1, N = 2, E = 4, S = 8)
�� �� BFS�� �����ϰ� �ٽ� ���� �ǵ��� ���´�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {0, -1, 0, 1}; // WNES order
	static int[] dx = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		visited = new boolean[m][n];
		int roomCnt = 0, maxRoomSize = 0, breakMaxRoomSize = 0;
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					maxRoomSize = Math.max(maxRoomSize, BFS(i, j));
					roomCnt++;
				}
			}
		}
		res.append(roomCnt).append('\n').append(maxRoomSize).append('\n');

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k <= 8; k *= 2) {
					if ((map[i][j] & k) != 0) {
						for (boolean[] row : visited) {
							Arrays.fill(row, false);
						}
						map[i][j] -= k;
						breakMaxRoomSize = Math.max(breakMaxRoomSize, BFS(i, j));
						map[i][j] += k;
					}
				}
			}
		}
		res.append(breakMaxRoomSize);

		bw.write(res.toString());
		bw.close();
		br.close();
	}

	private static int BFS(int c, int r) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(c, r));
		visited[c][r] = true;
		int area = 1;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int y = cur.y;
			int x = cur.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny >= m || nx >= n)
					continue;

				if (visited[ny][nx])
					continue;

				if (((int)Math.pow(2, i) & map[y][x]) == 0) {
					area++;
					visited[ny][nx] = true;
					queue.add(new Node(ny, nx));
				}
			}
		}

		return area;
	}

}

class Node {
	int y, x;

	public Node(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

}
