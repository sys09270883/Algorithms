/*
https://www.acmicpc.net/problem/1120
[문제]
길이가 N으로 같은 문자열 X와 Y가 있을 때, 두 문자열 X와 Y의 차이는 X[i] ≠ Y[i]인 i의 개수이다. 예를 들어, X=”jimin”, Y=”minji”이면, 둘의 차이는 4이다.

두 문자열 A와 B가 주어진다. 이때, A의 길이는 B의 길이보다 작거나 같다. 이제 A의 길이가 B의 길이와 같아질 때 까지 다음과 같은 연산을 할 수 있다.

A의 앞에 아무 알파벳이나 추가한다.
A의 뒤에 아무 알파벳이나 추가한다.
이때, A와 B의 길이가 같으면서, A와 B의 차이를 최소로 하는 프로그램을 작성하시오.

[입력]
첫째 줄에 A와 B가 주어진다. A와 B의 길이는 최대 50이고, A의 길이는 B의 길이보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.

[출력]
A와 B의 길이가 같으면서, A와 B의 차이를 최소가 되도록 했을 때, 그 차이를 출력하시오.

[풀이]
A가 B에 가장 많이 포함되는 경우를 찾는다.

ex)  A : abcd
     B : xabcdy
일 경우 가장 많이 겹치는 부분에서 앞에는 x, 뒤에는 y를 붙여준다.

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
