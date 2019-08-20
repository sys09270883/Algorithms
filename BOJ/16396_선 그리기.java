/*
https://www.acmicpc.net/problem/16396
[����]
�ؿ����� ��ī �ؼ��̴� ũ���Ľ��� �� ������ ������ ���� ���� ������ �׸��� �־���.

�ؼ����� ����� ���� �ִ� �ؿ��̴� �ؼ��̰� �׸� ��� ������ ���� ��ǥ�� ����(projection)���� �� ����� ������ ���� ���� �ñ��Ͽ���.

�ؿ��̿��� �� �������ϴ� �������� �ؿ����� �ñ����� �ذ��ϱ� ���� ���α׷��� ����������.

[�Է�]
ù ��° �ٿ��� �ؼ��̰� �׸� ���� ���� N�� �Էµȴ�.

�� ��° �ٺ��� N+1 ��° �ٱ����� �ؼ��̰� �׸� ���� ���� ��ǥ Xi�� �� ��ǥ Yi �� ������� �־�����. Xi �� Yi �� �����̸�, ����� ���еȴ�.

N�� ������ 1���� 10,000�����̴�. ���� ���� ��ǥ�� �� ��ǥ�� 1���� 10,000������ �ڿ����̴�.

[���]
���� ��ǥ�� ����� ���� �� ���� ���� ������ ����Ѵ�. 

[Ǯ��]
�Է¹��� ������ ���� �κ��� true�� �ٲ��ְ� �������� true�� �κи��� sum�� ������Ų��.

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
