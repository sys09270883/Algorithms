/*
[����]
���� ������ �̷���� ���� �ִ�. �� ������ �������, ������ �մ� �ٸ��� ����ڴٴ� �������� �α���̸� �� �缱�� �� �־���. 
������ ���� ����ɿ� ��������, �ٸ��� ���´ٴ� ���� �Ʊ��ٴ� ������ �ϰ� �Ǿ���. 
�׷��� �״�, �������� ������ �� ���� �ٸ� ���� �մ� �ٸ� �ϳ����� ������ �Ͽ���, �� ���� �ٸ��� ���� ª�� �Ͽ� ���� �Ƴ��� �Ͽ���.

�� ����� N��Nũ���� ������ ���� �����Ѵ�. �� ����� ���� ������ �̷���� ������, ���̶� ������������ ������ �پ��ִ� ����� ���Ѵ�. 
������ �� ���� ������ �̷���� ������ �����̴�.



���� �׸����� ���� �ִ� �κ��� �����̰�, ���� ���� �κ��� �ٴ��̴�. �� �ٴٿ� ���� ª�� �ٸ��� ���� �� ����� �����ϰ��� �Ѵ�. 
���� ª�� �ٸ���, �ٸ��� ���ڿ��� �����ϴ� ĭ�� ���� ���� ���� �ٸ��� ���Ѵ�. ���� �׸����� �� ����� �����ϴ� �ٸ��� �� �� �ִ�.



���� ���� ��� �ܿ��� �ٸ��� ���� ����� ���� ���� ������, ���� ��찡 ���� �ٸ��� ���̰� 3���� ���� ª��(���� ���̰� 3�� �ٸ� �ٸ��� ���� �� �ִ� ����� �� ���� �ִ�).

������ �־��� ��, ���� ª�� �ٸ� �ϳ��� ���� �� ����� �����ϴ� ����� ã���ÿ�.

[�Է�]
ù �ٿ��� ������ ũ�� N(100������ �ڿ���)�� �־�����. �� ���� N�ٿ��� N���� ���ڰ� ��ĭ�� ���̿� �ΰ� �־�����, 0�� �ٴ�, 1�� ������ ��Ÿ����. 
�׻� �� �� �̻��� ���� �ִ� �����͸� �Է����� �־�����.

[���]
ù° �ٿ� ���� ª�� �ٸ��� ���̸� ����Ѵ�.

[Ǯ��]
��ü ���� �Է¹ް�, ������ ��ȣ�� �����Ѵ�. (���� ����� ������ ������ �ϴܱ��� ������)
���� ������ ���� ������ �� ������ dist�� ������Ű�� �ʰ�, �� ������ �ٸ� ������ ������ �� ���� dist�� ������Ų��.
dist�� �ּڰ��� ����Ѵ�.

 + �� �������� BFS�� �ϱ� ������ visited �迭�� �ʱ�ȭ�� �ϱ� ������ �޸𸮿� �ð��� ���� ��ƸԴ� �ڵ��� �� ����...
 + AC�� �޾����� �� ���� ������� �ٽ� Ǯ�� ���߰ڴ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st = null;
		int landNum = 1, ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					BFS(i, j, landNum++);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					for (boolean[] row : visited) {
						Arrays.fill(row, false);
					}
					
					ans = Math.min(ans, BFS(i, j));
				}
			}
		}

		bw.write(String.valueOf(ans));
		bw.close();
		br.close();
	}

	private static int BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c, 0));
		visited[r][c] = true;
		int startLandNum = map[r][c];

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int dist = cur.dist;

			if (map[x][y] > 0 && map[x][y] != startLandNum) {
				return dist - 1;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny])
					continue;

				visited[nx][ny] = true;
				queue.add(new Node(nx, ny, map[nx][ny] == startLandNum ? dist : dist + 1));
			}
		}

		return Integer.MIN_VALUE;
	}

	private static void BFS(int r, int c, int landNum) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r, c, 0));
		map[r][c] = landNum;
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;

				map[nx][ny] = landNum;
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
			}
		}
	}

}

class Node {
	int x, y, dist;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Node(int x, int y, int dist) {
		this(x, y);
		this.dist = dist;
	}
}