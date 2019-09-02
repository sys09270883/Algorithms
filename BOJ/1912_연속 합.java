/*
https://www.acmicpc.net/problem/1912

[����]
n���� ������ �̷���� ������ ������ �־�����. �츮�� �� �� ���ӵ� �� ���� ���� �����ؼ� ���� �� �ִ� �� �� ���� ū ���� ���Ϸ��� �Ѵ�. ��, ���� �� �� �̻� �����ؾ� �Ѵ�.

���� �� 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 �̶�� ������ �־����ٰ� ����. ���⼭ ������ 12+21�� 33�� ������ �ȴ�.

[�Է�]
ù° �ٿ� ���� n(1 �� n �� 100,000)�� �־����� ��° �ٿ��� n���� ������ �̷���� ������ �־�����. ���� -1,000���� ũ�ų� ����, 1,000���� �۰ų� ���� �����̴�.

[���]
ù° �ٿ� ���� ����Ѵ�.

[Ǯ��]
i������ �ִ� ���� �κ����� arr[i]���� ������ �ǹ̰� �����Ƿ� ���� �ٲ��ش�.
���� i���� �ִ� ���� �κ� �� sum[i] = max(sum[i - 1] + arr[i], arr[i])�̴�.

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
