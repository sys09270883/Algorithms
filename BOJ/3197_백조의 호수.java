/*
https://www.acmicpc.net/problem/3197
[����]
�� ������ ������ ȣ������ ��� �־���. �׷����� �� ������ ȣ���� ���� �ִ� �������� ������ ���Ѵ�.

ȣ���� ���η� R, ���η� C��ŭ�� ���簢�� ����̴�. � ĭ�� �������� �����ִ�.

ȣ���� ���ʷ� ��µ�, ���� �� ������ ������ ��� ���� ������ ��´�. �� ���� ������ �����Ϸ��� ���γ� ���η� ��� �ִ� �͸� (�밢���� ������� �ʴ´�) �����Ѵ�.

�Ʒ����� �� ���� ���� �ִ�.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX.......... 
....XXXXXXXXX.XXX .....XXXX..X..... ......X.......... 
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X..... 
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX.... 
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX.... 
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............ 
..XXXXX...XXX.... ....XX.....X..... ................. 
....XXXXX.XXX.... .....XX....X..... ................. 
in the beginning   after first day   after second day 
������ ���� �� �������� ���γ� ���ηθ�(�밢���� �����Ѵ�) ������ �� �ִ�.

��ĥ�� ������ �������� ���� �� �ִ� �� ����ϴ� ���α׷��� �ۼ��Ѵ�.

[�Է�]
�Է��� ù° �ٿ��� R�� C�� �־�����. ��, 1 �� R, C �� 1500.

�� R�� ���� C��ŭ�� ���ڿ��� �־�����.

'.'�� �� ����, 'X'�� ���� ����, 'L'�� ������ �ִ� �������� ��Ÿ����.

[���]
ù° �ٿ� �������� �־��� �ɸ��� ���� ����Ѵ�.

[Ǯ��]
������ ���� ������ BFS�� �ϴ� ������� �����ߴµ� �־��� ��� 1500 * 1500 * 1499������ TLE����.
N�� ° ������ ������ ���Ѵٸ�, N - 1�� ° ���� ������ ���� �� �����Ƿ� �̺�Ž������ �����ϸ� 1500 * 1500 * log(1499)�� �ð����⵵�� ���� �� �ִ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	final static int MAX = 1501;
	static char[][] map = new char[MAX][MAX];
	static int[][] meltDay = new int[MAX][MAX];
	static boolean[][] visited = new boolean[MAX][MAX];
	static int R, C, maxDay = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Node start, end;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String input = null;
		boolean flag = false;
		
		for (int i = 1; i <= R; i++) {
			input = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = input.charAt(j - 1);
				
				if (map[i][j] == 'L') {
					if (!flag) {
						start = new Node(i, j);
						flag = true;
					}
					
					else {
						end = new Node(i, j);
					}
				}
			}
		}
		
		melt();
		
		int s = 0, e = maxDay;
		
		while (s <= e) {
			int m = (s + e) / 2;
			
			if (move(m))
				e = m - 1;
			
			else
				s = m + 1;
		}
		
		bw.write(String.valueOf(s));
		bw.close();
		br.close();
	}
	
	private static void melt() {
		for (int[] row : meltDay) {
			Arrays.fill(row, -1);
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == '.' || map[i][j] == 'L') {
					queue.add(new Node(i, j));
					meltDay[i][j] = 0;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > R || ny > C)
					continue;
				
				if (meltDay[nx][ny] > -1)
					continue;
				
				queue.add(new Node(nx, ny));
				meltDay[nx][ny] = meltDay[x][y] + 1;
				maxDay = Math.max(maxDay, meltDay[nx][ny]);
			}
		}
	}
	
	private static boolean move(int day) {
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			if (x == end.x && y == end.y)
				return true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 1 || ny < 1 || nx > R || ny > C)
					continue;
				
				if (visited[nx][ny] || meltDay[nx][ny] > day)
					continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
			}
		}
		
		return false;
	}
	
}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}