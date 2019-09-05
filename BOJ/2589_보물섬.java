/*
https://www.acmicpc.net/problem/2589
[����]
������ ������ �߰��� ��ũ ������ ������ ã�Ƴ�����. ������ ������ �Ʒ� �׸��� ���� ���簢�� ����̸� ���� ĭ���� �������� �ִ�. 
�� ĭ�� ����(L)�� �ٴ�(W)�� ǥ�õǾ� �ִ�. �� �������� �̵��� �����¿�� �̿��� �����θ� �����ϸ�, �� ĭ �̵��ϴµ� �� �ð��� �ɸ���. 
������ ���� ���� �ִ� �Ÿ��� �̵��ϴµ� �־� ���� �� �ð��� �ɸ��� ���� �� ���� ������ �����ִ�. 
������ ��Ÿ���� �� �� ���̸� �ִ� �Ÿ��� �̵��Ϸ��� ���� ���� �� �� �̻� �������ų�, �ָ� ���ư����� �� �ȴ�.



���� ��� ���� ���� ������ �־����ٸ� ������ �Ʒ� ǥ�õ� �� ���� ���� �ְ� �ǰ�, �� �� ������ �ִ� �Ÿ��� �̵��ϴ� �ð��� 8�ð��� �ȴ�.



���� ������ �־��� ��, ������ ���� �ִ� �� �� ���� �ִ� �Ÿ��� �̵��ϴ� �ð��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ��� ���� ������ ������ ũ��� ������ ũ�Ⱑ ��ĭ�� ���̿� �ΰ� �־�����. 
�̾� L�� W�� ǥ�õ� ���� ������ �Ʒ��� ���� ���� �־�����, �� ���� ���̿��� �� ĭ�� ����. ���� ������ ����, ������ ũ��� ���� 50�����̴�.

[���]
ù° �ٿ� ������ ���� �ִ� �� �� ���̸� �ִ� �Ÿ��� �̵��ϴ� �ð��� ����Ѵ�.

[Ǯ��]
L�� �������� BFS�� �ؼ� ���� �ָ� �� �� �ִ� �Ÿ��� ��ȯ�Ѵ�.
��ȯ�� ������ �ּҰ��� ����Ѵ�.


*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		String str = null;
		int res = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					res = Math.max(res, BFS(i, j));
					
					for (boolean[] row : visited) {
						Arrays.fill(row, false);
					}
				}
			}
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}

	private static int BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c, 0));
		visited[r][c] = true;
		int endCnt = -1;
		
		while (!queue.isEmpty()) { 
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			endCnt = cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (visited[nx][ny] || map[nx][ny] == 'W')
					continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny, cnt + 1));
			}
		}
		
		return endCnt;
	}
}

class Node {
	int x, y, cnt;
	
	public Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}