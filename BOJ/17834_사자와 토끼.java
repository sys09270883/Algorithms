/*
https://www.acmicpc.net/problem/17834
[����]
���ڿ� �䳢�� ���������� �α⸦ ���� �ִ� ��մ� ��������̴�. ���ڿ� �䳢�� ���� ���ؼ��� 2���� �÷��̾�� 1���� ������ �ʿ��ϴ�. 
�������� N���� ��Ǯ�� M���� ���ֱ�� �̷���� �ִ�. 
���ֱ��� ���� �ٸ� �� ��Ǯ�� ��������� �����ϸ�, � ��Ǯ���� �ٸ� ��Ǯ���� 1�� �̻��� ���ֱ��� ���ϸ� �ݵ�� ���� �� �� �ִ�.

������ ������ ���� ������ �̷������.

  

������ ���ڿ� �䳢�� �ʱ� ��ġ�� ���� ��� ��Ǯ�� ���� ���Ѵ�. 
���ڿ� �䳢�� �ʱ� ��ġ�� ���� �� ������, ������ ��ġ�� ���� �÷��̾�Ը�, �䳢�� ��ġ�� �䳢 �÷��̾�Ը� �˷��ش�.

�� �ϸ���, ���ڿ� �䳢�� ���� ��ġ�� ��Ǯ���� ���ֱ� 1���� ���� �̵��ؾ� �Ѵ�. �� �÷��̾�� �ڽ��� ���� �̵��� ��ġ�� ���ǿ��Ը� ���Ѵ�. �̵����� ���� ���� ����.

�̵��� ��, ���ڿ� �䳢�� ���� ��Ǯ�� �ִٸ� ���ڰ� �䳢�� ��ƸԾ� ������ ������. 
�׷��� �ʴٸ�, �ٽ� 2�� ���ư� ���� ����Ͽ� �����Ѵ�. ���� ������ ������ �ʴ� �̻�, �̵� �Ŀ��� �� �÷��̾�� ��� ���� ��ġ�� �� �� ����. 
����, ���ڴ� ���ֱ� �������� �䳢�� �� �� ���� ������, ���� ���ֱ��� ���� �̵��Ͽ��� ���� �ٸ� ��Ǯ�� �����ߴٸ� ������ ������ �ʴ´�.

�̷��� ���� �ɸ����� ���� �䳢�� ���ڿ��Լ� ��������, ���ڴ� �䳢�� ã�Ƴ��� �����̴�. 
�׷��� ������ ���� ���ڿ� �䳢�� �ʱ� ��ġ�� ����, ��� �������� ������ ������ ������ �ʴ� ��찡 �ִٴ� ���� �߰��ߴ�. 
���� ���, ���� �׸��� ���� �����ǿ����� ������ ������ �ʴ� (������ �ʱ� ��ġ, �䳢�� �ʱ� ��ġ)�� ������ ������ ���� 8������ �����Ѵ� : 
(1,2) (1,4) (2,1) (2,3) (3,2) (3,4) (4,1) (4,3). �̷� ��쿡��, ������ �� �÷��̾ ������ ��ġ�� �˰� �Ϻη� ������ �������� �ص� ���� �� ����!

�������� ����� �־��� ��, ��� �������� ������ ������ ������ �ʴ� �ʱ� ��ġ�� ����� ���� �� ������ ������ ���غ���.

[�Է�]
ù ��° �ٿ� ��Ǯ�� �� N(2 �� N �� 50,000)�� ���ֱ��� �� M(1 �� M �� 500,000)�� �������� ���еǾ� �־�����.

�� ��° �ٺ��� M���� �ٿ� ����, u, v(1 �� u,v �� N, u �� v)�� �������� ���еǾ� �־�����. �̴� u�� ��Ǯ�� v�� ��Ǯ�� ���ֱ�� ����Ǿ� ������ �ǹ��Ѵ�.

[���]
ù ��° �ٿ� ��� �������� ������ ������ ������ �ʴ� �ʱ� ��ġ�� ����� ���� ����Ѵ�.

���� 32-bit�� ����(int)�� �ִ밪���� Ŭ �� ������ ��������.

[Ǯ��]
������ ������ �������� �׷����� �̺б׷����� �������� ��, �ϸ��� ���� �ݴ� ������ ������ ��ġ�ؾ� �Ѵ�.
���� �� ������ �̺б׷����� Ǯ �� �ִ�.
�̺б׷����� ��, redCnt * blueCnt * 2�� ������ �� �ִ� �� �������̴�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int N, M, u, v;
	static long redCnt, blueCnt;
	static ArrayList<ArrayList<Integer>> adj;
	static int[] colors;
	static boolean isBipartite;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		M = io.nextInt();
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N + 1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		colors = new int[N + 1];
		isBipartite = true;
		
		for (int i = 0; i < M; i++) {
			u = io.nextInt();
			v = io.nextInt();
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		for (int i = 1; i < N + 1; i++) {
			if (!isBipartite)
				break;
			
			if (colors[i] == 0)
				BFS(i, 1);
		}
		
		io.write(isBipartite ? redCnt * blueCnt * 2l : 0l);
	}
	
	private static void BFS(int idx, int color) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(idx);
		colors[idx] = color;
		redCnt++;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int next : adj.get(cur)) {
				if (colors[next] == 0) {
					colors[next] = -colors[cur];
					
					if (colors[next] == 1)
						redCnt++;
					
					else
						blueCnt++;
					
					queue.add(next);
				}
				
				else if (colors[cur] == colors[next]){
					isBipartite = false;
					return;
				}
			}
		}
	}
	
	
}

class FastIO {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	
	FastIO() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	String next() {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return st.nextToken();
	}
	
	int nextInt() {
		return Integer.parseInt(next());
	}
	
	long nextLong() {
		return Long.parseLong(next());
	}
	
	double nextDouble() {
		return Double.parseDouble(next());
	}
	
	String nextLine() {
		String str = null;
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	void write(double d) throws IOException { 
		bw.write(String.valueOf(d));
		close();
	}
	
	void write(int i) throws IOException {
		bw.write(String.valueOf(i));
		close();
	}
	
	void write(long l) throws IOException {
		bw.write(String.valueOf(l));
		close();
	}
	
	void write(StringBuilder sb) throws IOException {
		bw.write(sb.toString().trim());
		close();
	}
	
	void write(String str) throws IOException {
		bw.write(str.trim());
		close();
	}
	
	void close() throws IOException {
		bw.close();
		br.close();
	}
}
