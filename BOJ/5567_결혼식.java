/*
https://www.acmicpc.net/problem/5567
[����]
����̴� �ڽ��� ��ȥ�Ŀ� �б� ���� �� �ڽ��� ģ���� ģ���� ģ���� �ʴ��ϱ�� �ߴ�. 
������� ����� ��� N���̰�, �� �л����� �й��� ��� 1���� N�����̴�. ������� �й��� 1�̴�.

����̴� ������� ģ�� ���踦 ��� ������ ����Ʈ�� ������ �ִ�. �� ����Ʈ�� �������� ��ȥ�Ŀ� �ʴ��� ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������� ������ �� n (2 �� n �� 500)�� �־�����. ��° �ٿ��� ����Ʈ�� ���� m (1 �� m �� 10000)�� �־�����. 
���� �ٺ��� m�� �ٿ��� ģ�� ���� ai bi�� �־�����. (1 �� ai < bi �� n) ai�� bi�� ģ����� ���̸�, bi�� ai�� ģ�������̴�. 

[���]
ù° �ٿ� ������� ��ȥ�Ŀ� �ʴ��ϴ� ������ ���� ����Ѵ�.

[Ǯ��]
ģ���� ģ�������� ���ؼ���(cnt <= 1) BFS Ž���� �ϸ鼭 ī�����Ѵ�.
 + ���� ������ ��������.
*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, ans;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        ans = 0;
        adj = new ArrayList<ArrayList<Integer>>(n + 3);
        for (int i = 1; i < n + 3; i++) {
			adj.add(new ArrayList<Integer>());
		}
        visited = new boolean[n + 3];
        StringTokenizer st = null;
        int a, b;
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
        
        BFS();
        
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
 
    private static void BFS() {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(1, 0));
    	visited[1] = true;
    	
    	while (!queue.isEmpty()) {
    		Node tmp = queue.poll();
    		int cur = tmp.idx;
    		int cnt = tmp.cnt;
    		
    		if (cnt > 1)
    			continue;
    		
    		for (int next : adj.get(cur)) {
				if (!visited[next]) {
					ans++;
					visited[next] = true;
					queue.add(new Node(next, cnt + 1));
				}
			}
    	}
    }
}

class Node {
	int idx, cnt;

	public Node(int idx, int cnt) {
		super();
		this.idx = idx;
		this.cnt = cnt;
	}
	
}