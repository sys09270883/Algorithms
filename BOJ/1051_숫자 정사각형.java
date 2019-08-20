/*
https://www.acmicpc.net/problem/1051
[문제]
N*M크기의 직사각형이 있다. 각 칸은 한 자리 숫자가 적혀 있다. 
이 직사각형에서 꼭짓점에 쓰여 있는 수가 모두 같은 가장 큰 정사각형을 찾는 프로그램을 작성하시오. 이때, 정사각형은 행 또는 열에 평행해야 한다.

[입력]
첫째 줄에 N과 M이 주어진다. N과 M은 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에 수가 주어진다.

[출력]
첫째 줄에 정답 정사각형의 크기를 출력한다.

[풀이]
row, col의 최솟값에서부터 1이 될 때까지 감소시키면서 꼭지점을 찾는다.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int row, col;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];
		String str;
		for (int i = 0; i < row; i++) {
			str = br.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bw.write(String.valueOf(func()));
		bw.close();
		br.close();
	}

	private static int func() {
		int d = Math.min(row, col);

		while(d-- > 0) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(i + d >= row || j + d >= col)
						continue;

					if(isEqual(map[i][j], map[i + d][j], map[i][j + d], map[i + d][j + d]))
						return (int)Math.pow((d + 1), 2);
				}
			}
		}

		return -1;
	}

	private static boolean isEqual(int a, int b, int c, int d) {
		if(a == b && b == c && c == d && d == a)
			return true;
		return false;
	}
}
