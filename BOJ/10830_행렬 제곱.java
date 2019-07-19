import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		int[][] A = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] C = matrixPow(A, B);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(C[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString().trim());
		
		bw.close();
		br.close();
	}
	
	private static int[][] matrixMul(int[][] A, int[][] B){
		int N = A.length;
		
		int[][] C = new int[N][N];
		
		for (int[] row : C) {
			Arrays.fill(row, 0);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					C[i][j] += (A[i][k] * B[k][j]) % 1000;
				}
				C[i][j] %= 1000;
			}
		}
		
		return C;
	}
	
	private static int[][] matrixPow(int[][] A, long B){
		if(B == 1) {
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A.length; j++) {
					if(A[i][j] == 1000)
						A[i][j] = 0;
				}
			}
			return A;
		}
		
		int[][] C = matrixPow(A, B/2);
		C = matrixMul(C, C);
		
		if(B % 2 == 1){
			C = matrixMul(C, A);
		}
		
		return C;
	}
}