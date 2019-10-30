/*
https://www.acmicpc.net/problem/1922
[문제]
도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 하지만 아쉽게도 허브가 있지 않아 컴퓨터와 컴퓨터를 직접 연결하여야 한다. 
그런데 모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. (a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. 
a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어 있다.)

그런데 이왕이면 컴퓨터를 연결하는 비용을 최소로 하여야 컴퓨터를 연결하는 비용 외에 다른 곳에 돈을 더 쓸 수 있을 것이다. 
이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 모든 컴퓨터를 연결할 수 없는 경우는 없다.

[입력]
첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.

둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.

셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 
이 비용의 정보는 세 개의 정수로 주어지는데, 만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 만큼 든다는 것을 의미한다.

[출력]
모든 컴퓨터를 연결하는데 필요한 최소비용을 첫째 줄에 출력한다.

[풀이]
크루스칼 알고리즘으로 MST를 구현한다. 

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Edge> edge;
	static int[] parent;
	static int N, M;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int a, b, c;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edge = new ArrayList<Edge>(M << 1);
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
        StringTokenizer st = null;
        
        for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			edge.add(new Edge(a, b, c));
			edge.add(new Edge(b, a, c));
		}
        
        Collections.sort(edge);
        
        bw.write(String.valueOf(kruskal()));
        bw.close();
        br.close();
    }
    
    private static int kruskal() {
    	int totalCost = 0;
    	
    	for (Edge e : edge) {
			int v1 = e.v1;
			int v2 = e.v2;
			int c = e.cost;
			
			if (find(v1) != find(v2)) {
				union(v1, v2);
				totalCost += c;
			}
		}
    	
    	return totalCost;
    }
    
    private static int find(int x) {
    	if (x == parent[x])
    		return x;
    	
    	return parent[x] = find(parent[x]);
    }
    
    private static void union(int x, int y) {
    	x = find(x);
    	y = find(y);
    	
    	if (x != y)
    		parent[y] =x;
    }
    
}

class Edge implements Comparable<Edge>{
	int v1, v2, cost;

	public Edge(int v1, int v2, int cost) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
	
}