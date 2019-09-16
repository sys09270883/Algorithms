/*
https://www.acmicpc.net/problem/16954
[문제]
욱제는 학교 숙제로 크기가 8×8인 체스판에서 탈출하는 게임을 만들었다. 체스판의 모든 칸은 빈 칸 또는 벽 중 하나이다. 
욱제의 캐릭터는 가장 왼쪽 아랫 칸에 있고, 이 캐릭터는 가장 오른쪽 윗 칸으로 이동해야 한다.

이 게임의 특징은 벽이 움직인다는 점이다. 1초마다 모든 벽이 아래에 있는 행으로 한 칸씩 내려가고, 가장 아래에 있어서 아래에 행이 없다면 벽이 사라지게 된다. 
욱제의 캐릭터는 1초에 인접한 한 칸 또는 대각선 방향으로 인접한 한 칸으로 이동하거나, 현재 위치에 서 있을 수 있다. 이동할 때는 빈 칸으로만 이동할 수 있다.

1초 동안 욱제의 캐릭터가 먼저 이동하고, 그 다음 벽이 이동한다. 벽이 캐릭터가 있는 칸으로 이동하면 더 이상 캐릭터는 이동할 수 없다.

욱제의 캐릭터가 가장 오른쪽 윗 칸으로 이동할 수 있는지 없는지 구해보자.

[입력]
8개 줄에 걸쳐서 체스판의 상태가 주어진다. '.'은 빈 칸, '#'는 벽이다. 가장 왼쪽 아랫칸은 항상 벽이 아니다.

[출력]
욱제의 캐릭터가 가장 오른쪽 윗 칸에 도착할 수 있으면 1, 없으면 0을 출력한다.

[풀이]
처음에 전역변수 체스판 하나로 처리를 하려다 보니 큐에 들어가면서 그 단계의 체스판이 나와야 하는데 뒤섞여버렸다.
그래서 애초에 시간 당 벽의 위치를 저장하는 3차원 wall배열을 만들었다.
처음 입력받을 때 벽의 위치를 wallList에 담고, wallList에 있는 벽들의 x좌표와 시간(t)를 증가시키면서 3차원 배열에 저장한다.

끝나는 조건은 해당 열에 벽이 없거나, 시간이 8초가 지났거나, x == 0일 때이다.
9방향으로 BFS를 하는데 다음 칸이 벽이 아니여도 1초 후에 벽이면 이동을 하면 안되므로, t일 때의 좌표와 t + 1일때의 좌표가 벽이 아닐경우에만 큐에 넣어준다.
끝나는 조건에 해당하지 않을 경우 0을 리턴한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static char[][] chess;
	static char[][][] wall;
	static ArrayList<Node> wallList;
	static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int[] dy = {1, 0, -1, 1, 0, -1, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		chess = new char[8][8];
		wall = new char[8][8][8];
		wallList = new ArrayList<Node>();
		String input = null;
		
		for (int i = 0; i < 8; i++) {
			input = br.readLine();
			for (int j = 0; j < 8; j++) {
				chess[i][j] = input.charAt(j);
				
				if (chess[i][j] == '#') {
					wall[0][i][j] = '#';
					wallList.add(new Node(i, j, 0));
				}
			}
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					if (wall[i][j][k] == '#')
						continue;
					
					wall[i][j][k] = '.';
				}
			}
		}
		
		makeWall();
		
		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}
	
	private static void makeWall() {
		for (int i = 0; i < wallList.size(); i++) {
			int x = wallList.get(i).x;
			int y = wallList.get(i).y;
			int t = 1;
			
			while (true) {
				int nx = x + 1;
				int ny = y;
				
				if (nx >= 8)
					break;
				
				wall[t++][nx][ny] = '#';
				x = nx;
				y = ny;
			}
		}
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(7, 0, 0));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			
			if (end(x, cnt))
				return 1;
			
			for (int i = 0; i < 9; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int ncnt = cnt + 1;
				
				if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
					continue;
				
				if (wall[ncnt][nx][ny] == '#' || wall[cnt][nx][ny] == '#')
					continue;
				
				queue.add(new Node(nx, ny, ncnt));
			}
		}
		
		return 0;
	}
	
	private static boolean end(int x, int cnt) {
		if (x == 0 || cnt >= 8)
			return true;
		
		for (int i = x - 1; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (wall[cnt][i][j] == '#')
					return false;
			}
		}

		return true;
	}
}

class Node {
	int x, y, cnt;

	public Node(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}