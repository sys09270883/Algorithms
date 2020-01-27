import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	final static int INF = Integer.MAX_VALUE;
	static int N, M;
	static int[][] arr;
	static int[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String... args) throws IOException {
		N = io.nextInt();	M = io.nextInt();
		arr = new int[N][M];
		visited = new int[N][M][3];
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = io.nextInt();
				Arrays.fill(visited[i][j], INF);
			}
		}
		
		if (arr[0][0] < 14)
			DFS(0, 0, 0, 0);
		int ans = Math.min(visited[N - 1][M - 1][1], visited[N - 1][M - 1][2]);
		res.append(ans == INF ? "BAD" : ans);
		io.write(res);
	}
	
	private static void DFS(int x, int y, int s, int cnt) {
		if (visited[x][y][s] <= cnt)
			return;
		visited[x][y][s] = cnt;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			
			if (s == 0 && arr[nx][ny] >= 14)
				DFS(nx, ny, 1, cnt + 1);
			if (s == 1 && arr[nx][ny] < 14) {
				DFS(nx, ny, 2, cnt);
				DFS(nx, ny, 0, cnt);
			}
			if (s == 2 && arr[nx][ny] < 14)
				DFS(nx, ny, 0, cnt);
		}
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

	String next() throws IOException {
		while (st == null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}

	int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	long nextLong() throws IOException {
		return Long.parseLong(next());
	}

	double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	String nextLine() throws IOException {
		return br.readLine();
	}

	void write(double d) throws IOException {
		bw.write(String.valueOf(d));
		close();
	}

	void write(char c) throws IOException {
		bw.write(c);
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
