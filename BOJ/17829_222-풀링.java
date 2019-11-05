/*
https://www.acmicpc.net/problem/17829
222-Ǯ�� ����
�ð� ����	�޸� ����	����	����	���� ���	���� ����
1 ��	256 MB	32	32	31	100.000%
[����]
���� ������ �޲ٴ� �����̴� ���� ���� �������� �����ϴ� ��, �̹��� ó���� ���� ���̴� �ռ��� �Ű��(Convolutional Neural Network, CNN)
�� Ǯ�� ���꿡 ������ �޾� �ڽŸ��� Ǯ���� ����� �̸� 222-Ǯ���̶� �θ���� �ߴ�.

������ 8��8 ����� �־����ٰ� �������� �� 222-Ǯ���� 1ȸ �����ϴ� ������ ������ ���̴�

����� 2��2 ���簢������ ������.



�� ���簢������ 2��°�� ū ���� �����. ���⼭ 2��°�� ū ����, ���簢���� �� ���Ҹ� ũ������� a4 �� a3 �� a2 �� a1 �� ���� ��, ���� a2�� ���Ѵ�.



2�� ������ ���� ����� ũ�Ⱑ �پ��� �ȴ�.

�����̴� N��N ��Ŀ� 222-Ǯ���� �ݺ��ؼ� �����Ͽ� ũ�⸦ 1��1�� ������� �� � ���� ���������� �ñ����Ѵ�.

���� Ȱ���� ġ�� ���� ����� �����̸� �ֵ��ϸ� �������� �ñ����� ��� �ذ�������.

[�Է�]
ù° �ٿ� N(2 �� N �� 1024)�� �־�����. N�� �׻� 2�� �ŵ����� ���̴�. (N=2K, 1 �� K �� 10)

���� N���� �ٸ��� �� ���� ���� N���� ���ʴ�� �־�����. ����� ��� ������ -10,000 �̻� 10,000 ������ �����̴�. 

[���]
�������� ���� ���� ����Ѵ�.

[Ǯ��]
�ܼ� ������������. 
  1. ����, ���ΰ� N�� ������ �迭�� ��Ģ�� ���� ������� 2���� �迭�� �����.
  2. ������� 2���� �迭�� �ٽ� ���� �迭�� �����Ѵ�.
  3. �� ������ N�� 1�� ������ �ݺ��Ѵ�. 

*/
import java.io.*;
import java.util.*;

public class Main {

	static FastIO io = new FastIO();
	
	public static void main(String... args) throws IOException {
		int N = io.nextInt();
		int[][] arr = new int[N][N];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = io.nextInt();
			}
		}
		
		while (N > 1) {
			int[][] tmp = new int[N >> 1][N >> 1];
			
			for (int i = 0; i < N; i += 2) {
				for (int j = 0; j < N; j += 2) {
					int tmpX = i;
					int tmpY = j;
					
					pq.clear();
					pq.add(arr[tmpX][tmpY]);
					pq.add(arr[tmpX + 1][tmpY]);
					pq.add(arr[tmpX][tmpY + 1]);
					pq.add(arr[tmpX + 1][tmpY + 1]);
					pq.poll();
					
					tmp[i >> 1][j >> 1] = pq.poll();
				}
			}
			
			N >>= 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = tmp[i][j];
				}
			}
		}
		
		io.write(arr[0][0]);
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
