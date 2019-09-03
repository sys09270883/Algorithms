/*
https://www.acmicpc.net/problem/1916
[����]
N�� ���ð� �ִ�. �׸��� �� ���ÿ��� ����Ͽ� �ٸ� ���ÿ� �����ϴ� M���� ������ �ִ�. 
�츮�� A��° ���ÿ��� B��° ���ñ��� ���µ� ��� ���� ����� �ּ�ȭ ��Ű���� �Ѵ�. 
�׷��� A��° ���ÿ��� B��° ���ñ��� ���µ� ��� �ּҺ���� ����Ͽ���. ������ ��ȣ�� 1���� N�����̴�.

[�Է�]
ù° �ٿ� ������ ���� N(1 �� N �� 1,000)�� �־����� ��° �ٿ��� ������ ���� M(1 �� M �� 100,000)�� �־�����. 
�׸��� ��° �ٺ��� M+2�ٱ��� ������ ���� ������ ������ �־�����. ���� ó������ �� ������ ��� ������ ��ȣ�� �־�����. 
�׸��� �� �������� �������� ���� ��ȣ�� �־����� �� �� ���� ����� �־�����. ���� ����� 0���� ũ�ų� ����, 100,000���� ���� �����̴�.

�׸��� M+3° �ٿ��� �츮�� ���ϰ��� �ϴ� ���� ������� ���ù�ȣ�� �������� ���ù�ȣ�� �־�����. ��������� �������� �� �� �ִ� ��츸 �Է����� �־�����.

[���]
ù° �ٿ� ��� ���ÿ��� ���� ���ñ��� ���µ� ��� �ּ� ����� ����Ѵ�.

[Ǯ��]
���� ���ÿ� ��� ����� ���� ��带 ������ ��������Ʈ�� �����ϰ�, ���ͽ�Ʈ��� �ּҰ��� ���Ѵ�.
�������� �����ϸ� �ش��ϴ� �ڽ�Ʈ�� �����Ѵ�. 
+ �� ������ ��� �������� ������ ��츸 ���� ������ ����ó���� �ʿ����.

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
