/*
https://www.acmicpc.net/problem/14502
[����]
��ü�� ġ������ ���̷����� �����ϴ� �����ҿ��� ���̷����� ����Ǿ���. ������ ���̷����� ���� ������ �ʾҰ�, ���̷����� Ȯ���� ���� ���ؼ� �����ҿ� ���� ������� �Ѵ�.

�����Ҵ� ũ�Ⱑ N��M�� ���簢������ ��Ÿ�� �� ������, ���簢���� 1��1 ũ���� ���簢������ �������� �ִ�. �����Ҵ� �� ĭ, ������ �̷���� ������, ���� ĭ �ϳ��� ���� �����Ѵ�. 

�Ϻ� ĭ�� ���̷����� �����ϸ�, �� ���̷����� �����¿�� ������ �� ĭ���� ��� �������� �� �ִ�. ���� ���� �� �ִ� ���� ������ 3���̸�, �� 3���� ������ �Ѵ�.

���� ���, �Ʒ��� ���� �����Ұ� ���� ��츦 ���캸��.

2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
�̶�, 0�� �� ĭ, 1�� ��, 2�� ���̷����� �ִ� ���̴�. �ƹ��� ���� ������ �ʴ´ٸ�, ���̷����� ��� �� ĭ���� �������� �� �ִ�.

2�� 1��, 1�� 2��, 4�� 6���� ���� ����ٸ� ������ ����� �Ʒ��� �������� �ȴ�.

2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
���̷����� ���� ���� ����� �Ʒ��� ��������.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
���� 3�� ���� ��, ���̷����� ���� �� ���� ���� ���� �����̶�� �Ѵ�. ���� �������� ���� ������ ũ��� 27�̴�.

�������� ������ �־����� �� ���� �� �ִ� ���� ���� ũ���� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� ũ�� N�� ���� ũ�� M�� �־�����. (3 �� N, M �� 8)

��° �ٺ��� N���� �ٿ� ������ ����� �־�����. 0�� �� ĭ, 1�� ��, 2�� ���̷����� �ִ� ��ġ�̴�. 2�� ������ 2���� ũ�ų� ����, 10���� �۰ų� ���� �ڿ����̴�.

�� ĭ�� ������ 3�� �̻��̴�.

[���]
ù° �ٿ� ���� �� �ִ� ���� ������ �ִ� ũ�⸦ ����Ѵ�.

[Ǯ��]
DFS �Ǵ� BFS�� 3���� ���� ġ�� ��� ����� ���� ������ �ִ� ũ�⸦ ���Ѵ�.
����� 3������ �ִ�.
  1. ��� DFS (DFS)
  2. ���� DFS (DFS2)
  3. ť  BFS 
�̷��� ������ ���� Ž�������� ���̰� ��������� ����, ť�� �޸𸮰� Ŀ���� ������ 1�� ����� 2, 3�� ����� ���� �޸𸮸� ���� �����ϸ鼭 ������ �����Ѵ�.


*/
import java.io.*;
import java.util.*;

public class Main {

	static Stack<Node> stack;
	static Queue<Node> queue;
	static int N, M;
	static int[][] map;
	static int[][] copy;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M];
		stack = new Stack<Node>();
		queue = new LinkedList<Node>();
		ArrayList<Node> list = new ArrayList<Node>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0)
					list.add(new Node(i, j));
			}
		}
		
		int len = list.size(), max = Integer.MIN_VALUE;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				for (int k = j + 1; k < len; k++) {
					Node wall_1 = list.get(i);
					Node wall_2 = list.get(j);
					Node wall_3 = list.get(k);
					
					copyArray();
					
					copy[wall_1.x][wall_1.y] = 1;
					copy[wall_2.x][wall_2.y] = 1;
					copy[wall_3.x][wall_3.y] = 1;
					
					DFS();
//					BFS();
					
					max = Math.max(max, countSafeZone());
				}
			}
		}
		
		bw.write(String.valueOf(max));
		bw.close();
		br.close();
	}
	
	private static void DFS() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 2) {
//					DFS(i, j);
					DFS2(i, j);
				}
			}
		}

	}
	
	private static void DFS(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			
			if (copy[nx][ny] == 0) {
				copy[nx][ny] = 2;
				DFS(nx, ny);
			}
		}
	}
	
	private static void DFS2(int r, int c) {
		stack.add(new Node(r, c));
		
		while(!stack.isEmpty()) {
			Node tmp = stack.pop();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					stack.add(new Node(nx, ny));
				}
			}
		}
	}
	
	private static void BFS() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 2)
					BFS(i, j);
			}
		}
	}
	
	private static void BFS(int r, int c) {
		queue.add(new Node(r, c));
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					queue.add(new Node(nx, ny));
				}
			}
		}
	}
	
	private static void copyArray() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	
	private static int countSafeZone() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					cnt++;
			}
		}
		
		return cnt;
	}
}

class Node {
	int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}