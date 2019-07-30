/*
https://www.acmicpc.net/problem/2667
[문제]
<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.



[입력]
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

[출력]
첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

[풀이]
BFS로 단지를 돌면서 1일 경우 0으로 바꿔주고 카운팅을 한다.
PQ에 cnt를 넣어주면서 맵을 순회한다.
출력은 PQ의 크기, PQ가 빌 때까지 PQ.poll()을 한다.
단지의 개수가 1일 경우에 예외처리 해주어야 한다.

ex) 입력이

5
01001
00011
10011
11000
11111

일 때, 단지의 개수가 1일 경우(map[0][1]), cnt가 0이므로 PQ에 0이 들어간다.
따라서 출력할 때 cnt가 0일 경우 1로 바꾸어 출력해야 한다.
    int tmp = pq.poll();
    sb.append(tmp == 0 ? 1 : tmp).append("\n");
    
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int nHouse = 0;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = tmp.charAt(j) - '0';
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1)
					BFS(i, j);
			}
		}
		
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) {
			int tmp = pq.poll();
			sb.append(tmp == 0 ? 1 : tmp).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static void BFS(int row, int column) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(row, column));
		
		int cnt = 0;
		while(!queue.isEmpty()) {
			Node tmp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = tmp.row + dx[i];
				int ny = tmp.column + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if(map[nx][ny] == 0)
					continue;
				
				cnt++;
				map[nx][ny] = 0;
				queue.add(new Node(nx, ny));
			}
		} 
		pq.add(cnt);
	}
}

class Node {
	int row, column;

	Node(int row, int column) {
		this.row = row;
		this.column = column;
	}
}
