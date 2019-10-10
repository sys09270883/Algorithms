/*
https://www.acmicpc.net/problem/3197
[문제]
두 마리의 백조가 호수에서 살고 있었다. 그렇지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.

호수는 가로로 R, 세로로 C만큼의 직사각형 모양이다. 어떤 칸은 얼음으로 덮여있다.

호수는 차례로 녹는데, 매일 물 공간과 접촉한 모든 빙판 공간은 녹는다. 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 (대각선은 고려하지 않는다) 생각한다.

아래에는 세 가지 예가 있다.

...XXXXXX..XX.XXX ....XXXX.......XX .....XX.......... 
....XXXXXXXXX.XXX .....XXXX..X..... ......X.......... 
...XXXXXXXXXXXX.. ....XXX..XXXX.... .....X.....X..... 
..XXXXX..XXXXXX.. ...XXX....XXXX... ....X......XX.... 
.XXXXXX..XXXXXX.. ..XXXX....XXXX... ...XX......XX.... 
XXXXXXX...XXXX... ..XXXX.....XX.... ....X............ 
..XXXXX...XXX.... ....XX.....X..... ................. 
....XXXXX.XXX.... .....XX....X..... ................. 
in the beginning   after first day   after second day 
백조는 오직 물 공간에서 세로나 가로로만(대각선은 제외한다) 움직일 수 있다.

며칠이 지나야 백조들이 만날 수 있는 지 계산하는 프로그램을 작성한다.

[입력]
입력의 첫째 줄에는 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1500.

각 R줄 동안 C만큼의 문자열이 주어진다.

'.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간으로 나타낸다.

[출력]
첫째 줄에 문제에서 주어진 걸리는 날을 출력한다.

[풀이]
얼음이 녹을 때마다 BFS를 하는 방식으로 접근했는데 최악의 경우 1500 * 1500 * 1499번으로 TLE였다.
N일 째 백조를 만나지 못한다면, N - 1일 째 역시 백조를 만날 수 없으므로 이분탐색으로 접근하면 1500 * 1500 * log(1499)로 시간복잡도를 낮출 수 있다.

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