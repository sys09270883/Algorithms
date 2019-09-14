/*
https://www.acmicpc.net/problem/4485
[����]
������ ���� ���ӿ��� ȭ���� ������ ����(rupee)��. �׷��� ��Ȥ '���Ϸ���'�� �Ҹ��� ������ ���ǵ� �����ϴµ�, �̰� ȹ���ϸ� ������ ������ ���ǰ� �����ϰ� �ȴ�!

������ ���� �ø����� ���ΰ�, ��ũ�� ���� ���Ϸ��Ǹ� ������ N x N ũ���� ������ ���� ���� ���� �ִ�. 
[0][0]�� ĭ�̱⵵ �ϴ�. �� �̷� ���� ���Գİ� ���´ٸ� �ۿ��� ������� �ڲ� "������ ������ ������ ��� �ְ� ������?"��� ����ñ� �����̴�. 
��ũ�� ��� ���� ���� ���ΰ��̰� ���ٴ� �׳� �����ִ� �����ε�, ���� Ÿ��Ʋ�� ���ٰ� �����ִٰ� �ڲ� ������� �̷��� �����ϴϱ� ���ź��� �ɸ� ���⿡ ���� ���̴�.

�Ͽ�ư ����...�ƴ� ��ũ�� �� ������ �ݴ��� �ⱸ, ���� ������ �Ʒ� ĭ�� [N-1][N-1]���� �̵��ؾ� �Ѵ�. 
������ �� ĭ���� ���Ϸ��ǰ� �ִµ�, �� ĭ�� ������ �ش� ���Ϸ����� ũ�⸸ŭ �������� �Ұ� �ȴ�. 
��ũ�� �Ҵ� �ݾ��� �ּҷ� �Ͽ� ���� �ǳ������ �̵��ؾ� �ϸ�, �� ���� �����¿� ������ ������ 1ĭ�� �̵��� �� �ִ�.

��ũ�� ���� ���ۿ� ���� �ּ� �ݾ��� ���ϱ�?

[�Է�]
�Է��� ���� ���� �׽�Ʈ ���̽��� �̷���� �ִ�.

�� �׽�Ʈ ���̽��� ù° �ٿ��� ������ ũ�⸦ ��Ÿ���� ���� N�� �־�����. (2 �� N �� 125) N = 0�� �Է��� �־����� ��ü �Է��� ����ȴ�.

�̾ N���� �ٿ� ���� ������ �� ĭ�� �ִ� ���Ϸ����� ũ�Ⱑ �������� ���еǾ� ���ʴ�� �־�����. 
���Ϸ����� ũ�Ⱑ k�� �� ĭ�� ������ k���Ǹ� �Ҵ´ٴ� ���̴�. ���⼭ �־����� ��� ������ 0 �̻� 9 ������ �� �ڸ� ����.

[���]
�� �׽�Ʈ ���̽����� �� �ٿ� ���� ������ ���Ŀ� ���缭 ����Ѵ�. ������ ���� ����� �����Ͻÿ�.

[Ǯ��]
���� ���� �Ҵ� ĭ�� �湮�ϸ鼭 �����ؾ� �ϹǷ� ���ͽ�Ʈ�� �̿��Ѵ�.
�켱���� ť�� �湮���� ���� dist[][]�� ���ؼ� (dist[][] < cost�� Ȯ��) 4�������� Ȯ�����ش�.

 + ��忡 ���� �켱���� ť�� ��� ����� ���ٽ����� �����ϸ� �޸�, �ð����� �Ҹ��ϴ�... (���ٽ��� �ܺ� ���ͷ����͸� ����ϱ� ����)
 + ���ٽ� �ʱ�ȭ�� ��������...
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