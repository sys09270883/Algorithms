/*
[문제]
싱기한 네자리 숫자란, [1000,9999]인 10진수 숫자중에서,  다음의 조건을 만족하는 숫자를 말한다.

숫자를 10진수, 12진수, 16진수로 나타낸 다음, 각각의 숫자에 대해, 각 숫자의 자리수를 더했을 때, 세 값이 모두 같아야 한다.
여러분은 싱기한 네자리 숫자를 모두 출력해야 한다.

[입력]
입력은 주어지지 않는다.

[출력]
싱기한 네자리 숫자를 오름차순으로 한줄에 하나씩 출력한다.

[풀이]
각 자리수를 해당 진수로 나눈 합을 비교하면 된다.


*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int d;
		StringBuilder sb = new StringBuilder();
		for (int i = 1000; i < 10000; i++) {
			d = func(i, 10);
			if(d == func(i, 12) && d == func(i, 16))
				sb.append(i).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static int func(int n, int base) {
		int ret = 0;
		
		while(n > 0) {
			ret += n % base;
			n /= base;
		}
		
		return ret;
	}
}
