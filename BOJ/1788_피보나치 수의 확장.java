import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] fibo = new long[1000001];
		fibo[0] = 0;
		fibo[1] = 1;
		
		long mod = 1000000000;
		for (int i = 2; i < fibo.length; i++) {
			fibo[i] = (fibo[i-2] + fibo[i-1]) % mod;
		}
		
		if(n >= 0){
			if(n == 0){
				System.out.print("0\n" + fibo[n]);
				return;
			}
			System.out.print("1\n" + fibo[n]);
		}
		else {
			if(-n % 2 == 1){
				System.out.print("1\n" + fibo[-n]);
			}
			else{ 
				System.out.print("-1\n" + fibo[-n]);
			}
		}
		
		br.close();
	}
}