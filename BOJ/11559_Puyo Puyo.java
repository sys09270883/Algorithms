/*
https://www.acmicpc.net/problem/11559
[����]
�ѿ�ѿ��� ���� ������ ����.

�ʵ忡 ���� ���� ������ �ѿ並 ���´�. �ѿ�� �߷��� ������ �޾� �Ʒ��� �ٴ��̳� �ٸ� �ѿ䰡 ���� ������ �Ʒ��� ��������.

�ѿ並 ���� �� ��, ���� �� �ѿ䰡 4�� �̻� �����¿�� ����Ǿ� ������ ����� ���� �� �ѿ���� �Ѳ����� ��������.

�ѿ���� �������� ���� ���� �ٸ� �ѿ���� �ִٸ�, ���� �߷��� ������ �޾� ���ʴ�� �Ʒ��� �������� �ȴ�.

�Ʒ��� �������� ���� �ٽ� ���� ���� �ѿ���� 4�� �̻� ���̰� �Ǹ� �� ������ �Ǵµ�, ���� �� �ѿ���� �������� �ٽ� ������ �ݺ��� ������ 1���⾿ �þ��.

���� �� �ִ� �ѿ䰡 ���� �׷��� �ִٸ� ���ÿ� ������ �ϰ� ���� �׷��� �������� �ѹ��� ���Ⱑ �߰��ȴ�.

���Դ� �ֱ� �ѿ�ѿ� ���ӿ� ǫ ������. 
�� ������ 1:1�� �ٴ� ���������̶� �� �״� �͵� �߿�������, ������ �Ͷ߸��ٸ� ���Ⱑ �� ���� ���� �ٷ� �ľ��� �� �ִ� �ɷµ� �ʿ��ϴ�. 
������ ���� �Ƿ��� �����Ͽ� ���Դ� �ڱ� �ʵ忡�� �Ű� ���� �ٻڴ�. ������ �ʵ尡 �־����� ��, ���Ⱑ �� �� �������� �Ͼ�� ����Ͽ� ���Ը� ��������!

[�Է�]
12*6�� ���ڰ� �־�����.

�̶� .�� ������̰� .�� �ƴѰ��� ������ ������ �ѿ並 ��Ÿ����.

R�� ����, G�� �ʷ�, B�� �Ķ�, P�� ����, Y�� ����̴�.(��� �빮�ڷ� �־�����.)

�Է����� �־����� �ʵ�� �ѿ���� ���� �Ʒ��� ������ ���� ����(�� �ѿ� �Ʒ��� �� ĭ�� �ִ� ���� ����) �̴�.

[���]
���� �־��� ��Ȳ���� ��Ⱑ �Ǵ��� ����϶�. (�ϳ��� ������ �ʴ´ٸ� 0�� ����ϸ� �ȴ�.)

[Ǯ��]
DFS, BFS�� �׷����� Ž���ϸ鼭 4ĭ �̻� �پ��ִ� ��� '.'���� �ٲ��ְ� ������Ʈ�Ѵ�.
������Ʈ �� ������ �κ��� Ž���ϸ�, �� �̻� Ž���� �� ���� ��� Ż���Ѵ�.

 + ������ ���� �ʾҰ�, ������ �������ǿ� ���� �����ؼ� ���� ��̴�... ���� Ȯ�� �� ����..

*/
import java.io.*;
import java.util.*;

public class Main {

	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static ArrayList<Node> list;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new char[12][6];
		visited = new boolean[12][6];
		String input = null;
		list = new ArrayList<Node>();
		int res = 0;
		
		for (int i = 0; i < 12; i++) {
			input = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		while (true) {
			boolean flag = false;
			
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (!visited[i][j] && map[i][j] != '.') {
						DFS(i, j, map[i][j]);
						
						if (list.size() >= 4) {
							flag = true;
							for (Node n : list) {
								map[n.x][n.y] = '.';
							}
						}
						
						list.clear();
					}
				}
			}
			
			if (!flag)
				break;
			
			res++;
			update();
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static void DFS(int x, int y, char ch) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6)
				continue;
			
			if (visited[nx][ny] || map[nx][ny] != ch)
				continue;
			
			visited[nx][ny] = true;
			list.add(new Node(nx, ny));
			DFS(nx, ny, ch);
		}
	}
	
	private static void update() {
		for (int i = 0; i < 6; i++) {
			for (int j = 10; j >= 0; j--) {
				for (int k = 11; k > j; k--) {
					if (map[j][i] != '.' && map[k][i] == '.') {
						map[k][i] = map[j][i];
						map[j][i] = '.';
						break;
					}
				}
			}
		}
		
		for (boolean[] row : visited) {
			Arrays.fill(row, false);
		}
	}
	
}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}