/*
https://www.acmicpc.net/problem/14920
[����]
������ ��ȭ�Ŀ� ���� �������� ���� C(n)�� ��������:

     C(n+1) = C(n)/2     (C(n)�� ¦���� ��)
            = 3*C(n)+1   (C(n)�� Ȧ���� ��)
���� C(1)�� �ڿ����� �־�����, �� ��ȭ���� �ڿ����� �̷������ ������ ���Ѵ�.  ���� ���, C(1)=26�̸�, ������ ������ �ȴ�.

26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1, 4, 2, 1, ...

�� ���, ������ �޺κ��� 4, 2, 1 �� ������ �ݺ��ȴ�. ������ C(1)�� 5��260���� ���� �ڿ����� ��� ������ �������� 4, 2, 1�� ������ �ȴٴ� ���� �˷��� �ִ�.

�־��� �Է� C(1)�� ���Ͽ� C(n)�� ó������ 1�� �Ǵ� n�� ����Ͻÿ�.

[�Է�]
C(1); 1 �� C(1) �� 100000

[���]
C(n)�� ó������ 1�� �Ǵ� n

[Ǯ��]
�־��� ���ǿ� ���� �����ϸ鼭 1�� �� �ݺ����� Ż���ϰ� Ƚ���� ����Ѵ�.
 + C(1) = 1�� ���� �����ߴ�...
*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String... args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int start = n, next;
		int i = 1;
		boolean RUN = true;
		
		if (n == 1) {
			bw.write("1");
			RUN = false;
		}
		
		while (RUN) {
			if (start % 2 == 0)
				next = start / 2;
			
			else
				next = 3 * start + 1;
			
			if (next == 1) {
				bw.write(String.valueOf((i + 1)));
				break;
			}
			
			start = next;
			i++;
		}
		
		bw.close();
		br.close();
	}
	
}