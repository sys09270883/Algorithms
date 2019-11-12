/*
https://www.acmicpc.net/problem/17839
[����]
���̴� ���� �����ϴ� ������ �ϰ� �ִ�. �� ������ is ��� �ܾ �̿��� � �繰�� �ٸ� �繰�� �ٲ� �� �ִ�. ��Ģ�� ������ ����.

���� ���� �� �� ���� ����� �����س��´�.
�� ��, ��� ����� ���´� p is q �� �����̸�, p, q�� �繰�̴�.
�� �繰 p, q�� ���� p is q ��� ����� �繰 p�� �繰 q�� �ٲ۴�.
�̷��� ������ ����� �����Ѵٰ� �θ���.
� �繰 p�� ���� ������ �� �ִ� ����� �� ���� �̻��̸�, �� �� �ƹ��ų� �ϳ� ��� ������ �� �ִ�. 
(�ƹ� ��ɵ� �������� ���� ���� �ִ�.) �׸��� � �繰 p�� ����� �� �� �̻� ������ ����� �ٽ� p�� ������ ���� ����.

���� �ʱ⿡ ������ ��ɵ��� �־����� ��, Baba�� ����� �����Ͽ� � �繰�� ���� �� �ִ��� ���غ���.

[�Է�]
ù �ٿ� ��ü ����� �� N(1 �� N �� 100,000)�� �־�����.

���� N���� �ٿ� ���� ����� �־�����. �� ����� p is q�� ���·� �־�����,  p�� q�� ù ���ڰ� ���� �빮���̰�, ������ ���ڴ� ���� �ҹ����� ���� 10 �̳��� ���ڿ��̴�.

[���]
Baba�� ����� �� �� �̻� ������ ����� ���� �� �ִ� �繰�� ���������� ����Ѵ�. ��, ������ �� �ִ� ����� ���ٸ�, �ƹ��͵� ������� �ʴ´�.

[Ǯ��]
int ��� String���� ��������Ʈ�� �����Ѵ�.
Baba���� BFS�� �ϸ鼭 �湮 ǥ�ø� �ϰ�, �湮�� �ܾ���� �����ؼ� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static Map<String, ArrayList<String>> adj;
	static Map<String, Boolean> visited;
	static int N;
	
	public static void main(String... args) throws IOException {
		N = io.nextInt();
		String p = null, q = null;
		adj = new HashMap<String, ArrayList<String>>();
		visited = new HashMap<String, Boolean>();
		List<String> list = new ArrayList<String>();
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			p = io.next();
			io.next();
			q = io.next();
			
			if (adj.get(p) == null)
				adj.put(p, new ArrayList<String>());
			
			adj.get(p).add(q);
		}
		
		BFS();
		
		for (String str : visited.keySet()) {
			if (visited.get(str))
				list.add(str);
		}
		
		Collections.sort(list);
		int size = list.size();
		
		for (int i = 0; i < size; i++) {
			res.append(list.get(i)).append('\n');
		}
		
		io.write(res);
	}
	
	private static void BFS() {
		Queue<String> queue = new LinkedList<String>();
		queue.add("Baba");
		
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			
			if (adj.get(cur) == null)
				continue;
			
			for (String next : adj.get(cur)) {
				if (visited.get(next) == null || visited.get(next) == false) {
					visited.put(next, true);
					queue.add(next);
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
