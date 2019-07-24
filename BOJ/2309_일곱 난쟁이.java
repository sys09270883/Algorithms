import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int sum = 0;
		int[] arr = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		Loop: for (int i = 0; i < 8; i++) {
			for (int j = i+1; j < 9; j++) {
				if(sum - arr[i] - arr[j] == 100){
					arr[i] = Integer.MAX_VALUE;
					arr[j] = Integer.MAX_VALUE;
					break Loop;
				}
			}
		}
		
		Arrays.sort(arr);
		for (int i = 0; i < 7; i++) {
			sb.append(arr[i]).append('\n');
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
}
