/*
https://www.acmicpc.net/problem/1236
[����]
�����̴� ���簢�� ����� ���� ������ �ִ�. ���� 1���� �� ���� ������ ���ؼ� ��ȣ�ǰ� �ִ�. �����̴� ��� ��� ��� ���� �� �� �̻��� ������ ������ ���ڴٰ� �����ߴ�.

���� ũ��� ������ ����ִ��� �־����� ��, �� ���� ������ �ּҷ� �߰��ؾ� �����̸� ������Ű���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ���� ���� ũ�� N�� ���� ũ�� M�� �־�����. N�� M�� 50���� �۰ų� ���� �ڿ����̴�. 
��° �ٺ��� N���� �ٿ��� ���� ���°� �־�����. ���� ���´� .�� ��ĭ, X�� ������ �ִ� ĭ�̴�.

[���]
ù° �ٿ� �߰��ؾ� �ϴ� ������ �ּڰ��� ����Ѵ�.

[Ǯ��]
������ �ִ� ���� ��� ���� ��� �����.
������ ��� ���� ���ؼ� �ּ��� �� ū �� �Ǵ� �� ū ���� ���� ���� �ʿ��ϴ�.
�����ִ� ��� ���� ���ؼ� �� ū ���� ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	static char[][] castle;
	static boolean[] row, col;
	static int N, M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		castle = new char[N][M];
		row = new boolean[N];
		col = new boolean[M];
		String str = null;
		int r = 0, c = 0;

		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				castle[i][j] = str.charAt(j);
				
				if (castle[i][j] == 'X') {
					row[i] = true;
					col[j] = true;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (!row[i])
				r++;
		}

		for (int i = 0; i < M; i++) {
			if (!col[i])
				c++;
		}

		bw.write(r > c ? String.valueOf(r) : String.valueOf(c));
		bw.close();
		br.close();
	}

}
