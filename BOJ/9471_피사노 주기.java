/*
https://www.acmicpc.net/problem/9471
[문제]
1960년, IBM의 직원 Donald Wall은 피보나치 수열을 m으로 나눈 나머지가 주기를 이룬다는 것을 증명했다.

예를 들어, 피보나치 수열의 처음 10개를 11로 나눈 예는 다음과 같다.

n	1	2	3	4	5	6	7	8	9	10
F(n)	1	1	2	3	5	8	13	21	34	55
F(n) mod 11	1	1	2	3	5	8	2	10	1	0
나머지를 이용해서 만든 수열은 주기가 나타날 수 있다. k(m)을 반복하는 부분 수열의 길이라고 했을 때, k(11) = 10임을 알 수 있다.

Wall은 아래와 같은 여러 가지 성질도 증명했다.

m > 2인 경우에 k(m)은 짝수이다.
임의의 짝수 정수 n > 2에 대해서, k(m) = n인 m이 항상 존재한다.
k(m) ≤ m2 - 1
k(2n) = 3×2(n-1)
k(5n) = 4×5n
k(2×5n) = 6n
n > 2라면, k(10n) = 15×10(n-1)
m이 주어졌을 때, k(m)을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 P (1 ≤ P ≤ 1000)가 주어진다. 각 테스트 케이스는 N과 M으로 이루어져 있다. 
N은 테스트 케이스의 번호이고, M은 문제에서 설명한 m이다. (2 ≤ M ≤ 1,000,000)

[출력]
각 테스트 케이스마다 테스트 케이스 번호를 출력하고 k(M)을 출력한다.

[풀이]
어레이리스트에 피사노 주기를 넣어주고, (0, 1)이 반복되면 반복문을 탈출하고 (0, 1)을 빼낸다.
어레이리스트의 크기가 피사노 주기의 길이이다.

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	
	public static void main(String... args) throws IOException {
		int P = io.nextInt();
		StringBuilder res = new StringBuilder();
		
		while (P-- > 0) {
			int N = io.nextInt();
			int M = io.nextInt();
			ArrayList<Integer> fibo = new ArrayList<Integer>();
			fibo.add(0);
			fibo.add(1);
			int before = 0, cur = 1, next;
			
			while (true) {
				next = (cur + before) % M;
				fibo.add(next);
				before = cur;
				cur = next;
				
				if (before == 0 && cur == 1)
					break;
			}
			
			fibo.remove(fibo.size() - 1);
			fibo.remove(fibo.size() - 1);
			int size = fibo.size();
			
			res.append(N).append(' ').append(size).append('\n');
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
