import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int nHouse = 0;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1)
					BFS(i, j);
			}
		}
		
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) {
			int tmp = pq.poll();
			sb.append(tmp == 0 ? 1 : tmp).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static void BFS(int row, int column) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(row, column));
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = tmp.row + dx[i];
				int ny = tmp.column + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if(map[nx][ny] == 0)
					continue;
				
				cnt++;
				map[nx][ny] = 0;
				queue.add(new Node(nx, ny));
			}
		} 
		pq.add(cnt);
	}
}

class Node {
	int row, column;

	Node(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
