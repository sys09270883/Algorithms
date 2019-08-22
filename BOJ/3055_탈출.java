/*
https://www.acmicpc.net/problem/3055
[����]
����� ������ ���� �̹����� ���� ���� ������ �տ� �־���, �� �ɷ��� �����غ��� ���� ��ó�� Ƽ������ ȫ���� ����Ű���� �Ѵ�. 
�� ������ ����ġ�� �� ���� ��� �ִ�. ����ġ�� ���� ģ�� ģ���� ����� ���� ������ ���� ������ ȫ���� ���Ϸ��� �Ѵ�.

Ƽ������ ������ R�� C���� �̷���� �ִ�. ����ִ� ���� '.'�� ǥ�õǾ� �ְ�, ���� ���ִ� ������ '*', ���� 'X'�� ǥ�õǾ� �ִ�. 
����� ���� 'D'��, ����ġ�� ��ġ�� 'S'�� ��Ÿ������ �ִ�.

�� �и��� ����ġ�� ���� �ִ� ĭ�� ������ �� ĭ �� �ϳ��� �̵��� �� �ִ�. (��, �Ʒ�, ������, ����) ���� �� �и��� ����ִ� ĭ���� Ȯ���Ѵ�. 
���� �ִ� ĭ�� �������ִ� ����ִ� ĭ(��� �� ���� ����)�� ���� ���� �ȴ�. ���� ����ġ�� ���� ����� �� ����. 
��, ����ġ�� ���� ���ִ� �������� �̵��� �� ����, ���� ����� �ұ��� �̵��� �� ����.

Ƽ������ ������ �־����� ��, ����ġ�� �����ϰ� ����� ���� �̵��ϱ� ���� �ʿ��� �ּ� �ð��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

����ġ�� ���� �� ������ ĭ���� �̵��� �� ����. ��, ���� �ð��� ���� �� ������ ĭ���� ����ġ�� �̵��� �� ����. �̵��� �� ������ ����ġ�� ���� ������ �����̴�. 

[�Է�]
ù° �ٿ� 50���� �۰ų� ���� �ڿ��� R�� C�� �־�����.

���� R�� �ٿ��� Ƽ������ ������ �־�����, �������� ������ ���ڸ� �־�����. 'D'�� 'S'�� �ϳ����� �־�����.

[���]
ù° �ٿ� ����ġ�� ����� ���� �̵��� �� �ִ� ���� ���� �ð��� ����Ѵ�. ����, �����ϰ� ����� ���� �̵��� �� ���ٸ�, "KAKTUS"�� ����Ѵ�.

[Ǯ��]
������ BFS�� �ϸ鼭 ��¥�� ����Ѵ�.
����ġ���� BFS�� �ϸ鼭 ��¥�� ���ϸ鼭 �̵��Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static Queue<Node> waterQueue = new LinkedList<Node>();
	static int[][] waterMap, visited;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int R, C, start_x, start_y, end_x, end_y;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new int[R][C];
		waterMap = new int[R][C];
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == '*')
					waterQueue.add(new Node(i, j));
				
				else if (map[i][j] == 'S') {
					start_x = i;
					start_y = j;
				}
				
				else if (map[i][j] == 'D') {
					end_x = i;
					end_y = j;
				}
			}
		}
		
		waterBFS();
		
		BFS();
		
		bw.write(visited[end_x][end_y] != 0 ? String.valueOf(visited[end_x][end_y]) : "KAKTUS");
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start_x, start_y));
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				
				if(visited[nx][ny] == 0 && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {  
					if(waterMap[nx][ny] == 0) {
						visited[nx][ny] = visited[tmp.x][tmp.y] + 1;
						queue.add(new Node(nx, ny));
					}
					
					else {
						if(waterMap[nx][ny] > visited[tmp.x][tmp.y] + 1) {
							visited[nx][ny] = visited[tmp.x][tmp.y] + 1;
							queue.add(new Node(nx, ny));
						}
					}
				}
			}
		}
	}
	
	private static void waterBFS() {
		while(!waterQueue.isEmpty()) {
			Node tmp = waterQueue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				
				if (waterMap[nx][ny] == 0 && map[nx][ny] == '.') {
					waterMap[nx][ny] = waterMap[tmp.x][tmp.y] + 1;
					waterQueue.add(new Node(nx, ny));
				}
			}
		}
	}
}

class Node {
	int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
