/*
https://www.acmicpc.net/problem/7562
[문제]
체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?



[입력]
입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.

각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.

[출력]
각 테스트 케이스마다 나이트가 몇 번만에 이동할 수 있는지 출력한다.

[풀이]
기본적인 BFS문제...


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