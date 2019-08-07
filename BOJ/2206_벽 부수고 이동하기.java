/*
https://www.acmicpc.net/problem/2206
[문제]
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

[입력]
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

[출력]
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

[풀이]
일반적인 BFS에서 벽을 부쉈는지 안부쉈는지를 체크하기 위해 visited[N][M][2] 변수를 둔다.
  1. 벽을 부순적이 있고 map[i][j] == 0일 경우, 방문체크하고 큐에 넣는다.
  2. 벽을 부순적이 없고 map[i][j] == 0일 경우, 방문체크하고 큐에 넣는다.
  3. 벽을 부순적이 없고 map[i][j] == 1일 경우, 방문체크하고 벽을 부순것도 체크를 하고 큐에 넣는다.

출력변수 ans는 int 최댓값으로 초기화하고, (N-1, M-1)에 도달했을 때 cnt와 비교하여 갱신한다.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N, M, ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		BFS();
		
		bw.write(ans == Integer.MAX_VALUE ? "-1" : String.valueOf(ans));
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			
			if(tmp.row == N-1 && tmp.col == M-1) {
				ans = Math.min(ans, tmp.cnt);
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.row + dx[i];
				int ny = tmp.col + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(tmp.breakWall == 1) {
					if(map[nx][ny] == 0 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall));
					}
				}
				
				else {
					if(map[nx][ny] == 0 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall));
					}
					
					else if(map[nx][ny] == 1 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall + 1));
					}
				}
			}
		}
	}
}

class Node {
	int row, col, cnt, breakWall;
	
	Node(int row, int col, int cnt, int breakWall) {
		this.row = row;
		this.col = col;
		this.cnt = cnt;
		this.breakWall = breakWall;
	}
}
