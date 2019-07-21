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
		
		int N = Integer.parseInt(br.readLine());
		Stack<Double> stack = new Stack<Double>();
		String expr = br.readLine();
		double[] value = new double[N];
		for (int i = 0; i < N; i++) {
			value[i] = Double.parseDouble(br.readLine());
		}
		
		for (int i = 0; i < expr.length(); i++) {
			char tmp = expr.charAt(i);
			
			if(isOperand(tmp)){
				double y = stack.pop();
				double x = stack.pop();
				stack.push(calc(x, y, tmp));
			}
			
			else
				stack.push(value[tmp-'A']);
		}
		
		bw.write(String.format("%.2f", stack.pop()));
		bw.close();
		br.close();
	}
	
	private static double calc(double x, double y, char o){
		switch(o){
		case '+':
			return x + y;
		case '-':
			return x - y;
		case '*':
			return x * y;
		case '/':
			return x / y;
		default :
			return Double.MIN_VALUE;
		}
	}
	
	private static boolean isOperand(char c){
		return c =='+' || c == '-' || c == '*' || c == '/' ? true : false; 
	}
}