/*
https://www.acmicpc.net/problem/4889
[문제]
여는 괄호와 닫는 괄호만으로 이루어진 문자열이 주어진다. 여기서 안정적인 문자열을 만들기 위한 최소 연산의 수를 구하려고 한다. 안정적인 문자열의 정의란 다음과 같다.

빈 문자열은 안정적이다.
S가 안정적이라면, {S}도 안정적인 문자열이다.
S와 T가 안정적이라면, ST(두 문자열의 연결)도 안정적이다.
{}, {}{}, {{}{}}는 안정적인 문자열이지만, }{, {{}{, {}{는 안정적인 문자열이 아니다.

문자열에 행할 수 있는 연산은 여는 괄호를 닫는 괄호로 바꾸거나, 닫는 괄호를 여는 괄호로 바꾸는 것 2가지이다.

[입력]
입력은 여러 개의 데이터 세트로 이루어져 있다. 각 데이터 세트는 한 줄로 이루어져 있다. 줄에는 여는 괄호와 닫는 괄호만으로 이루어진 문자열이 주어진다. 문자열의 길이가 2000을 넘는 경우는 없고, 항상 길이는 짝수이다.

입력의 마지막 줄은 '-'가 한 개 이상 주어진다.

[출력]
각 테스트 케이스에 대해서, 테스트 케이스 번호와 입력으로 주어진 문자열을 안정적으로 바꾸는데 필요한 최소 연산의 수를 출력한다.

[풀이]
'{'와 '}'가 만나는 경우 스택에서 지워주고 나머지 경우는 다 넣어준다. 2개씩 나누어서 '}'와 '{'가 만나는 경우는 cnt+=2, 그 외의 경우에는 cnt++을 해준다. 
+ '}'와 '{'가 만나는 경우를 스택에서 확인할 때에는 '{'와 '}'로 확인한다.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int idx = 0;
		int cnt;
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		while(true){
			cnt = 0;
			Stack<Character> st = new Stack<Character>();
			String str = br.readLine();
			if(str.contains("-"))
				break;
			
			for (int i = 0; i < str.length(); i++) {
				if(st.isEmpty())
					st.push(str.charAt(i));
				
				else {
					char top = st.peek();
					if(top == '{' && str.charAt(i) == '}')
						st.pop();
					else
						st.push(str.charAt(i));
				}
				
			}
			
			while(!st.isEmpty())
				sb.append(st.pop());
			
			for (int i = 1; i < sb.length(); i+=2) {
				if(sb.charAt(i-1) == '{' && sb.charAt(i) == '}')
					cnt+=2;
				else
					cnt++;
			}
			
			sb.setLength(0);
			res.append(String.format("%d. %d\n", ++idx, cnt));
		}
		
		bw.write(res.toString().trim());
		
		bw.close();
		br.close();
	}
}

