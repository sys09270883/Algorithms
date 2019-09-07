/*
https://www.acmicpc.net/problem/1261
[����]
�˰��� ����� ��� �̷ο� ������. �̷δ� N*M ũ���̸�, �� 1*1ũ���� ������ �̷���� �ִ�. 
�̷δ� �� �� �Ǵ� ������ �̷���� �ְ�, �� ���� �����Ӱ� �ٴ� �� ������, ���� �μ��� ������ �̵��� �� ����.

�˰��� ����� ������������, �׻� ��� ���� �濡 �־�� �Ѵ�. ��, ���� ���� �ٸ� �濡 ���� ���� ����. 
� �濡�� �̵��� �� �ִ� ���� �����¿�� ������ �� ���̴�. 
��, ���� ����� (x, y)�� ���� ��, �̵��� �� �ִ� ���� (x+1, y), (x, y+1), (x-1, y), (x, y-1) �̴�. 
��, �̷��� ������ �̵� �� ���� ����.

���� ��ҿ��� �̵��� �� ������, �˰����� ���� AOJ�� �̿��� ���� �μ��� ���� �� �ִ�. ���� �μ���, �� ��� ������ ������ ���Ѵ�.

���� �� ������ �˰��̿� �ִٸ�, ������� �ñ��� ���� sudo�� �̿��� ���� �� ���� �� ���ֹ��� �� ������, 
��Ÿ���Ե� �� ������ Baekjoon Online Judge�� ���ϵǾ� �ֱ� ������, sudo�� ����� �� ����.

���� (1, 1)�� �ִ� �˰��� ����� (N, M)���� �̵��Ϸ��� ���� �ּ� �� �� �μ���� �ϴ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �̷��� ũ�⸦ ��Ÿ���� ���� ũ�� M, ���� ũ�� N (1 �� N, M �� 100)�� �־�����. 
���� N���� �ٿ��� �̷��� ���¸� ��Ÿ���� ���� 0�� 1�� �־�����. 0�� �� ���� �ǹ��ϰ�, 1�� ���� �ǹ��Ѵ�.

(1, 1)�� (N, M)�� �׻� �շ��ִ�.

[���]
ù° �ٿ� �˰��� ����� (N, M)���� �̵��ϱ� ���� ���� �ּ� �� �� �μ���� �ϴ��� ����Ѵ�.

[Ǯ��]
���� ���� ������ �����Ƿ� ���� �� ���� �켱������ �ִ´�.
(y, x)�� (N-1, M-1)�� ������ ������ �켱���� ť�� ������ �����Ѵ�.
(N-1, M-1)�� �����ϸ� �� ���� cnt�� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int M, N;
	static int[][] maze;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		maze = new int[N][M];
		visited = new boolean[N][M];
		String input = null;
		
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j) - '0';
			}
		}
		
		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cnt - o2.cnt);
		pq.add(new Node(0, 0, 0));
		visited[0][0] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			
			if (y == N - 1 && x == M - 1)
				return cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;
				
				if (visited[ny][nx])
					continue;
				
				pq.add(maze[ny][nx] == 1 ? new Node(ny, nx, cnt + 1) : new Node(ny, nx, cnt));
				visited[ny][nx] = true;
			}
		}
		
		return -1;
	}
}

class Node {
	int y, x, cnt;
	
	public Node(int y, int x, int cnt) { 
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}