/*
https://www.acmicpc.net/problem/13913
[문제]
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 
수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

[출력]
첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.

[풀이]
전형적인 BFS에 경로를 저장하는 배열을 추가한다.
K에 도달했을 때, 경로를 역으로 탐색한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static int MAX = 100001; 
	static int N, K;
	static int[] map;
	static boolean[] visited;
	static int[] path;
	static StringBuilder res;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[MAX];
		visited = new boolean[MAX];
		path = new int[MAX];
		res = new StringBuilder();

		BFS();
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(N, 0));
		visited[N] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int cur = tmp.cur;
			int cnt = tmp.cnt;
			
			if (cur == K) {
				res.append(cnt).append('\n');
				
				Stack<Integer> stack = new Stack<Integer>();
				while (cur != N) {
					stack.add(cur);
					cur = path[cur];
				}
				stack.add(N);
				while (!stack.isEmpty()) {
					res.append(stack.pop()).append(' ');
				}
				
				return;
			}
			
			for (int i = 0; i < 3; i++) {
				int next = move(cur, i);
				
				if (next < 0 || next >= MAX)
					continue;
				
				if (visited[next])
					continue;
				
				visited[next] = true;
				path[next] = cur;
				queue.add(new Node(next, cnt + 1));
			}
		}
	}
	
	private static int move(int cur, int i) {
		int res = 0;
		
		switch (i) {
		case 0:
			res = cur + 1;
			break;
		case 1:
			res = cur - 1;
			break;
		case 2:
			res = cur * 2;
			break;
		}
		
		return res;
	}
	
}

class Node {
	int cur, cnt;

	public Node(int cur, int cnt) {
		super();
		this.cur = cur;
		this.cnt = cnt;
	}
	
}