/*
https://www.acmicpc.net/problem/9019
[����]
�� ���� ��ɾ� D, S, L, R �� �̿��ϴ� ������ ���Ⱑ �ִ�. �� ���⿡�� �������Ͱ� �ϳ� �ִµ�, �� �������Ϳ��� 0 �̻� 10,000 �̸��� �������� ������ �� �ִ�. 
�� ��ɾ�� �� �������Ϳ� ����� n�� ������ ���� ��ȯ�Ѵ�. n�� �� �ڸ����� d1, d2, d3, d4��� ����(�� n = ((d1 �� 10 + d2) �� 10 + d3) �� 10 + d4��� ����)

D: D �� n�� �� ��� �ٲ۴�. ��� ���� 9999 ���� ū ��쿡�� 10000 ���� ���� �������� ���Ѵ�. �� ��� ��(2n mod 10000)�� �������Ϳ� �����Ѵ�.
S: S �� n���� 1 �� �� ��� n-1�� �������Ϳ� �����Ѵ�. n�� 0 �̶�� 9999 �� ��� �������Ϳ� ����ȴ�.
L: L �� n�� �� �ڸ����� �������� ȸ������ �� ����� �������Ϳ� �����Ѵ�. �� ������ ������ �������Ϳ� ����� �� �ڸ����� ������� d2, d3, d4, d1�� �ȴ�.
R: R �� n�� �� �ڸ����� ���������� ȸ������ �� ����� �������Ϳ� �����Ѵ�. �� ������ ������ �������Ϳ� ����� �� �ڸ����� ������� d4, d1, d2, d3�� �ȴ�.
������ ����� ��ó��, L �� R ��ɾ�� ���� �ڸ����� �����ϰ� ������ �����Ѵ�. ���� �� n = 1234 ��� ���⿡ L �� �����ϸ� 2341 �� �ǰ� R �� �����ϸ� 4123 �� �ȴ�.

�������� �ۼ��� ���α׷��� �־��� ���� �ٸ� �� ���� A�� B(A �� B)�� ���Ͽ� A�� B�� �ٲٴ� �ּ����� ��ɾ �����ϴ� ���α׷��̴�. 
���� �� A = 1234, B = 3412 ��� ������ ���� �� ���� ��ɾ �����ϸ� A�� B�� ��ȯ�� �� �ִ�.

1234 ��L 2341 ��L 3412
1234 ��R 4123 ��R 3412

���� �������� ���α׷��� �� ��쿡 LL �̳� RR �� ����ؾ� �Ѵ�.

n�� �ڸ����� 0 �� ���Ե� ��쿡 �����ؾ� �Ѵ�. ���� �� 1000 �� L �� �����ϸ� 0001 �� �ǹǷ� ����� 1 �� �ȴ�. �׷��� R �� �����ϸ� 0100 �� �ǹǷ� ����� 100 �� �ȴ�.

[�Է�]
���α׷� �Է��� T ���� �׽�Ʈ ���̽��� �����ȴ�. �׽�Ʈ ���̽� ���� T �� �Է��� ù �ٿ� �־�����. 
�� �׽�Ʈ ���̽��δ� �� ���� ���� A�� B(A �� B)�� �������� �и��Ǿ� ���ʷ� �־����µ� A�� ���������� �ʱ� ���� ��Ÿ���� B�� ���� ���� ��Ÿ����. 
A �� B�� ��� 0 �̻� 10,000 �̸��̴�.

[���]
A���� B�� ��ȯ�ϱ� ���� �ʿ��� �ּ����� ��ɾ� ������ ����Ѵ�.

[Ǯ��]
��忡�� ������ StringBuilder�� ���� �����ϰ� �ѱ�� ������� �ߴµ�, �ð��ʰ��� �߻��ߴ�.
�׷��� ó������ String �迭�� ����� �����ϴ� ������ �ϴϱ� ������ �Ǿ���...
���� ��ü�� �������� �ð��ʰ��� ���ؼ� �����߾�� �ߴ�...

*/
import java.io.*;
import java.util.*;

public class Main {

	static boolean[] visited;
	static String[] command;
	static StringBuilder ans;
	static int A, B;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		command = new String[10000];
		visited = new boolean[10000];
		ans = new StringBuilder();
		
		while(T-- > 0) {
			Arrays.fill(visited, false);
			Arrays.fill(command, "");
			st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			BFS();
			
			ans.append(command[B]).append('\n');
		}
		
		bw.write(ans.toString().trim());
		bw.close();
		br.close();
	}
	
	private static int D(int input) {
		int output = 0;
		output = (input * 2) % 10000;
		
		return output;
	}
	
	private static int S(int input) {
		int output = 0;
		output = (input - 1 + 10000) % 10000;
		
		return output;
	}
	
	private static int L(int input) {
		int output = (input % 1000) * 10 + input / 1000;
		
		return output;
	}
	
	private static int R(int input) {
		int output = (input % 10) * 1000 + input / 10;
		
		return output;
	}
	
	private static void BFS() {
		visited[A] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(A);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			if (now == B)
				return;
			
			for (int i = 0; i < 4; i++) {
				int next = func(DSLR(i), now);
				
				if (!visited[next]) {
					visited[next] = true;
					command[next] = command[now] + DSLR(i);
					queue.add(next);
				}
			}
		}
	}
	
	private static char DSLR(int i) {
		switch (i) {
		case 0:
			return 'D';
		case 1:
			return 'S';
		case 2:
			return 'L';
		case 3:
			return 'R';
		default:
			return '\0';
		}
	}
	
	private static int func(char c, int now) {
		int ret;
		
		switch (c) {
		case 'D':
			ret = D(now);
			break;
		case 'S':
			ret = S(now);
			break;
		case 'L':
			ret = L(now);
			break;
		case 'R':
			ret = R(now);
			break;
		default:
			return -1;
		}
		
		return ret;
	}
}

