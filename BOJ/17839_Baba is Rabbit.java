/*
https://www.acmicpc.net/problem/17839
[문제]
원이는 요즘 유행하는 게임을 하고 있다. 이 게임은 is 라는 단어를 이용해 어떤 사물을 다른 사물로 바꿀 수 있다. 규칙은 다음과 같다.

게임 시작 시 몇 개의 명령을 설정해놓는다.
이 때, 모든 명령의 형태는 p is q 의 형태이며, p, q는 사물이다.
두 사물 p, q에 대해 p is q 라는 명령은 사물 p를 사물 q로 바꾼다.
이러한 행위를 명령을 적용한다고 부른다.
어떤 사물 p에 대해 적용할 수 있는 명령이 두 가지 이상이면, 그 중 아무거나 하나 골라서 적용할 수 있다. 
(아무 명령도 적용하지 않을 수도 있다.) 그리고 어떤 사물 p에 명령을 한 번 이상 적용한 결과로 다시 p가 나오는 경우는 없다.

게임 초기에 설정된 명령들이 주어졌을 때, Baba에 명령을 적용하여 어떤 사물로 만들 수 있는지 구해보자.

[입력]
첫 줄에 전체 명령의 수 N(1 ≤ N ≤ 100,000)이 주어진다.

이후 N개의 줄에 걸쳐 명령이 주어진다. 각 명령은 p is q의 형태로 주어지며,  p와 q는 첫 글자가 영문 대문자이고, 나머지 글자는 영문 소문자인 길이 10 이내의 문자열이다.

[출력]
Baba에 명령을 한 번 이상 적용한 결과로 나올 수 있는 사물을 사전순으로 출력한다. 단, 적용할 수 있는 명령이 없다면, 아무것도 출력하지 않는다.

[풀이]
int 대신 String으로 인접리스트를 구성한다.
Baba에서 BFS를 하면서 방문 표시를 하고, 방문한 단어들을 정렬해서 출력한다.

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
