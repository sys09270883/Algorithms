/*
https://www.acmicpc.net/problem/9471
[����]
1960��, IBM�� ���� Donald Wall�� �Ǻ���ġ ������ m���� ���� �������� �ֱ⸦ �̷�ٴ� ���� �����ߴ�.

���� ���, �Ǻ���ġ ������ ó�� 10���� 11�� ���� ���� ������ ����.

n	1	2	3	4	5	6	7	8	9	10
F(n)	1	1	2	3	5	8	13	21	34	55
F(n) mod 11	1	1	2	3	5	8	2	10	1	0
�������� �̿��ؼ� ���� ������ �ֱⰡ ��Ÿ�� �� �ִ�. k(m)�� �ݺ��ϴ� �κ� ������ ���̶�� ���� ��, k(11) = 10���� �� �� �ִ�.

Wall�� �Ʒ��� ���� ���� ���� ������ �����ߴ�.

m > 2�� ��쿡 k(m)�� ¦���̴�.
������ ¦�� ���� n > 2�� ���ؼ�, k(m) = n�� m�� �׻� �����Ѵ�.
k(m) �� m2 - 1
k(2n) = 3��2(n-1)
k(5n) = 4��5n
k(2��5n) = 6n
n > 2���, k(10n) = 15��10(n-1)
m�� �־����� ��, k(m)�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� P (1 �� P �� 1000)�� �־�����. �� �׽�Ʈ ���̽��� N�� M���� �̷���� �ִ�. 
N�� �׽�Ʈ ���̽��� ��ȣ�̰�, M�� �������� ������ m�̴�. (2 �� M �� 1,000,000)

[���]
�� �׽�Ʈ ���̽����� �׽�Ʈ ���̽� ��ȣ�� ����ϰ� k(M)�� ����Ѵ�.

[Ǯ��]
��̸���Ʈ�� �ǻ�� �ֱ⸦ �־��ְ�, (0, 1)�� �ݺ��Ǹ� �ݺ����� Ż���ϰ� (0, 1)�� ������.
��̸���Ʈ�� ũ�Ⱑ �ǻ�� �ֱ��� �����̴�.

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
