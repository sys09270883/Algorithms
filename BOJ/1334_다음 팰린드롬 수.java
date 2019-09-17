/*
https://www.acmicpc.net/problem/1334
[����]
�Ӹ���� ���� ������ �о, �ڷ� �о ���� �����̴�. 101, 4, 6666�� ���� ���ڴ� �Ӹ���� ���̰�, 10, 564, 15452�� ���� ���ڴ� �ƴϴ�.

� �� N�� �־��� ��, N���� ū �Ӹ���� �� �߿��� ���� ���� ���� ����Ѵ�.

[�Է�]
ù° �ٿ� N�� �־�����. N�� �ִ� 50�ڸ��� ����̴�. ù ���ڴ� 0�� �ƴϴ�.

[���]
ù° �ٿ� ������ ������ ����Ѵ�.

[Ǯ��]
�켱 �Է��� 50�ڸ� ���̱� ������ long���ε� �ٷ� �� ����. ���� ���ڿ��� ó���ؾ� �Ѵ�.
����ó���� �� ���������� ū ������ �̷���.
  1. ���ڿ��� ���̰� Ȧ������ ¦������ �����Ѵ�.
  2. ¦���� ��� ���ڿ��� �������� �ɰ���(s1, s2).
    1) s1�� ������ ���� s2�� ���Ͽ� s1�� Ŭ ��� s1�� s1�� ������ ���� ��ģ��.
    2) s1�� s2���� ũ�� ���� ����
      A) s1�� ������ ��ȸ�Ѵ�. ��ȸ�ϸ鼭 ���� 9���� ������� ������Ű�� �ݺ����� Ż���ϸ�, 9�� ���� 0���� �ٲ��ش�.
      B) s1�迭�� ù ��° ���� 0�� ��쿡�� �ڸ����� �ϳ� �ø��� ù ���� ������ ���� 1�� �����.
  3. Ȧ���� ���� ��� ���� ���� ó���ϸ�, ¦���� ���� �����ϰ� ó���Ѵ�.
    1) ��� ���� 9���� ������� ��� ���� ������Ű�� s1�� ��� ��, s1�� ������ ���� ��ģ��.
    2) ��� ���� 9�� ��쿡�� 0���� �ٲ��ְ� ¦���� ���� ���� ó���� �Ѵ�.

*/
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();
		System.out.println(nextPelindrom(N));

		br.close();
	}

	private static String nextPelindrom(String N) {
		StringBuilder res = new StringBuilder();
		int len = N.length();
		
		if (len % 2 == 0) {
			int mid = (N.length() + 1) / 2;
			char[] s1 = N.substring(0, mid).toCharArray();
			char[] s2 = N.substring(mid, N.length()).toCharArray();

			reverse(s1);
			if (compare(s1, s2) > 0)
				res.append(reverse(s1)).append(reverse(s1));
			
			else {
				increase(reverse(s1));
				
				if (s1[0] == '0') {
					res.setLength(0);
					res.append('1');
					for (int i = 0; i < s1.length * 2 - 1; i++) {
						res.append('0');
					}
					res.append('1');
				}
				
				else
					res.append(s1).append(reverse(s1));
			}

			return res.toString();
		}
		
		else {
			int mid = (N.length() - 1) / 2;
			char[] s1 = N.substring(0, mid).toCharArray();
			char[] s2 = N.substring(mid + 1, N.length()).toCharArray();
			
			reverse(s1);
			if (compare(s1, s2) > 0) {
				res.append(reverse(s1)).append(N.charAt(mid)).append(reverse(s1));
			}
			
			else {
				if (N.charAt(mid) < '9') {
					res.append(reverse(s1)).append((char)(N.charAt(mid) + 1)).append(reverse(s1));
				}
				
				else if (N.charAt(mid) == '9' && N.length() == 1)
					res.append("11");
				
				else {
					increase(reverse(s1));
					
					if (s1[0] == '0') {
						res.setLength(0);
						res.append('1');
						for (int i = 0; i < s1.length * 2; i++) {
							res.append('0');
						}
						res.append('1');
					}
					
					else
						res.append(s1).append('0').append(reverse(s1));
				}
			}
			
			return res.toString();
		}
	}

	private static int compare(char[] s1, char[] s2) {
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] != s2[i])
				return s1[i] - s2[i];
		}

		return 0;
	}

	private static char[] reverse(char[] s) {
		char[] tmp = new char[s.length];

		int len = s.length;
		for (int i = 0; i < len; i++) {
			tmp[i] = s[len - 1 - i];
		}

		for (int i = 0; i < len; i++) {
			s[i] = tmp[i];
		}

		return s;
	}
	
	private static void increase(char[] s) {
		int len = s.length;
		for (int i = 0; i < len; i++) {
			if (s[len - 1 - i] < '9') {
				s[len - 1 - i]++;
				break;
			}
			
			else {
				s[len - 1 - i] = '0';
			}
		}
	}
}
