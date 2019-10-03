/*
https://www.acmicpc.net/problem/16234
[����]
N��Nũ���� ���� �ְ�, ���� 1��1���� ĭ���� �������� �ִ�. ������ ������ ���� �ϳ��� �����ϸ�, r�� c���� �ִ� ���󿡴� A[r][c]���� ��� �ִ�. 
������ ���� ���̿��� ���漱�� �����Ѵ�. ��� ����� 1��1 ũ���̱� ������, ��� ���漱�� ���簢�� �����̴�.

���ú��� �α� �̵��� ���۵Ǵ� ���̴�.

�α� �̵��� ������ ���� ����ǰ�, �� �̻� �Ʒ� ����� ���� �α� �̵��� ���� ������ ���ӵȴ�.

���漱�� �����ϴ� �� ������ �α� ���̰� L�� �̻�, R�� ���϶��, �� ���� �����ϴ� ���漱�� ���� �Ϸ絿�� ����.
���� ���ǿ� ���� ������ϴ� ���漱�� ��� ���ȴٸ�, �α� �̵��� �����Ѵ�.
���漱�� �����־� ������ ĭ���� �̿��� �̵��� �� ������, �� ���� ���� �Ϸ� ������ �����̶�� �Ѵ�.
������ �̷�� �ִ� �� ĭ�� �α����� (������ �α���) / (������ �̷�� �ִ� ĭ�� ����)�� �ȴ�. ���ǻ� �Ҽ����� ������.
������ ��ü�ϰ�, ��� ���漱�� �ݴ´�.
�� ������ �α����� �־����� ��, �α� �̵��� �� �� �߻��ϴ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N, L, R�� �־�����. (1 �� N �� 50, 1 �� L �� R �� 100)

��° �ٺ��� N���� �ٿ� �� ������ �α����� �־�����. r�� c���� �־����� ������ A[r][c]�� ���̴�. (1 �� A[r][c] �� 100)

�α� �̵��� �߻��ϴ� Ƚ���� 2,000�� ���� �۰ų� ���� �Է¸� �־�����.

[���]
�α� �̵��� �� �� �߻��ϴ��� ù° �ٿ� ����Ѵ�.

[Ǯ��]
�������� ����Ž�� (DFS, BFS)������ ���� ������ �߸� �����ؼ� ���� �ɷȴ� ��������.
�Ϸ翡 ������ ������ �ִ��� �α� �̵��� 1���̿���.

BFS�� �����ϸ鼭 ��θ� ���ÿ� �ִ´�. BFS�� ������ ���ÿ� �ִ� ����� ���� �ٽ� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, L, R, res = 0;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag = true;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			flag = false;
			
			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;
					
					BFS(i, j);
				}
			}
			
			if (!flag)
				break;
			
			res++;
		}

		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}

	private static boolean BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> path = new Stack<Node>();
		int totalSum = map[r][c], cnt = 1;
		path.add(new Node(r, c));
		queue.add(new Node(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny])
					continue;

				int diff = Math.abs(map[nx][ny] - map[x][y]);

				if (L <= diff && diff <= R) {
					flag = true;
					visited[nx][ny] = true;
					cnt++;
					totalSum += map[nx][ny];
					path.add(new Node(nx, ny));
					queue.add(new Node(nx, ny));
				}
			}
		}
		
		int avr = totalSum / cnt;

		while (!path.isEmpty()) {
			Node tmp = path.pop();
			map[tmp.x][tmp.y] = avr;
		}

		return true;
	}
}

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
