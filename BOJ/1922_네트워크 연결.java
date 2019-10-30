/*
https://www.acmicpc.net/problem/1922
[����]
�����̴� ��ǻ�Ϳ� ��ǻ�͸� ��� �����ϴ� ��Ʈ��ũ�� �����Ϸ� �Ѵ�. ������ �ƽ��Ե� ��갡 ���� �ʾ� ��ǻ�Ϳ� ��ǻ�͸� ���� �����Ͽ��� �Ѵ�. 
�׷��� ��ΰ� �ڷḦ �����ϱ� ���ؼ��� ��� ��ǻ�Ͱ� ������ �Ǿ� �־�� �Ѵ�. (a�� b�� ������ �Ǿ� �ִٴ� ���� a���� b���� ��ΰ� �����Ѵٴ� ���� �ǹ��Ѵ�. 
a���� b�� �����ϴ� ���� �ְ�, b�� c�� �����ϴ� ���� ������ a�� c�� ������ �Ǿ� �ִ�.)

�׷��� �̿��̸� ��ǻ�͸� �����ϴ� ����� �ּҷ� �Ͽ��� ��ǻ�͸� �����ϴ� ��� �ܿ� �ٸ� ���� ���� �� �� �� ���� ���̴�. 
���� �� ��ǻ�͸� �����ϴµ� �ʿ��� ����� �־����� �� ��� ��ǻ�͸� �����ϴµ� �ʿ��� �ּҺ���� ����϶�. ��� ��ǻ�͸� ������ �� ���� ���� ����.

[�Է�]
ù° �ٿ� ��ǻ���� �� N (1 �� N �� 1000)�� �־�����.

��° �ٿ��� ������ �� �ִ� ���� �� M (1 �� M �� 100,000)�� �־�����.

��° �ٺ��� M+2��° �ٱ��� �� M���� �ٿ� �� ��ǻ�͸� �����ϴµ� ��� ����� �־�����. 
�� ����� ������ �� ���� ������ �־����µ�, ���࿡ a b c �� �־��� �ִٰ� �ϸ� a��ǻ�Ϳ� b��ǻ�͸� �����ϴµ� ����� c (1 �� c �� 10,000) ��ŭ ��ٴ� ���� �ǹ��Ѵ�.

[���]
��� ��ǻ�͸� �����ϴµ� �ʿ��� �ּҺ���� ù° �ٿ� ����Ѵ�.

[Ǯ��]
ũ�罺Į �˰������� MST�� �����Ѵ�. 

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