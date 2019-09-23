/*
https://www.acmicpc.net/problem/1600
[문제]
동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그 녀석은 말(Horse)이 되기를 간절히 원했다. 
그래서 그는 말의 움직임을 유심히 살펴보고 그대로 따라 하기로 하였다. 말은 말이다. 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다. 
다음 그림에 말의 이동방법이 나타나있다. x표시한 곳으로 말이 갈 수 있다는 뜻이다. 참고로 말은 장애물을 뛰어넘을 수 있다.

 	x	 	x	 
x	 	 	 	x
 	 	말	 	 
x	 	 	 	x
 	x	 	x	 
근데 원숭이는 한 가지 착각하고 있는 것이 있다. 
말은 저렇게 움직일 수 있지만 원숭이는 능력이 부족해서 총 K번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 
대각선 방향은 인접한 칸에 포함되지 않는다.

이제 원숭이는 머나먼 여행길을 떠난다. 격자판의 맨 왼쪽 위에서 시작해서 맨 오른쪽 아래까지 가야한다. 
인접한 네 방향으로 한 번 움직이는 것, 말의 움직임으로 한 번 움직이는 것, 모두 한 번의 동작으로 친다. 
격자판이 주어졌을 때, 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 방법을 알아내는 프로그램을 작성하시오.

[입력]
첫째 줄에 자연수 K가 주어진다. 둘째 줄에 격자판의 가로길이 W, 세로길이 H가 주어진다. 그 다음 H줄에 걸쳐 W개의 숫자가 주어지는데, 0은 아무것도 없는 평지, 1은 장애물을 뜻한다. 장애물이 있는 곳으로는 이동할 수 없다. 시작점과 도착점은 항상 평지이다. W와 H는 1이상 200이하의 자연수이고, K는 0이상 30이하의 정수이다.

[출력]
첫째 줄에 원숭이의 동작수의 최솟값을 출력한다. 시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.

[풀이]
BFS를 진행하면서 말로 움직이는 경우를 카운팅한다. 다음 지역을 방문할 때 말로 움직이는 경우(i > 3), 횟수가 K인 경우는 제외한다.
횟수를 증가시키면서 방문표시와 큐에 집어넣어준다.

 + 중복해서 큐에 넣어주는 경우를 고려해주어야 한다.
 
ex)
1
4 4
0 0 1 1
0 1 1 1
1 1 0 0
1 1 0 0
과 같은 입력이 있을 때, (2, 2) 지점으로 가기 위해서는 (0, 1), (1, 0)으로 이동 후 말의 움직임으로 이동해야 하는데,
이 경우 같은 상황에 대해서 큐에 2번 넣어주게 되므로 방문 표시를 추가적으로 해야 한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0, -2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {0, -1, 0, 1, 1, -1, 2, -2, 2, -2, 1, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}

	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;
			int horseCnt = tmp.horseCnt;
			int cnt = tmp.cnt;

			if (x == H - 1 && y == W - 1)
				return cnt;

			for (int i = 0; i < 12; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nhorseCnt = horseCnt;

				if (nx < 0 || ny < 0 || nx >= H || ny >= W)
					continue;

				if (visited[nx][ny][nhorseCnt] || map[nx][ny] == 1)
					continue;

				if (i > 3) {
					if (nhorseCnt == K)
						continue;
					
					nhorseCnt++;
				}
				
				if (visited[nx][ny][nhorseCnt])
					continue;

				visited[nx][ny][nhorseCnt] = true;
				queue.add(new Node(nx, ny, nhorseCnt, cnt + 1));
			}
		}

		return -1;
	}

}

class Node {
	int x, y, horseCnt, cnt;

	public Node(int x, int y, int horseCnt, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.horseCnt = horseCnt;
		this.cnt = cnt;
	}

}