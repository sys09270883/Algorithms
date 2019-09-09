/*
[문제]
상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이가 가지고 있는 평면도에는 문서의 위치가 모두 나타나 있다. 
빌딩의 문은 모두 잠겨있기 때문에, 문을 열려면 열쇠가 필요하다. 상근이는 일부 열쇠를 이미 가지고 있고, 일부 열쇠는 빌딩의 바닥에 놓여져 있다.

상근이가 훔칠 수 있는 문서의 최대 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 수는 100개를 넘지 않는다.

각 테스트 케이스의 첫째 줄에는 지도의 높이와 너비 h와 w (2 ≤ h, w ≤ 100)가 주어진다. 
다음 h개 줄에는 빌딩을 나타내는 w개의 문자가 주어지며, 각 문자는 다음 중 하나이다.

'.'는 빈 공간을 나타낸다.
'*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
'$'는 상근이가 훔쳐야하는 문서이다.
알파벳 대문자는 문을 나타낸다.
알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
마지막 줄에는 상근이가 이미 가지고 있는 열쇠가 공백없이 주어진다. 만약, 열쇠를 하나도 가지고 있지 않는 경우에는 "0"이 주어진다.

상근이는 빌딩 밖으로 나갈 수 있다. 
각각의 문에 대해서, 그 문을 열 수 있는 열쇠의 개수는 0개, 1개, 또는 그 이상이고, 각각의 열쇠에 대해서, 
그 열쇠로 열 수 있는 문의 개수도 0개, 1개, 또는 그 이상이다. 열쇠는 여러 번 사용할 수 있다.

[출력]
각 테스트 케이스 마다, 상근이가 훔칠 수 있는 문서의 최대 개수를 출력한다.

[풀이]
1. 어디서든 접근할 수 있게 하기 위해 맵의 테두리를 '.'로 초기화시킨다.
2. BFS 중에 키가 없어서 열지 못하는 문을 doors에 저장한다.
3. BFS 중에 키를 얻었을 때에는 얻은 키로 열 수 있는 문을 doors를 순회하면서 큐에 넣어준다.
4. 큐가 빌 때까지 이 과정을 반복하고 큐가 비면 cnt를 출력한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int T, h, w;
	static char[][] map;
	static boolean[][] visited;
	static HashSet<Character> keys;
	static HashSet<Node> doors;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		String input = null;
		StringBuilder res = new StringBuilder();

		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			map = new char[h + 2][w + 2];
			for (char[] row : map) {
				Arrays.fill(row, '.');
			}
			doors = new HashSet<Node>();
			visited = new boolean[h + 2][w + 2];
			keys = new HashSet<Character>();
			cnt = 0;

			// input map
			for (int i = 1; i < h + 1; i++) {
				input = br.readLine();
				for (int j = 1; j < w + 1; j++) {
					map[i][j] = input.charAt(j - 1);
				}
			}

			// input key
			if (!(input = br.readLine()).equals("0")) {
				for (int i = 0; i < input.length(); i++) {
					keys.add(input.charAt(i));
				}
			}

			BFS();
			res.append(cnt).append('\n');
		}

		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}

	private static void BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0));
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int y = cur.y;
			int x = cur.x;

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny < 0 || nx < 0 || ny > h + 1 || nx > w + 1)
					continue;

				if (visited[ny][nx] || map[ny][nx] == '*')
					continue;
				
				if ('A' <= map[ny][nx] && map[ny][nx] <= 'Z') {
					boolean flag = false;
					for (char c : keys) {
						if (map[ny][nx] == c - 32) {
							flag = true;
							break;
						}
					}
					
					if (!flag) {
						doors.add(new Node(ny, nx));
						continue;
					}
				}
				
				else if ('a' <= map[ny][nx] && map[ny][nx] <= 'z') {
					keys.add(map[ny][nx]);
					for (Node node : doors) {
						if (map[node.y][node.x] == map[ny][nx] - 32) {
							if (visited[node.y][node.x])
								continue;
							
							visited[node.y][node.x] = true;
							queue.add(new Node(node.y, node.x));
						}
					}
				}
				
				else if (map[ny][nx] == '$')
					cnt++;
				
				visited[ny][nx] = true;
				queue.add(new Node(ny, nx));
			}
		}
	}

}

class Node {
	int y, x;

	public Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}