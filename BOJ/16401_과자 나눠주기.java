/*
https://www.acmicpc.net/problem/16401
[����]
������ �Ǹ�, ȫ���� ������ ��ī���� � �´�.  ���� ���� ��ī���� �޷��� ���� ȫ���̴� ���� ���ڸ� �ϳ��� �����ش�.

��ī���� ���ڸ� �Դ� ������ ���� ���� �ʱ� ������, ȫ���̴� ��ī�鿡�� �ִ��� �� ���ڸ� �����ַ��� �Ѵ�.

�׷��� ������ ������ ���̰� �ϳ��� �ٸ��� ��ī���� �ο��� �Ͼ��. ���� �ݵ�� ��� ��ī���� ���� ������ ���� ���ڸ� �����־�� �Ѵ�.

M���� ��ī�� �ְ� N���� ���ڰ� ���� ��, ��ī 1���� �� �� �ִ� ���� ������ �ִ� ���̸� ���϶�.

��, ���� ���ڴ� ���̿� ������� ���� �������� ������ �� ������, ���ڸ� �ϳ��� ��ĥ ���� ����. ��, ���� ������ ���̴� ���� �������� �Ѵ�.

[�Է�]
ù° �ٿ�  ��ī�� �� M (1 �� M �� 1,000,000), ������ �� N (1 �� N �� 1,000,000)�� �־�����.

��° �ٿ� ���� N���� ���� L1, L2, ..., LN�� �������� ���еǾ� �־�����. ������ ���̴� (1 �� L1, L2, ..., LN �� 1,000,000,000) �� �����Ѵ�.

[���]
ù° �ٿ� ��ī 1���� �� �� �ִ� ���� ������ �ִ� ���̸� ����Ѵ�.

��, ��� ��ī���� ���� ������ ������ڸ� ������ �� ���ٸ�, 0�� ����Ѵ�.

[Ǯ��]
�̺�Ž������ ������ �� �� �ִ� �� �� ���� ū ���� ���Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int[] snack;
	static int M, N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		snack = new int[1000001];
		int low = 1, high = 0, res = 0;
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
			high = Math.max(high, snack[i]);
		}
		
		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			if (possible(mid)) {
				res = mid;
				low = mid + 1;
			}
			
			else
				high = mid - 1;
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static boolean possible(int len) {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			cnt += snack[i] / len;
		}
		
		if (cnt >= M)
			return true;
		
		return false;
	}
	
}
