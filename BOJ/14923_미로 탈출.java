/*
https://www.acmicpc.net/problem/14923
[문제]
홍익이는 사악한 마법사의 꾐에 속아 N x M 미로 (Hx, Hy) 위치에 떨어졌다. 다행히도 홍익이는 마법사가 만든 미로의 탈출 위치(Ex, Ey)를 알고 있다. 
하지만 미로에는 곳곳에 마법사가 설치한 벽이 있어 홍익이가 탈출하기 어렵게 하고 있다.

홍익이는 마법사의 연구실에서 훔친 지팡이가 있어, 벽을 길로 만들 수 있다. 그렇지만, 안타깝게도 마법의 지팡이는 단 한 번만 사용할 수 있다.

이때, 홍익이를 도와 미로에서 탈출할 수 있는지 알아보고, 할 수 있다면 가장 빠른 경로의 거리 D는 얼마인지 알아보자.

인접한 칸으로 이동하는데 똑같은 시간이 들고, 벽을 부수는 데 시간이 걸리지 않는다.

[입력]
N M
Hx Hy
Ex Ey
N X M 행렬
2 ≤ N ≤ 1000, 2 ≤ M ≤ 1000
1 ≤ Hx, Hy, Ex, Ey ≤ 1000
(Hx, Hy)≠ (Ex, Ey)
행렬은 0과 1로만 이루어져 있고, 0이 빈 칸, 1이 벽이다.

[출력]
D (탈출 할 수 없다면, -1을 출력한다.)

[풀이]
벽을 부수었는지 안 부수었는지를 확인하기 위해 3차원 방문 배열에 저장한다.
BFS 탐색을 진행하면서 벽이지만 마법의 지팡이가 있을 경우 부수고, 해당 정보를 저장하는 노드를 큐에 다시 넣는다.
마법의 지팡이가 없는데 벽일 경우 아무것도 진행하지 않고 그 외의 경우에는 일반적인 BFS로 진행한다.
목적지에 도달하면 카운트를, 그렇지 않으면 -1을 리턴해서 형식에 맞게 출력한다.  

*/
import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;
	static boolean[][][] visited;
	static int N, M, startX, startY, endX, endY; 
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String... args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken()) - 1;
		endY = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(startX, startY, 0, 0));
		visited[startX][startY][0] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			int cnt = tmp.cnt;
			int breakWall = tmp.breakWall;
			
			if (x == endX && y == endY)
				return cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nBreakWall = breakWall;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (visited[nx][ny][nBreakWall])
					continue;
				
				if (map[nx][ny] == 1) {
					if (nBreakWall == 1)
						continue;
					
					else {
						nBreakWall = 1;
						visited[nx][ny][nBreakWall] = true;
						queue.add(new Node(nx, ny, cnt + 1, nBreakWall));
					}
				}
				
				else {
					if (map[nx][ny] == 1) {
						nBreakWall = 1;
						visited[nx][ny][nBreakWall] = true;
						queue.add(new Node(nx, ny, cnt + 1, nBreakWall));
					}
					
					else {
						visited[nx][ny][nBreakWall] = true;
						queue.add(new Node(nx, ny, cnt + 1, nBreakWall));
					}
				}
			}
		}
		
		return -1;
	}
	
}

class Node {
	int x, y, cnt, breakWall;

	public Node(int x, int y, int cnt, int breakWall) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.breakWall = breakWall;
	}
	
}