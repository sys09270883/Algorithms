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

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		while(T-- > 0){
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] note_1 = new int[M];
			for (int i = 0; i < M; i++) {
				note_1[i] = Integer.parseInt(st.nextToken());
			}
			
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] note_2 = new int[N];
			for (int i = 0; i < N; i++) {
				note_2[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(note_1);
			for (int i = 0; i < N; i++) {
				bw.append(Arrays.binarySearch(note_1, note_2[i]) > -1 ? '1' : '0');
				bw.newLine();
			}
		}
		
		bw.close();
		br.close();
	}
}