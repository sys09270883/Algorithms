/*
https://www.acmicpc.net/problem/17362
[����]
�� ������ ����ϴ°�?

img1

�� ������ ���������� ���ͳݿ� ���ƴٴϴ� ��������, �۳� �������� ���� A�������� ������ ���� �� �ϴ� ����л��� �������� �������� �Ұ��Ǿ���. 
���۱��� �ִ� ������ �� �־� �˾ƺ��� ��ư� ���������� ���� �ٶ���.

���� ��¥�� �ٰ����µ��� ������ A�� ������ �������� ���� �������� �۳� �������� ���� A���� �����ؼ� ������ ������ �ߴ�. 
�̸� ���� ���� �� ������ ã�ƺ� �������� �ش� ������ 2007�г⵵ 6�� ����б� 1�г� ���������з��� ���� ���� 26������ �˰� �Ǿ���.

�������� �����ް� �������� ���캸�� �������� �Ʒ��� ���� ������ �߰��ߴ�.

img2

�����߰�����, �������� ���� ���� 19�� ���� �� ��° �ٿ� �����ϴ� �� '1000'�� ������ �ڿ����� �ٲپ��� �� �׿� �ش��ϴ� ���� ����ϴ� ���α׷��� �ۼ��ؾ� �Ѵ�.

[�Է�]
ù ��° �ٿ� �ڿ��� n (1 �� n �� 109)�� �־�����.

[���]
ù ��° �ٿ� 19�� ���� �� ��° �ٿ� �����ϴ� �� '1000'�� �ڿ��� n���� �ٲپ��� �� �׿� �ش��ϴ� ���� ��ȣ�� ����Ѵ�. ��, 1 �̻� 5 ������ �ڿ��� �� �ϳ��� ����ؾ� �Ѵ�.

[Ǯ��]
 1  2  3  4  5
 9  8  7  6
   10 11 12 13
17 16 15 14
...

�־��� ���� 8�� ������� ������ �ִ�.
�������� 6, 7, 0(=8)�� �� ���� 4, 3, 2, ������ ���� 8�� ���� �������̴�.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		switch(n%8) {
		case 0:
			n = 2;
			break;
		case 7:
			n = 3;
			break;
		case 6:
			n = 4;
			break;
		default:
			n %= 8;
			break;
		}
		
		bw.write(String.valueOf(n));
		bw.close();
		br.close();
	}
}
