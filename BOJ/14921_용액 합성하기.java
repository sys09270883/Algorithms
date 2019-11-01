/*
https://www.acmicpc.net/problem/14921
[����]
ȫ�ʹ� ȭ�п����Ҵ� �پ��� ����� �����ϰ� �ִ�.  �� ����� -100,000,000���� 100,000,000������ Ư�� ���� ���µ�,

���� ���� �� ����� ȥ���ϸ�, �� Ư������ �� ����� Ư������ ���� �ȴ�.

����� �� ����� ȥ���Ͽ� Ư������ 0�� ���� ����� ����� ������� �ϴµ�, �� ����� 10ml������� 10ml�� ����ְ�, �� 20ml ������� �� �ϳ� �ִ�. 
�Դٰ� ����� �跮�� �� ���, �� ����� ���� ���� 10ml�� ��� 20ml�� ����µ�, �� �ѹ��ۿ� �� �� ����.  
�׷��� �̸� ����� Ư�������� ����, � �� ����� ���� ������ ���ؾ� �Ѵ�.

���� ���, �����ҿ� �ִ� ��׵��� Ư������ [-101, -3, -1, 5, 93]�̶�� ����. �� ��쿡 Ư�� ���� ���� -101, 93�� ����� ȥ���ϸ� -8�� ����� ���� �� �ִ�. 
���� Ư������ 5�� ��װ� 93�� ����� ȥ���ϸ� Ư�� ���� 98�� ����� ���� �� �ִ�.  ��� ������ ������ ������ ����, Ư������ 2�� ����� 0�� ���� ����� ����̴�.

��׵��� Ư���� A_1, �� ,A_N�� ������������ �־����� ��, �� �� �� ���� ����� ȥ���Ͽ� ���� �� �ִ� 0�� ���� ����� Ư���� B�� ����Ͻÿ�.

[�Է�]
N
A_1 A_2 �� A_N
2 <= N <= 100,000
-100,000,000 <=A_i<=100,000,000
A_(i-1) <= A_i <= A_(i+1)

[���]
B

[Ǯ��]
�Է��� ���� ������������ ������ ��, 0�� ����� �ֵ��� ã�´�.

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String... args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> list = new ArrayList<Integer>(N);
		int a = 0, b = 0, max = 100000001;
		
		while (st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list, (o1, o2) -> Math.abs(o1) - Math.abs(o2));
		
		for (int i = 0; i < N - 1; i++) {
			if (Math.abs(list.get(i) + list.get(i + 1)) < max) {
				max = Math.abs(list.get(i) + list.get(i + 1));
				a = list.get(i);
				b = list.get(i + 1);
			}
		}
		
		bw.write(String.valueOf(a + b));
		bw.close();
		br.close();
	}
	
}