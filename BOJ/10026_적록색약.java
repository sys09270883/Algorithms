/*
https://www.acmicpc.net/problem/10026
[����]
���ϻ����� �������� �ʷϻ��� ���̸� ���� ������ ���Ѵ�. ����, ���ϻ����� ����� ���� �׸��� �ƴ� ����� ���� �׸����� �� �ٸ� �� �ִ�.

ũ�Ⱑ N��N�� �׸����� �� ĭ�� R(����), G(�ʷ�), B(�Ķ�) �� �ϳ��� ��ĥ�� �׸��� �ִ�. 
�׸��� �� ���� �������� �������� �ִµ�, ������ ���� ������ �̷���� �ִ�. 
��, ���� ������ �����¿�� ������ �ִ� ��쿡 �� ���ڴ� ���� ������ ���Ѵ�. (������ ���̸� ���� ������ ���ϴ� ��쵵 ���� �����̶� �Ѵ�)

���� ���, �׸��� �Ʒ��� ���� ��쿡

RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
���ϻ����� �ƴ� ����� ���� �� ������ ���� �� 4���̴�. (���� 2, �Ķ� 1, �ʷ� 1) ������, ���ϻ����� ����� ������ 3�� �� �� �ִ�. (����-�ʷ� 2, �Ķ� 1)

�׸��� �Է����� �־����� ��, ���ϻ����� ����� ���� ���� �ƴ� ����� ���� �� ������ ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� �־�����. (1 �� N �� 100)

��° �ٺ��� N�� �ٿ��� �׸��� �־�����.

[���]
���ϻ����� �ƴ� ����� ���� ���� ������ ������ ���ϻ����� ����� ���� ���� ������ ���� �������� ������ ����Ѵ�.

[Ǯ��]
DFS�Ǵ� BFS�� ���� Ž���ϸ鼭 ������ Ž���Ѵ�.
���ϻ����� ���� R�� G�� ���� ������ ���� ������ G�� R�� �ٲپ� Ž���ߴ�.

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