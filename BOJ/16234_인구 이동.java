/*
https://www.acmicpc.net/problem/16234
[문제]
N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 
인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.

오늘부터 인구 이동이 시작되는 날이다.

인구 이동은 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.

국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.
각 나라의 인구수가 주어졌을 때, 인구 이동이 몇 번 발생하는지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)

둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (1 ≤ A[r][c] ≤ 100)

인구 이동이 발생하는 횟수가 2,000번 보다 작거나 같은 입력만 주어진다.

[출력]
인구 이동이 몇 번 발생하는지 첫째 줄에 출력한다.

[풀이]
전형적인 완전탐색 (DFS, BFS)였지만 문제 조건을 잘못 이해해서 오래 걸렸던 문제였다.
하루에 연합이 여러개 있더라도 인구 이동은 1번이였다.

BFS를 진행하면서 경로를 스택에 넣는다. BFS가 끝나면 스택에 있는 노드들로 맵을 다시 갱신한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N, L, R, res = 0;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag = true;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			flag = false;
			
			for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;
					
					BFS(i, j);
				}
			}
			
			if (!flag)
				break;
			
			res++;
		}

		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}

	private static boolean BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> path = new Stack<Node>();
		int totalSum = map[r][c], cnt = 1;
		path.add(new Node(r, c));
		queue.add(new Node(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int x = tmp.x;
			int y = tmp.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (visited[nx][ny])
					continue;

				int diff = Math.abs(map[nx][ny] - map[x][y]);

				if (L <= diff && diff <= R) {
					flag = true;
					visited[nx][ny] = true;
					cnt++;
					totalSum += map[nx][ny];
					path.add(new Node(nx, ny));
					queue.add(new Node(nx, ny));
				}
			}
		}
		
		int avr = totalSum / cnt;

		while (!path.isEmpty()) {
			Node tmp = path.pop();
			map[tmp.x][tmp.y] = avr;
		}

		return true;
	}
}

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
