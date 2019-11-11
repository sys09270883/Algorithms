/*
https://www.acmicpc.net/problem/16933
[문제]
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 
당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 
최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다. 
이동하지 않고 같은 칸에 머물러있는 경우도 가능하다. 이 경우도 방문한 칸의 개수가 하나 늘어나는 것으로 생각해야 한다.

이번 문제에서는 낮과 밤이 번갈아가면서 등장한다. 가장 처음에 이동할 때는 낮이고, 한 번 이동할 때마다 낮과 밤이 바뀌게 된다. 
이동하지 않고 같은 칸에 머무르는 경우에도 낮과 밤이 바뀌게 된다.

만약에 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개 까지 부수고 이동하여도 된다. 단, 벽은 낮에만 부술 수 있다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

[입력]
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000), K(1 ≤ K ≤ 10)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

[출력]
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

[풀이]
전형적인 BFS로 해결할 수 있고 방문 조건을 일반화하다가 실수해서 오래 걸린 문제였다.
 + 실수하지말자...

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited; 
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		K = io.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M][K + 1];
		String input = null;
		
		for (int i = 0; i < N; i++) {
			input = io.nextLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		io.write(BFS());
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(0, 0, 1, 0, 0));
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			int moveCnt = tmp.moveCnt;
			int breakCnt = tmp.breakCnt;
			int day = tmp.day;
			
			if (x == N - 1 && y == M - 1)
				return moveCnt;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nMoveCnt = moveCnt + 1;
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (day == 0) {
					if (map[nx][ny] == 0) {
						if (!visited[nx][ny][breakCnt]) {
							visited[nx][ny][breakCnt] = true;						
							queue.add(new Node(nx, ny, nMoveCnt, breakCnt, 1));
						}
					}
					
					else {
						if (breakCnt >= K)
							continue;
						
						if (!visited[nx][ny][breakCnt + 1]) {
							visited[nx][ny][breakCnt + 1] = true;
							queue.add(new Node(nx, ny, nMoveCnt, breakCnt + 1, 1));
						}
					}
				}
				
				else {
					if (map[nx][ny] == 0) {
						if (!visited[nx][ny][breakCnt]) {
							visited[nx][ny][breakCnt] = true;
							queue.add(new Node(nx, ny, nMoveCnt, breakCnt, 0));
						}
					}
					
					else
						queue.add(new Node(x, y, nMoveCnt, breakCnt, 0));
				}
			}
		}
		
		return -1;
	}
}

class Node {
	int x, y, moveCnt, breakCnt, day;

	public Node(int x, int y, int moveCnt, int breakCnt, int day) {
		super();
		this.x = x;
		this.y = y;
		this.moveCnt = moveCnt;
		this.breakCnt = breakCnt;
		this.day = day;
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
