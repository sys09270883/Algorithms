/*
https://www.acmicpc.net/problem/14442
[문제]
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 
당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 
최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개 까지 부수고 이동하여도 된다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

[입력]
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000), K(1 ≤ K ≤ 10)이 주어진다. 
다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

[출력]
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

[풀이]
벽을 몇 번 부쉈는지를 저장하는 3차원 배열에 방문표시를 하면서 BFS를 진행한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io;
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String... args) throws IOException {
		io = new FastIO();
		N = io.nextInt();
		M = io.nextInt();
		K = io.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M][K + 1];
		String str = null;
		
		for (int i = 0; i < N; i++) {
			str = io.next();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int a = BFS();
		io.write(a);
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			int cnt = tmp.cnt;
			int breakWall = tmp.breakWall;
			
			if (x == N - 1 && y == M - 1)
				return cnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nBreakWall = breakWall;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (visited[nx][ny][nBreakWall])
					continue;
				
				if (map[nx][ny] == 0) {
					visited[nx][ny][nBreakWall] = true;
					queue.add(new Node(nx, ny, cnt + 1, nBreakWall));
				}
				
				else {
					if (nBreakWall + 1 > K)
						continue;
					
					visited[nx][ny][++nBreakWall] = true;
					queue.add(new Node(nx, ny, cnt + 1, nBreakWall));
				}
			}
		}
		
		return -1;
	}
	
}

class Node {
	int x, y, cnt, breakWall;

	public Node(int x, int y, int cnt, int breakWall) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.breakWall = breakWall;
	}
	
}

class FastIO {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	FastIO() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return st.nextToken();
	}
	
	int nextInt() {
		return Integer.parseInt(next());
	}
	
	long nextLong() {
		return Long.parseLong(next());
	}
	
	double nextDouble() {
		return Double.parseDouble(next());
	}
	
	String nextLine() {
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	void write(double d) throws IOException { 
		bw.write(String.valueOf(d));
		close();
	}
	
	void write(int i) throws IOException {
		bw.write(String.valueOf(i));
		close();
	}
	
	void write(long l) throws IOException {
		bw.write(String.valueOf(l));
		close();
	}
	
	void write(StringBuilder sb) throws IOException {
		bw.write(sb.toString().trim());
		close();
	}
	
	void write(String str) throws IOException {
		bw.write(str.trim());
		close();
	}
	
	void close() throws IOException {
		bw.close();
		br.close();
	}
}
