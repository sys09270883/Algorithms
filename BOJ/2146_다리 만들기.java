/*
[문제]
여러 섬으로 이루어진 나라가 있다. 이 나라의 대통령은, 섬들을 잇는 다리를 만들겠다는 공약으로 인기몰이를 해 당선될 수 있었다. 
하지만 막상 대통령에 취임하자, 다리를 놓는다는 것이 아깝다는 생각을 하게 되었다. 
그래서 그는, 생색내는 식으로 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 하였고, 그 또한 다리를 가장 짧게 하여 돈을 아끼려 하였다.

이 나라는 N×N크기의 이차원 평면상에 존재한다. 이 나라는 여러 섬으로 이루어져 있으며, 섬이란 동서남북으로 육지가 붙어있는 덩어리를 말한다. 
다음은 세 개의 섬으로 이루어진 나라의 지도이다.



위의 그림에서 색이 있는 부분이 육지이고, 색이 없는 부분이 바다이다. 이 바다에 가장 짧은 다리를 놓아 두 대륙을 연결하고자 한다. 
가장 짧은 다리란, 다리가 격자에서 차지하는 칸의 수가 가장 작은 다리를 말한다. 다음 그림에서 두 대륙을 연결하는 다리를 볼 수 있다.



물론 위의 방법 외에도 다리를 놓는 방법이 여러 가지 있으나, 위의 경우가 놓는 다리의 길이가 3으로 가장 짧다(물론 길이가 3인 다른 다리를 놓을 수 있는 방법도 몇 가지 있다).

지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오.

[입력]
첫 줄에는 지도의 크기 N(100이하의 자연수)가 주어진다. 그 다음 N줄에는 N개의 숫자가 빈칸을 사이에 두고 주어지며, 0은 바다, 1은 육지를 나타낸다. 
항상 두 개 이상의 섬이 있는 데이터만 입력으로 주어진다.

[출력]
첫째 줄에 가장 짧은 다리의 길이를 출력한다.

[풀이]
전체 맵을 입력받고, 섬마다 번호를 지정한다. (왼쪽 상단의 섬부터 오른쪽 하단까지 증가순)
같은 섬에서 같은 섬으로 갈 때에는 dist를 증가시키지 않고, 한 섬에서 다른 섬까지 도달할 때 까지 dist를 증가시킨다.
dist의 최솟값을 출력한다.

 + 매 지점에서 BFS를 하기 때문에 visited 배열의 초기화를 하기 때문에 메모리와 시간을 많이 잡아먹는 코드인 것 같다...
 + AC는 받았지만 더 좋은 방법으로 다시 풀어 봐야겠다.

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