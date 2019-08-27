/*
https://www.acmicpc.net/problem/1194
[����]
���� �ν��̰� ��ȹ�� ������ ���� �� ó�� �߱� ������ �� ����, �غ��ߴ� ������̴�. 
������, �Ź� ���� ������ ������ �ν��̴� ��¿ �� ���� ������ �� �տ��� ������ �����ϰ� ���Ҵ�.

�ν��̴� �Ź� �ڽ��� ������ ���Ϸ��� ���������, ���� �ϸ� �ƹ��� �� �˾Ƶ��� �͸� ���Ƽ�, ���� �̸԰� ����� �Ǿ���ȴ�.
�ᱹ �ν��̴� ��� ��� ���� �׽� �� Ȧ�� �Ͼ, â �ۿ� ���ִ� ���� ���Ҵ�.

�Ϸ�ۿ� ���� �ʾҴ�. ���� �����̸� �� ��������. �̹��� ��������ȸ��. �̰� ��ġ�� ���� ������.

�����̴� �ν��̰� ���õ� ���°�ó�� �׳� �� �������� �� ������ �𸥴ٰ� �����ߴ�. ������ �׷��⿣ �ν����� ������ ���� �� ���� �ʹ��� ���ȴ�.

�ν��̴� ���� �̷� �ӿ� �ִ�. �̷δ� ���簢�� ����̰�, ������� ������ ���� �̷θ� Ż���Ϸ��� �Ѵ�. �̷δ� ������ ���� �����Ǿ����ִ�.

�� �� : ������ �̵��� �� �ִ�. ('.���� ǥ�õ�)
�� : ���� �̵��� �� ����. (��#��)
���� : ������ �̵��� �� �ִ�. �� ���� ó�� ���� ���踦 ���´�. (a - f)
�� : �����ϴ� ���谡 ���� ���� �̵��� �� �ִ�. (A - F)
�ν����� ���� ��ġ : �� ���̰�, �ν��̰� ���� �� �ִ� ���̴�. (���� 0)
�ⱸ : ���� �������� ������, �ν��̰� �����ϴ� ���̴�. �� ���� ���� �̷θ� Ż���Ѵ�. (���� 1)
���� �������� ��ȸ�� ��ġ�� �ʱ� ���ؼ�, �̷θ� Ż���Ϸ��� �Ѵ�. �� ���� �������� ���� ��ġ���� �����̳� �������� �� ĭ �̵��ϴ� ���̴�.

�ν��̰� �̷θ� Ż���ϴµ� �ɸ��� �̵� Ƚ���� �ּڰ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �̷��� ���� ũ�� N�� ���� ũ�� M�� �־�����. (1 �� N, M �� 50) ��° �ٺ��� N���� �ٿ� �̷��� ����� �־�����. ���� Ÿ���� ���谡 ���� �� ���� �� �ְ�, ���� ���������̴�. �׸���, �����̰� ���踦 ���ܳ��� �ٸ� ���� �����ϴ� ���谡 ���� ���� �ִ�. 0�� �� ��, 1�� ��� �� �� �ִ�. �׸���, ����� ���� �� ����� �� �ִ�.

[���]
ù° �ٿ� �ν��̰� �̷θ� Ż���ϴµ� ��� �̵� Ƚ���� �ּڰ��� ����Ѵ�. ���� �ν��̰� �̷θ� Ż�� �� �� ������, -1�� ����Ѵ�.

[Ǯ��]
1. key���� ���������� visited �迭�� ��Ʈ����ŷ���� ǥ���Ѵ�.
2. ���踦 ������ | �������� key�� �����ϰ� ť�� �ִ´�.
3. ���� �����ϸ� ������ & �������� ���踦 ������ �ִ��� �˻��Ѵ�.
4. �����ϸ� ������ �Ÿ��� ��ȯ�ϰ�, �������� ���ϸ� -1�� ��ȯ�Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][1 << 6];
		Node start = null;
		String str = null;

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					start = new Node(i, j, 0, 0);
				}
			}
		}

		bw.write(String.valueOf(BFS(start)));
		bw.close();
		br.close();
	}

	private static int BFS(Node start) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y][0] = true;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			int key = cur.key;

			if (map[x][y] == '1')
				return cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nkey = key;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '#' || visited[nx][ny][key])
					continue;

				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f')
					nkey |= (1 << map[nx][ny] - 'a');

				if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
					if((nkey & (1 << (map[nx][ny] - 'A'))) == 0)
						continue;
				}

				visited[nx][ny][nkey] = true;
				queue.add(new Node(nx, ny, cnt + 1, nkey));
			}
		}
		return -1;
	}
}

class Node {
	int x, y, cnt, key;

	Node(int x, int y, int cnt, int key) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.key = key;
	}
}