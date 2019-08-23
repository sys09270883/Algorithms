/*
https://www.acmicpc.net/problem/1120
[����]
���̰� N���� ���� ���ڿ� X�� Y�� ���� ��, �� ���ڿ� X�� Y�� ���̴� X[i] �� Y[i]�� i�� �����̴�. ���� ���, X=��jimin��, Y=��minji���̸�, ���� ���̴� 4�̴�.

�� ���ڿ� A�� B�� �־�����. �̶�, A�� ���̴� B�� ���̺��� �۰ų� ����. ���� A�� ���̰� B�� ���̿� ������ �� ���� ������ ���� ������ �� �� �ִ�.

A�� �տ� �ƹ� ���ĺ��̳� �߰��Ѵ�.
A�� �ڿ� �ƹ� ���ĺ��̳� �߰��Ѵ�.
�̶�, A�� B�� ���̰� �����鼭, A�� B�� ���̸� �ּҷ� �ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� A�� B�� �־�����. A�� B�� ���̴� �ִ� 50�̰�, A�� ���̴� B�� ���̺��� �۰ų� ����, ���ĺ� �ҹ��ڷθ� �̷���� �ִ�.

[���]
A�� B�� ���̰� �����鼭, A�� B�� ���̸� �ּҰ� �ǵ��� ���� ��, �� ���̸� ����Ͻÿ�.

[Ǯ��]
A�� B�� ���� ���� ���ԵǴ� ��츦 ã�´�.

ex)  A : abcd
     B : xabcdy
�� ��� ���� ���� ��ġ�� �κп��� �տ��� x, �ڿ��� y�� �ٿ��ش�.

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String A = st.nextToken();
		String B = st.nextToken();
		
		int cnt, min = Integer.MAX_VALUE;
		for (int i = 0; i <= B.length() - A.length(); i++) {
			cnt = 0;
			
			for (int j = 0; j < A.length(); j++) {
				if(A.charAt(j) != B.charAt(j + i))
					cnt++;
			}
			
			min = Math.min(min, cnt);
		}
		
		bw.write(String.valueOf(min));
		bw.close();
		br.close();
	}
}
