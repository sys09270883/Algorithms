/*
https://www.acmicpc.net/problem/1236
[문제]
영식이는 직사각형 모양의 성을 가지고 있다. 성의 1층은 몇 명의 경비원에 의해서 보호되고 있다. 영식이는 모든 행과 모든 열에 한 명 이상의 경비원이 있으면 좋겠다고 생각했다.

성의 크기와 경비원이 어디있는지 주어졌을 때, 몇 명의 경비원을 최소로 추가해야 영식이를 만족시키는지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 성의 세로 크기 N과 가로 크기 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 
둘째 줄부터 N개의 줄에는 성의 상태가 주어진다. 성의 상태는 .은 빈칸, X는 경비원이 있는 칸이다.

[출력]
첫째 줄에 추가해야 하는 경비원의 최솟값을 출력한다.

[풀이]
경비원이 있는 줄의 행과 열을 모두 지운다.
나머지 행과 열에 대해서 최소한 더 큰 행 또는 더 큰 열의 경비원 수가 필요하다.
남아있는 행과 열을 비교해서 더 큰 수를 출력한다.

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
