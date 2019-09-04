/*
https://www.acmicpc.net/problem/1526
[문제]
은민이는 4와 7을 좋아하고, 나머지 숫자는 싫어한다. 금민수는 어떤 수가 4와 7로만 이루어진 수를 말한다.

N이 주어졌을 때, N보다 작거나 같은 금민수 중 가장 큰 것을 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N이 주어진다. N은 4보다 크거나 같고 1,000,000보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 N보다 작거나 같은 금민수 중 가장 큰 것을 출력한다.

[풀이]
큐에다가 숫자를 넣고 결과 값을 계속 갱신한다.
큐에서 빼고 10을 곱하면서 숫자를 붙인다(4, 7).

1) 4, 7
2) 44, 47, 74, 77 ...

*/
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<Integer>();
        int res = 0;
        
        if (N >= 4) {
        	res = 4;
        	queue.add(4);
        }
        
        if (N >= 7) {
        	res = 7;
        	queue.add(7);
        }
        
        while (!queue.isEmpty()) {
        	int num = queue.poll();
        	num *= 10;
        	
        	if (num + 4 <= N) {
        		res = num + 4;
        		queue.add(num + 4);
        	}
        	
        	if (num + 7 <= N) {
        		res = num + 7;
        		queue.add(num + 7);
        	}
        }
        
        bw.write(String.valueOf(res));
        bw.close();
        br.close();
    }
    
}
