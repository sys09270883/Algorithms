/*
https://www.acmicpc.net/problem/14503
[����]
�κ� û�ұⰡ �־����� ��, û���ϴ� ������ ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�κ� û�ұⰡ �ִ� ��Ҵ� N��M ũ���� ���簢������ ��Ÿ�� �� ������, 1��1ũ���� ���簢�� ĭ���� �������� �ִ�. 
������ ĭ�� �� �Ǵ� �� ĭ�̴�. û�ұ�� �ٶ󺸴� ������ ������, �� ������ ��, ��, ��, ���� �ϳ��̴�. 
������ �� ĭ�� (r, c)�� ��Ÿ�� �� �ְ�, r�� �������κ��� ������ ĭ�� ����, c�� �������� ���� ������ ĭ�� �����̴�.

�κ� û�ұ�� ������ ���� �۵��Ѵ�.

���� ��ġ�� û���Ѵ�.
���� ��ġ���� ���� ������ �������� ���ʹ������ ���ʴ�� Ž���� �����Ѵ�.
���� ���⿡ ���� û������ ���� ������ �����Ѵٸ�, �� �������� ȸ���� ���� �� ĭ�� �����ϰ� 1������ �����Ѵ�.
���� ���⿡ û���� ������ ���ٸ�, �� �������� ȸ���ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���� ��쿡��, �ٶ󺸴� ������ ������ ä�� �� ĭ ������ �ϰ� 2������ ���ư���.
�� ���� ��� û�Ұ� �̹� �Ǿ��ְų� ���̸鼭, ���� ������ ���̶� ������ �� �� ���� ��쿡�� �۵��� �����.
�κ� û�ұ�� �̹� û�ҵǾ��ִ� ĭ�� �� û������ ������, ���� ����� �� ����.

[�Է�]
ù° �ٿ� ���� ũ�� N�� ���� ũ�� M�� �־�����. (3 �� N, M �� 50)

��° �ٿ� �κ� û�ұⰡ �ִ� ĭ�� ��ǥ (r, c)�� �ٶ󺸴� ���� d�� �־�����. 
d�� 0�� ��쿡�� ������, 1�� ��쿡�� ������, 2�� ��쿡�� ������, 3�� ��쿡�� ������ �ٶ󺸰� �ִ� ���̴�.

��° �ٺ��� N���� �ٿ� ����� ���°� ���ʺ��� ���� �������, �� ���� ���ʺ��� ���� ������� �־�����. �� ĭ�� 0, ���� 1�� �־�����. ����� ��� �ܰ��� ���̴�.

�κ� û�ұⰡ �ִ� ĭ�� ���´� �׻� �� ĭ�̴�.

[���]
�κ� û�ұⰡ û���ϴ� ĭ�� ������ ����Ѵ�.

[Ǯ��]
û������ ���� ������ 0, ���� 1, û�Ҹ� �� ������ 2�� �����.
�κ��� �ʱ� ��ġ�� �׻� 0�̹Ƿ� ���� ������ 2�� �ʱ�ȭ�ϰ� �ݺ����� �����Ѵ�.

�ݺ����� Ż�� ������ ���� ��ġ�κ��� 4������ û�Ҹ� �� �κ��̰ų� ���� ��, ������ �� �� ���� ���(���� ���� ��) �ݺ����� �����Ѵ�.
������ �� �� �ִ� ���(���� ���� ��)�� ������ �����ϸ鼭 �����ϰ� �ٽ� �ݺ����� �����Ѵ�. (c, d)

û���� ������ �ִ��� Ȯ���ϰ� ������ Ʋ�� û�Ҹ� �� �� ���� ��� �ش� ��ġ�� �̵��ϰ�, �� ������ 2�� ����� û�� Ƚ���� ������Ų��.
û���� ������ ���� ���� ���⸸ �ٲٰ� �ݺ��Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, robotX, robotY, robotDir;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}; // NESW
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		robotX = Integer.parseInt(st.nextToken());
		robotY = Integer.parseInt(st.nextToken());
		robotDir = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bw.write(String.valueOf(clean()));
		bw.close();
		br.close();
	}
		
	private static int clean() {
		Robot robot = new Robot(robotX, robotY, robotDir, 1);
		map[robotX][robotY] = 2;
		
		while (true) {
			int x = robot.x;
			int y = robot.y;
			int dir = robot.dir;
			
			if (map[x - 1][y] >= 1 && map[x][y + 1] >= 1 && map[x + 1][y] >= 1 && map[x][y - 1] >= 1) {
				x -= dx[dir];
				y -= dy[dir];
				
				if (map[x][y] == 1)
					break;
				
				else {
					robot.x = x;
					robot.y = y;
					continue;
				}
			}
			
			dir = rotateReverseClockwise(dir);
			
			if (checkSpace(x, y, dir)) {
				robot.dir = dir;
				go(robot);
				map[robot.x][robot.y] = 2;
				robot.cnt++;
				continue;
			}
			
			else {
				robot.dir = dir;
				continue;
			}
		}
		
		return robot.cnt;
	}
	
	private static void go(Robot robot) {
		robot.x += dx[robot.dir];
		robot.y += dy[robot.dir];
	}
	
	private static int rotateReverseClockwise(int dir) {
		return (dir + 3) % 4;
	}
	
	private static boolean checkSpace(int x, int y, int dir) {
		x += dx[dir];
		y += dy[dir];
		
		if (map[x][y] != 0)
			return false;
		
		return true;
	}
	
}

class Robot {
	int x, y, dir, cnt;

	public Robot(int x, int y, int dir ,int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
	
}