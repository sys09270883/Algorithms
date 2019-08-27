/*
https://www.acmicpc.net/problem/13460
[����]
��ŸƮ��ũ���� �Ǹ��ϴ� ��̿� �峭�� �߿��� ���� �αⰡ ���� ��ǰ�� ���� Ż���̴�. 
���� Ż���� ���簢�� ���忡 ���� ������ �Ķ� ������ �ϳ��� ���� ����, ���� ������ ������ ���� ������ �����̴�.

������ ���� ũ��� N, ���� ũ��� M�̰�, ���ǻ� 1��1ũ���� ĭ���� �������� �ִ�. 
���� �ٱ� ��� ���� ��� ������ �ְ�, ���忡�� ������ �ϳ� �ִ�. 
���� ������ �Ķ� ������ ũ��� ���忡�� 1��1ũ���� ĭ�� ���� ä��� �������̰�, ���� �ϳ��� �� �ִ�. 
������ ��ǥ�� ���� ������ ������ ���ؼ� ������ ���̴�. �̶�, �Ķ� ������ ���ۿ� ���� �� �ȴ�.

�̶�, ������ ������ �ǵ帱 ���� ����, �߷��� �̿��ؼ� �̸� ���� ������ �Ѵ�. 
�������� ����̱�, ���������� ����̱�, �������� ����̱�, �Ʒ������� ����̱�� ���� �� ���� ������ �����ϴ�.

������ ���ۿ��� ���� ���ÿ� �����δ�. ���� ������ ���ۿ� ������ ����������, �Ķ� ������ ���ۿ� ������ �����̴�. 
���� ������ �Ķ� ������ ���ÿ� ���ۿ� ������ �����̴�. ���� ������ �Ķ� ������ ���ÿ� ���� ĭ�� ���� �� ����. 
��, ���� ������ �Ķ� ������ ũ��� �� ĭ�� ��� �����Ѵ�. ����̴� ������ �׸��ϴ� ���� �� �̻� ������ �������� ���� �� �����̴�.

������ ���°� �־����� ��, �ּ� �� �� ���� ���� ������ ������ ���� ���� �� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù ��° �ٿ��� ������ ����, ���� ũ�⸦ �ǹ��ϴ� �� ���� N, M (3 �� N, M �� 10)�� �־�����. 
���� N���� �ٿ� ������ ����� ��Ÿ���� ���� M�� ���ڿ��� �־�����. �� ���ڿ��� '.', '#', 'O', 'R', 'B' �� �̷���� �ִ�. 
'.'�� �� ĭ�� �ǹ��ϰ�, '#'�� ���� �̵��� �� ���� ��ֹ� �Ǵ� ���� �ǹ��ϸ�, 'O'�� ������ ��ġ�� �ǹ��Ѵ�. 'R'�� ���� ������ ��ġ, 'B'�� �Ķ� ������ ��ġ�̴�.

�ԷµǴ� ��� ������ �����ڸ����� ��� '#'�� �ִ�. ������ ������ �� �� �̸�, ���� ������ �Ķ� ������ �׻� 1���� �־�����.

[���]
�ּ� �� �� ���� ���� ������ ������ ���� ���� �� �ִ��� ����Ѵ�. ����, 10�� ���Ϸ� �������� ���� ������ ������ ���� ���� �� ������ -1�� ����Ѵ�.

[Ǯ��]
�������� �Ķ����� ���ؼ� BFS�ϰ�, ���� ��ġ�� ��쿡�� ���� �̵��� ���� ��ġ�� ��ĭ �ڷ� ������.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M, rx, ry, bx, by, hx, hy;
	static char[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][][][] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visited = new boolean[N][M][N][M];
		sb = new StringBuilder("-1");

		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);

				if (arr[i][j] == 'R') {
					rx = i;
					ry = j;
				}

				else if (arr[i][j] == 'B') {
					bx = i;
					by = j;
				}

				else if (arr[i][j] == 'O') {
					hx = i;
					hy = j;
				}
			}
		}

		BFS();

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(rx, ry, bx, by, 0));
		visited[rx][ry][bx][by] = true;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int rx = cur.rx;
			int ry = cur.ry;
			int bx = cur.bx;
			int by = cur.by;
			int cnt = cur.cnt;

			if (cnt > 10)
				continue;

			if (bx == hx && by == hy)
				continue;

			if (rx == hx && ry == hy) {
				sb.setLength(0);
				sb.append(cnt);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nrx = rx;
				int nry = ry;

				while (true) {
					if (arr[nrx][nry] != '#' && arr[nrx][nry] != 'O') {
						nrx += dx[i];
						nry += dy[i];
					}

					else {
						if (arr[nrx][nry] == '#') {
							nrx -= dx[i];
							nry -= dy[i];
						}
						break;
					}
				}

				int nbx = bx;
				int nby = by;

				while (true) {
					if (arr[nbx][nby] != '#' && arr[nbx][nby] != 'O') {
						nbx += dx[i];
						nby += dy[i];
					}

					else {
						if (arr[nbx][nby] == '#') {
							nbx -= dx[i];
							nby -= dy[i];
						}

						break;
					}
				}

				if ((nrx == nbx && nry == nby) && arr[nrx][nry] != 'O') {
					int rdist = Math.abs(nrx - rx) + Math.abs(nry - ry);
					int bdist = Math.abs(nbx - bx) + Math.abs(nby - by);

					if (rdist > bdist) {
						nrx -= dx[i];
						nry -= dy[i];
					}

					else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}

				if (!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby] = true;
					queue.add(new Node(nrx, nry, nbx, nby, cnt + 1));
				}
			}
		}
	}
}

class Node {
	int rx, ry, bx, by, cnt;

	Node(int rx, int ry, int bx, int by, int cnt){
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}