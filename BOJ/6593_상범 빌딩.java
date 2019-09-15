/*
https://www.acmicpc.net/problem/6593
[문제]
당신은 상범 빌딩에 갇히고 말았다. 여기서 탈출하는 가장 빠른 길은 무엇일까? 상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다. 
각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있게 되어있다. 
당신은 각 칸에서 인접한 6개의 칸(동,서,남,북,상,하)으로 1분의 시간을 들여 이동할 수 있다. 
즉, 대각선으로 이동하는 것은 불가능하다. 그리고 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.

당신은 상범 빌딩을 탈출할 수 있을까? 만약 그렇다면 얼마나 걸릴까?

[입력]
입력은 여러 개의 테스트 케이스로 이루어지며, 각 테스트 케이스는 세 개의 정수 L, R, C로 시작한다. L(1 ≤ L ≤ 30)은 상범 빌딩의 층 수이다.
R(1 ≤ R ≤ 30)과 C(1 ≤ C ≤ 30)는 상범 빌딩의 한 층의 행과 열의 개수를 나타낸다.

그 다음 각 줄이 C개의 문자로 이루어진 R개의 행이 L번 주어진다. 각 문자는 상범 빌딩의 한 칸을 나타낸다. 
금으로 막혀있어 지나갈 수 없는 칸은 '#'으로 표현되고, 비어있는 칸은 '.'으로 표현된다. 
당신의 시작 지점은 'S'로 표현되고, 탈출할 수 있는 출구는 'E'로 표현된다. 각 층 사이에는 빈 줄이 있으며, 입력의 끝은 L, R, C가 모두 0으로 표현된다.

[출력]
각 빌딩에 대해 한 줄씩 답을 출력한다. 만약 당신이 탈출할 수 있다면 다음과 같이 출력한다.

Escaped in x minute(s).
여기서 x는 상범 빌딩을 탈출하는 데에 필요한 최단 시간이다.

만일 탈출이 불가능하다면, 다음과 같이 출력한다.

Trapped!

[풀이]
전형적인 BFS문제였다... 높이만 추가된 경우이므로 쉽게 풀 수 있다. 

*/
import java.io.*;
import java.util.*;

public class Main {

	final static String RUN = "0 0 0";
	static char[][][] building;
	static boolean[][][] visited;
	static int L, R, C;
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, -1, 0, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = null;
		StringTokenizer st = null;
		Node start = null, end = null;
		int ans;
		StringBuilder res = new StringBuilder();
		
		while (!(input = br.readLine()).equals(RUN)) {
			st = new StringTokenizer(input);
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			building = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					input = br.readLine();
					for (int k = 0; k < C; k++) {
						building[i][j][k] = input.charAt(k);
						
						if (building[i][j][k] == 'S')
							start = new Node(i, j, k, 0);
						
						else if (building[i][j][k] == 'E')
							end = new Node(i, j, k, 0);
					}
				}
				br.readLine();
			}
			
			ans = BFS(start, end);
			
			if (ans >= 0)
				res.append("Escaped in ").append(ans).append(" minute(s).\n");
			
			else
				res.append("Trapped!").append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	
	private static int BFS(Node start, Node end) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y][start.z] = true;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.z;
			int cnt = cur.cnt;
			
			if (x == end.x && y == end.y && z == end.z)
				return cnt;
			
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if (nx < 0 || ny < 0 || nz < 0 || nx >= L || ny >= R || nz >= C)
					continue;
				
				if (visited[nx][ny][nz] || building[nx][ny][nz] == '#')
					continue;
				
				visited[nx][ny][nz] = true;
				queue.add(new Node(nx, ny, nz, cnt + 1));
			}
		}
		
		return -1;
	}
	
}

class Node {
	int x, y, z, cnt;

	public Node(int x, int y, int z, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}
}
