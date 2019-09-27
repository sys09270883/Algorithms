/*
[����]
������ �׸��� ���� �ﰢ���� ���� ������� ������ �ִ�. ù �ﰢ���� ���ﰢ������ ���� ���̴� 1�̴�. 
�� �������� ������ ���� �������� ���ﰢ���� ��� �߰��Ѵ�. �������� ���� �� ���� ���̸� k�� ���� ��, �� ���� ���̰� k�� ���ﰢ���� �߰��Ѵ�.

�ĵ��� ���� P(N)�� ������ �ִ� ���ﰢ���� ���� �����̴�. P(1)���� P(10)���� ù 10�� ���ڴ� 1, 1, 1, 2, 2, 3, 4, 5, 7, 9�̴�.

N�� �־����� ��, P(N)�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �׽�Ʈ ���̽��� ���� T�� �־�����. �� �׽�Ʈ ���̽��� �� �ٷ� �̷���� �ְ�, N�� �־�����. (1 �� N �� 100)

[���]
�� �׽�Ʈ ���̽����� P(N)�� ����Ѵ�.

[Ǯ��]
f(n) = f(n - 1) + f(n - 5)�̴�. ���� Ŀ�� �� �����Ƿ� BigInteger�� ó���ߴ�.

*/
import java.io.*;
import java.math.BigInteger;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		
		BigInteger[] f = new BigInteger[100];
		f[0] = BigInteger.ONE;
		f[1] = BigInteger.ONE;
		f[2] = BigInteger.ONE;
		f[3] = new BigInteger("2");
		f[4] = new BigInteger("2");
		
		for (int i = 5; i < 100; i++) {
			f[i] = f[i - 1].add(f[i - 5]);
		}
		
		while(T-- > 0){
			res.append(f[Integer.parseInt(br.readLine()) - 1]).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
}
