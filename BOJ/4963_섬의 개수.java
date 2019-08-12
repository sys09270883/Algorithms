/*
https://www.acmicpc.net/problem/4963
[문제]
정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.



한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 

두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러쌓여 있으며, 지도 밖으로 나갈 수 없다.

[입력]
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. 
w와 h는 50보다 작거나 같은 양의 정수이다.

둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.

입력의 마지막 줄에는 0이 두 개 주어진다.

[출력]
각 테스트 케이스에 대해서, 섬의 개수를 출력한다.

[풀이]
map을 순회하면서 1인 지점에서 카운팅을 하고 BFS한다.
BFS에서 map이 값을 0으로 바꿔준다.

*/
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
