/*
https://www.acmicpc.net/problem/5430
[문제]
선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.

함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고, D는 첫 번째 숫자를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.

함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 숫자를 버리는 함수이다.

배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.

각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.

다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)

다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 수가 주어진다. (1 ≤ xi ≤ 100)

전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.

[출력]
각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.

[풀이]
R 함수 : flag *= -1
D 함수 : 
  1. flag == 1인 경우는 리스트의 앞에서 제거하고,
  2. flag == -1인 경우는 리스트의 뒤에서 제거한다.

flag == 1인 경우 순서대로 result에 쌓고, flag == -1인 경우는 역순으로 쌓는다.

+ 예외처리
    1. D 함수 실행중에 값이 없는데 제거하려는 경우 -> NoSuchElementException을 발생시킴.
    2. 배열의 길이가 0인 [] 배열이 들어올 경우 -> StringIndexOutOfBoundsException을 발생시킴.

+ 첫 시도에 R 함수로 배열의 값을 전부 바꿔버렸는데, 입력데이터가 100만이므로 100만 * 100만 = 1억번의 연산을 수행한다.
  시간제한 1초에 걸리는 연산이므로 앞, 뒤를 번갈아 가는 flag를 사용해야한다.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
	static LinkedList<Integer> list;
	static int flag;
	static StringBuilder sb;
	static StringBuilder result;
	static StringTokenizer st;
	static String funcStr;
	static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		result = new StringBuilder();
		list = new LinkedList<Integer>();
		st = null;
		funcStr = null;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			flag = 1;
			funcStr = br.readLine();
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "[,]");

			for (int i = 0; i < n; i++)
				list.add(Integer.parseInt(st.nextToken()));

			try {
				func();
			} catch (NoSuchElementException e) {
				// TODO: handle exception
				result.append("error\n");
				continue;
			}
			
			assemble();
			
			try {
				sb.replace(sb.length() - 1, sb.length(), "]\n");
			} catch (StringIndexOutOfBoundsException e) {
				// TODO: handle exception
				result.append("[]\n");
				continue;
			}
			
			list.clear();
			result.append('[').append(sb);
			sb.setLength(0);
		}

		bw.write(result.toString().trim());
		bw.close();
		br.close();
	}

	private static void func() {
		for (int i = 0; i < funcStr.length(); i++) {
			if(funcStr.charAt(i) == 'R')
				// function R
				flag *= -1;

			else {
				// function D
				if(flag == 1)
					list.removeFirst();
				
				else
					list.removeLast();
			}
		}
	}
	
	private static void assemble() {
		if(flag == 1) {
			while(!list.isEmpty()) 
				sb.append(list.pollFirst()).append(',');
		}
		
		else {
			while(!list.isEmpty())
				sb.append(list.pollLast()).append(',');
		}
	}
}
