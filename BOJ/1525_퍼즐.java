/*
https://www.acmicpc.net/problem/1525
[����]
3*3 ǥ�� ������ ���� ���� ä���� �ִ�. ������ �Ʒ� ���� �� ĭ�� ��� �ִ� ĭ�̴�.

1	2	3
4	5	6
7	8	 
� ���� ������ �ִ� �� ���� ĭ �߿� �ϳ��� ��� ������, ���� �� ĭ���� �̵���ų ���� �ִ�.
���� ǥ �ٱ����� ������ ���� �Ұ����ϴ�. �츮�� ��ǥ�� �ʱ� ���°� �־����� ��, �ּ��� �̵����� ���� ���� ������ ���¸� ����� ���̴�. ������ ���� ����.

1	 	3
4	2	5
7	8	6
1	2	3
4	 	5
7	8	6
1	2	3
4	5	 
7	8	6
1	2	3
4	5	6
7	8	 
���� �� ���¿��� �� ���� �̵��� ���� ������ ���¸� ���� �� �ִ�. �̿� ���� �ּ� �̵� Ƚ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
�� �ٿ� ���ļ� ǥ�� ä���� �ִ� ��ȩ ���� ���� �־�����. �� �ٿ� �� ���� ���� �־�����, �� ĭ�� 0���� ��Ÿ����.

[���]
ù° �ٿ� �ּ��� �̵� Ƚ���� ����Ѵ�. �̵��� �Ұ����� ��� -1�� ����Ѵ�.

[Ǯ��]
�Է��� �� ���� ���ڿ��� �ް� �ߺ� �湮ǥ�ø� HashSet���� �ߴ�.
������ ������ ���ڿ��� "123456780"�� ����̴�.

'0'�� ��ġ�� x, y��ǥ�� �� �� �ְ� �� ��ǥ�� �������� 4���� BFS�� �Ѵ�.
�湮�� ���� �ʾ����� HashSet�� �湮�� ǥ���ϰ� ť�� ���� �ִ´�. ������ �������� Ż������ ���ϸ� -1�� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static String END = "123456780";
	static StringBuilder START;
	static Set<Integer> visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = null;
		START = new StringBuilder();
		visited = new HashSet<>();
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				START.append(st.nextToken());
			}
		}
		
		bw.write(String.valueOf(BFS()));
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(START, 0));
		visited.add(Integer.parseInt(START.toString()));
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			StringBuilder cur = tmp.num;
			int cnt = tmp.cnt;
			
			if (cur.toString().equals(END))
				return cnt;
			
			int zero = cur.indexOf("0");
			int x = zero / 3;
			int y = zero % 3;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3)
					continue;
				
				StringBuilder next = new StringBuilder(cur.toString());
				swap(next, x * 3 + y, nx * 3 + ny);
				int nextInt = Integer.parseInt(next.toString());
				
				if (!visited.contains(nextInt)) {
					visited.add(nextInt);
					queue.add(new Node(next, cnt + 1));
				}
			}
		}
		
		return -1;
	}
	
	private static void swap(StringBuilder s, int idx1, int idx2) {
		char tmp = s.charAt(idx1);
		s.setCharAt(idx1, s.charAt(idx2));
		s.setCharAt(idx2, tmp);
	}
	
}

class Node {
	StringBuilder num;
	int cnt;
	
	public Node(StringBuilder num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
	
}