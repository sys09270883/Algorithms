/*
https://www.acmicpc.net/problem/5052
[문제]
전화번호 목록이 주어진다. 이때, 이 목록이 일관성이 있는지 없는지를 구하는 프로그램을 작성하시오.

전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.

예를 들어, 전화번호 목록이 아래와 같은 경우를 생각해보자

긴급전화: 911
상근: 97 625 999
선영: 91 12 54 26
이 경우에 선영이에게 전화를 걸 수 있는 방법이 없다. 전화기를 들고 선영이 번호의 처음 세 자리를 누르는 순간 바로 긴급전화가 걸리기 때문이다. 
따라서, 이 목록은 일관성이 없는 목록이다. 

[입력]
첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50) 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000) 
다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다. 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.

[출력]
각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.

[풀이]
트라이를 공부하면서 해 본 문제이다.
주어진 입력으로 트라이를 구성하고 일관성이 있는지 없는지를 판단한다. -> flag로 판단

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
