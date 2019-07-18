import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		ArrayList<Integer> primeList = new ArrayList<Integer>();

		int[] arr = new int[1001];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}

		for (int i = 2; i < 1000; i++) {
			if(arr[i] == 0)
				continue;
			for (int j = i+i; j < 1000; j+=i) {
				arr[j] = 0;
			}
		}

		for (int i = 2; i < arr.length-2; i++) {
			if(arr[i]!=0)
				primeList.add(arr[i]);
		}

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int cnt = 0;
		while(N-- > 0){
			if(Collections.binarySearch(primeList, Integer.parseInt(st.nextToken())) >= 0)
				cnt++;
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}

}