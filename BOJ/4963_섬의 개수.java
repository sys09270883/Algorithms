import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static int w, h, cnt = 0;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0, -1, 1, -1, 1};
	static int[] dy = {0, -1, 0, 1, -1, 1, 1, -1};
	static boolean[][] visited;
	static StringTokenizer st;
	static StringBuilder sb;
	static String input;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		while(true) {
			init();
			
			if(w == 0 && h == 0)
				break;
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(map[i][j] == 1) {
						cnt++;
						BFS(i, j);
					}
				}
			}
			
			sb.append(cnt).append('\n');
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void init() throws IOException{
		cnt = 0;
		st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new boolean[h][w];
	}
	
	private static void BFS(int i, int j) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j));
		visited[i][j] = true;
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			
			for (int k = 0; k < 8; k++) {
				int nx = tmp.row + dx[k];
				int ny = tmp.col + dy[k];
				
				if(nx < 0 || ny < 0 || nx >= h || ny >= w)
					continue;
				
				if(!visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					map[nx][ny] = 0;
					queue.add(new Node(nx, ny));
				}
			}
		}
	}
}

class Node {
	int row, col;
	
	Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
}