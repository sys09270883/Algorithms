/*
https://www.acmicpc.net/problem/4485
[문제]
젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!

젤다의 전설 시리즈의 주인공, 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다. 
[0][0]번 칸이기도 하다. 왜 이런 곳에 들어왔냐고 묻는다면 밖에서 사람들이 자꾸 "젤다의 전설에 나오는 녹색 애가 젤다지?"라고 물어봤기 때문이다. 
링크가 녹색 옷을 입은 주인공이고 젤다는 그냥 잡혀있는 공주인데, 게임 타이틀에 젤다가 나와있다고 자꾸 사람들이 이렇게 착각하니까 정신병에 걸릴 위기에 놓인 것이다.

하여튼 젤다...아니 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다. 
동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다. 
링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.

링크가 잃을 수밖에 없는 최소 금액은 얼마일까?

[입력]
입력은 여러 개의 테스트 케이스로 이루어져 있다.

각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.

이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 
도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.

[출력]
각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.

[풀이]
가장 적게 잃는 칸만 방문하면서 도착해야 하므로 다익스트라를 이용한다.
우선순위 큐에 방문하지 않은 dist[][]에 대해서 (dist[][] < cost로 확인) 4방향으로 확인해준다.

 + 노드에 대한 우선순위 큐의 계산 방식을 람다식으로 정의하면 메모리, 시간에서 불리하다... (람다식이 외부 이터레이터를 사용하기 때문)
 + 람다식 초기화를 지양하자...
*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] cave;
	static int[][] dist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int idx = 1;
		StringTokenizer st = null;
		StringBuilder res = new StringBuilder();
		
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			init();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res.append("Problem " + idx++ + ": ").append(dijkstra()).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void init() {
		cave = new int[N][N];
		dist = new int[N][N];
		for (int[] row : dist) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
	}
	
	private static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(0, 0, cave[0][0]));
		dist[0][0] = cave[0][0];
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			int cost = cur.cost;

			if (dist[x][y] < cost)
				continue;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if (dist[nx][ny] > dist[x][y] + cave[nx][ny]) {
					dist[nx][ny] = dist[x][y] + cave[nx][ny];
					pq.add(new Node(nx, ny, dist[nx][ny]));
				}
			}
		}
		
		return dist[N - 1][N - 1];
	}
	
}

class Node implements Comparable<Node>{
	
	int x, y, cost;
	
	public Node(int x, int y, int cost) {
		super();
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
	
}