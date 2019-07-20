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

