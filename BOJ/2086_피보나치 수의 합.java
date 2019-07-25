import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	final static int mod = 1000000000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long[][] fibo = {{1, 1}, {1, 0}};
		
		long[][] matrixB = matrixPow(fibo, b+2);
		long[][] matrixA = matrixPow(fibo, a+1);
		
		System.out.println(((matrixB[0][1] - 1) % mod - (matrixA[0][1] - 1) % mod + mod) % mod);
		
		bw.close();
		br.close();	
	}

	private static long[][] matrixMul(long[][] a, long[][] b){
		long c[][] = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] += (a[i][k] % mod * b[k][j] % mod) % mod;
				}
				c[i][j] %= mod;
			}
		}

		return c;
	}

	private static long[][] matrixPow(long[][] a, long b){
		if(b <= 1)
			return a;
		
		a = matrixPow(a, b/2);
		a = matrixMul(a, a);

		if(b % 2 == 1){
			long[][] tmp = {{1, 1}, {1, 0}};
			a = matrixMul(a, tmp);
		}

		return a;
	}
}