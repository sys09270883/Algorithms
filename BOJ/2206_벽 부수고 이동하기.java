import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int N, M, ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		BFS();
		
		bw.write(ans == Integer.MAX_VALUE ? "-1" : String.valueOf(ans));
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			
			if(tmp.row == N-1 && tmp.col == M-1) {
				ans = Math.min(ans, tmp.cnt);
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.row + dx[i];
				int ny = tmp.col + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				
				if(tmp.breakWall == 1) {
					if(map[nx][ny] == 0 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall));
					}
				}
				
				else {
					if(map[nx][ny] == 0 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall));
					}
					
					else if(map[nx][ny] == 1 && !visited[nx][ny][tmp.breakWall]) {
						visited[nx][ny][tmp.breakWall] = true;
						queue.add(new Node(nx, ny, tmp.cnt + 1, tmp.breakWall + 1));
					}
				}
			}
		}
	}
}

class Node {
	int row, col, cnt, breakWall;
	
	Node(int row, int col, int cnt, int breakWall) {
		this.row = row;
		this.col = col;
		this.cnt = cnt;
		this.breakWall = breakWall;
	}
}
