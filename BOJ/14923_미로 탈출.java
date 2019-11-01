/*
https://www.acmicpc.net/problem/14923
[����]
ȫ���̴� ����� �������� �տ� �Ӿ� N x M �̷� (Hx, Hy) ��ġ�� ��������. �������� ȫ���̴� �����簡 ���� �̷��� Ż�� ��ġ(Ex, Ey)�� �˰� �ִ�. 
������ �̷ο��� ������ �����簡 ��ġ�� ���� �־� ȫ���̰� Ż���ϱ� ��ư� �ϰ� �ִ�.

ȫ���̴� �������� �����ǿ��� ��ģ �����̰� �־�, ���� ��� ���� �� �ִ�. �׷�����, ��Ÿ���Ե� ������ �����̴� �� �� ���� ����� �� �ִ�.

�̶�, ȫ���̸� ���� �̷ο��� Ż���� �� �ִ��� �˾ƺ���, �� �� �ִٸ� ���� ���� ����� �Ÿ� D�� ������ �˾ƺ���.

������ ĭ���� �̵��ϴµ� �Ȱ��� �ð��� ���, ���� �μ��� �� �ð��� �ɸ��� �ʴ´�.

[�Է�]
N M
Hx Hy
Ex Ey
N X M ���
2 �� N �� 1000, 2 �� M �� 1000
1 �� Hx, Hy, Ex, Ey �� 1000
(Hx, Hy)�� (Ex, Ey)
����� 0�� 1�θ� �̷���� �ְ�, 0�� �� ĭ, 1�� ���̴�.

[���]
D (Ż�� �� �� ���ٸ�, -1�� ����Ѵ�.)

[Ǯ��]
���� �μ������� �� �μ��������� Ȯ���ϱ� ���� 3���� �湮 �迭�� �����Ѵ�.
BFS Ž���� �����ϸ鼭 �������� ������ �����̰� ���� ��� �μ���, �ش� ������ �����ϴ� ��带 ť�� �ٽ� �ִ´�.
������ �����̰� ���µ� ���� ��� �ƹ��͵� �������� �ʰ� �� ���� ��쿡�� �Ϲ����� BFS�� �����Ѵ�.
�������� �����ϸ� ī��Ʈ��, �׷��� ������ -1�� �����ؼ� ���Ŀ� �°� ����Ѵ�.  

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