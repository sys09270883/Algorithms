/*
https://www.acmicpc.net/problem/2547
[문제]
사탕 박사 고창영은 사탕 공장을 그만 두고 초등학교 선생님으로 취직했다.

오늘은 창영이네 반 학생들과 소풍을 가는 날이다. N명의 학생은 모두 가방에 사탕을 가득 담아왔다.

그런데, 사탕을 많이 가져온 학생들이 사탕을 적게 가져온 학생들을 놀리기 시작했다.

창영이는 "얘들아~ 그러면 안되지ㅜㅜ 사탕을 모두 여기에 가져와보렴. 선생님이 공평하게 나눠줄게"

창영이는 모든 학생들이 같은 사탕을 가지게 하려고 한다.

이것이 가능한 일일까?

[입력]
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 빈 줄로 구분되어 있고, 다음과 같이 구성되어 있다.

테스트 케이스의 첫째 줄에 학생의 수 N이 주어진다. 다음 N개의 줄에는 각 학생들이 가져온 사탕의 수가 주어진다.

N은 100,000보다 작거나 같은 자연수이고, 사탕의 개수는 0보다 크거나 같은 정수이다. 
학생들이 가져온 사탕 개수는 10^18보다 작거나 같다. (학생들이 가져온 사탕 개수의 합은 10^18을 넘어갈 수 있다)

[출력]
각 테스트 케이스에 대해 모두에게 같은 사탕을 나눠줄 수 있으면 YES를, 없으면 NO를 출력한다.

[풀이]
문제 자체는 쉽지만 sum이 오버플로우 나는 경우를 처리해야 한다.
입력 값 자체를 N으로 나눈 나머지를 더해주고, sum 역시 N으로 나눈 나머지를 취한다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	
	public static void main(String... args) throws IOException {
		int T = io.nextInt();
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0) {
			io.nextLine();
			int N = io.nextInt();
			long sum = 0;
			
			for (int i = 0; i < N; i++) {
				sum += (io.nextLong() % N);
				sum %= N;
			}
			
			if (sum == 0)
				res.append("YES\n");
			
			else
				res.append("NO\n");
		}
		
		io.write(res);
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
