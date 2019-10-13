/*
https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWuSgKpqmooDFASy&categoryId=AWuSgKpqmooDFASy&categoryType=CODE
[����]

�θ� �ձ��� ��� �������� ħ���� ���߾���.

������ �������� ������ ���������� ���� �������� ������ ���� ���������� �ı����ߴ�.

�θ� �ձ��� Ư¡�� ��� ���õ��� �Ǽ� �� �� �Ϸķ� �̾����� �������.

��� ���ÿ� ���� ������ ��ġ�ϸ� �� ���ÿ� �Ÿ� D ������ �ٸ� ���ÿ��� ���� ������ �ִ� ������ �����ų�, Ȥ�� ���� �������� �Ÿ� D ������ �ٸ� ���÷� �����°��� �����ϴ�.

��� �ձ��� ��ħ���� ����ϱ� ���ؼ� ��� ���� �̵��� �Ǿ���ϸ� ��� ���� ���� ���̿� ���������� �̵��� �����ϵ��� ���� ������ ����Ϸ��� �Ѵ�.
(��, 0�� ��ġ�� N+1�� ��ġ���� ���� ������ ���� �Ѵ�.)

������ ���� �Ǽ��� ���Ͽ� �ּ� ������ ��ġ�ϴ� ��ȹ�� ������� �Ҷ�

���õ鸶�� ���������� �����ִ� ���� ���� ������ �־����� ��, �̵��� �ʿ��� ���������� �ּ� ������ ���Ͽ���.


[�Է�]

ù ��° �ٿ� �׽�Ʈ ���̽��� �� T�� �־�����.

�� �׽�Ʈ ���̽��� ù ��° �ٿ��� �θ� �ձ��� ���� �� N(1 �� N �� 300,000)�� �̵� ���� �Ÿ� D(1 �� D �� N)�� �־�����.

�� ��° �ٿ��� 1�� ���ú��� ���ʴ�� �� ���ÿ� ���������� �����ִ� ���� ���� ������ �־�����.

1�� ���������� �ǹ��ϸ� 0�� �ı� ���� ���� �ǹ��Ѵ�.

i�� ���ÿ� (i+1)�� ���� ������ �Ÿ��� ��� 1 �� i < N�� ���ؼ� 1�̴�.


[���]

�� �׽�Ʈ ���̽����� ��#x��(x�� �׽�Ʈ���̽� ��ȣ�� �ǹ��ϸ� 1���� �����Ѵ�)�� ����ϰ�,

�� �׽�Ʈ ���̽����� �θ� �ձ��� �߰��� �Ǽ� �ؾ� �ϴ� �������� �� �ּ� ������ ���Ͽ���.

[Ǯ��]
0�� ��쿡 ī��Ʈ�� �÷��ְ� 0�� �ƴ� ������ �� ������ ī��Ʈ�� �̵��Ÿ��� �����ְ� ī��Ʈ�� 0���� �ʱ�ȭ ��Ų��. (�Ҽ��� ����)
�Է¿� 0�� ���� ��쿡�� ī��Ʈ�� �̵��Ÿ��� ������ ���� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()), N, D, ans, cnt;
		StringBuilder res = new StringBuilder();
		StringTokenizer st = null;
		
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			ans = 0;
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) != 0) {
					ans += cnt / D;
					cnt = 0;
				}
				
				else
					cnt++;
			}
			
			res.append('#').append(i).append(' ').append(ans == 0 ? ans += cnt / D : ans).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
