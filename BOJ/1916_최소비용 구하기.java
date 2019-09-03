/*
https://www.acmicpc.net/problem/1916
[문제]
N의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 
우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 
그러면 A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.

[입력]
첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 
그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 
그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.

그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.

[출력]
첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.

[풀이]
다음 도시와 드는 비용을 갖는 노드를 가지는 인접리스트를 정의하고, 다익스트라로 최소값을 구한다.
목적지에 도달하면 해당하는 코스트를 리턴한다. 
+ 이 문제의 경우 목적지에 가능한 경우만 오기 때문에 예외처리는 필요없다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Node>> adj;
	static boolean[] visited;
	static int[] cost;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N, M, from, to, n1, n2, c;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adj = new ArrayList<ArrayList<Node>>(N + 1); 
        for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
        cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        StringTokenizer st = null;
        
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adj.get(n1).add(new Node(n2, c));
		}
        
        st = new StringTokenizer(br.readLine(), " ");
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        cost[from] = 0;
        
        bw.write(String.valueOf(dijkstra(from, to)));
        bw.close();
        br.close();
    }
    
    private static int dijkstra(int from, int to) {
    	PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
    	pq.add(new Node(from, cost[from]));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		int idx = cur.idx;
    		int c = cur.cost;
    		
    		if (visited[idx])
    			continue;
    		visited[idx] = true;
    		
    		if (idx == to)
    			return c;
    		
    		for (Node n : adj.get(idx)) {
    			int next = n.idx;
    			int nextC = n.cost;
    			
    			if (cost[next] > cost[idx] + nextC) {
    				cost[next] = cost[idx] + nextC;
    				pq.add(new Node(next, cost[next]));
    			}
			}
    	}
    	
    	return -1;
    }
}

class Node {
	int idx, cost;
	
	public Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}
