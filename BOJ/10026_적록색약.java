/*
https://www.acmicpc.net/problem/10026
[문제]
적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.

크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 
그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 
또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

예를 들어, 그림이 아래와 같은 경우에

RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)

그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)

둘째 줄부터 N개 줄에는 그림이 주어진다.

[출력]
적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.

[풀이]
DFS또는 BFS로 완전 탐색하면서 구역을 탐색한다.
적록색맹의 경우는 R과 G를 같은 것으로 보기 때문에 G를 R로 바꾸어 탐색했다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static char[][] arr;
	static boolean[][] visited;
	static int N;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N];
		sb = new StringBuilder();
		
		int cntNormal = 0, cntBlind = 0;
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					BFS_NORMAL(i, j);
					cntNormal++;
				}
			}
		}
		
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					BFS_BLIND(i, j);
					cntBlind++;
				}
			}
		}
		
		sb.append(cntNormal).append(' ').append(cntBlind);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	private static void BFS_NORMAL(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if (!visited[nx][ny] && normalSight(arr[nx][ny], arr[x][y])) {
					visited[nx][ny] = true;
					queue.add(new Node(nx, ny));
				}
			}
		}
	}
	
	private static void BFS_BLIND(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if (!visited[nx][ny] && blindSight(arr[nx][ny], arr[x][y])) {
					visited[nx][ny] = true;
					queue.add(new Node(nx, ny));
				}
			}
		}
	}
	
	private static boolean normalSight(char c1, char c2) {
		return c1 == c2;
	}
	
	private static boolean blindSight(char c1, char c2) {
		char[] tmp = convert(c1, c2);
		return tmp[0] == tmp[1];
	}
	
	private static char[] convert(char c1, char c2) {
		char[] ret = new char[2];
		ret[0] = (c1 == 'G' ? 'R' : c1);
		ret[1] = (c2 == 'G' ? 'R' : c2);
		
		return ret;
	}
}

class Node {
	int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}