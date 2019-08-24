/*
https://www.acmicpc.net/problem/2573
[����]
���� �³�ȭ�� ���Ͽ� �ϱ��� ������ ��� �ִ�. ������ �׸� 1�� ���� 2���� �迭�� ǥ���Ѵٰ� ����. 
������ �� �κк� ���� ������ �迭�� �� ĭ�� ���� ������ ����ȴ�. ���� �̿��� �ٴٿ� �ش�Ǵ� ĭ���� 0�� ����ȴ�. �׸� 1���� ��ĭ�� ��� 0���� ä���� �ִٰ� �����Ѵ�.



�׸� 1. ���� ������ 5�̰� ���� ������ 7�� 2���� �迭�� ����� ������ ���� ����

������ ���̴� �ٴ幰�� ���� �����ִ� �κп��� �� ���� �پ��� ������, 
�迭���� ������ �� �κп� �ش�Ǵ� ĭ�� �ִ� ���̴� �ϳ⸶�� �� ĭ�� �������� �� �������� �پ��ִ� 0�� ����� ĭ�� ������ŭ �پ���. 
��, �� ĭ�� ����� ���̴� 0���� �� �پ���� �ʴ´�. �ٴ幰�� ȣ��ó�� ���꿡 �ѷ��ο� ���� ���� �ִ�. ���� �׸� 1�� ������ �ϳ��Ŀ� �׸� 2�� ���� �����ȴ�.

�׸� 3�� �׸� 1�� ������ 2�� �Ŀ� ���� ����� �����ش�. 2���� �迭���� �������� �������� �پ��ִ� ĭ���� ���� ����Ǿ� �ִٰ� ���Ѵ�. 
���� �׸� 2�� ������ �� ���������, �׸� 3�� ������ �� ����� �и��Ǿ� �ִ�.



�� ����� ������ �־��� ��, �� ������ �� ��� �̻����� �и��Ǵ� ������ �ð�(��)�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
�׸� 1�� ���꿡 ���ؼ��� 2�� ���̴�. ���� ���� �� ���� ������ �� ��� �̻����� �и����� ������ ���α׷��� 0�� ����Ѵ�.

[�Է�]
ù �ٿ��� ������ �迭�� ���� ������ ���� ������ ��Ÿ���� �� ���� N�� M�� �� ���� ��ĭ�� ���̿� �ΰ� �־�����. 
N�� M�� 3 �̻� 300 �����̴�. �� ���� N���� �ٿ��� �� �ٸ��� �迭�� �� ���� ��Ÿ���� M���� ������ �� ���� �� ĭ�� ���̿� �ΰ� �־�����. 
�� ĭ�� ���� ���� 0 �̻� 10 �����̴�. �迭���� ������ �����ϴ� ĭ�� ����, ��, 1 �̻��� ������ ���� ĭ�� ������ 10,000 �� �����̴�. 
�迭�� ù ��° ��� ��, ������ ��� ������ �׻� 0���� ä������.

[���]
ù �ٿ� ������ �и��Ǵ� ������ �ð�(��)�� ����Ѵ�. ���� ������ �� ���� ������ �и����� ������ 0�� ����Ѵ�.

[Ǯ��]
BFS�� ������ ���̸鼭 ī�����Ѵ�.
cnt >= 2�� �׶����� �ݺ����� Ż���Ѵ�.

 + ��������� cnt == 0�� ���ļ� ���ѷ����� ���Ҵ�. Ȯ�� ������!!
 + �ݺ��� ������ visited�� false�� �ʱ�ȭ�ؾ� �Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		StringBuilder sb = new StringBuilder();
		int cnt, res = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] > 0) {
						visited[i][j] = true;
						BFS(i, j);
						cnt++;
					}
				}
			}
			
			if (cnt == 0) {
				sb.append(0);
				break;
			}
			
			else if(cnt >= 2) {
				sb.append(res);
				break;
			}
			
			res++;
			
			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
	
	private static void BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c));
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (!visited[nx][ny] && map[nx][ny] == 0) {
					map[x][y] = map[x][y] > 0 ? map[x][y] - 1 : 0;
				}
				
				if (!visited[nx][ny] && map[nx][ny] > 0) {
					visited[nx][ny] = true;
					queue.add(new Node(nx, ny));
				}
			}
		}
	}
}

class Node {
	int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}