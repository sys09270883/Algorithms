/*
[문제]
문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.

먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.

알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
문자열의 시작과 끝은 공백이 아니다.
'<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다. 
단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다. 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.

[입력]
첫째 줄에 문자열 S가 주어진다. S의 길이는 100,000 이하이다.

[출력]
첫째 줄에 문자열 S의 단어를 뒤집어서 출력한다.

[풀이]
'<'와 '>'를 만날 때마다 flag를 바꿔준다.
flag가 1일 때에는 스택에 넣고, -1일 때에는 큐에 넣어주면서 공백, 문장의 마지막, '<'를 만나면 res에 더해준다.
풀기는 풀었지만 코드가 난해해서 알아보기 힘든 것같다...
다시 한번 풀어보자...


*/
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Character> stack = new Stack<>();
		Queue<Character> queue = new LinkedList<>();
		StringBuilder res = new StringBuilder();
		String str = br.readLine();
		int flag = 1;
		
		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			
			if (tmp == '<') {
				flag = -1;
				if(!stack.isEmpty()) {
					while(!stack.isEmpty())
						res.append(stack.pop());
				}
			}
			
			else if (tmp == '>') {
				flag = 1;
				queue.add(tmp);
				while(!queue.isEmpty())
					res.append(queue.poll());
				continue;
			}
			
			if (flag == 1)
				stack.add(tmp);
			
			else if (flag == -1)
				queue.add(tmp);
			
			if (flag == 1 && (tmp == ' ' || i == str.length() - 1 || tmp == '<')) {
				if (tmp == ' ')
					stack.pop();
				
				while (!stack.isEmpty())
					res.append(stack.pop());
				res.append(' ');
			}
		}
		
		bw.write(res.toString());
		bw.close();
		br.close();
	}
}
