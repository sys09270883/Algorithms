/*
https://www.acmicpc.net/problem/5586
[문제]
입력으로 주어지는 문자열에서 연속으로 3개의 문자가 JOI 또는 IOI인 곳이 각각 몇 개 있는지 구하는 프로그램을 작성하시오. 
문자열을 알파벳 대문자로만 이루어져 있다. 예를 들어, 아래와 같이 "JOIOIOI"에는 JOI가 1개, IOI가 2개 있다.



[입력]
첫째 줄에 알파벳 10000자 이내의 문자열이 주어진다. 

[출력]
첫째 줄에 문자열에 포함되어 있는 JOI의 개수, 둘째 줄에 IOI의 개수를 출력한다.

[풀이]
3글자씩 나누어서 카운팅한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input = br.readLine();
        int cnt1 = 0, cnt2 = 0;
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i <= input.length() - 3; i++) {
			if (input.charAt(i) == 'J' && input.charAt(i + 1) == 'O' && input.charAt(i + 2) == 'I')
				cnt1++;
			
			else if(input.charAt(i) == 'I' && input.charAt(i + 1) == 'O' && input.charAt(i + 2) == 'I')
				cnt2++;
		}
        res.append(cnt1).append('\n').append(cnt2);
        
        bw.write(res.toString());
        bw.close();
        br.close();
    }
}
