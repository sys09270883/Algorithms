/*
https://www.acmicpc.net/problem/17070
[문제]
유현이가 새 집으로 이사했다. 새 집의 크기는 N×N의 격자판으로 나타낼 수 있고, 1×1크기의 정사각형 칸으로 나누어져 있다. 
각각의 칸은 (r, c)로 나타낼 수 있다. 여기서 r은 행의 번호, c는 열의 번호이고, 행과 열의 번호는 1부터 시작한다. 각각의 칸은 빈 칸이거나 벽이다.

오늘은 집 수리를 위해서 파이프 하나를 옮기려고 한다. 파이프는 아래와 같은 형태이고, 2개의 연속된 칸을 차지하는 크기이다.



파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.



파이프는 매우 무겁기 때문에, 유현이는 파이프를 밀어서 이동시키려고 한다. 벽에는 새로운 벽지를 발랐기 때문에, 파이프가 벽을 긁으면 안 된다.
즉, 파이프는 항상 빈 칸만 차지해야 한다.

파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 파이프는 밀면서 회전시킬 수 있다. 
회전은 45도만 회전시킬 수 있으며, 미는 방향은 오른쪽, 아래, 또는 오른쪽 아래 대각선 방향이어야 한다.

파이프가 가로로 놓여진 경우에 가능한 이동 방법은 총 2가지, 세로로 놓여진 경우에는 2가지, 대각선 방향으로 놓여진 경우에는 3가지가 있다.

아래 그림은 파이프가 놓여진 방향에 따라서 이동할 수 있는 방법을 모두 나타낸 것이고, 꼭 빈 칸이어야 하는 곳은 색으로 표시되어져 있다.



가로



세로



대각선

가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지하고 있고, 방향은 가로이다. 파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수를 구해보자.

[입력]
첫째 줄에 집의 크기 N(3 ≤ N ≤ 16)이 주어진다. 둘째 줄부터 N개의 줄에는 집의 상태가 주어진다. 
빈 칸은 0, 벽은 1로 주어진다. (1, 1)과 (1, 2)는 항상 빈 칸이다.

[출력]
첫째 줄에 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력한다. 이동시킬 수 없는 경우에는 0을 출력한다. 방법의 수는 항상 1,000,000보다 작거나 같다.

[풀이]
완전 탐색과 dp로 풀이가 가능하다.

1. 완전 탐색
  문제 자체는 주어진 조건에 맞춰 구현하면 되는 간단한 문제였다.
  추가 시간이 없는 문제여서 BFS로는 시간제한에 걸렸다. 스택으로 구현한 DFS도 겨우 시간을 통과할 정도였다.
  재귀로 구현한 DFS를 제출하니 통과했다.

2. dp
  (x, y)에 도달할 수 있는 경우의 수, dir을 방향으로 dp를 만든다.
  1) 이동했는데 방향이 가로인 경우 : 이동하기 전 방향이 가로, 대각
  2) 이동했는데 방향이 세로인 경우 : 이동하기 전 방향이 세로, 대각
  3) 이동했는데 방향이 대각인 경우 : 이동하기 전 방향이 가로, 세로, 대각
  최종적으로 dp[N - 1][N - 1][0 ~ 2]의 값들을 더한다.
  
 dp로 풀이하는 것이 더 좋은 풀이인 듯 싶다.
*/
// 1. 완전 탐색
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, cnt;
	static int[][] map;
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = io.nextInt();
			}
		}
		
		func(0, 1, 0);
		
		io.write(cnt);
	}
	
	private static void func(int x, int y, int dir) {
		if (x == N - 1 && y == N - 1) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if ((i == 0 && dir == 1) || (i == 1 && dir == 0))
				continue;
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= N || ny >= N || map[nx][ny] == 1)
				continue;
			
			if (i == 2 && (map[nx - 1][ny] == 1 || map[nx][ny - 1] == 1))
				continue;
			
			func(nx, ny, i);
		}
	}
	
}

class Node {
	int x, y, dir;

	public Node(int x, int y, int dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
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


// 2. dp
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, cnt;
	static int[][] map;
	static int[][][] dp;
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };

	public static void main(String... args) throws IOException {
		N = io.nextInt();
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1][3];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = io.nextInt();
			}
		}
		
		dp[0][1][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j + 1] == 0)
					dp[i][j + 1][0] += dp[i][j][0] + dp[i][j][2];
				
				if (map[i + 1][j] == 0)
					dp[i + 1][j][1] += dp[i][j][1] + dp[i][j][2];
				
				if (map[i + 1][j] == 0 && map[i][j + 1] == 0 && map[i + 1][j + 1] == 0)
					dp[i + 1][j + 1][2] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
			}
		}
		
		cnt = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		
		io.write(cnt);
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
