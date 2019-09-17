/*
https://www.acmicpc.net/problem/2504
[문제]
4개의 기호 ‘(’, ‘)’, ‘[’, ‘]’를 이용해서 만들어지는 괄호열 중에서 올바른 괄호열이란 다음과 같이 정의된다.

한 쌍의 괄호로만 이루어진 ‘()’와 ‘[]’는 올바른 괄호열이다. 
만일 X가 올바른 괄호열이면 ‘(X)’이나 ‘[X]’도 모두 올바른 괄호열이 된다. 
X와 Y 모두 올바른 괄호열이라면 이들을 결합한 XY도 올바른 괄호열이 된다.
예를 들어 ‘(()[[]])’나 ‘(())[][]’ 는 올바른 괄호열이지만 ‘([)]’ 나 ‘(()()[]’ 은 모두 올바른 괄호열이 아니다. 
우리는 어떤 올바른 괄호열 X에 대하여 그 괄호열의 값(괄호값)을 아래와 같이 정의하고 값(X)로 표시한다. 

‘()’ 인 괄호열의 값은 2이다.
‘[]’ 인 괄호열의 값은 3이다.
‘(X)’ 의 괄호값은 2×값(X) 으로 계산된다.
‘[X]’ 의 괄호값은 3×값(X) 으로 계산된다.
올바른 괄호열 X와 Y가 결합된 XY의 괄호값은 값(XY)= 값(X)+값(Y) 로 계산된다.
예를 들어 ‘(()[[]])([])’ 의 괄호값을 구해보자.  ‘()[[]]’ 의 괄호값이 2 + 3×3=11 이므로  ‘(()[[ ]])’의 괄호값은 2×11=22 이다. 
그리고  ‘([])’의 값은 2×3=6 이므로 전체 괄호열의 값은 22 + 6 = 28 이다.

여러분이 풀어야 할 문제는 주어진 괄호열을 읽고 그 괄호값을 앞에서 정의한대로 계산하여 출력하는 것이다. 

[입력]
첫째 줄에 괄호열을 나타내는 문자열(스트링)이 주어진다. 단 그 길이는 1 이상, 30 이하이다.

[출력]
첫째 줄에 그 괄호열의 값을 나타내는 정수를 출력한다. 만일 입력이 올바르지 못한 괄호열이면 반드시 0을 출력해야 한다. 

[풀이]
구현에 있어서 예외처리가 생각보다 많이 까다로웠던 문제였다.
올바르지 않은 괄호열에 대한 예외처리를 크게 두 부분으로 나누었다.
  1. 왼쪽 괄호('(', '[')와 오른쪽 괄호(')', ']')의 개수가 다르거나, 문자열을 순회하는 중 오른쪽 괄호가 왼쪽 괄호보다 클 경우
  2. 빈 스택에 접근하거나, 괄호를 숫자로 바꾸려는 시도를 할 경우 ('(', '['로만 이루어져 있는 경우)
  
특히 2번 경우에 대해서는 자바의 Exception으로 같이 처리했다.

스택에서 오른쪽 괄호를 만나면 2가지 경우가 있다.
  1. 짝이 되는 왼쪽 괄호를 만나는 경우
  2. 그 외의 경우

1번의 경우에서는 괄호를 빼내고, 해당 괄호의 값을 넣는다.
2번의 경우에서는 해당 괄호의 짝을 찾을 때까지 스택의 수들을 서로 더하면서 넣어주기를 반복한다. 짝이 되는 괄호를 만나면 해당 값을 곱하여 넣는다.

최종적으로 문자열을 순회한 후 스택에 있는 값들을 모두 더하여 출력한다.
*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int leftBracket = 0, rightBracket = 0, res = 0;
		String input = br.readLine();
		Stack<String> stack = new Stack<String>();

		try {
			for (int i = 0; i < input.length(); i++) {
				String cur = String.valueOf(input.charAt(i));

				if (rightBracket > leftBracket) {
					System.out.println(0);
					return;
				}

				switch (cur) {

				case "(":
					stack.add(cur);
					leftBracket++;
					break;

				case "[":
					stack.add(cur);
					leftBracket++;
					break;

				case ")":
					if (stack.peek().equals("(")) {
						stack.pop();
						stack.add("2");
					}

					else {
						while (true) {
							String p = stack.pop();

							if (stack.peek().equals("(")) {
								stack.pop();
								stack.add(String.valueOf(Integer.parseInt(p) * 2));
								break;
							}

							else {
								String p2 = stack.pop();
								stack.add(String.valueOf(Integer.parseInt(p) + Integer.parseInt(p2)));
							}
						}
					}

					rightBracket++;
					break;

				case "]":
					if (stack.peek().equals("[")) {
						stack.pop();
						stack.add("3");
					}

					else {
						while (true) {
							String p = stack.pop();

							if (stack.peek().equals("[")) {
								stack.pop();
								stack.add(String.valueOf(Integer.parseInt(p) * 3));
								break;
							}

							else {
								String p2 = stack.pop();
								stack.add(String.valueOf(Integer.parseInt(p) + Integer.parseInt(p2)));
							}
						}
					}

					rightBracket++;
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(0);
			return;
		}

		if (leftBracket != rightBracket) {
			System.out.println(0);
			return;
		}

		while (!stack.isEmpty()) {
			res += Integer.parseInt(stack.pop());
		}

		System.out.println(res);
		br.close();
		return;
	}

}
