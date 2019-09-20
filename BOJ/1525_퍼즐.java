/*
https://www.acmicpc.net/problem/1525
[문제]
3*3 표에 다음과 같이 수가 채워져 있다. 오른쪽 아래 가장 끝 칸은 비어 있는 칸이다.

1	2	3
4	5	6
7	8	 
어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어 있으면, 수를 그 칸으로 이동시킬 수가 있다.
물론 표 바깥으로 나가는 경우는 불가능하다. 우리의 목표는 초기 상태가 주어졌을 때, 최소의 이동으로 위와 같은 정리된 상태를 만드는 것이다. 다음의 예를 보자.

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
가장 윗 상태에서 세 번의 이동을 통해 정리된 상태를 만들 수 있다. 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.

[입력]
세 줄에 걸쳐서 표에 채워져 있는 아홉 개의 수가 주어진다. 한 줄에 세 개의 수가 주어지며, 빈 칸은 0으로 나타낸다.

[출력]
첫째 줄에 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.

[풀이]
입력을 한 줄의 문자열로 받고 중복 방문표시를 HashSet으로 했다.
끝나는 조건은 문자열이 "123456780"인 경우이다.

'0'의 위치로 x, y좌표를 알 수 있고 그 좌표를 기준으로 4방향 BFS를 한다.
방문을 하지 않았으면 HashSet에 방문을 표시하고 큐에 값을 넣는다. 끝나는 조건으로 탈출하지 못하면 -1을 출력한다.

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