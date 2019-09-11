/*
https://www.acmicpc.net/problem/1938
[문제]
가로와 세로의 길이가 같은 평지에서 벌목을 한다. 그 지형은 0과 1로 나타나 있다. 1은 아직 잘려지지 않은 나무를 나타내고 0은 아무 것도 없음을 나타낸다. 다음 지형을 보자.

B 0 0 1 1
B 0 0 0 0
B 0 0 0 0
1 1 0 0 0
E E E 0 0
위의 지형에서 길이 3인 통나무 BBB를 밀거나 회전시켜 EEE의 위치로 옮기는 작업을 하는 문제를 생각해 보자. BBB와 EEE의 위치는 임의로 주어진다. 
단 문제에서 통나무의 길이는 항상 3이며 B의 개수와 E의 개수는 같다. 통나무를 움직이는 방법은 아래와 같이 상하좌우(Up, Down, Left, Right)와 회전(Turn)이 있다.

코드	의미
U	통나무를 위로 한 칸 옮긴다.
D	통나무를 아래로 한 칸 옮긴다.
L	통나무를 왼쪽으로 한 칸 옮긴다.
R	통나무를 오른쪽으로 한 칸 옮긴다.
T	중심점을 중심으로 90도 회전시킨다.
예를 들면, 다음과 같다. (초기상태로부터의 이동)

초기상태	상(U)	하(D)	좌(L)	우(R)	회전(T)

이와 같은 방식으로 이동시킬 때에 그 움직일 위치에 다른 나무, 즉 1이 없어야만 움직일 수 있다. 그리고 움직임은 위의 그림과 같이 한 번에 한 칸씩만 움직인다. 
단 움직이는 통나무는 어떤 경우이든지 중간단계에서 한 행이나 한 열에만 놓일 수 있다. 예를 들면 아래 그림에서 a와 같은 단계는 불가능하다. 
그리고 회전의 경우에서는 반드시 중심점을 중심으로 90도 회전을 해야 한다. (항상 통나무의 길이가 3이므로 중심점이 있음)

그리고 이런 회전(Turn)이 가능하기 위해서는 그 통나무를 둘러싸고 있는 3*3 정사각형의 구역에 단 한 그루의 나무도 없어야만 한다. 
즉, 아래그림 b, d와 같이 ?로 표시된 지역에 다른 나무, 즉 1이 없어야만 회전시킬 수 있다. 따라서 c와 같은 경우에, 통나무는 왼쪽 아직 벌채되지 않은 나무 때문에 회전시킬 수 없다.

a	b	c	d

문제는 통나무를 5개의 기본동작(U, D, L, R, T)만을 사용하여 처음위치(BBB)에서 최종위치(EEE)로 옮기는 프로그램을 작성하는 것이다. 
단, 최소 횟수의 단위 동작을 사용해야 한다.

[입력]
첫째 줄에 주어진 평지의 한 변의 길이 N이 주어진다. (4<=N<=50) 주어진다. 이어서 그 지형의 정보가 0, 1, B, E로 이루어진 문자열로 주어진다. 
한 줄에 입력되는 문자열의 길이는 N이며 입력 문자 사이에는 빈칸이 없다. 통나무와 최종 위치의 개수는 1개이다.

[출력]
첫째 줄에 최소 동작 횟수를 출력한다. 이동이 불가능하면 0만을 출력한다.

[풀이]
통나무의 중점의 x, y좌표와 상태(수직, 수평)을 기준으로 BFS한다.
방문표시 또한 x, y와 상태로 표시한다. ex) (2, 2, 0)과 (2, 2, 1)은 중점은 같지만 상태가 다르기 때문에 다르다.
시작 좌표의 x, y, status가 종료 좌표의 x, y, status와 일치할 때 까지 U, D, L, R, T 함수로 이동시킨다.

 + U, D, L, R, T 함수가 맵 밖을 나가지 않도록 유의해야 한다.

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
