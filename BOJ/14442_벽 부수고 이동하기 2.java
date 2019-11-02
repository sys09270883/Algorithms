/*
https://www.acmicpc.net/problem/14442
[����]
N��M�� ��ķ� ǥ���Ǵ� ���� �ִ�. �ʿ��� 0�� �̵��� �� �ִ� ���� ��Ÿ����, 1�� �̵��� �� ���� ���� �ִ� ���� ��Ÿ����. 
����� (1, 1)���� (N, M)�� ��ġ���� �̵��Ϸ� �ϴµ�, �̶� �ִ� ��η� �̵��Ϸ� �Ѵ�. 
�ִܰ�δ� �ʿ��� ���� ���� ������ ĭ�� ������ ��θ� ���ϴµ�, �̶� �����ϴ� ĭ�� ������ ĭ�� �����ؼ� ����.

���࿡ �̵��ϴ� ���߿� ���� �μ��� �̵��ϴ� ���� �� �� ��ΰ� ª�����ٸ�, ���� K�� ���� �μ��� �̵��Ͽ��� �ȴ�.

���� �־����� ��, �ִ� ��θ� ���� ���� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N(1 �� N �� 1,000), M(1 �� M �� 1,000), K(1 �� K �� 10)�� �־�����. 
���� N���� �ٿ� M���� ���ڷ� ���� �־�����. (1, 1)�� (N, M)�� �׻� 0�̶�� ��������.

[���]
ù° �ٿ� �ִ� �Ÿ��� ����Ѵ�. �Ұ����� ���� -1�� ����Ѵ�.

[Ǯ��]
���� �� �� �ν������� �����ϴ� 3���� �迭�� �湮ǥ�ø� �ϸ鼭 BFS�� �����Ѵ�.

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
