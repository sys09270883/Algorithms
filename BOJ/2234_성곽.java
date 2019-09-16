/*
https://www.acmicpc.net/problem/2234
[문제]

대략 위의 그림과 같이 생긴 성곽이 있다. 굵은 선은 벽을 나타내고, 점선은 벽이 없어서 지나다닐 수 있는 통로를 나타낸다. 
이러한 형태의 성의 지도를 입력받아서 다음을 계산하는 프로그램을 작성하시오.

  1. 이 성에 있는 방의 개수
  2. 가장 넓은 방의 넓이
  3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
위의 예에서는 방은 5개고, 가장 큰 방은 9개의 칸으로 이루어져 있으며, 위의 그림에서 화살표가 가리키는 벽을 제거하면 16인 크기의 방을 얻을 수 있다.

성은 m×n(1 ≤ m, n ≤ 50)개의 정사각형 칸으로 이루어진다. 성에는 최소 두 개의 방이 있어서, 항상 하나의 벽을 제거하여 두 방을 합치는 경우가 있다.

[입력]
첫째 줄에 두 정수 n, m이 주어진다. 다음 m개의 줄에는 n개의 정수로 벽에 대한 정보가 주어진다. 
벽에 대한 정보는 한 정수로 주어지는데, 서쪽에 벽이 있을 때는 1을, 북쪽에 벽이 있을 때는 2를, 동쪽에 벽이 있을 때는 4를, 남쪽에 벽이 있을 때는 8을 더한 값이 주어진다.
 참고로 이진수의 각 비트를 생각하면 쉽다. 따라서 이 값은 0부터 15까지의 범위 안에 있다.

[출력]
첫째 줄에 1의 답을, 둘째 줄에 2의 답을, 셋째 줄에 3의 답을 출력한다.

[풀이]
BFS + 비트마스킹 문제였다.
1번과 2번의 경우는 한 번의 BFS로 구한다.
큐에 y, x를 넣고 서북동남 순으로 갈 수 있는지 여부를 확인한다.
갈 수 있는지에 대한 여부는 현재 위치와 다음 갈 방향 (pow(2, i))를 & 연산으로 확인한다.

3번의 경우는 맵을 순회하면서 벽이 있어 가지 못할 경우 벽을 부수고 BFS를 진행한다.
벽을 부수는 방법은 map[i][j]에서 해당 방향을 의미하는 값을 빼주면 된다. (W = 1, N = 2, E = 4, S = 8)
뺀 후 BFS를 실행하고 다시 값을 되돌려 놓는다.

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
