/*
https://www.acmicpc.net/problem/14503
[문제]
로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.

로봇 청소기가 있는 장소는 N×M 크기의 직사각형으로 나타낼 수 있으며, 1×1크기의 정사각형 칸으로 나누어져 있다. 
각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북중 하나이다. 
지도의 각 칸은 (r, c)로 나타낼 수 있고, r은 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로 부터 떨어진 칸의 개수이다.

로봇 청소기는 다음과 같이 작동한다.

현재 위치를 청소한다.
현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.

[입력]
첫째 줄에 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 50)

둘째 줄에 로봇 청소기가 있는 칸의 좌표 (r, c)와 바라보는 방향 d가 주어진다. 
d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.

셋째 줄부터 N개의 줄에 장소의 상태가 북쪽부터 남쪽 순서대로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 빈 칸은 0, 벽은 1로 주어진다. 장소의 모든 외곽은 벽이다.

로봇 청소기가 있는 칸의 상태는 항상 빈 칸이다.

[출력]
로봇 청소기가 청소하는 칸의 개수를 출력한다.

[풀이]
청소하지 않은 공간은 0, 벽은 1, 청소를 한 공간은 2로 만든다.
로봇의 초기 위치는 항상 0이므로 시작 지점을 2로 초기화하고 반복문을 시작한다.

반복문의 탈출 조건은 현재 위치로부터 4방향이 청소를 한 부분이거나 벽일 때, 후진을 할 수 없는 경우(벽이 있을 때) 반복문을 종료한다.
후진을 할 수 있는 경우(벽이 없을 때)는 방향은 유지하면서 후진하고 다시 반복문을 실행한다. (c, d)

청소할 공간이 있는지 확인하고 방향을 틀고 청소를 할 수 있을 경우 해당 위치로 이동하고, 그 지점을 2로 만들고 청소 횟수를 증가시킨다.
청소할 공간이 없는 경우는 방향만 바꾸고 반복한다.

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