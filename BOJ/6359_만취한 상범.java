/*
https://www.acmicpc.net/problem/6359
[����]
�������б� ���ڰ� ������� ���Ͽ��� n���� ���� �Ϸķ� �þ ������ �ִ�. �� �濡�� ������ ���� ���� �л��� ���ݵǾ��ִ�.

�׷��� ��� ��, ���� ������ ����̴� ������ ������ ���ų��� ������ �ϱ�� �����ߴ�. 
������ ù ��° ���忡�� ����̴� ����Ű�� �� �� ����Ű��, �޷����� ������ �� ���� ��� ����. 
�� ���� ���忡���� 2, 4, 6, ... �� ���� �ٽ� ��װ�, �� ��° ���忡���� 3, 6, 9, ... �� ���� ���������� ��װ�, ����ִٸ� ����. 
k��° ���忡���� ��ȣ�� k�� ����� ���� ���� ������ ��װ�, ��� �ִٸ� ����. �̷��� n��° ������� ������ ����, ����̴� ����Ű�� ������ ���� ���ð� ������ ����.

���ݵǾ��ִ� �� ��(��¼�� 0��)�� �л����� �ڽ��� ���� ����� ���� ä ����̰� ���������ȴ� ���� ���ݰ� ��� ����ģ��.

���� ������ �־����� ��, �� ���� �л����� ������ �� �ִ��� �˾ƺ���.

[�Է�]
�Է��� ù ��° �ٿ��� �׽�Ʈ ���̽��� ���� T�� �־�����. �� �׽�Ʈ ���̽��� �� �ٿ� �� ���� ���� ���� n(5 �� n �� 100)�� �־�����.

[���]
�� �ٿ� �� ���� �� �׽�Ʈ ���̽��� ��, �� �� ���� Ż���� �� �ִ����� ����Ѵ�.

[Ǯ��]
�־��� ���ǿ� ���� Ż�� ������ ���� 1, Ż�� �Ұ����� ���� -1�� �����Ѵ�.
Ż�� ������ ���� ������ ����Ѵ�

*/
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()), n, ans;
		int[] room = new int[101];
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0) {
			n = Integer.parseInt(br.readLine());
			Arrays.fill(room, 1);
			ans = 0;
			
			for (int i = 2; i <= n; i++) {
				for (int j = 1; i * j <= n; j++) {
					room[i * j] *= -1;
				}
			}
			
			for (int i = 1; i <= n; i++) {
				if (room[i] == 1)
					ans++;
			}
			
			res.append(ans).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
