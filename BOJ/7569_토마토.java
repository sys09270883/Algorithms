/*
https://www.acmicpc.net/problem/7569
[����]
ö���� �丶�� ���忡���� �丶�並 �����ϴ� ū â�� ������ �ִ�. 
�丶��� �Ʒ��� �׸��� ���� ���ڸ�� ������ ĭ�� �ϳ��� ���� ����, ���ڵ��� �������� �׾� �÷��� â�� �����Ѵ�.



â�� �����Ǵ� �丶��� �߿��� �� ���� �͵� ������, ���� ���� ���� �丶��鵵 ���� �� �ִ�. 
���� �� �Ϸ簡 ������, ���� �丶����� ������ ���� �ִ� ���� ���� �丶����� ���� �丶���� ������ �޾� �Ͱ� �ȴ�. 
�ϳ��� �丶�信 ������ ���� ��, �Ʒ�, ����, ������, ��, �� ���� ���⿡ �ִ� �丶�並 �ǹ��Ѵ�. 
�밢�� ���⿡ �ִ� �丶��鿡�Դ� ������ ���� ���ϸ�, �丶�䰡 ȥ�� ������ �ʹ� ���� ���ٰ� �����Ѵ�. 
ö���� â�� ������ �丶����� ��ĥ�� ������ �� �Ͱ� �Ǵ��� �� �ּ� �ϼ��� �˰� �;� �Ѵ�.

�丶�並 â�� �����ϴ� ���ڸ���� ���ڵ��� ũ��� ���� �丶���� ���� ���� �丶����� ������ �־����� ��, 
��ĥ�� ������ �丶����� ��� �ʹ���, �� �ּ� �ϼ��� ���ϴ� ���α׷��� �ۼ��϶�. ��, ������ �Ϻ� ĭ���� �丶�䰡 ������� ���� ���� �ִ�.

[�Է�]
ù �ٿ��� ������ ũ�⸦ ��Ÿ���� �� ���� M,N�� �׾ƿ÷����� ������ ���� ��Ÿ���� H�� �־�����. 
M�� ������ ���� ĭ�� ��, N�� ������ ���� ĭ�� ���� ��Ÿ����. ��, 2 �� M �� 100, 2 �� N �� 100, 1 �� H �� 100 �̴�. 
��° �ٺ��ʹ� ���� ���� ���ں��� ���� ���� ���ڱ����� ����� �丶����� ������ �־�����. 
��, ��° �ٺ��� N���� �ٿ��� �ϳ��� ���ڿ� ��� �丶���� ������ �־�����. 
�� �ٿ��� ���� �����ٿ� ����ִ� �丶����� ���°� M���� ������ �־�����. 
���� 1�� ���� �丶��, ���� 0 �� ���� ���� �丶��, ���� -1�� �丶�䰡 ������� ���� ĭ�� ��Ÿ����. �̷��� N���� ���� H�� �ݺ��Ͽ� �־�����.

[���]
�������� �丶�䰡 ��� ���� ������ �ּ� ��ĥ�� �ɸ������� ����ؼ� ����ؾ� �Ѵ�. 
����, ����� ������ ��� �丶�䰡 �;��ִ� �����̸� 0�� ����ؾ� �ϰ�, �丶�䰡 ��� ������ ���ϴ� ��Ȳ�̸� -1�� ����ؾ� �Ѵ�.

[Ǯ��]
6�������� bfs�ϸ鼭 day�� �迭�� �����Ѵ�. (���ο� �迭�� ���� �ϴ°� ������ ���ǻ� �̷��� ��)
BFS�� ���� �� �迭�� 0�� ������ -1�� ����ϰ� �ƴ� ��쿡�� maxDAY�� ����Ѵ�.

*/

import java.io.*;
import java.util.*;

public class Main {

	static Queue<Node> queue;
	static int N, M, H;
	static int[][][] arr;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, -1, 0, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[M][N][H];
		visited = new boolean[M][N][H];
		queue = new LinkedList<Node>();

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if (arr[i][j][k] == 1)
						queue.add(new Node(i, j, k, 0));
				} 
			}
		}

		int res = BFS();
		
		bw.write(existZero() == false ? String.valueOf(res) : "-1");
		bw.close();
		br.close();
	}

	private static int BFS() {
		int maxDay = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.z;
			int day = cur.day;

			if (visited[x][y][z])
				continue;

			maxDay = Math.max(maxDay, day);
			visited[x][y][z] = true;

			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];

				if (nx < 0 || ny < 0 || nz < 0 || nx >= M || ny >= N || nz >= H)
					continue;

				if (arr[nx][ny][nz] != 0)
					continue;

				arr[nx][ny][nz] = maxDay + 1;
				queue.add(new Node(nx, ny, nz, day + 1));
			}
		}
		
		return maxDay;
	}
	
	private static boolean existZero() {
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j][k] == 0)
						return true;
				}
			}
		}
		
		return false;
	}
}

class Node {
	int x, y, z, day;

	Node(int x, int y, int z, int day) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.day = day;
	}
}