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

		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();

		int N = A.length();
		int M = B.length();

		char[][] arr = new char[M][N];
		for (char[] row : arr) {
			Arrays.fill(row, '.');
		}

		int idxA = 0, idxB = 0;
		LOOP: for (idxA = 0; idxA < N; idxA++) {
			for (idxB = 0; idxB < M; idxB++) {
				if(A.charAt(idxA) == B.charAt(idxB)){
					break LOOP;
				}
			}
		}


		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(j == idxA)
					for (int k = 0; k < M; k++) {
						arr[k][j] = B.charAt(k);
					}
			}
			if(i == idxB)
				for (int k = 0; k < N; k++) {
					arr[i][k] = A.charAt(k);
				}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString().trim());

		bw.close();
		br.close();
	}
}

