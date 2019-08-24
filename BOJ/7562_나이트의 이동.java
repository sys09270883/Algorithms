/*
https://www.acmicpc.net/problem/7562
[����]
ü���� ���� �� ����Ʈ�� ������ �ִ�. ����Ʈ�� �� ���� �̵��� �� �ִ� ĭ�� �Ʒ� �׸��� �����ִ�.
����Ʈ�� �̵��Ϸ��� �ϴ� ĭ�� �־�����. ����Ʈ�� �� �� �����̸� �� ĭ���� �̵��� �� ������?



[�Է�]
�Է��� ù° �ٿ��� �׽�Ʈ ���̽��� ������ �־�����.

�� �׽�Ʈ ���̽��� �� �ٷ� �̷���� �ִ�. ù° �ٿ��� ü������ �� ���� ���� l(4 �� l �� 300)�� �־�����.
ü������ ũ��� l �� l�̴�. ü������ �� ĭ�� �� ���� �� {0, ..., l-1} �� {0, ..., l-1}�� ��Ÿ�� �� �ִ�.
��° �ٰ� ��° �ٿ��� ����Ʈ�� ���� �ִ� ĭ, ����Ʈ�� �̵��Ϸ��� �ϴ� ĭ�� �־�����.

[���]
�� �׽�Ʈ ���̽����� ����Ʈ�� �� ������ �̵��� �� �ִ��� ����Ѵ�.

[Ǯ��]
�⺻���� BFS����...


*/
import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
	static int[][] chess;
	static boolean[][] visited;
	static int l, curX, curY, tarX, tarY;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T;
		StringTokenizer st = null;
		chess = new int[301][301];
		visited = new boolean[301][301];
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			l = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			curX = Integer.parseInt(st.nextToken());
			curY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			tarX = Integer.parseInt(st.nextToken());
			tarY = Integer.parseInt(st.nextToken());
			
			BFS(curX, curY);
			
			for (int[] row : chess) {
				Arrays.fill(row, 0);
			}
			
			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c, 0));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			int cnt = tmp.cnt;
			
			if (x == tarX && y == tarY) {
				sb.append(cnt).append('\n');
				return;
			}
			
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= l || ny >= l)
					continue;
				
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new Node(nx, ny, cnt + 1));
				}
			}
		}
	}
}

class Node {
	int x, y, cnt;
	
	Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}