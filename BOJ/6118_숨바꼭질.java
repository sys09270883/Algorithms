/*
https://www.acmicpc.net/problem/6118
[문제]
재서기는 수혀니와 교외 농장에서 숨바꼭질을 하고 있다. 농장에는 헛간이 많이 널려있고 재석이는 그 중에 하나에 숨어야 한다. 
헛간의 개수는 N(2 <= N <= 20,000)개이며, 1 부터 샌다고 하자.  

재서기는 수혀니가 1번 헛간부터 찾을 것을 알고 있다. 모든 헛간은 M(1<= M <= 50,000)개의 양방향 길로 이어져 있고, 
그 양 끝을 A_i 와 B_i(1<= A_i <= N; 1 <= B_i <= N; A_i != B_i)로 나타낸다. 또한 어떤 헛간에서 다른 헛간으로는 언제나 도달 가능하다고 생각해도 좋다. 

재서기는 발냄새가 지독하기 때문에 최대한 냄새가 안나게 숨을 장소를 찾고자 한다. 
냄새는 1번 헛간에서의 거리(여기서 거리라 함은 지나야 하는 길의 최소 개수이다)가 멀어질수록 감소한다고 한다. 재서기의 발냄새를 최대한 숨길 수 있는 헛간을 찾을 수 있게 도와주자!

[입력]
첫 번째 줄에는 N과 M이 공백을 사이에 두고 주어진다.

이후 M줄에 걸쳐서 A_i와 B_i가 공백을 사이에 두고 주어진다.

 

[출력]
출력은 한줄로 이루어지며, 세 개의 값을 공백으로 구분지어 출력해야한다. 

첫 번째는 숨어야 하는 헛간 번호를(만약 거리가 같은 헛간이 여러개면 가장 작은 헛간 번호를 출력한다), 두 번째는 그 헛간까지의 거리를, 
세 번째는 그 헛간과 같은 거리를 갖는 헛간의 개수를 출력해야한다.

[풀이]
첫 번째 BFS에서 최대거리를 구한다.
두 번째 BFS에서 최대거리와 같은 거리에 있는 헛간들을 우선순위 큐에 넣어준다.
우선순위 큐의 peek()과 최대거리와 우선순위 큐의 크기를 차례대로 출력한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		visited = new boolean[N + 1];
		pq = new PriorityQueue<Integer>();
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
					
			adj.get(A).add(B);
			adj.get(B).add(A);
		}
		
		int maxDist = BFS();
		Arrays.fill(visited, false);
		BFS(maxDist);
		
		res.append(pq.peek()).append(" ").append(maxDist).append(" ").append(pq.size());
		
		bw.write(res.toString());
		bw.close();
		br.close();
	}
	
	private static int BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		int maxDist = Integer.MIN_VALUE;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int num = tmp.num;
			int cnt = tmp.cnt;
			maxDist = cnt;
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
		
		return maxDist;
	}
	
	private static void BFS(int maxDist) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(1, 0));
		visited[1] = true;
		
		while (!queue.isEmpty()) {
			Node tmp = queue.poll();
			int num = tmp.num;
			int cnt = tmp.cnt;
			
			if (cnt == maxDist)
				pq.add(num);
			
			for (int i : adj.get(num)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
		}
	}
	
}

class Node {
	int num, cnt;

	public Node(int num, int cnt) {
		super();
		this.num = num;
		this.cnt = cnt;
	}
	
}