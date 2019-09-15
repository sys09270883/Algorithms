/*
https://www.acmicpc.net/problem/6593
[����]
����� ��� ������ ������ ���Ҵ�. ���⼭ Ż���ϴ� ���� ���� ���� �����ϱ�? ��� ������ �� ���� ���̰� 1�� ������ü(���� ������ü)�� �̷�����ִ�. 
�� ������ü�� ������ �̷���� �־� ������ �� ���ų�, ����־ ������ �� �ְ� �Ǿ��ִ�. 
����� �� ĭ���� ������ 6���� ĭ(��,��,��,��,��,��)���� 1���� �ð��� �鿩 �̵��� �� �ִ�. 
��, �밢������ �̵��ϴ� ���� �Ұ����ϴ�. �׸��� ��� ������ �ٱ��鵵 ��� ������ �����־� �ⱸ�� ���ؼ��� Ż���� �� �ִ�.

����� ��� ������ Ż���� �� ������? ���� �׷��ٸ� �󸶳� �ɸ���?

[�Է�]
�Է��� ���� ���� �׽�Ʈ ���̽��� �̷������, �� �׽�Ʈ ���̽��� �� ���� ���� L, R, C�� �����Ѵ�. L(1 �� L �� 30)�� ��� ������ �� ���̴�.
R(1 �� R �� 30)�� C(1 �� C �� 30)�� ��� ������ �� ���� ��� ���� ������ ��Ÿ����.

�� ���� �� ���� C���� ���ڷ� �̷���� R���� ���� L�� �־�����. �� ���ڴ� ��� ������ �� ĭ�� ��Ÿ����. 
������ �����־� ������ �� ���� ĭ�� '#'���� ǥ���ǰ�, ����ִ� ĭ�� '.'���� ǥ���ȴ�. 
����� ���� ������ 'S'�� ǥ���ǰ�, Ż���� �� �ִ� �ⱸ�� 'E'�� ǥ���ȴ�. �� �� ���̿��� �� ���� ������, �Է��� ���� L, R, C�� ��� 0���� ǥ���ȴ�.

[���]
�� ������ ���� �� �پ� ���� ����Ѵ�. ���� ����� Ż���� �� �ִٸ� ������ ���� ����Ѵ�.

Escaped in x minute(s).
���⼭ x�� ��� ������ Ż���ϴ� ���� �ʿ��� �ִ� �ð��̴�.

���� Ż���� �Ұ����ϴٸ�, ������ ���� ����Ѵ�.

Trapped!

[Ǯ��]
�������� BFS��������... ���̸� �߰��� ����̹Ƿ� ���� Ǯ �� �ִ�. 

*/
import java.io.*;
import java.util.*;

public class Main {

	final static String RUN = "0 0 0";
	static char[][][] building;
	static boolean[][][] visited;
	static int L, R, C;
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, -1, 0, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = null;
		StringTokenizer st = null;
		Node start = null, end = null;
		int ans;
		StringBuilder res = new StringBuilder();
		
		while (!(input = br.readLine()).equals(RUN)) {
			st = new StringTokenizer(input);
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			building = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					input = br.readLine();
					for (int k = 0; k < C; k++) {
						building[i][j][k] = input.charAt(k);
						
						if (building[i][j][k] == 'S')
							start = new Node(i, j, k, 0);
						
						else if (building[i][j][k] == 'E')
							end = new Node(i, j, k, 0);
					}
				}
				br.readLine();
			}
			
			ans = BFS(start, end);
			
			if (ans >= 0)
				res.append("Escaped in ").append(ans).append(" minute(s).\n");
			
			else
				res.append("Trapped!").append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	
	private static int BFS(Node start, Node end) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(start);
		visited[start.x][start.y][start.z] = true;
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int z = cur.z;
			int cnt = cur.cnt;
			
			if (x == end.x && y == end.y && z == end.z)
				return cnt;
			
			for (int i = 0; i < 6; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nz = z + dz[i];
				
				if (nx < 0 || ny < 0 || nz < 0 || nx >= L || ny >= R || nz >= C)
					continue;
				
				if (visited[nx][ny][nz] || building[nx][ny][nz] == '#')
					continue;
				
				visited[nx][ny][nz] = true;
				queue.add(new Node(nx, ny, nz, cnt + 1));
			}
		}
		
		return -1;
	}
	
}

class Node {
	int x, y, z, cnt;

	public Node(int x, int y, int z, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.cnt = cnt;
	}
}
