/*
https://www.acmicpc.net/problem/10282
[����]
�����־��� ��Ŀ yum3�� ��Ʈ��ũ �ü��� �� ��ǻ�͸� ��ŷ�ߴ�! ���� ���ο� �����ϴ� ��ǻ�͵��� ���� �ϳ��� �����Ǳ� �����Ѵ�. 
� ��ǻ�� a�� �ٸ� ��ǻ�� b�� �����Ѵٸ�, b�� �����Ǹ� �׷κ��� ���� �ð� �� a�� �����ǰ� ����. �̶� b�� a�� �������� �ʴ´ٸ�, a�� �����Ǵ��� b�� �����ϴ�.

�����־��� ��Ŀ yum3�� ��ŷ�� ��ǻ�� ��ȣ�� �� �������� �־��� ��, 
��ŷ���� ��ǻ�ͱ��� �����Ͽ� �� �� ���� ��ǻ�Ͱ� �����Ǹ� �׿� �ɸ��� �ð��� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ������ �־�����. �׽�Ʈ ���̽��� ������ �ִ� 100���̴�. �� �׽�Ʈ ���̽��� ������ ���� �̷���� �ִ�.

ù° �ٿ� ��ǻ�� ���� n, ������ ���� d, ��ŷ���� ��ǻ���� ��ȣ c�� �־�����(1 �� n �� 10,000, 1 �� d �� 100,000, 1 �� c �� n).
�̾ d���� �ٿ� �� �������� ��Ÿ���� ���� a, b, s�� �־�����(1 �� a, b �� n, a �� b, 0 �� s �� 1,000). 
�̴� ��ǻ�� a�� ��ǻ�� b�� �����ϸ�, ��ǻ�� b�� �����Ǹ� s�� �� ��ǻ�� a�� �������� ���Ѵ�.
�� �׽�Ʈ ���̽����� ���� ������ (a, b)�� �� �� �̻� �������� �ʴ´�.

[���]
�� �׽�Ʈ ���̽����� �� �ٿ� ���� �� �����Ǵ� ��ǻ�� ��, ������ ��ǻ�Ͱ� �����Ǳ���� �ɸ��� �ð��� �������� �������� ����Ѵ�.

[Ǯ��]
���ͽ�Ʈ��� �ð����� �����ϸ鼭 �ʱⰪ(�ִ밪)�� �ƴϸ� ī������ �ϰ�(������ ��ǻ�� ��) �ʱⰪ�� �ƴ� �� �� �ִ��� �ɸ��� �ð��̴�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Node>> adj;
	static int[] times;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0){
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int cnt = 0, max = 0;
			adj = new ArrayList<ArrayList<Node>>(n + 1);
			for (int i = 0; i < n + 1; i++) {
				adj.add(new ArrayList<Node>());
			}
			times = new int[n + 1];
			Arrays.fill(times, Integer.MAX_VALUE);
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adj.get(b).add(new Node(a, s));
			}
			
			dijkstra(c);
			
			for (int i = 1; i <= n; i++) {
				if (times[i] != Integer.MAX_VALUE) {
					cnt++;
					max = max < times[i] ? times[i] : max;
				}
			}
			
			res.append(cnt).append(" ").append(max).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, times[start] = 0));
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int cur = tmp.num;
			int time = tmp.time;
			
			if (time > times[cur])
				continue;
			
			for (Node n : adj.get(cur)) {
				int next = n.num;
				int ntime = n.time;
				
				if (times[next] > ntime + times[cur]) {
					times[next] = ntime + times[cur];
					pq.add(new Node(next, times[next]));
				}
			}
		}
	}
	
}

class Node implements Comparable<Node>{
	int num, time;

	public Node(int num, int time) {
		super();
		this.num = num;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}
	
}
