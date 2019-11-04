/*
[����]
�۰ ö���� �Ǻ���ġ ������ �̿��Ͽ� ���� �ǾƳ� ���� �쿬�� ��Ʃ�꿡�� ���� �Ǿ���. 
�Ǻ���ġ �����̶� f1 = 1, f2 = 1, fn+2 = fn+1 + fn (��� n �� 1�� ����)�� �����ϴ� �����̴�.

�� ���� �Ǻ���ġ ������ �� �ڸ� ���ڿ� �ش��ϴ� �ǹ��� ������ ���������.
�Ǻ���ġ ������ ù 8�� ���� ����� ���� ����ٸ�, ������ ���� �ǹ��� �� 10�� ������ �� ���̴�.

1 �� 1 �� 2 �� 3 �� 5 �� 8 �� 1 �� 3 �� 2 �� 1

ö���� �ڽŵ� �� ����� ����� ����� �����Ծ���. �׷��� �Ǻ���ġ ������ �ʹ� ������ �����ؼ�, ������ ���� ö���� ����ϱ⿡�� �������.

�׷��� ö���� ����� ���� �ٲٱ�� �ߴ�. 
ö���� � �� M�� ���� ��, �Ǻ���ġ ������ �� ���� M���� ���� �������� ���ο� ���� �� �����, �� ������ �� �ڸ� ���ڷ� �� �ǾƳ� ���� ������ �Ѵ�.

���� ��� M=10�� ��, ���ο� ���� �� ������ ����.

{1, 1, 2, 3, 5, 8, 3, 1, ��}

���� ö���� 1 ��1 �� 2 �� 3 �� 5 �� 8 �� 3 �� 1 �� 4 �� �� ������ �ǹ��� ������ �ȴ�.

�̶�, ö���� � N�� ���� N��°�� ������ �Ǵ� �ǹ��� ��ȣ(i.e. ���ο� ���� �� N��° ��)�� �ñ�������.

Q���� N�� ���Ƿ� �־����� ��, ������ ���ǿ� ���� N��°�� ������ �Ǵ� �ǹ��� ��ȣ�� ����ϴ� ���α׷��� �ۼ��Ͽ���.

[�Է�]
ù ��° �ٿ��� ���� Q�� M�� ������ ���̿� �ΰ� �־�����.

�� ��° �ٺ��� Q���� �ٿ� ������ ���Ǹ� ��Ÿ���� ���� N�� �־�����.

[���]
������ N�� ����, N��°�� ������ �Ǵ� �ǹ��� ��ȣ(���ο� ������ N ��° ��)�� �Է¿� �־��� ������� �� Q���� �ٿ� ����Ѵ�.

[Ǯ��]
�ǻ�� �ֱ⸦ ���� ����, �ǻ�� �ֱ⸦ ��ȸ�ϸ鼭 �� �ڸ����� �ǾƳ�� ����Ʈ�� �ִ´�.
N��° �ǾƳ���� N % pianoSong.size()�� ����.

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
