/*
https://www.acmicpc.net/problem/2805
[����]
����̴� ���� M���Ͱ� �ʿ��ϴ�. ��ó�� ������ ������ ���� ��� ���ع��ȱ� ������, ���ο� ���� �㰡�� ��û�ߴ�. 
���δ� ����̳� �� ��ó�� ���� �� �ٿ� ���� ���� �㰡�� ���־���, ����̴� ���� ������ �������ܱ��� �̿��ؼ� ������ ���Ұ��̴�.

�������ܱ�� ������ ���� �����Ѵ�. ����, ����̴� ���ܱ⿡ ���� H�� �����ؾ� �Ѵ�. ���̸� �����ϸ� �鳯�� �����κ��� H���� ���� �ö󰣴�. 
�� ����, �� �ٿ� �������ִ� ������ ��� �����ع�����. ����, ���̰� H���� ū ������ H ���� �κ��� �߸� ���̰�, ���� ������ �߸��� ���� ���̴�. 
���� ���, �� �ٿ� �������ִ� ������ ���̰� 20, 15, 10, 17�̶�� ����. 
����̰� ���̸� 15�� �����ߴٸ�, ������ �ڸ� ���� ���̴� 15, 15, 10, 15�� �� ���̰�, ����̴� ���̰� 5�� ������ 2�� ������ ��� ���� �� ���̴�. 
(�� 7���͸� ���� ��� ����)

����̴� ȯ�濡 �ſ� ������ ���� ������, ������ �ʿ��� ��ŭ�� ������ ���������� �Ѵ�. 
�̶�, ��� M������ ������ ���� �������� ���ؼ� ���ܱ⿡ ������ �� �ִ� ������ �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ �� N�� ����̰� ������ ���������� �ϴ� ������ ���� M�� �־�����. (1 �� N �� 1,000,000, 1 �� M �� 2,000,000,000)

��° �ٿ��� ������ ���̰� �־�����. ������ ������ ���� �׻� M�� �ѱ� ������, ����̴� ���� �ʿ��� ������ �׻� ������ �� �ִ�. ���̴� 1,000,000,000���� �۰ų� ���� ���� ���� �Ǵ� 0�̴�.

[���]
��� M������ ������ ���� �������� ���ؼ� ���ܱ⿡ ������ �� �ִ� ������ �ִ��� ����Ѵ�.

[Ǯ��]
1���� �ִ� ũ�����(high) ������ ���ϰ�, mid���� ū �������� �հ� M�� ���ϸ鼭 res�� �����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static long N, M;
	static long[] trees;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		st = new StringTokenizer(br.readLine());
		trees = new long[1000001];
		long low = 1, high = 0, res = 0;
		
		for (int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
			high = high < trees[i] ? trees[i] : high;
		}
		
		while (low <= high) {
			long mid = low + (high - low) / 2;
			long sum = 0;
			
			for (int i = 0; i < N; i++) {
				if (mid < trees[i]) {
					sum += trees[i] - mid;
				}
			}
			
			if (sum >= M) {
				if (res < mid)
					res = mid;
				low = mid + 1;
			}
			
			else {
				high = mid - 1;
			}
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
}
