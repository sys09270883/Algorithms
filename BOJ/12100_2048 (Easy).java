/*
https://www.acmicpc.net/problem/12100
[����]
2048 ������ 4��4 ũ���� ���忡�� ȥ�� ���� ����ִ� �����̴�. �� ��ũ�� ������ ������ �غ� �� �ִ�.

�� ���ӿ��� �� ���� �̵��� ���� ���� �ִ� ��ü ����� �����¿� �� ���� �� �ϳ��� �̵���Ű�� ���̴�. �̶�, ���� ���� ���� �� ����� �浹�ϸ� �� ����� �ϳ��� �������� �ȴ�. �� ���� �̵����� �̹� ������ ����� �� �ٸ� ��ϰ� �ٽ� ������ �� ����. (���� ���ӿ����� �̵��� �� �� �� ������ ����� �߰�������, �� �������� ����� �߰��Ǵ� ���� ����)

		
<�׸� 1>	<�׸� 2>	<�׸� 3>
<�׸� 1>�� ��쿡�� ���� ����� �̵���Ű�� <�׸� 2>�� ���°� �ȴ�. ���⼭, �������� ����� �̵���Ű�� <�׸� 3>�� ���°� �ȴ�.

			
<�׸� 4>	<�׸� 5>	<�׸� 6>	<�׸� 7>
<�׸� 4>�� ���¿��� ����� ���������� �̵���Ű�� <�׸� 5>�� �ǰ�, ���⼭ �ٽ� ���� ����� �̵���Ű�� <�׸� 6>�� �ȴ�. 
���⼭ ���������� ����� �̵����� <�׸� 7>�� ���� �� �ִ�.

	
<�׸� 8>	<�׸� 9>
<�׸� 8>�� ���¿��� �������� ����� �ű�� ��� �ɱ�? 2�� �浹�ϱ� ������, 4�� �������� �ǰ� <�׸� 9>�� ���°� �ȴ�.

			
<�׸� 10>	<�׸� 11>	<�׸� 12>	<�׸� 13>
<�׸� 10>���� ���� ����� �̵���Ű�� <�׸� 11>�� ���°� �ȴ�. 

<�׸� 12>�� ��쿡 ���� ����� �̵���Ű�� <�׸� 13>�� ���°� �Ǵµ�, �� ������ �� ���� �̵����� �̹� ������ ����� �� ������ �� ���� �����̴�.

	
<�׸� 14>	<�׸� 15>
����������, �Ȱ��� ���� �� ���� �ִ� ��쿡�� �̵��Ϸ��� �ϴ� ���� ĭ�� ���� ��������. ���� ���, ���� �̵���Ű�� ��쿡�� ���ʿ� �ִ� ����� ���� �������� �ȴ�. 
<�׸� 14>�� ��쿡 ���� �̵��ϸ� <�׸� 15>�� �����.

�� �������� �ٷ�� 2048 ������ ������ ũ�Ⱑ N��N �̴�. 
������ ũ��� �������� ��� ���°� �־����� ��, �ִ� 5�� �̵��ؼ� ���� �� �ִ� ���� ū ����� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ũ�� N (1 �� N �� 20)�� �־�����. ��° �ٺ��� N���� �ٿ��� �������� �ʱ� ���°� �־�����. 
0�� �� ĭ�� ��Ÿ����, �̿��� ���� ��� ����� ��Ÿ����. ��Ͽ� ���� �ִ� ���� 2���� ũ�ų� ����, 1024���� �۰ų� ���� 2�� �������̴�. ����� ��� �ϳ� �־�����.

[���]
�ִ� 5�� �̵����Ѽ� ���� �� �ִ� ���� ū ����� ����Ѵ�.

[Ǯ��]
���� �ܰ迡�� 3������ ���� �� �ִ�.

1. �迭�� �̵��Ѵ�. move()
2. �迭�� �̵��������� �����Ѵ�. merge()
 - �̵��ϸ鼭 ���� ���̸� ���� ���� ���ϰ�, ���� �ε����� 0�� �־��ش�.
3. �迭�� �ٽ� �̵��������� �̵��մϴ�. move()
 - 2�� �������� ���� 0�� ó�����ִ� ����

*/
import java.io.*;
import java.util.*;

public class Main {

	final static int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
	static int N, ans;
	static int[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		StringTokenizer st = null;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = LEFT; i <= DOWN; i++) {
			DFS(0, i, board);
		}
		
		bw.write(String.valueOf(ans));
		bw.close();
		br.close();
	}
	
	private static void maxValue(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = Math.max(ans, board[i][j]);
			}
		}
	}
	
	private static int[][] merge(int dir, int[][] board) {
		switch (dir) {
		case LEFT:
			for (int i = 0; i < N; i++) {
				int prev = 0;
				for (int j = 0; j < N; j++) {
					if (prev == 0 || (prev != 0 && prev != board[i][j]))
						prev = board[i][j];
					
					else if (prev == board[i][j]) {
						board[i][j] += prev;
						board[i][j - 1] = 0;
						prev = 0;
					}
				}
			}
			break;
			
		case RIGHT:
			for (int i = N - 1; i >= 0; i--) {
				int prev = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (prev == 0 || (prev != 0 && prev != board[i][j]))
						prev = board[i][j];
					
					else if (prev == board[i][j]) {
						board[i][j] += prev;
						board[i][j + 1] = 0;
						prev = 0;
					}
				}
			}
			break;
			
		case UP:
			for (int j = 0; j < N; j++) {
				int prev = 0;
				for (int i = 0; i < N; i++) {
					if (prev == 0 || (prev != 0 && prev != board[i][j]))
						prev = board[i][j];
					
					else if (prev == board[i][j]) {
						board[i][j] += prev;
						board[i - 1][j] = 0;
						prev = 0;
					}
				}
			}
			break;
			
		case DOWN:
			for (int j = N - 1; j >= 0; j--) {
				int prev = 0;
				for (int i = N - 1; i >= 0; i--) {
					if (prev == 0 || (prev != 0 && prev != board[i][j]))
						prev = board[i][j];
					
					else if (prev == board[i][j]) {
						board[i][j] += prev;
						board[i + 1][j] = 0;
						prev = 0;
					}
				}
			}
			break;
		}
		
		return board;
	}
	
	private static int[][] move(int dir, int[][] board) {
		int[][] newBoard = new int[N][N];
		
		switch (dir) {
		case LEFT:
			for (int i = 0; i < N; i++) {
				int ny = 0;
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0)
						newBoard[i][ny++] = board[i][j];
				}
			}
			
			break;
			
		case RIGHT:
			for (int i = N - 1; i >= 0; i--) {
				int ny = N - 1;
				for (int j = N - 1; j >= 0; j--) {
					if (board[i][j] != 0)
						newBoard[i][ny--] = board[i][j];
				}
			}
			break;
			
		case UP:
			for (int j = 0; j < N; j++) {
				int nx = 0;
				for (int i = 0; i < N; i++) {
					if (board[i][j] != 0) {
						newBoard[nx++][j] = board[i][j];
					}
				}
			}
			break;
			
		case DOWN:
			for (int j = N - 1; j >= 0; j--) {
				int nx = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (board[i][j] != 0)
						newBoard[nx--][j] = board[i][j];
				}
			}
			break;
		}
		
		return newBoard;
	}
	
	private static void DFS(int depth, int dir, int[][] board) {
		int[][] copy = move(dir, board);
		copy = merge(dir, copy);
		copy = move(dir, copy);
		
		if (++depth == 5) {
			maxValue(copy);
			return;
		}
		
		for (int i = LEFT; i <= DOWN; i++) {
			DFS(depth, i, copy);
		}
	}
}
