/*
https://www.acmicpc.net/problem/2309
[문제]
왕비를 피해 일곱 난쟁이들과 함께 평화롭게 생활하고 있던 백설공주에게 위기가 찾아왔다. 일과를 마치고 돌아온 난쟁이가 일곱 명이 아닌 아홉 명이었던 것이다.

아홉 명의 난쟁이는 모두 자신이 "백설 공주와 일곱 난쟁이"의 주인공이라고 주장했다. 뛰어난 수학적 직관력을 가지고 있던 백설공주는, 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해 냈다.

아홉 난쟁이의 키가 주어졌을 때, 백설공주를 도와 일곱 난쟁이를 찾는 프로그램을 작성하시오.

[입력]
아홉 개의 줄에 걸쳐 난쟁이들의 키가 주어진다. 주어지는 키는 100을 넘지 않는 자연수이며, 아홉 난쟁이의 키는 모두 다르며, 가능한 정답이 여러 가지인 경우에는 아무거나 출력한다.

[출력]
일곱 난쟁이의 키를 오름차순으로 출력한다. 일곱 난쟁이를 찾을 수 없는 경우는 없다.

[풀이]
9명의 난쟁이의 합을 다 더하고 2명을 선택해서 뺐을 때 100이 되는 경우를 찾는다.
+ 45번째 줄 for문 탈출 신경쓰기! 
*/
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
