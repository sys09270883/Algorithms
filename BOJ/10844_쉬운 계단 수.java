import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, divisor = 1000000000;
	static long[][] arr;
	static BufferedWriter bw;
	static BufferedReader br;

	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new long[N+1][10];
		for (int i = 1; i < 10; i++) {
			arr[1][i] = 1;
		}
		
		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < 10; j++) {
				if(j == 0)
					arr[i][j] = arr[i-1][j+1];
				
				else if(j == 9)
					arr[i][j] = arr[i-1][j-1];
				
				else
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j+1];
				
				arr[i][j] %= divisor;
			}
		}
		
		long sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += arr[N][i];
		}

		bw.write(String.valueOf(sum % divisor));
		bw.close();
		br.close();
	}
}

