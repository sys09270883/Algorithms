/*
[문제]
작곡가 철수는 피보나치 수열을 이용하여 만든 피아노 곡을 우연히 유튜브에서 보게 되었다. 
피보나치 수열이란 f1 = 1, f2 = 1, fn+2 = fn+1 + fn (모든 n ≥ 1에 대해)을 만족하는 수열이다.

이 곡은 피보나치 수열의 각 자리 숫자에 해당하는 건반을 눌러서 만들어졌다.
피보나치 수열의 첫 8개 항을 사용해 곡을 만든다면, 다음과 같이 건반을 총 10번 누르게 될 것이다.

1 → 1 → 2 → 3 → 5 → 8 → 1 → 3 → 2 → 1

철수는 자신도 이 방법을 사용해 보기로 마음먹었다. 그런데 피보나치 수열은 너무 빠르게 증가해서, 덧셈에 약한 철수가 계산하기에는 어려웠다.

그래서 철수는 방법을 조금 바꾸기로 했다. 
철수는 어떤 수 M을 정한 후, 피보나치 수열의 각 항을 M으로 나눈 나머지로 새로운 수열 을 만들고, 그 수열의 각 자리 숫자로 새 피아노 곡을 쓰려고 한다.

예를 들어 M=10일 때, 새로운 수열 은 다음과 같다.

{1, 1, 2, 3, 5, 8, 3, 1, …}

따라서 철수는 1 →1 → 2 → 3 → 5 → 8 → 3 → 1 → 4 → … 순으로 건반을 누르게 된다.

이때, 철수는 어떤 N에 대해 N번째로 누르게 되는 건반의 번호(i.e. 새로운 수열 의 N번째 항)가 궁금해졌다.

Q개의 N이 질의로 주어졌을 때, 각각의 질의에 대해 N번째로 누르게 되는 건반의 번호를 출력하는 프로그램을 작성하여라.

[입력]
첫 번째 줄에는 정수 Q와 M이 공백을 사이에 두고 주어진다.

두 번째 줄부터 Q개의 줄에 각각의 질의를 나타내는 정수 N이 주어진다.

[출력]
각각의 N에 대해, N번째로 누르게 되는 건반의 번호(새로운 수열의 N 번째 항)를 입력에 주어진 순서대로 총 Q개의 줄에 출력한다.

[풀이]
피사노 주기를 구한 다음, 피사노 주기를 순회하면서 각 자리수를 피아노곡 리스트에 넣는다.
N번째 피아노곡은 N % pianoSong.size()와 같다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	
	public static void main(String... args) throws IOException {
		int Q = io.nextInt();
		int M = io.nextInt();
		ArrayList<Integer> pisanoPeriod = new ArrayList<Integer>();
		ArrayList<Integer> pianoSong = new ArrayList<Integer>();
		pisanoPeriod.add(0);
		pisanoPeriod.add(1);
		int before = 0, cur = 1, next;
		StringBuilder res = new StringBuilder();
		
		while (true) {
			next = (cur + before) % M;
			pisanoPeriod.add(next);
			before = cur;
			cur = next;
			
			if (before == 0 && cur == 1)
				break;
		}
		
		pisanoPeriod.remove(pisanoPeriod.size() - 1);
		pisanoPeriod.remove(pisanoPeriod.size() - 1);
		int size = pisanoPeriod.size();
		
		for (int i = 0; i < size; i++) {
			func(pisanoPeriod.get(i), pianoSong);
		}
		
		size = pianoSong.size();
		
		for (int i = 0; i < Q; i++) {
			long N = io.nextLong();
			res.append(pianoSong.get((int)(N % size))).append('\n');
		}
		
		io.write(res);
	}
	
	private static void func(int num, ArrayList<Integer> newList) {
		Stack<Integer> stack = new Stack<Integer>();
		while(true) {
			if (num >= 10) {
				stack.add(num % 10);
				num /= 10;
			}
			
			else {
				stack.add(num);
				break;
			}
		}
		
		while (!stack.isEmpty()) {
			newList.add(stack.pop());
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
