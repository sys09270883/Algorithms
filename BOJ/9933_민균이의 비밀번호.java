/*
https://www.acmicpc.net/problem/9933
[����]
â���̴� �α����� ��ǻ�͸� ��ŷ�� �ؽ�Ʈ ���� �ϳ��� �ڽ��� ���Ϸ� �����ߴ�. 
���Ͽ��� �ܾ �� �ٿ� �ϳ��� �����־���, �� �� �ϳ��� �α��̰� �¶��� �������� ����ϴ� ��й�ȣ�̴�.

������ ���캸�� â���̴� ��� �ܾ��� ���̰� Ȧ����� ����� �˾Ƴ�����. �׸��� ������ �α��̰� �� ��Ͽ� ���ؼ� ����ߴ� ���� �����س´�. 
�α����� ��й�ȣ�� ��Ͽ� ���ԵǾ� ������, ��й�ȣ�� ����� �� ���ڿ��� ���ԵǾ� �ִ�.

���� ���, �α����� ��й�ȣ�� "tulipan"�� ��쿡 ��Ͽ��� "napilut"�� �����ؾ� �Ѵ�. �� �� ���� ������ ���� ��� ��й�ȣ�� ��� �����ϴٰ� �Ѵ�.

�α����� ���Ͽ� �����ִ� �ܾ ��� �־����� ��, ��й�ȣ�� ���̿� ��� ���ڸ� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �ܾ��� �� N (2 �� N �� 100)�� �־�����. ���� N�� �ٿ��� ���Ͽ� �����ִ� �ܾ �� �ٿ� �ϳ��� �־�����. 
�ܾ�� ���ĺ� �ҹ��ڷθ� �̷���� ������, ���̴� 2���� ũ�� 14���� ���� Ȧ���̴�.

[���]
ù° �ٿ� ��й�ȣ�� ���̿� ��� ���ڸ� ����Ѵ�. �׻� ���� ������ ��츸 �Է����� �־�����.

[Ǯ��]
HashSet�� �̸��� ������ �����鼭 false�� ��ȯ�� �� �ݺ����� �����ϰ� ���� ����Ѵ�.
 + HashSet�� add�޼ҵ�� �̹� �ִ� ���� ���Ͽ� false�� ��ȯ��.

*/
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        HashSet<String> hs = new HashSet<String>();
        StringBuilder sb = null;
        
        for (int i = 0; i < N; i++) {
        	sb = new StringBuilder(br.readLine());
        	
        	String a = sb.toString();
        	String b = sb.reverse().toString();
        	
        	if (!hs.add(a) || !hs.add(b)) {
        		bw.write(String.valueOf(a.length()));
        		bw.write(" ");
        		bw.write(a.charAt(a.length() / 2));
        		break;
        	}
		}
        
        bw.close();
        br.close();
    }
    
}
