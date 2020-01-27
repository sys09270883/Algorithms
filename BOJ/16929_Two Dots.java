import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M, sx, sy;
	static boolean flag = false;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String... args) throws IOException {
		N = io.nextInt();	M = io.nextInt();
		arr = new char[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = io.next();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (boolean[] row : visited) {
					Arrays.fill(row, false);
					sx = i;
					sy = j;
					DFS(i, j, 1);
				}
			}
		}
		
		io.write(flag ? "Yes" : "No");
	}

    private static void DFS(int x, int y, int cnt) {
    	if (flag)
    		return;
        visited[x][y] = true;
        
        for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			
			if (!visited[nx][ny] && arr[x][y] == arr[nx][ny]) {
				visited[nx][ny] = true;
				DFS(nx, ny, cnt + 1);
			}
			else if (visited[nx][ny] && arr[x][y] == arr[nx][ny]){
				if (cnt >= 4 && sx == nx && sy == ny) {
					flag = true;
					return;
				}
			}
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
