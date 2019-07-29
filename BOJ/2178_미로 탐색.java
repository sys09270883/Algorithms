/*
https://www.acmicpc.net/problem/2178
[문제]
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

[입력]
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

[출력]
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

[풀이]
4방향의 위치상태를 표현하기 위해서
  int[] dx = {-1, 0, 1, 0};
  int[] dy = {0, -1, 0, 1};
변수를 만들어 놓는다.

  1. BFS의 매개변수로 row, column을 받고 Node(row, column)을 만들어 큐에 넣어준다.
  2. 큐에 있는 값들을 하나씩 뽑아서 상, 하, 좌, 우 4가지 방향으로 이동할 수 있는지를 확인하고 이동할 수 있으면 이동하고 1을 더해준다.
  3. 방문표시를 한다.
  4. 큐에 들어갈 값이 없으면 종료를 하고 maze[N-1][M-1]을 출력한다.

+ 노드에 거리변수를 두고 방문 전 노드의 거리변수 + 1로 갱신하면서 가는 것이 직관적인듯 하다.
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
	static int N;
	static int M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0 ,1};
	static boolean[][] visited;
	static int[][] maze;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
		}
		
		visited[0][0] = true;
		BFS(0, 0);
		
		bw.write(String.valueOf(maze[N-1][M-1]));
		bw.close();
		br.close();
	}
	
	public static void BFS(int row, int column) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(row, column));
		
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nX = tmp.row + dx[i];
				int nY = tmp.column + dy[i];
				
				if(nX < 0 || nY < 0 || nX >= N || nY >= M)
					continue;
				
				if(visited[nX][nY] || maze[nX][nY] == 0)
					continue;
				
				queue.add(new Node(nX, nY));
				maze[nX][nY] = maze[tmp.row][tmp.column] + 1;
				visited[nX][nY] = true;
			}
		}
	}
}

class Node {
	int row, column;
	
	public Node(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
