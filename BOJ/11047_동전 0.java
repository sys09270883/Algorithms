import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int cnt = 0;
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[N-i-1] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			if(arr[i] > K)
				continue;

			cnt += K / arr[i];
			K %= arr[i];
		}

		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}
