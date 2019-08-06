/*
https://www.acmicpc.net/problem/1038
[문제]
음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다.
예를 들어, 321과 950은 감소하는 수지만, 322와 958은 아니다. N번째 감소하는 수를 출력하는 프로그램을 작성하시오.
0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다. 만약 N번째 감소하는 수가 없다면 -1을 출력한다.

[입력]
첫째 줄에 N이 주어진다. N은 1,000,000보다 작거나 같은 자연수 또는 0이다.

[출력]
첫째 줄에 N번째 감소하는 수를 출력한다.

[풀이]
0~9까지 순서대로 배열과 큐에 넣는다.
큐에서 값을 빼고, 0부터 뺀 값을 10으로 나눈 나머지의 전 값까지 그 수의 뒤에 붙여준다.
9876543210이 될 때까지 붙이고(N = 1022), 그 이상의 수에 대해서는 예외를 발생시켜 처리한다.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	final static int MAX = 1000001;
	static int N;
	static long[] arr;
	static Queue<Long> queue;
	static BufferedWriter bw;
	static BufferedReader br;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		arr = new long[MAX];
		flag = false;
		
		for (int i = 0; i < 10; i++) {
			arr[i] = i;
			queue.add(Long.valueOf(i));
		}
		
		func();
		
		bw.write(flag == false ? String.valueOf(arr[N]) : "-1");
		bw.close();
		br.close();
	}
	
	private static void func() throws IOException{
		int idx = 10;
		
		while(idx <= N) {
			if(queue.isEmpty()) 
				return;
			
			long tmp = queue.poll();
			int lastNum;
			try {
				lastNum = Math.toIntExact(tmp) % 10;
			} catch (ArithmeticException e) {
				// TODO: handle exception
				flag = true;
				return;
			}
			
			for (int i = 0; i < lastNum; i++) {
				queue.add(tmp * 10 + i);
				arr[idx++] = tmp * 10 + i;
			}
		}
	}
}
