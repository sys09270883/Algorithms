/*
https://www.acmicpc.net/problem/15685
[����]
�巡�� Ŀ��� ������ ���� �� ���� �Ӽ����� �̷���� ������, ������ ��ǥ ��� ������ ���ǵȴ�. ��ǥ ����� x���� �� ����, y���� �� �����̴�.

���� ��
���� ����
����
0���� �巡�� Ŀ��� �Ʒ� �׸��� ���� ���̰� 1�� �����̴�. �Ʒ� �׸��� (0, 0)���� �����ϰ�, ���� ������ �������� 0���� �巡�� Ŀ���̴�.



1���� �巡�� Ŀ��� 0���� �巡�� Ŀ�긦 �� ���� �������� �ð� �������� 90�� ȸ����Ų ���� 0���� �巡�� Ŀ���� �� ���� ���� ���̴�. 
�� ���̶� ���� ������ ������ Ÿ�� �̵����� ��, ���� �� �Ÿ��� �ִ� ���� �ǹ��Ѵ�.



2���� �巡�� Ŀ�굵 1���븦 ���� ����� �̿��ؼ� ���� �� �ִ�. (�Ķ��� ������ ���� �߰��� ������ ��Ÿ����)



3���� �巡�� Ŀ�굵 2���� �巡�� Ŀ�긦 �̿��� ���� �� �ִ�. �Ʒ� �׸��� 3���� �巡�� Ŀ���̴�.



��, K(K > 1)���� �巡�� Ŀ��� K-1���� �巡�� Ŀ�긦 �� ���� �������� 90�� �ð� ���� ȸ�� ��Ų ����, �װ��� �� ���� ���� ���̴�.

ũ�Ⱑ 100��100�� ���� ���� �巡�� Ŀ�갡 N�� �ִ�. 
�̶�, ũ�Ⱑ 1��1�� ���簢���� �� �������� ��� �巡�� Ŀ���� �Ϻ��� ���簢���� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
������ ��ǥ�� (x, y)�� ��Ÿ����, 0 �� x �� 100, 0 �� y �� 100�� ��ȿ�� ��ǥ�̴�.

[�Է�]
ù° �ٿ� �巡�� Ŀ���� ���� N(1 �� N �� 20)�� �־�����. ��° �ٺ��� N���� �ٿ��� �巡�� Ŀ���� ������ �־�����. 
�巡�� Ŀ���� ������ �� ���� x, y, d, g�� �̷���� �ִ�. x�� y�� �巡�� Ŀ���� ���� ��, d�� ���� ����, g�� �����̴�. 
(0 �� x, y �� 100, 0 �� d �� 3, 0 �� g �� 10)

�Է����� �־����� �巡�� Ŀ��� ���� ������ ����� �ʴ´�. �巡�� Ŀ��� ���� ��ĥ �� �ִ�.

������ 0, 1, 2, 3 �� �ϳ��̰�, ������ �ǹ��Ѵ�.

0: x��ǥ�� �����ϴ� ���� (��)
1: y��ǥ�� �����ϴ� ���� (��)
2: x��ǥ�� �����ϴ� ���� (��)
3: y��ǥ�� �����ϴ� ���� (��)
[���]
ù° �ٿ� ũ�Ⱑ 1��1�� ���簢���� �� �������� ��� �巡�� Ŀ���� �Ϻ��� ���� ������ ����Ѵ�.

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
