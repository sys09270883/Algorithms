/*
https://www.acmicpc.net/problem/2468
[����]
�糭����û������ ���� �� ������ �帶ö�� ����ؼ� ������ ���� ���� ��ȹ�ϰ� �ִ�. ���� � ������ ���� ������ �ľ��Ѵ�. 
�� ������ �� ������ ���� �� ������ �� ���� ����� �ʴ� ������ ������ �ִ�� �� ���� ����� ���� ���� �����Ϸ��� �Ѵ�. 
�̶�, ������ �����ϰ� �ϱ� ���Ͽ�, �帶ö�� ������ ���� �翡 ���� ������ ���� ������ ��� ������ ���� ���ٰ� �����Ѵ�.

� ������ ���� ������ ��� ���� ũ�Ⱑ ���� N�� 2���� �迭 ���·� �־����� �迭�� �� ���Ҵ� �ش� ������ ���̸� ǥ���ϴ� �ڿ����̴�. 
���� ���, ������ N=5�� ������ ���� �����̴�.



���� ���� ���� ������ ���� �� ������ ���̰� 4 ������ ��� ������ ���� ���ٰ� ����. �� ��쿡 ���� ���� ������ ȸ������ ǥ���ϸ� ������ ����. 



���� ����� �ʴ� ������ �����̶� ���� ���� ����� �ʴ� �������� ��, �Ʒ�, ������ Ȥ�� �������� ������ ������ �� ũ�Ⱑ �ִ��� ������ ���Ѵ�. 
���� ��쿡�� ���� ����� �ʴ� ������ ������ 5���� �ȴ�(���������θ� �پ� �ִ� �� ������ �������� �ʴ´ٰ� ����Ѵ�). 

���� ���� ���� �������� ���̰� 6������ ������ ��� ���� ����� ���� �� ������ ���� ����� �ʴ� ������ ������ �Ʒ� �׸������� ���� �� ���� ���� Ȯ���� �� �ִ�. 



�̿� ���� �帶ö�� ������ ���� �翡 ���� ���� ����� �ʴ� ������ ������ ������ �ٸ��� �ȴ�.
���� ���� ���� �������� ������ ���� �翡 ���� ��� ��츦 �� ������ ���� ���� ����� �ʴ� ������ ������ ���� �߿��� �ִ��� ���� 5���� �� �� �ִ�. 

� ������ ���� ������ �־����� ��, �帶ö�� ���� ����� �ʴ� ������ ������ �ִ� ������ ����ϴ� ���α׷��� �ۼ��Ͻÿ�. 

[�Է�]
ù° �ٿ��� � ������ ��Ÿ���� 2���� �迭�� ��� ���� ������ ��Ÿ���� �� N�� �Էµȴ�. N�� 2 �̻� 100 ������ �����̴�. 
��° �ٺ��� N���� �� �ٿ��� 2���� �迭�� ù ��° ����� N��° ����� ������� �� �྿ ���� ������ �Էµȴ�. 
�� �ٿ��� �� ���� ù ��° ������ N��° ������ N���� ���� ������ ��Ÿ���� �ڿ����� �� ĭ�� ���̿� �ΰ� �Էµȴ�. ���̴� 1�̻� 100 ������ �����̴�.

[���]
ù° �ٿ� �帶ö�� ���� ����� �ʴ� ������ ������ �ִ� ������ ����Ѵ�.

[Ǯ��]
���� ���� ���� ���ں��� ū ���ڱ��� ������Ű�鼭 �� ���ڸ� �������� BFS�� �����Ѵ�.
BFS�� �ϰ� ������ ������ �ִ�켱����ť�� �־��ְ� �ִ� ���� Ȯ���Ѵ�.

�Է���
5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
ó�� �켱����ť�� �� ���� ���� ��쿡�� 1�� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] map;
	static boolean[][] visited;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st = null;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, cnt = 0;
        PriorityQueue<Integer> res = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
        
        for (int i = min; i <= max; i++) {
        	cnt = 0;
        	for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] > i && !visited[j][k]) {
						BFS(i, j, k);
						cnt++;
						res.add(cnt);
					}
				}
			}
        	
        	for (boolean[] row : visited) {
				Arrays.fill(row, false);
			}
		}
        
        bw.write(res.peek() == null ? "1" : String.valueOf(res.peek()));
        bw.close();
        br.close();
    }
    
    private static void BFS(int height, int r, int c) {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(r, c));
    	visited[r][c] = true;
    	
    	while(!queue.isEmpty()) {
    		Node cur = queue.poll();
    		int x = cur.x;
    		int y = cur.y;
    		
    		for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				
				if (visited[nx][ny])
					continue;
				
				if (map[nx][ny] <= height)
					continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny));
			}
    	}
    }
}

class Node {
	int x, y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
