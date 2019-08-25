/*
https://www.acmicpc.net/problem/14502
[문제]
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.

2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.

2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.

2 1 0 0 1 1 0
1 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 1 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

2 1 0 0 1 1 2
1 0 1 0 1 2 2
0 1 1 0 1 2 2
0 1 0 0 0 1 2
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.

연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

[출력]
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

[풀이]
DFS 또는 BFS로 3개의 벽을 치는 모든 경우의 수를 따져서 최대 크기를 구한다.
방법은 3가지가 있다.
  1. 재귀 DFS (DFS)
  2. 스택 DFS (DFS2)
  3. 큐  BFS 
이러한 종류의 완전 탐색에서는 깊이가 깊어질수록 스택, 큐의 메모리가 커지기 때문에 1번 방법이 2, 3번 방법에 비해 메모리를 적게 차지하면서 빠르게 실행한다.


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