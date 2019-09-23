/*
https://www.acmicpc.net/problem/1600
[����]
���������� �� Ż���� ������ �� ������ ���󱸰��� �ϰ� �ִ�. �� �༮�� ��(Horse)�� �Ǳ⸦ ������ ���ߴ�. 
�׷��� �״� ���� �������� ������ ���캸�� �״�� ���� �ϱ�� �Ͽ���. ���� ���̴�. ���� �����ǿ��� ü���� ����Ʈ�� ���� �̵������ ������. 
���� �׸��� ���� �̵������ ��Ÿ���ִ�. xǥ���� ������ ���� �� �� �ִٴ� ���̴�. ����� ���� ��ֹ��� �پ���� �� �ִ�.

 	x	 	x	 
x	 	 	 	x
 	 	��	 	 
x	 	 	 	x
 	x	 	x	 
�ٵ� �����̴� �� ���� �����ϰ� �ִ� ���� �ִ�. 
���� ������ ������ �� ������ �����̴� �ɷ��� �����ؼ� �� K���� ���� ���� ������ �� �ְ�, �� �ܿ��� �׳� ������ ĭ���θ� ������ �� �ִ�. 
�밢�� ������ ������ ĭ�� ���Ե��� �ʴ´�.

���� �����̴� �ӳ��� ������� ������. �������� �� ���� ������ �����ؼ� �� ������ �Ʒ����� �����Ѵ�. 
������ �� �������� �� �� �����̴� ��, ���� ���������� �� �� �����̴� ��, ��� �� ���� �������� ģ��. 
�������� �־����� ��, �����̰� �ּ����� �������� ������������ ������������ �� �� �ִ� ����� �˾Ƴ��� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �ڿ��� K�� �־�����. ��° �ٿ� �������� ���α��� W, ���α��� H�� �־�����. �� ���� H�ٿ� ���� W���� ���ڰ� �־����µ�, 0�� �ƹ��͵� ���� ����, 1�� ��ֹ��� ���Ѵ�. ��ֹ��� �ִ� �����δ� �̵��� �� ����. �������� �������� �׻� �����̴�. W�� H�� 1�̻� 200������ �ڿ����̰�, K�� 0�̻� 30������ �����̴�.

[���]
ù° �ٿ� �������� ���ۼ��� �ּڰ��� ����Ѵ�. ���������� ���������� �� �� ���� ��쿣 -1�� ����Ѵ�.

[Ǯ��]
BFS�� �����ϸ鼭 ���� �����̴� ��츦 ī�����Ѵ�. ���� ������ �湮�� �� ���� �����̴� ���(i > 3), Ƚ���� K�� ���� �����Ѵ�.
Ƚ���� ������Ű�鼭 �湮ǥ�ÿ� ť�� ����־��ش�.

 + �ߺ��ؼ� ť�� �־��ִ� ��츦 ������־�� �Ѵ�.
 
ex)
1
4 4
0 0 1 1
0 1 1 1
1 1 0 0
1 1 0 0
�� ���� �Է��� ���� ��, (2, 2) �������� ���� ���ؼ��� (0, 1), (1, 0)���� �̵� �� ���� ���������� �̵��ؾ� �ϴµ�,
�� ��� ���� ��Ȳ�� ���ؼ� ť�� 2�� �־��ְ� �ǹǷ� �湮 ǥ�ø� �߰������� �ؾ� �Ѵ�.

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
					
					if (visited[nx][ny][++nhorseCnt])
						continue;
				}

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