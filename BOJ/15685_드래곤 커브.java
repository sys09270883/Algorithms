/*


[풀이]
문제의 과정은 세 부분으로 나눌 수 있다.
  1. 드래곤커브의 정보를 입력받는 과정
  2. 입력받은 정보를 바탕으로 좌표평면에 점을 찍는 과정
  3. 정사각형의 개수를 확인하는 과정
  
각 단계의 드래곤 커브에서 점을 표시하고 다음 단계의 드래곤 커브를 갱신한다.
다음 단계의 드래곤 커브는 현재 단계의 드래곤 커브의 역순으로 1씩 증가하는 규칙이 있다. ((dir + 1) % 4)
최종적으로 배열을 순회하면서 정사각형의 개수를 센다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, endX, endY;
	static ArrayList<Integer> trace;
	static boolean[][] pointSet;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		pointSet = new boolean[101][101];
		trace = new ArrayList<Integer>();
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gnr = Integer.parseInt(st.nextToken());
			
			trace.clear();
			
			endX = x;
			endY = y;
			
			pointSet[endX][endY] = true;
			
			endX = x + dx[dir];
			endY = y + dy[dir];
			
			pointSet[endX][endY] = true;
			
			trace.add(dir);
			
			while (gnr-- > 0) {
				addPoint();
			}
		}
		
		bw.write(String.valueOf(countSquare()));
		bw.close();
		br.close();
	}
	
	private static void addPoint() {
		int size = trace.size();
		
		for (int i = size - 1; i >= 0; i--) {
			int dir = (trace.get(i) + 1) % 4;
			
			endX = endX + dx[dir];
			endY = endY + dy[dir];
			
			pointSet[endX][endY] = true;
			
			trace.add(dir);
		}
	}
	
	private static int countSquare() {
		int cnt = 0;
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (pointSet[i][j] && pointSet[i + 1][j] && pointSet[i][j + 1] && pointSet[i + 1][j + 1])
					cnt++;
			}
		}
		
		return cnt;
	}
	
}
