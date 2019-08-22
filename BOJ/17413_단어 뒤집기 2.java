/*
[����]
���ڿ� S�� �־����� ��, �� ���ڿ����� �ܾ ���������� �Ѵ�.

����, ���ڿ� S�� �Ʒ��Ͱ� ���� ��Ģ�� ��Ų��.

���ĺ� �ҹ���('a'-'z'), ����('0'-'9'), ����(' '), Ư�� ����('<', '>')�θ� �̷���� �ִ�.
���ڿ��� ���۰� ���� ������ �ƴϴ�.
'<'�� '>'�� ���ڿ��� �ִ� ��� �����ư��鼭 �����ϸ�, '<'�� ���� �����Ѵ�. ��, �� ������ ������ ����.
�±״� '<'�� �����ؼ� '>'�� ������ ���̰� 3 �̻��� �κ� ���ڿ��̰�, '<'�� '>' ���̿��� ���ĺ� �ҹ��ڿ� ���鸸 �ִ�. 
�ܾ�� ���ĺ� �ҹ��ڿ� ���ڷ� �̷���� �κ� ���ڿ��̰�, �����ϴ� �� �ܾ�� ���� �ϳ��� �����Ѵ�. �±״� �ܾ �ƴϸ�, �±׿� �ܾ� ���̿��� ������ ����.

[�Է�]
ù° �ٿ� ���ڿ� S�� �־�����. S�� ���̴� 100,000 �����̴�.

[���]
ù° �ٿ� ���ڿ� S�� �ܾ ����� ����Ѵ�.

[Ǯ��]
'<'�� '>'�� ���� ������ flag�� �ٲ��ش�.
flag�� 1�� ������ ���ÿ� �ְ�, -1�� ������ ť�� �־��ָ鼭 ����, ������ ������, '<'�� ������ res�� �����ش�.
Ǯ��� Ǯ������ �ڵ尡 �����ؼ� �˾ƺ��� ���� �Ͱ���...
�ٽ� �ѹ� Ǯ���...


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
