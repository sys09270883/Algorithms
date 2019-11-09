/*
https://www.acmicpc.net/problem/16946
[����]
N��M�� ��ķ� ǥ���Ǵ� ���� �ִ�. �ʿ��� 0�� �̵��� �� �ִ� ���� ��Ÿ����, 1�� �̵��� �� ���� ���� �ִ� ���� ��Ÿ����.
�� ĭ���� �ٸ� ĭ���� �̵��Ϸ���, �� ĭ�� �����ؾ� �Ѵ�. �� ĭ�� ���� ������ ��, �����ϴٰ� �Ѵ�.

������ ���� ���ؼ� ������ ���غ����� �Ѵ�.

���� �μ��� �̵��� �� �ִ� ������ �����Ѵ�.
�� ��ġ���� �̵��� �� �ִ� ĭ�� ������ �����.
[�Է�]
ù° �ٿ� N(1 �� N �� 1,000), M(1 �� M �� 1,000)�� �־�����. ���� N���� �ٿ� M���� ���ڷ� ���� �־�����.

[���]
���� ���·� ������ ����Ѵ�. ���� �� ĭ�� ���� 0�� ����ϰ�, ���� ���� �̵��� �� �ִ� ĭ�� ������ 10���� ���� �������� ����Ѵ�.

[Ǯ��]
�ش� ���� 0���� �ٲٰ� BFS�� ī���� �� �� �ٽ� ���� 1�� �ٲپ� �ָ鼭 �ݺ��ϴ� �˰����� ��û���� ���� �ߺ��� ���� �־��� �ð� �ȿ� �ذ��� �� ����.
�ߺ��� ����� �κ��� ���� �ʿ��� ���� �ƴ� �κ��ε�, �� �κи� BFS�� �̸� ���� �ߺ��� ���־� �Ѵ�.
���ο� 2���� �迭�� �ʿ��Ѵ�.

���� ���, �ֺ� 0�� ������ �����ָ� �ȴ�.
00220
33000
30101
01010

���� ��, �����¿쿡 �ִ� �ֺ� 0���� ������ ���ϸ� �Ǵµ� �ߺ��� ���� �� �ִ�.

���� �Է� 2�� ���� : (2,1)���� �ߺ� �߻�
46003
00732
09040
50403

�̷��� �ߺ��� ó���ϱ� ���� ���ο� 2���� �迭�� �� ��° BFS���� �ε����� �� �迭�� ����� �ش�.

���� �Է� 2�� ����
00110
22000
20304
05060

���������� ���� ����� ������ �����¿��� ���� HashSet�� �����鼭 �ߺ��� ���� �ʾ����� �ش� 0�� ������ ������ �����ش�.
HashSet�� add�� Set�� �����ϴ� ���̸� false, �������� ������ true�� �����Ѵ�.

������ �� ������ 10���� ������ ������ �����Ѵ�.

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
