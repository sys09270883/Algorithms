/*
[문제]
크기가 N*N인 행렬 A가 주어진다. 이때, A의 B제곱을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.

[입력]
첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)

둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다. 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.

[출력]
첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B제곱한 결과를 출력한다.

[풀이]
행렬곱에서 C[i][j]에 더할 때 1000으로 나눠줘야 하고, 더한 후에도 1000으로 나누어 주어야 한다.
matrixPow() 메소드에서 곱셈의 분할정복을 적용하며 B의 값이 1일 때, 원소의 값이 1000일 경우를 예외처리한다. 
*/
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
