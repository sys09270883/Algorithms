/*
https://www.acmicpc.net/problem/5586
[����]
�Է����� �־����� ���ڿ����� �������� 3���� ���ڰ� JOI �Ǵ� IOI�� ���� ���� �� �� �ִ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
���ڿ��� ���ĺ� �빮�ڷθ� �̷���� �ִ�. ���� ���, �Ʒ��� ���� "JOIOIOI"���� JOI�� 1��, IOI�� 2�� �ִ�.



[�Է�]
ù° �ٿ� ���ĺ� 10000�� �̳��� ���ڿ��� �־�����. 

[���]
ù° �ٿ� ���ڿ��� ���ԵǾ� �ִ� JOI�� ����, ��° �ٿ� IOI�� ������ ����Ѵ�.

[Ǯ��]
3���ھ� ����� ī�����Ѵ�.

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
