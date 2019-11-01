/*
https://www.acmicpc.net/problem/14919
[����]
0�� 1������ �Ǽ� a1, a2, ..., aN�� �Է����� �־����ٰ� ����.  ���� [0,1)�� ������ ���� m���� ���� L=1/m�� �κб������� ������. 
�� ������ ������� b1, b2, ..., bm�̴�.

b1: 0 �� x < L,
b2: L �� x < 2L,
...
bm: (m-1)L �� x < 1.
�Էµ� �Ǽ���, �� ���� b1, b2, ..., bm�� ���ԵǴ� ���� ������ ����Ͻÿ�.

[�Է�]
ù�ٿ� m (1 �� m �� 1,000), ��° �ٿ� N (1 �� N �� 1,000,000)���� �Ǽ� a1, ��, aN�� ��ĭ���� ���еǾ� �Էµȴ�. �Ǽ��� �Ҽ��� ����° �ڸ����� �־�����.

[���]
�� ���� b1, b2, ..., bm�� ���Ե� ���� �� ĭ���� ������ ����Ѵ�.

[Ǯ��]
������ �˰��� ��ü�� �����µ� �ε��Ҽ��� ó���� ��ٷο��� ��������.
double���� ��� 0.3�� ����� 0.30000000000���� ��� ���� ���� �ƴ϶� �ణ�� ������ �ְ� ���� ������ �̰��� �ذ��ؾ� �ߴ�.
������ �ִ� 1000������ ���� �� �ֱ� ������ �Ҽ��� 6�ڸ� + 3�ڸ�(1000)������ ��Ȯ�� ǥ���ؾ� �Ѵ�.

���� 10��° �ڸ��� 1�� �����ְ� 100���� �����ָ� ���Ϸ��� ��ġ������ ���� �� �ִ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String... args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int m, MUL = 1000000;
		double DIV = 0.0000000001;
		m = Integer.parseInt(br.readLine());
		int[] arr = new int[m];
		int[] res = new int[m];
		double MOD = (double)1 / m + DIV;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < m; i++) {
			arr[i] = (int)((i + 1) * MOD * MUL);
			if (((i + 1) * MUL) % m != 0)
				arr[i]++;
		}
		
		while (st.hasMoreTokens()) {
			double x = Double.parseDouble(st.nextToken());
			x += DIV;
			int real = (int)(x * MUL);
			
			for (int i = 0; i < m; i++) {
				if (real < arr[i]) {
					res[i]++;
					break;
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			sb.append(res[i]).append(' ');
		}
			
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}