import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int i = 0;
		for (i = 1; i < N; i++) {
			if(divideSum(i) == N){
				break;
			}
		}
		
		bw.write(i != N ? String.valueOf(i) : "0");
		bw.close();
		br.close();
	}

	private static int divideSum(int num){
		int divideSum = num;
		while(true){
			if(num == 0)
				break;
			
			divideSum += num%10;
			num /= 10;
		}
		
		return divideSum;
	}
}
