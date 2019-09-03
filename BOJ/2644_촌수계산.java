/*
https://www.acmicpc.net/problem/2644
[����]
�츮 ����� ���� Ȥ�� ģô�� ������ ���踦 �̼���� ������ ǥ���ϴ� ��Ư�� ��ȭ�� ������ �ִ�. 
�̷��� �̼��� ������ ���� ������� ���ȴ�. �⺻������ �θ�� �ڽ� ���̸� 1������ �����ϰ� �̷κ��� ����� ���� �̼��� ����Ѵ�. 
���� ��� ���� �ƹ���, �ƹ����� �Ҿƹ����� ���� 1������ ���� �Ҿƹ����� 2���� �ǰ�, �ƹ��� ������� �Ҿƹ����� 1��, ���� �ƹ��� ��������� 3���� �ȴ�.

���� ����鿡 ���� �θ� �ڽĵ� ���� ���谡 �־����� ��, �־��� �� ����� �̼��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
������� 1, 2, 3, ��, n (1��n��100)�� ���ӵ� ��ȣ�� ���� ǥ�õȴ�. 
�Է� ������ ù° �ٿ��� ��ü ����� �� n�� �־�����, ��° �ٿ��� �̼��� ����ؾ� �ϴ� ���� �ٸ� �� ����� ��ȣ�� �־�����. 
�׸��� ��° �ٿ��� �θ� �ڽĵ� ���� ������ ���� m�� �־�����. ��° �ٺ��ʹ� �θ� �ڽİ��� ���踦 ��Ÿ���� �� ��ȣ x,y�� �� �ٿ� ���´�. 
�̶� �տ� ������ ��ȣ x�� �ڿ� ������ ���� y�� �θ� ��ȣ�� ��Ÿ����.

�� ����� �θ�� �ִ� �� �� �־�����.

[���]
�Է¿��� �䱸�� �� ����� �̼��� ��Ÿ���� ������ ����Ѵ�. � ��쿡�� �� ����� ģô ���谡 ���� ���� �̼��� ����� �� ���� ���� �ִ�. 
�̶����� -1�� ����ؾ� �Ѵ�.

[Ǯ��]
��������Ʈ�� ������ �ְ� to�� ���� ������ BFS�Ѵ�.
�̼��� ����� �� ���� ���� -1�� ��ȯ�ϴ� �Ϳ� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n, from, to, m, x, y;
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
        visited = new boolean[n + 1];
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			addEdge(x, y);
		}
        
        bw.write(String.valueOf(BFS(from, to)));
        bw.close();
        br.close();
    }
    
    private static void addEdge(int a, int b) {
    	adj.get(a).add(b);
    	adj.get(b).add(a);
    }
    
    private static int BFS(int from, int to) {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(from, 0));
    	visited[from] = true;
    	
    	while (!queue.isEmpty()) {
    		Node cur = queue.poll();
    		int idx = cur.idx;
    		int cnt = cur.cnt;
    		
    		if (idx == to)
    			return cnt;
    		
    		for (int i : adj.get(idx)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.add(new Node(i, cnt + 1));
				}
			}
    	}
    	
    	return -1;
    }
    
}

class Node {
	int idx, cnt;
	
	public Node(int idx, int cnt) {
		this.idx = idx;
		this.cnt = cnt;
	}
}