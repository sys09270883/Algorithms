/*
https://www.acmicpc.net/problem/16946
[문제]
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
한 칸에서 다른 칸으로 이동하려면, 두 칸이 인접해야 한다. 두 칸이 변을 공유할 때, 인접하다고 한다.

각각의 벽에 대해서 다음을 구해보려고 한다.

벽을 부수고 이동할 수 있는 곳으로 변경한다.
그 위치에서 이동할 수 있는 칸의 개수를 세어본다.
[입력]
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다.

[출력]
맵의 형태로 정답을 출력한다. 원래 빈 칸인 곳은 0을 출력하고, 벽인 곳은 이동할 수 있는 칸의 개수를 10으로 나눈 나머지를 출력한다.

[풀이]
해당 벽을 0으로 바꾸고 BFS로 카운팅 한 후 다시 벽을 1로 바꾸어 주면서 반복하는 알고리즘은 엄청나게 많은 중복이 생겨 주어진 시간 안에 해결할 수 없다.
중복이 생기는 부분은 기존 맵에서 벽이 아닌 부분인데, 이 부분만 BFS로 미리 구해 중복을 없애야 한다.
새로운 2차원 배열이 필요한다.

벽일 경우, 주변 0의 개수를 더해주면 된다.
00220
33000
30101
01010

벽일 때, 상하좌우에 있는 주변 0들의 개수를 더하면 되는데 중복이 생길 수 있다.

예제 입력 2의 예시 : (2,1)에서 중복 발생
46003
00732
09040
50403

이러한 중복을 처리하기 위해 새로운 2차원 배열에 몇 번째 BFS인지 인덱싱을 한 배열을 만들어 준다.

예제 입력 2의 예시
00110
22000
20304
05060

최종적으로 값을 출력할 때에는 상하좌우의 값을 HashSet에 넣으면서 중복이 되지 않았으면 해당 0의 개수의 값들을 더해준다.
HashSet의 add는 Set에 존재하는 값이면 false, 존재하지 않으면 true를 리턴한다.

더해줄 때 값들을 10으로 나누는 조건을 유의한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M;
	static int[][] map;
	static int[][] reverseMap;
	static int[][] reverseIdxMap;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static Queue<Node> fillQueue;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		String input = null;
		StringBuilder res = new StringBuilder();
		map = new int[N][M];
		reverseMap = new int[N][M];
		reverseIdxMap = new int[N][M];
		visited = new boolean[N][M];
		int idx = 0;
		fillQueue = new LinkedList<Node>();
		
		for (int i = 0; i < N; i++) {
			input = io.nextLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
				reverseMap[i][j] = -(map[i][j] - 1);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					int fill = BFS(i, j, ++idx);
					
					while (!fillQueue.isEmpty()) {
						Node tmp = fillQueue.poll();
						int x = tmp.x;
						int y = tmp.y;
						
						if (reverseMap[x][y] < fill)
							reverseMap[x][y] = fill;
					}
				}
			}
		}
		
		Set<Integer> hs = new HashSet<Integer>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					hs.clear();
					int moveCnt = 1;
					
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if (nx < 0 || ny < 0 || nx >= N || ny >= M)
							continue;
						
						if (hs.add(reverseIdxMap[nx][ny]))
							moveCnt += reverseMap[nx][ny];
					}
					
					res.append(moveCnt % 10);
				}
				
				else
					res.append(0);
			}
			
			res.append('\n');
		}
		
		io.write(res);
	}
	
	private static int BFS(int r, int c, int idx) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(r, c));
		fillQueue.add(new Node(r, c));
		visited[r][c] = true;
		reverseIdxMap[r][c] = idx;
		int cnt = 1;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if (visited[nx][ny] || reverseMap[nx][ny] == 0)
					continue;
				
				cnt++;
				fillQueue.add(new Node(nx, ny));
				reverseIdxMap[nx][ny] = idx;
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
			}
		}
		
		return cnt;
	}
	
}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
