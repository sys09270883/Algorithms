/*
https://www.acmicpc.net/problem/1526
[����]
�����̴� 4�� 7�� �����ϰ�, ������ ���ڴ� �Ⱦ��Ѵ�. �ݹμ��� � ���� 4�� 7�θ� �̷���� ���� ���Ѵ�.

N�� �־����� ��, N���� �۰ų� ���� �ݹμ� �� ���� ū ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� �־�����. N�� 4���� ũ�ų� ���� 1,000,000���� �۰ų� ���� �ڿ����̴�.

[���]
ù° �ٿ� N���� �۰ų� ���� �ݹμ� �� ���� ū ���� ����Ѵ�.

[Ǯ��]
ť���ٰ� ���ڸ� �ְ� ��� ���� ��� �����Ѵ�.
ť���� ���� 10�� ���ϸ鼭 ���ڸ� ���δ�(4, 7).

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
