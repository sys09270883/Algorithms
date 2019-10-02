/*
https://www.acmicpc.net/problem/1613
[����]
����, �� �߿����� �ѱ��翡 �ع��� �����̴� ���� ������ ��ǵ��� ���� ���踦 �� �˰� �ִ�. 
��, �����ֶ��� ����ȣ������ ���� �Ͼ����, ������ȭ�� �⹦��ȭ���� ���� �Ͼ�ٴ� ���� ������ �˰� �ִ� ���̴�.

�����̰� �˰� �ִ� �Ϻ� ��ǵ��� ���� ������� �־��� ��, �־��� ��ǵ��� ���� ���赵 �� �� ������? �̸� �ذ��ϴ� ���α׷��� �ۼ��� ������ ����.

[�Է�]
ù° �ٿ� ù �ٿ� ����� ���� n(400 ������ �ڿ���)�� �˰� �ִ� ����� ���� ������ ���� k(50,000 ������ �ڿ���)�� �־�����. 
���� k�ٿ��� ���� ���踦 �˰� �ִ� �� ����� ��ȣ�� �־�����. �̴� �տ� �ִ� ��ȣ�� ����� �ڿ� �ִ� ��ȣ�� ��Ǻ��� ���� �Ͼ���� �ǹ��Ѵ�. 
���� ����� ���� ���谡 ����� ���� ����. �������� ����� ���� ���踦 �˰� ���� ��� ���� �� s(50,000 ������ �ڿ���)�� �־�����. 
���� s�ٿ��� ���� ���� �ٸ� �� ����� ��ȣ�� �־�����. ����� ��ȣ�� 1���� ũ�ų� ����, N���� �۰ų� ���� �ڿ����̴�.

[���]
s�ٿ� ���� ������ ���Ѵ�. �� �ٿ� ���� �տ� �ִ� ��ȣ�� ����� ���� �Ͼ���� -1, �ڿ� �ִ� ��ȣ�� ����� ���� �Ͼ���� 1, ��� �𸣸�(������ �� ������) 0�� ����Ѵ�.

[Ǯ��]
�÷��̵� �ͼ��� ���� ���踦 �����Ѵ�(1, -1)
 + �÷��̵� �ͼ� ��������

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int n, k, s;
	static int[][] history;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		history = new int[n + 1][n + 1];
		StringBuilder res = new StringBuilder();
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			history[first][second] = -1;
			history[second][first] = 1;
		}
		
		floyd();
		
		s = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			res.append(history[from][to]).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void floyd() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (i == j && j == k && k == i)
						continue;
					
					if (history[j][k] == 0) {
						if (history[j][i] == 1 && history[i][k] == 1)
							history[j][k] = 1;
						
						else if (history[j][i] == -1 && history[i][k] == -1)
							history[j][k] = -1;
					}
				}
			}
		}
	}
	
}