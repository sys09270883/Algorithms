import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] map;
	static int T, N, M, K;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int cnt = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[x][y] = 1;				
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 1) {
						DFS(i, j);
						cnt++;
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void DFS(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nX = x + dx[i];
			int nY = y + dy[i];
			
			if(nX < 0 || nX >= N || nY < 0 || nY >= M)
				continue;
			
			if(map[nX][nY] == 0)
				continue;
			
			map[nX][nY] = 0;
			DFS(nX, nY);
		}
	}
}