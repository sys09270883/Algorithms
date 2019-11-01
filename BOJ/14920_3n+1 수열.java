/*
https://www.acmicpc.net/problem/14920
[문제]
다음의 점화식에 의해 정해지는 수열 C(n)을 생각하자:

     C(n+1) = C(n)/2     (C(n)이 짝수일 때)
            = 3*C(n)+1   (C(n)이 홀수일 때)
초항 C(1)이 자연수로 주어지면, 이 점화식은 자연수로 이루어지는 수열을 정한다.  예를 들어, C(1)=26이면, 다음의 수열이 된다.

26, 13, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1, 4, 2, 1, ...

이 경우, 수열의 뒷부분은 4, 2, 1 이 끝없이 반복된다. 실제로 C(1)이 5×260보다 작은 자연수인 모든 수열은 언젠가는 4, 2, 1로 끝나게 된다는 것이 알려져 있다.

주어진 입력 C(1)에 대하여 C(n)이 처음으로 1이 되는 n을 출력하시오.

[입력]
C(1); 1 ≤ C(1) ≤ 100000

[출력]
C(n)이 처음으로 1이 되는 n

[풀이]
주어진 조건에 따라 진행하면서 1일 때 반복문을 탈출하고 횟수를 출력한다.
 + C(1) = 1일 때를 간과했다...
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