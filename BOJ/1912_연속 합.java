/*
https://www.acmicpc.net/problem/1912

[문제]
n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.

예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.

[입력]
첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

[출력]
첫째 줄에 답을 출력한다.

[풀이]
i까지의 최대 연속 부분합이 arr[i]보다 작으면 의미가 없으므로 값을 바꿔준다.
따라서 i까지 최대 연속 부분 합 sum[i] = max(sum[i - 1] + arr[i], arr[i])이다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine()), res = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        int[] sum = new int[n];
        
        for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
        
        sum[0] = arr[0];
        
        for (int i = 1; i < n; i++) {
        	sum[i] = Math.max(sum[i - 1] + arr[i], arr[i]);
			res = Math.max(res, sum[i]);
		}
        res = Math.max(res, sum[0]);
        
        bw.write(String.valueOf(res));
        bw.close();
        br.close();
    }
    
}
