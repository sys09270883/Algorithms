/*
https://www.acmicpc.net/problem/1018
[����]
�����̴� �ڽ��� ���ÿ��� MN���� ���� ���簢������ �������� �ִ� M*N ũ���� ���带 ã�Ҵ�. 
� ���簢���� ���������� ĥ���� �ְ�, �������� ������� ĥ���� �ִ�. �����̴� �� ���带 �߶� 8*8 ũ���� ü�������� ������� �Ѵ�.

ü������ �������� ����� �����Ƽ� ĥ���� �־�� �Ѵ�. 
��ü������, �� ĭ�� �������� ��� �� �ϳ��� ��ĥ�Ǿ� �ְ�, ���� �����ϴ� �� ���� �簢���� �ٸ� ������ ĥ���� �־�� �Ѵ�. 
���� �� ���Ǹ� ������ ü������ ��ĥ�ϴ� ���� �� �������̴�. �ϳ��� �� ���� �� ĭ�� ����� ���, �ϳ��� �������� ����̴�.

���尡 ü����ó�� ĥ���� �ִٴ� ������ ���, �����̴� 8*8 ũ���� ü�������� �߶� �Ŀ� �� ���� ���簢���� �ٽ� ĥ�ؾ߰ڴٰ� �����ߴ�. 
�翬�� 8*8 ũ��� �ƹ������� ��� �ȴ�. �����̰� �ٽ� ĥ�ؾ� �ϴ� ���簢���� �ּ� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� M�� �־�����. N�� M�� 8���� ũ�ų� ����, 50���� �۰ų� ���� �ڿ����̴�. 
��° �ٺ��� N���� �ٿ��� ������ �� ���� ���°� �־�����. B�� �������̸�, W�� ����̴�.

[���]
ù° �ٿ� �����̰� �ٽ� ĥ�ؾ� �ϴ� ���簢�� ������ �ּڰ��� ����Ѵ�.

[Ǯ��]
(0, 0)�� W�� ���� B�� ��� 2������ �ִ�.
8*8�� �������� �� ���� ����� ���� �������� 2���� ���� ������ �� �� �������� ���Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {

	final static int BOARD_SIZE = 8;
	static int N, M;
	static char[][] board;
	static String[] whiteBoard = {"WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", 
			"WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW"};
	static String[] blackBoard = {"BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", 
			"BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB"};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		String input = null;
		int res = Integer.MAX_VALUE;
		board = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			input = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		
		for (int i = 0; i < N - 7; i++) {
			for (int j = 0; j < M - 7; j++) {
				res = Math.min(res, Math.min(whiteFirst(i, j), blackFirst(i, j)));
			}
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static int whiteFirst(int r, int c) {
		int cnt = 0;
		for (int i = r; i < r + BOARD_SIZE; i++) {
			for (int j = c; j < c + BOARD_SIZE; j++) {
				if (board[i][j] != whiteBoard[i - r].charAt(j - c))
					cnt++;
			}
		}
		
		return cnt;
	}
	
	private static int blackFirst(int r, int c) {
		int cnt = 0;
		for (int i = r; i < r + BOARD_SIZE; i++) {
			for (int j = c; j < c + BOARD_SIZE; j++) {
				if (board[i][j] != blackBoard[i - r].charAt(j - c))
					cnt++;
			}
		}
		
		return cnt;
	}
}
