/*
https://www.acmicpc.net/problem/16954
[����]
������ �б� ������ ũ�Ⱑ 8��8�� ü���ǿ��� Ż���ϴ� ������ �������. ü������ ��� ĭ�� �� ĭ �Ǵ� �� �� �ϳ��̴�. 
������ ĳ���ʹ� ���� ���� �Ʒ� ĭ�� �ְ�, �� ĳ���ʹ� ���� ������ �� ĭ���� �̵��ؾ� �Ѵ�.

�� ������ Ư¡�� ���� �����δٴ� ���̴�. 1�ʸ��� ��� ���� �Ʒ��� �ִ� ������ �� ĭ�� ��������, ���� �Ʒ��� �־ �Ʒ��� ���� ���ٸ� ���� ������� �ȴ�. 
������ ĳ���ʹ� 1�ʿ� ������ �� ĭ �Ǵ� �밢�� �������� ������ �� ĭ���� �̵��ϰų�, ���� ��ġ�� �� ���� �� �ִ�. �̵��� ���� �� ĭ���θ� �̵��� �� �ִ�.

1�� ���� ������ ĳ���Ͱ� ���� �̵��ϰ�, �� ���� ���� �̵��Ѵ�. ���� ĳ���Ͱ� �ִ� ĭ���� �̵��ϸ� �� �̻� ĳ���ʹ� �̵��� �� ����.

������ ĳ���Ͱ� ���� ������ �� ĭ���� �̵��� �� �ִ��� ������ ���غ���.

[�Է�]
8�� �ٿ� ���ļ� ü������ ���°� �־�����. '.'�� �� ĭ, '#'�� ���̴�. ���� ���� �Ʒ�ĭ�� �׻� ���� �ƴϴ�.

[���]
������ ĳ���Ͱ� ���� ������ �� ĭ�� ������ �� ������ 1, ������ 0�� ����Ѵ�.

[Ǯ��]
ó���� �������� ü���� �ϳ��� ó���� �Ϸ��� ���� ť�� ���鼭 �� �ܰ��� ü������ ���;� �ϴµ� �ڼ������ȴ�.
�׷��� ���ʿ� �ð� �� ���� ��ġ�� �����ϴ� 3���� wall�迭�� �������.
ó�� �Է¹��� �� ���� ��ġ�� wallList�� ���, wallList�� �ִ� ������ x��ǥ�� �ð�(t)�� ������Ű�鼭 3���� �迭�� �����Ѵ�.

������ ������ �ش� ���� ���� ���ų�, �ð��� 8�ʰ� �����ų�, x == 0�� ���̴�.
9�������� BFS�� �ϴµ� ���� ĭ�� ���� �ƴϿ��� 1�� �Ŀ� ���̸� �̵��� �ϸ� �ȵǹǷ�, t�� ���� ��ǥ�� t + 1�϶��� ��ǥ�� ���� �ƴҰ�쿡�� ť�� �־��ش�.
������ ���ǿ� �ش����� ���� ��� 0�� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static char[][] chess;
	static char[][][] wall;
	static ArrayList<Node> wallList;
	static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
	static int[] dy = {1, 0, -1, 1, 0, -1, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		chess = new char[8][8];
		wall = new char[8][8][8];
		wallList = new ArrayList<Node>();
		String input = null;
		
		for (int i = 0; i < 8; i++) {
			input = br.readLine();
			for (int j = 0; j < 8; j++) {
				chess[i][j] = input.charAt(j);
				
				if (chess[i][j] == '#') {
					wall[0][i][j] = '#';
					wallList.add(new Node(i, j, 0));
				}
			}
		}
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					if (wall[i][j][k] == '#')
						continue;
					
					wall[i][j][k] = '.';
				}
			}
		}
		
		makeWall();
		
		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}
	
	private static void makeWall() {
		for (int i = 0; i < wallList.size(); i++) {
			int x = wallList.get(i).x;
			int y = wallList.get(i).y;
			int t = 1;
			
			while (true) {
				int nx = x + 1;
				int ny = y;
				
				if (nx >= 8)
					break;
				
				wall[t++][nx][ny] = '#';
				x = nx;
				y = ny;
			}
		}
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(7, 0, 0));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			
			if (end(x, cnt))
				return 1;
			
			for (int i = 0; i < 9; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int ncnt = cnt + 1;
				
				if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
					continue;
				
				if (wall[ncnt][nx][ny] == '#' || wall[cnt][nx][ny] == '#')
					continue;
				
				queue.add(new Node(nx, ny, ncnt));
			}
		}
		
		return 0;
	}
	
	private static boolean end(int x, int cnt) {
		if (x == 0 || cnt >= 8)
			return true;
		
		for (int i = x - 1; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (wall[cnt][i][j] == '#')
					return false;
			}
		}

		return true;
	}
}

class Node {
	int x, y, cnt;

	public Node(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}

}