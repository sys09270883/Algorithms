/*
[문제]
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

[입력]
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

[출력]
주어진 수들 중 소수의 개수를 출력한다.

[풀이]
1~1000 까지의 수 중에서 소수를 판별하고 소수를 ArrayList에 오름차순으로 넣는다.
Collections.binarySearch로 찾으려는 수가 소수이면 cnt를 증가시키고 cnt를 출력한다.
*/
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
