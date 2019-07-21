/*
[문제]
후위 표기식과 각 피연산자에 대응하는 값들이 주어져 있을 때, 그 식을 계산하는 프로그램을 작성하시오.

[입력]
첫째 줄에 피연산자의 개수(1 ≤ N ≤ 26) 가 주어진다. 그리고 둘째 줄에는 후위 표기식이 주어진다. (여기서 피연산자는 A~Z의 영대문자이며, A부터 순서대로 N개의 영대문자만이 사용되며, 길이는 100을 넘지 않는다) 그리고 셋째 줄부터 N+2번째 줄까지는 각 피연산자에 대응하는 값이 주어진다. (3번째 줄에는 A에 해당하는 값, 4번째 줄에는 B에 해당하는값 , 5번째 줄에는 C ...이 주어진다, 그리고 피연산자에 대응 하는 값은 정수이다)

[출력]
계산 결과를 소숫점 둘째 자리까지 출력한다. 

[풀이]
피연산자는 스택에 push한다.
연산자는 스택에서 2번 pop하고 그 수들을 연산하고, 연산한 수를 다시 스택에 push한다.
계산이 다 끝나면 스택에 남아있는 값을 출력한다.
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
