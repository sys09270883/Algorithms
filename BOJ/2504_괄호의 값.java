/*
https://www.acmicpc.net/problem/2504
[����]
4���� ��ȣ ��(��, ��)��, ��[��, ��]���� �̿��ؼ� ��������� ��ȣ�� �߿��� �ùٸ� ��ȣ���̶� ������ ���� ���ǵȴ�.

�� ���� ��ȣ�θ� �̷���� ��()���� ��[]���� �ùٸ� ��ȣ���̴�. 
���� X�� �ùٸ� ��ȣ���̸� ��(X)���̳� ��[X]���� ��� �ùٸ� ��ȣ���� �ȴ�. 
X�� Y ��� �ùٸ� ��ȣ���̶�� �̵��� ������ XY�� �ùٸ� ��ȣ���� �ȴ�.
���� ��� ��(()[[]])���� ��(())[][]�� �� �ùٸ� ��ȣ�������� ��([)]�� �� ��(()()[]�� �� ��� �ùٸ� ��ȣ���� �ƴϴ�. 
�츮�� � �ùٸ� ��ȣ�� X�� ���Ͽ� �� ��ȣ���� ��(��ȣ��)�� �Ʒ��� ���� �����ϰ� ��(X)�� ǥ���Ѵ�. 

��()�� �� ��ȣ���� ���� 2�̴�.
��[]�� �� ��ȣ���� ���� 3�̴�.
��(X)�� �� ��ȣ���� 2����(X) ���� ���ȴ�.
��[X]�� �� ��ȣ���� 3����(X) ���� ���ȴ�.
�ùٸ� ��ȣ�� X�� Y�� ���յ� XY�� ��ȣ���� ��(XY)= ��(X)+��(Y) �� ���ȴ�.
���� ��� ��(()[[]])([])�� �� ��ȣ���� ���غ���.  ��()[[]]�� �� ��ȣ���� 2 + 3��3=11 �̹Ƿ�  ��(()[[ ]])���� ��ȣ���� 2��11=22 �̴�. 
�׸���  ��([])���� ���� 2��3=6 �̹Ƿ� ��ü ��ȣ���� ���� 22 + 6 = 28 �̴�.

�������� Ǯ��� �� ������ �־��� ��ȣ���� �а� �� ��ȣ���� �տ��� �����Ѵ�� ����Ͽ� ����ϴ� ���̴�. 

[�Է�]
ù° �ٿ� ��ȣ���� ��Ÿ���� ���ڿ�(��Ʈ��)�� �־�����. �� �� ���̴� 1 �̻�, 30 �����̴�.

[���]
ù° �ٿ� �� ��ȣ���� ���� ��Ÿ���� ������ ����Ѵ�. ���� �Է��� �ùٸ��� ���� ��ȣ���̸� �ݵ�� 0�� ����ؾ� �Ѵ�. 

[Ǯ��]
������ �־ ����ó���� �������� ���� ��ٷο��� ��������.
�ùٸ��� ���� ��ȣ���� ���� ����ó���� ũ�� �� �κ����� ��������.
  1. ���� ��ȣ('(', '[')�� ������ ��ȣ(')', ']')�� ������ �ٸ��ų�, ���ڿ��� ��ȸ�ϴ� �� ������ ��ȣ�� ���� ��ȣ���� Ŭ ���
  2. �� ���ÿ� �����ϰų�, ��ȣ�� ���ڷ� �ٲٷ��� �õ��� �� ��� ('(', '['�θ� �̷���� �ִ� ���)
  
Ư�� 2�� ��쿡 ���ؼ��� �ڹ��� Exception���� ���� ó���ߴ�.

���ÿ��� ������ ��ȣ�� ������ 2���� ��찡 �ִ�.
  1. ¦�� �Ǵ� ���� ��ȣ�� ������ ���
  2. �� ���� ���

1���� ��쿡���� ��ȣ�� ������, �ش� ��ȣ�� ���� �ִ´�.
2���� ��쿡���� �ش� ��ȣ�� ¦�� ã�� ������ ������ ������ ���� ���ϸ鼭 �־��ֱ⸦ �ݺ��Ѵ�. ¦�� �Ǵ� ��ȣ�� ������ �ش� ���� ���Ͽ� �ִ´�.

���������� ���ڿ��� ��ȸ�� �� ���ÿ� �ִ� ������ ��� ���Ͽ� ����Ѵ�.
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
