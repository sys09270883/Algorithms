/*
https://www.acmicpc.net/problem/1194
[문제]
지금 민식이가 계획한 여행은 달이 맨 처음 뜨기 시작할 때 부터, 준비했던 여행길이다. 
하지만, 매번 달이 차오를 때마다 민식이는 어쩔 수 없는 현실의 벽 앞에서 다짐을 포기하고 말았다.

민식이는 매번 자신의 다짐을 말하려고 노력했지만, 말을 하면 아무도 못 알아들을 것만 같아서, 지레 겁먹고 벙어리가 되어버렸다.
결국 민식이는 모두 잠든 새벽 네시 반 홀로 일어나, 창 밖에 떠있는 달을 보았다.

하루밖에 남지 않았다. 달은 내일이면 다 차오른다. 이번이 마지막기회다. 이걸 놓치면 영영 못간다.

영식이는 민식이가 오늘도 여태것처럼 그냥 잠 들어버려서 못 갈지도 모른다고 생각했다. 하지만 그러기엔 민식이의 눈에는 저기 뜬 달이 너무나 떨렸다.

민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다. 미로는 다음과 같이 구성되어져있다.

빈 곳 : 언제나 이동할 수 있다. ('.‘로 표시됨)
벽 : 절대 이동할 수 없다. (‘#’)
열쇠 : 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. (a - f)
문 : 대응하는 열쇠가 있을 때만 이동할 수 있다. (A - F)
민식이의 현재 위치 : 빈 곳이고, 민식이가 현재 서 있는 곳이다. (숫자 0)
출구 : 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. (숫자 1)
달이 차오르는 기회를 놓치지 않기 위해서, 미로를 탈출하려고 한다. 한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.

민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다. 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다. 그리고, 영식이가 열쇠를 숨겨놓는 다면 문에 대응하는 열쇠가 없을 수도 있다. 0은 한 개, 1은 적어도 한 개 있다. 그리고, 열쇠는 여러 번 사용할 수 있다.

[출력]
첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.

[풀이]
1. key값의 존재유무를 visited 배열에 비트마스킹으로 표현한다.
2. 열쇠를 주으면 | 연산으로 key를 갱신하고 큐에 넣는다.
3. 문에 도달하면 열쇠의 & 연산으로 열쇠를 가지고 있는지 검사한다.
4. 도착하면 움직인 거리를 반환하고, 도착하지 못하면 -1을 반환한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][1 << 6];
		Node start = null;
		String str = null;

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '0') {
					start = new Node(i, j, 0, 0);
				}
			}
		}

		bw.write(String.valueOf(BFS(start)));
		bw.close();
		br.close();
	}

	private static int BFS(Node start) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y][0] = true;

		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			int key = cur.key;

			if (map[x][y] == '1')
				return cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nkey = key;

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '#' || visited[nx][ny][key])
					continue;

				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f')
					nkey |= (1 << map[nx][ny] - 'a');

				if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
					if((nkey & (1 << (map[nx][ny] - 'A'))) == 0)
						continue;
				}

				visited[nx][ny][nkey] = true;
				queue.add(new Node(nx, ny, cnt + 1, nkey));
			}
		}
		return -1;
	}
}

class Node {
	int x, y, cnt, key;

	Node(int x, int y, int cnt, int key) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.key = key;
	}
}