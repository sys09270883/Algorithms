/*
https://www.acmicpc.net/problem/5052
[����]
��ȭ��ȣ ����� �־�����. �̶�, �� ����� �ϰ����� �ִ��� �������� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

��ȭ��ȣ ����� �ϰ����� �����Ϸ���, �� ��ȣ�� �ٸ� ��ȣ�� ���ξ��� ��찡 ����� �Ѵ�.

���� ���, ��ȭ��ȣ ����� �Ʒ��� ���� ��츦 �����غ���

�����ȭ: 911
���: 97 625 999
����: 91 12 54 26
�� ��쿡 �����̿��� ��ȭ�� �� �� �ִ� ����� ����. ��ȭ�⸦ ��� ������ ��ȣ�� ó�� �� �ڸ��� ������ ���� �ٷ� �����ȭ�� �ɸ��� �����̴�. 
����, �� ����� �ϰ����� ���� ����̴�. 

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� t�� �־�����. (1 �� t �� 50) �� �׽�Ʈ ���̽��� ù° �ٿ��� ��ȭ��ȣ�� �� n�� �־�����. (1 �� n �� 10000) 
���� n���� �ٿ��� ��Ͽ� ���ԵǾ� �ִ� ��ȭ��ȣ�� �ϳ��� �־�����. ��ȭ��ȣ�� ���̴� ���� 10�ڸ��̸�, ��Ͽ� �ִ� �� ��ȭ��ȣ�� ���� ���� ����.

[���]
�� �׽�Ʈ ���̽��� ���ؼ�, �ϰ��� �ִ� ����� ��쿡�� YES, �ƴ� ��쿡�� NO�� ����Ѵ�.

[Ǯ��]
Ʈ���̸� �����ϸ鼭 �� �� �����̴�.
�־��� �Է����� Ʈ���̸� �����ϰ� �ϰ����� �ִ��� �������� �Ǵ��Ѵ�. -> flag�� �Ǵ�

 + https://code0xff.tistory.com/76
 + https://namu.wiki/w/%ED%8A%B8%EB%9D%BC%EC%9D%B4

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	static int flag;
	
	public static void main(String... args) throws IOException {
		int t = io.nextInt();
		StringBuilder res = new StringBuilder();
		
		while (t-- > 0) {
			int n = io.nextInt();
			
			Node root = new Node('\0', 0);
			
			for (int i = 1; i <= n; i++) {
				Node node = root;
				String str = io.nextLine();
				int len = str.length();
				
				for (int j = 0; j < len; j++) {
					char c = str.charAt(j);
					
					if (node.next[c - '0'] == null) {
						node.cnt++;
						node.next[c - '0'] = new Node(c, 0);
					}
					
					node = node.next[c - '0'];
				}
			}
			
			flag = 0;
			find(root);
			
			res.append(flag != n ? "NO\n" : "YES\n");
		}
		
		io.write(res);
	}
	
	private static void find(Node node) {
		if (node.cnt == 0) {
			flag++;
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			if (node.next[i] != null) {
				find(node.next[i]);
			}
		}
	}
	
}

class Node {
	char data;
	int cnt;
	Node[] next = new Node[10];
	
	public Node(char data, int cnt) {
		super();
		this.data = data;
		this.cnt = cnt;
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
