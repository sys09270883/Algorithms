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

		int RUN = Integer.parseInt(br.readLine());
		int cnt = 0;
		Stack<Character> stack = new Stack<Character>();
		while(RUN-- > 0){
			String str = br.readLine();
			int len = str.length();

			for (int i = 0; i < len; i++) {
				if(stack.isEmpty())
					stack.push(str.charAt(i));

				else {
					if(stack.peek() == str.charAt(i))
						stack.pop();

					else
						stack.push(str.charAt(i));
				}
			}
			if(stack.isEmpty())
				cnt++;

			while(!stack.isEmpty())
				stack.pop();

		}

		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}