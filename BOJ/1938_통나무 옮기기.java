/*
https://www.acmicpc.net/problem/1938
[����]
���ο� ������ ���̰� ���� �������� ������ �Ѵ�. �� ������ 0�� 1�� ��Ÿ�� �ִ�. 1�� ���� �߷����� ���� ������ ��Ÿ���� 0�� �ƹ� �͵� ������ ��Ÿ����. ���� ������ ����.

B 0 0 1 1
B 0 0 0 0
B 0 0 0 0
1 1 0 0 0
E E E 0 0
���� �������� ���� 3�� �볪�� BBB�� �аų� ȸ������ EEE�� ��ġ�� �ű�� �۾��� �ϴ� ������ ������ ����. BBB�� EEE�� ��ġ�� ���Ƿ� �־�����. 
�� �������� �볪���� ���̴� �׻� 3�̸� B�� ������ E�� ������ ����. �볪���� �����̴� ����� �Ʒ��� ���� �����¿�(Up, Down, Left, Right)�� ȸ��(Turn)�� �ִ�.

�ڵ�	�ǹ�
U	�볪���� ���� �� ĭ �ű��.
D	�볪���� �Ʒ��� �� ĭ �ű��.
L	�볪���� �������� �� ĭ �ű��.
R	�볪���� ���������� �� ĭ �ű��.
T	�߽����� �߽����� 90�� ȸ����Ų��.
���� ���, ������ ����. (�ʱ���·κ����� �̵�)

�ʱ����	��(U)	��(D)	��(L)	��(R)	ȸ��(T)

�̿� ���� ������� �̵���ų ���� �� ������ ��ġ�� �ٸ� ����, �� 1�� ����߸� ������ �� �ִ�. �׸��� �������� ���� �׸��� ���� �� ���� �� ĭ���� �����δ�. 
�� �����̴� �볪���� � ����̵��� �߰��ܰ迡�� �� ���̳� �� ������ ���� �� �ִ�. ���� ��� �Ʒ� �׸����� a�� ���� �ܰ�� �Ұ����ϴ�. 
�׸��� ȸ���� ��쿡���� �ݵ�� �߽����� �߽����� 90�� ȸ���� �ؾ� �Ѵ�. (�׻� �볪���� ���̰� 3�̹Ƿ� �߽����� ����)

�׸��� �̷� ȸ��(Turn)�� �����ϱ� ���ؼ��� �� �볪���� �ѷ��ΰ� �ִ� 3*3 ���簢���� ������ �� �� �׷��� ������ ����߸� �Ѵ�. 
��, �Ʒ��׸� b, d�� ���� ?�� ǥ�õ� ������ �ٸ� ����, �� 1�� ����߸� ȸ����ų �� �ִ�. ���� c�� ���� ��쿡, �볪���� ���� ���� ��ä���� ���� ���� ������ ȸ����ų �� ����.

a	b	c	d

������ �볪���� 5���� �⺻����(U, D, L, R, T)���� ����Ͽ� ó����ġ(BBB)���� ������ġ(EEE)�� �ű�� ���α׷��� �ۼ��ϴ� ���̴�. 
��, �ּ� Ƚ���� ���� ������ ����ؾ� �Ѵ�.

[�Է�]
ù° �ٿ� �־��� ������ �� ���� ���� N�� �־�����. (4<=N<=50) �־�����. �̾ �� ������ ������ 0, 1, B, E�� �̷���� ���ڿ��� �־�����. 
�� �ٿ� �ԷµǴ� ���ڿ��� ���̴� N�̸� �Է� ���� ���̿��� ��ĭ�� ����. �볪���� ���� ��ġ�� ������ 1���̴�.

[���]
ù° �ٿ� �ּ� ���� Ƚ���� ����Ѵ�. �̵��� �Ұ����ϸ� 0���� ����Ѵ�.

[Ǯ��]
�볪���� ������ x, y��ǥ�� ����(����, ����)�� �������� BFS�Ѵ�.
�湮ǥ�� ���� x, y�� ���·� ǥ���Ѵ�. ex) (2, 2, 0)�� (2, 2, 1)�� ������ ������ ���°� �ٸ��� ������ �ٸ���.
���� ��ǥ�� x, y, status�� ���� ��ǥ�� x, y, status�� ��ġ�� �� ���� U, D, L, R, T �Լ��� �̵���Ų��.

 + U, D, L, R, T �Լ��� �� ���� ������ �ʵ��� �����ؾ� �Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	final static int VERTICAL = 0;
	final static int HORIZONTAL = 1;
	static int N, startX, startY, startStatus, endX, endY, endStatus;
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][2];
		String input = null;
		boolean startFlag = false, endFlag = false;

		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!startFlag && map[i][j] == 'B') {
					if (i + 1 < N && map[i + 1][j] == 'B') {
						startX = i + 1;
						startY = j;
						startStatus = VERTICAL;
						startFlag = true;
					}

					else if (j + 1 < N && map[i][j + 1] == 'B') {
						startX = i;
						startY = j + 1;
						startStatus = HORIZONTAL;
						startFlag = true;
					}
				}

				else if (!endFlag && map[i][j] == 'E') {
					if (i + 1 < N && map[i + 1][j] == 'E') {
						endX = i + 1;
						endY = j;
						endStatus = VERTICAL;
						endFlag = true;
					}

					else if (j + 1 < N && map[i][j + 1] == 'E') {
						endX = i;
						endY = j + 1;
						endStatus = HORIZONTAL;
						endFlag= true;
					}
				}
			}
		}

		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}

	private static int[] U(int x, int y, int status) {
		int[] arr = new int[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = status;

		if (status == VERTICAL) {
			if (x < N - 1 && x - 2 >= 0 && map[x - 2][y] != '1') {
				for (int i = 2; i >= 0; i--) {
					map[x - i][y] = map[x + 1 - i][y];
				}
				map[x + 1][y] = '0';

				arr[0] = x - 1;
			}
		}

		else {
			if (y < N && y > 0 && x - 1 >= 0 && map[x - 1][y - 1] != '1' && map[x - 1][y] != '1' && map[x - 1][y + 1] != '1') {
				for (int i = 2; i >= 0; i--) {
					map[x - 1][y + 1 - i] = map[x][y + 1 - i];
					map[x][y + 1 - i] = '0';
				}

				arr[0] = x - 1;
			}
		}

		return arr;
	}

	private static int[] D(int x, int y, int status) {
		int[] arr = new int[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = status;

		if (status == VERTICAL) {
			if (x > 0 && x + 2 < N && map[x + 2][y] != '1') {
				for (int i = 0; i < 3; i++) {
					map[x + 2 - i][y] = map[x + 1 - i][y];
				}

				arr[0] = x + 1;
			}
		}

		else {
			if (y > 0 && x + 1 < N && map[x + 1][y - 1] != '1' && map[x + 1][y] != '1' && map[x + 1][y + 1] != '1') {
				for (int i = 0; i < 3; i++) {

					map[x + 1][y - 1 + i] = map[x][y - 1 + i];
					map[x][y - 1 + i] = '0';
				}

				arr[0] = x + 1;
			}
		}

		return arr;
	}

	private static int[] L(int x, int y, int status) {
		int[] arr = new int[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = status;

		if (status == VERTICAL) {
			if (x < N - 1 && x > 0 && y - 1 >= 0 && map[x - 1][y - 1] != '1' && map[x][y - 1] != '1' && map[x + 1][y - 1] != '1') {
				for (int i = 0; i < 3; i++) {
					map[x - 1 + i][y - 1] = map[x - 1 + i][y];
					map[x - 1 + i][y] = '0';
				}

				arr[1] = y - 1;
			}
		}

		else {
			if (y - 2 >= 0 && map[x][y - 2] != '1') {
				for (int i = 2; i >= 0; i--) {
					map[x][y - i] = map[x][y + 1 - i];
				}
				map[x][y + 1] = '0';

				arr[1] = y - 1;
			}
		}

		return arr;
	}

	private static int[] R(int x, int y, int status) {
		int[] arr = new int[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = status;

		if (status == VERTICAL) {
			if (x < N - 1 && x > 0 && y + 1 < N && map[x - 1][y + 1] != '1' && map[x][y + 1] != '1' && map[x + 1][y + 1] != '1') {
				for (int i = 2; i >= 0; i--) {
					map[x + 1 - i][y + 1] = map[x + 1 - i][y];
					map[x + 1 - i][y] = '0';
				}

				arr[1] = y + 1;
			}
		}

		else {
			if (y > 0 && y + 2 < N && map[x][y + 2] != '1') {
				for (int i = 0; i < 3; i++) {
					map[x][y + 2 - i] = map[x][y + 1 - i];
				}
				map[x][y - 1] = '0';

				arr[1] = y + 1;
			}
		}

		return arr;
	}

	private static int[] T(int x, int y, int status) {
		int[] arr = new int[3];
		arr[0] = x;
		arr[1] = y;
		arr[2] = status;

		if (status == VERTICAL) {
			if (y > 0 && y + 1 < N && x > 0 && x + 1 < N) {
				for (int i = 0; i < 3; i++) {
					if (map[x - 1 + i][y - 1] == '1' || map[x - 1 + i][y + 1] == '1') 
						return arr;
				}

				map[x - 1][y] = '0';
				map[x + 1][y] = '0';
				map[x][y - 1] = 'B';
				map[x][y + 1] = 'B';

				arr[2] = HORIZONTAL;
			}
		}

		else {
			if (x > 0 && x + 1 < N && y > 0 && y + 1 < N) {
				for (int i = 0; i < 3; i++) {
					if (map[x - 1][y - 1 + i] == '1' || map[x + 1][y - 1 + i] == '1')
						return arr;
				}

				map[x][y - 1] = '0';
				map[x][y + 1] = '0';
				map[x - 1][y] = 'B';
				map[x + 1][y] = 'B';
			}

			arr[2] = VERTICAL;
		}

		return arr;
	}

	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(startX, startY, 0, startStatus));
		visited[startX][startY][startStatus] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			int status = cur.status;

			if (x == endX && y == endY && status == endStatus)
				return cnt;

			for (int i = 0; i < 5; i++) {
				int[] arr = func(i, x, y, status);
				int nx = arr[0];
				int ny = arr[1];
				int nstatus = arr[2];

				if (visited[nx][ny][nstatus])
					continue;

				visited[nx][ny][nstatus] = true;
				queue.add(new Node(nx, ny, cnt + 1, nstatus));
			}
		}

		return 0;
	}

	private static int[] func(int input, int x, int y, int status) {
		int arr[] = new int[3];

		switch (input) {
		case 0:
			arr = U(x, y, status);
			break;
		case 1:
			arr = D(x, y, status);
			break;
		case 2:
			arr = L(x, y, status);
			break;
		case 3:
			arr = R(x, y, status);
			break;
		case 4:
			arr = T(x, y, status);
			break;
		}

		return arr;
	}
}

class Node {
	int x, y, cnt, status;

	public Node(int x, int y, int cnt, int status) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.status = status;
	}
}
