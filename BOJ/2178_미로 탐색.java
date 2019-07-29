import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0 ,1};
	static boolean[][] visited;
	static int[][] maze;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
		}
		
		visited[0][0] = true;
		BFS(0, 0);
		
		bw.write(String.valueOf(maze[N-1][M-1]));
		bw.close();
		br.close();
	}
	
	public static void BFS(int row, int column) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(row, column));
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nX = tmp.row + dx[i];
				int nY = tmp.column + dy[i];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= M)
					continue;
				
				if(visited[nX][nY] || maze[nX][nY] == 0)
					continue;
				
				queue.add(new Node(nX, nY));
				maze[nX][nY] = maze[tmp.row][tmp.column] + 1;
				visited[nX][nY] = true;
			}
		}
	}
}

class Node {
	int row, column;
	
	public Node(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
