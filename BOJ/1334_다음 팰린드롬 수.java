/*
https://www.acmicpc.net/problem/1334
[문제]
팰린드롬 수는 앞으로 읽어도, 뒤로 읽어도 같은 숫자이다. 101, 4, 6666와 같은 숫자는 팰린드롬 수이고, 10, 564, 15452와 같은 숫자는 아니다.

어떤 수 N이 주어질 때, N보다 큰 팰린드롬 수 중에서 가장 작은 수를 출력한다.

[입력]
첫째 줄에 N이 주어진다. N은 최대 50자리인 양수이다. 첫 숫자는 0이 아니다.

[출력]
첫째 줄에 문제의 정답을 출력한다.

[풀이]
우선 입력이 50자리 수이기 때문에 long으로도 다룰 수 없다. 따라서 문자열로 처리해야 한다.
예외처리가 좀 복잡하지만 큰 로직은 이렇다.
  1. 문자열의 길이가 홀수인지 짝수인지 구분한다.
  2. 짝수일 경우 문자열을 절반으로 쪼갠다(s1, s2).
    1) s1을 뒤집은 수와 s2를 비교하여 s1이 클 경우 s1과 s1을 뒤집은 수를 합친다.
    2) s1이 s2보다 크지 않은 경우는
      A) s1을 뒤집고 순회한다. 순회하면서 값이 9보다 작을경우 증가시키고 반복문을 탈출하며, 9일 경우는 0으로 바꿔준다.
      B) s1배열의 첫 번째 값이 0일 경우에는 자리수를 하나 올리고 첫 수와 마지막 수를 1로 만든다.
  3. 홀수일 경우는 가운데 수를 따로 처리하며, 짝수일 경우와 동일하게 처리한다.
    1) 가운데 수가 9보다 작을경우 가운데 수를 증가시키고 s1과 가운데 수, s1을 뒤집은 수를 합친다.
    2) 가운데 수가 9일 경우에는 0으로 바꿔주고 짝수일 때와 같은 처리를 한다.

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
