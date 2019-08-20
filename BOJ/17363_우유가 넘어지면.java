/*
https://www.acmicpc.net/problem/17363
[����]
���� ������ �پ �����̴� ���� ����ִ� ����� �غ��Ѵ�. ���� �غ��� ����� �ٷ� �̰��̴�.

������ �Ѿ��� �� ����� �ϰ�? ���� "�ƾ�"! ���!

ģ������ ������ �ɵ巷����, �����̴� �ؼ��� ���ٿ���.

"����"�� ���η� ���� �ִ� ���ڸ� �������� �� �Ѿ�߸��� "�ƾ�"�� ���ݾ�? �̰� ��ġ ������ �Ѿ����� �����ϴ� �� ���ٴ� ���� ���� ����Ʈ��!

milk

�׷����� �ұ��ϰ� ģ������ ���� ����, �����̴� ģ������ ������������ ������ ���ڸ� �Ѿ�߸��� ����� ������� ���Ѵٰ� �����ߴ�. �׷��� ���ڸ� �Ѿ�߸��� ���α׷��� ����� ģ���鿡�� ����� �����ֱ�� �ߴ�.

�����̴� ���ڿ� "����"�� �ƴ϶� �ٸ� �׸��� �׷��� �־ ���α׷��� �� �����ϱ⸦ ���Ѵ�. �����̴� ������ ���� ���ڷ� ������ �� ĭ�� �Ʒ� ���ڵ� �� �ϳ��� �׷� �ִ� ������� �׸��� �׸���.

����	�̸�	ASCII	���� ��
.	����	46	.
O	�빮�� O	79	O
-	������	45	|
|	���� ��	124	-
/	������	47	\
\	��������	92	/
^	ĳ��	94	<
<	���� �ε�ȣ	60	v
v	�ҹ��� V	118	>
>	������ �ε�ȣ	62	^
�׷��� �����̴� ������ ����� �����ϴ��� ���α׷��� ���� �ð��� ����. ������ ��� ���α׷��� ����� ����.

[�Է�]
ù �ٿ� �׸��� ���� ���̿� ���� ���̸� �ǹ��ϴ� ���� N�� M(1 �� N, M �� 100)�� �־�����.

���� N���� �ٿ� ���� �׸��� �� ���� �ǹ��ϴ� M������ ���ڿ��� �ϳ��� �־�����. ���ڿ��� ������ �������� ������ ���� ǥ�� �־��� ���ڷθ� �̷���� ������ ����ȴ�.

[���]
M���� �ٿ� ���� �Էµ� �׸��� �������� �Ѿ�߷��� ���� ����� ����Ѵ�.

[Ǯ��]
(0, 0) -> (0, 6)
(6, 0) -> (0, 0)
(0, 13) -> (13, 6)
(6, 13) -> (13, 0)
...
(x, y) -> (y, M - x - i)�� ��ȯ�Ѵ�.


*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static char[][] arr;
	static int N, M;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		char[][] res = func();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(res[i][j]);
			}
			sb.append('\n');
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static char[][] func() {
		char[][] ret = new char[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				ret[i][j] = translate(arr[j][M - i - 1]);
			}
		}
		
		return ret;
	}
	
	private static char translate(char c) {
		char ret = '\0';
		
		switch(c) {
		case '-':
			ret = '|';
			break;
		case '|':
			ret = '-';
			break;
		case '/':
			ret = '\\';
			break;
		case '\\':
			ret = '/';
			break;
		case '^':
			ret = '<';
			break;
		case '<':
			ret = 'v';
			break;
		case 'v':
			ret = '>';
			break;
		case '>':
			ret = '^';
			break;
		default:
			ret = c;
			break;
		}
		
		return ret;
	}
}
