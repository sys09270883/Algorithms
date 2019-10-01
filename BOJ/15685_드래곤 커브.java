/*


[Ǯ��]
������ ������ �� �κ����� ���� �� �ִ�.
  1. �巡��Ŀ���� ������ �Է¹޴� ����
  2. �Է¹��� ������ �������� ��ǥ��鿡 ���� ��� ����
  3. ���簢���� ������ Ȯ���ϴ� ����
  
�� �ܰ��� �巡�� Ŀ�꿡�� ���� ǥ���ϰ� ���� �ܰ��� �巡�� Ŀ�긦 �����Ѵ�.
���� �ܰ��� �巡�� Ŀ��� ���� �ܰ��� �巡�� Ŀ���� �������� 1�� �����ϴ� ��Ģ�� �ִ�. ((dir + 1) % 4)
���������� �迭�� ��ȸ�ϸ鼭 ���簢���� ������ ����.

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
