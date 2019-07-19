/*
[문제]
실수 a와 정수 b가 주어졌을 때, a의 b제곱을 정확하게 계산하는 프로그램을 작성하시오.

[입력]
첫째 줄에 a와 b가 주어진다. (0 < a < 100, 1 ≤ b ≤ 100) a는 최대 소수점 9자리이며, 소수가 0으로 끝나는 경우는 없다. a는 항상 소수점이 포함되어 있다.

[출력]
첫째 줄에 a의 b제곱을 출력한다.

[풀이]
정확한 숫자 표현이 중요했던 문제였다. 실수를 정확하게 다루기 위해서 BigDecimal 클래스와 과학적 표현을 없애기 위해서 toPlainString() 메소드를 사용했다.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		BigDecimal res = new BigDecimal(st.nextToken()).pow(Integer.parseInt(st.nextToken()));
		
		bw.write(res.toPlainString());
		
		bw.close();
		br.close();
	}
}
