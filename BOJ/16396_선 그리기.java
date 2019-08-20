/*
https://www.acmicpc.net/problem/16396
[문제]
준용이의 조카 준섭이는 크레파스로 한 직선에 평행한 여러 개의 선분을 그리고 있었다.

준섭이의 모습을 보고 있던 준용이는 준섭이가 그린 모든 선들을 직선 좌표에 투사(projection)했을 때 투사된 선들의 길이 합이 궁금하였다.

준용이에게 잘 보여야하는 여러분은 준용이의 궁금증을 해결하기 위해 프로그램을 구현해주자.

[입력]
첫 번째 줄에는 준섭이가 그린 선의 개수 N이 입력된다.

두 번째 줄부터 N+1 번째 줄까지는 준섭이가 그린 선의 시작 좌표 Xi와 끝 좌표 Yi 가 순서대로 주어진다. Xi 와 Yi 는 정수이며, 띄어쓰기로 구분된다.

N의 범위는 1부터 10,000까지이다. 선의 시작 좌표와 끝 좌표는 1부터 10,000까지의 자연수이다.

[출력]
직선 좌표에 투사된 선의 총 길이 합을 정수로 출력한다. 

[풀이]
입력받은 선분의 길이 부분을 true로 바꿔주고 마지막에 true인 부분마다 sum을 증가시킨다.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	final static int len = 10001;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		boolean[] line = new boolean[len];
		StringTokenizer st = null;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			for (int j = x1; j < x2; j++) {
				line[j] = true;
			}
		}
		
		for (int i = 1; i < len; i++) {
			if(line[i])
				sum++;
		}
		
		bw.write(String.valueOf(sum));
		bw.close();
		br.close();
	}
}
