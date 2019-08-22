/*
https://www.acmicpc.net/problem/3055
[문제]
사악한 암흑의 군주 이민혁은 드디어 마법 구슬을 손에 넣었고, 그 능력을 실험해보기 위해 근처의 티떱숲에 홍수를 일으키려고 한다. 
이 숲에는 고슴도치가 한 마리 살고 있다. 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.

티떱숲의 지도는 R행 C열로 이루어져 있다. 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 
비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.

매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다. 
물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 
또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.

티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.

고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다. 

[입력]
첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.

다음 R개 줄에는 티떱숲의 지도가 주어지며, 문제에서 설명한 문자만 주어진다. 'D'와 'S'는 하나씩만 주어진다.

[출력]
첫째 줄에 고슴도치가 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전하게 비버의 굴로 이동할 수 없다면, "KAKTUS"를 출력한다.

[풀이]
물에서 BFS를 하면서 날짜를 기록한다.
고슴도치에서 BFS를 하면서 날짜와 비교하면서 이동한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static Queue<Node> waterQueue = new LinkedList<Node>();
	static int[][] waterMap, visited;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int R, C, start_x, start_y, end_x, end_y;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new int[R][C];
		waterMap = new int[R][C];
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				
				if (map[i][j] == '*')
					waterQueue.add(new Node(i, j));
				
				else if (map[i][j] == 'S') {
					start_x = i;
					start_y = j;
				}
				
				else if (map[i][j] == 'D') {
					end_x = i;
					end_y = j;
				}
			}
		}
		
		waterBFS();
		
		BFS();
		
		bw.write(visited[end_x][end_y] != 0 ? String.valueOf(visited[end_x][end_y]) : "KAKTUS");
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(start_x, start_y));
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				
				if(visited[nx][ny] == 0 && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {  
					if(waterMap[nx][ny] == 0) {
						visited[nx][ny] = visited[tmp.x][tmp.y] + 1;
						queue.add(new Node(nx, ny));
					}
					
					else {
						if(waterMap[nx][ny] > visited[tmp.x][tmp.y] + 1) {
							visited[nx][ny] = visited[tmp.x][tmp.y] + 1;
							queue.add(new Node(nx, ny));
						}
					}
				}
			}
		}
	}
	
	private static void waterBFS() {
		while(!waterQueue.isEmpty()) {
			Node tmp = waterQueue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				
				if (waterMap[nx][ny] == 0 && map[nx][ny] == '.') {
					waterMap[nx][ny] = waterMap[tmp.x][tmp.y] + 1;
					waterQueue.add(new Node(nx, ny));
				}
			}
		}
	}
}

class Node {
	int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
