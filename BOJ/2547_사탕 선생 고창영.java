/*
https://www.acmicpc.net/problem/2547
[����]
���� �ڻ� ��â���� ���� ������ �׸� �ΰ� �ʵ��б� ���������� �����ߴ�.

������ â���̳� �� �л���� ��ǳ�� ���� ���̴�. N���� �л��� ��� ���濡 ������ ���� ��ƿԴ�.

�׷���, ������ ���� ������ �л����� ������ ���� ������ �л����� ��� �����ߴ�.

â���̴� "����~ �׷��� �ȵ����̤� ������ ��� ���⿡ �����ͺ���. �������� �����ϰ� �����ٰ�"

â���̴� ��� �л����� ���� ������ ������ �Ϸ��� �Ѵ�.

�̰��� ������ ���ϱ�?

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� T�� �־�����. �� �׽�Ʈ ���̽��� �� �ٷ� ���еǾ� �ְ�, ������ ���� �����Ǿ� �ִ�.

�׽�Ʈ ���̽��� ù° �ٿ� �л��� �� N�� �־�����. ���� N���� �ٿ��� �� �л����� ������ ������ ���� �־�����.

N�� 100,000���� �۰ų� ���� �ڿ����̰�, ������ ������ 0���� ũ�ų� ���� �����̴�. 
�л����� ������ ���� ������ 10^18���� �۰ų� ����. (�л����� ������ ���� ������ ���� 10^18�� �Ѿ �� �ִ�)

[���]
�� �׽�Ʈ ���̽��� ���� ��ο��� ���� ������ ������ �� ������ YES��, ������ NO�� ����Ѵ�.

[Ǯ��]
���� ��ü�� ������ sum�� �����÷ο� ���� ��츦 ó���ؾ� �Ѵ�.
�Է� �� ��ü�� N���� ���� �������� �����ְ�, sum ���� N���� ���� �������� ���Ѵ�.

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
