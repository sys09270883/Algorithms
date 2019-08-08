import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long[][] arr;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		arr = new long[N + 1][2];
		cnt = 0;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				for (int k = j + 1; k < N + 1; k++) {
					isRightAngle(i, j, k);
				}
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
	
	private static void isRightAngle(int i, int j, int k){
		if(isZero(i, j, k) || isZero(j, k, i) || isZero(k, i, j))
			cnt++;
	}
	
	private static boolean isZero(int i, int j, int k) {
		if((arr[i][0] - arr[j][0]) * (arr[i][0] - arr[k][0]) + (arr[i][1] - arr[j][1]) * (arr[i][1] - arr[k][1]) == 0)
			return true;
		
		return false;
	}
}
