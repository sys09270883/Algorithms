import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		Pattern p = Pattern.compile("[A-Z]");
		Stack<Character> stack = new Stack<Character>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char tmp = str.charAt(i);
			Matcher m = p.matcher(String.valueOf(tmp));

			if(m.matches())
				sb.append(tmp);

			else {
				if(stack.isEmpty()){
					stack.add(tmp);
				}

				else if(tmp == ')'){
					while(!stack.isEmpty() && !isBracket(stack.peek()))
						sb.append(stack.pop());
					stack.pop();
				}

				else if(isPlusOrMinus(tmp)){
					while(!stack.isEmpty() && (isMulOrDiv(stack.peek()) || isPlusOrMinus(stack.peek())))
						sb.append(stack.pop());
					stack.add(tmp);
				}

				else if(isMulOrDiv(tmp)){
					while(!stack.isEmpty() && isMulOrDiv(stack.peek()))
						sb.append(stack.pop());
					stack.add(tmp);
				}

				else
					stack.add(tmp);
			}
		}

		while(!stack.isEmpty())
			sb.append(stack.pop());

		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	private static boolean isPlusOrMinus(char c){
		return c == '+' || c == '-' ? true : false;
	}

	private static boolean isMulOrDiv(char c){
		return c == '*' || c == '/' ? true : false;
	}

	private static boolean isBracket(char c){
		return c == '(' ? true : false;
	}
}