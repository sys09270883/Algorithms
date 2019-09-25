/*
https://www.acmicpc.net/problem/1976
[����]
�����̴� ģ����� �Բ� ������ ������ �Ѵ�. �ѱ����� ���ð� N�� �ְ� ������ �� ���� ���̿� ���� ���� ����, ���� ���� �ִ�. 
�������� ���� ������ �־����� ��, �� ���� ��ΰ� ������ ������ �˾ƺ���. ���� �߰��� �ٸ� ���ø� �����ؼ� ������ �� ���� �ִ�. 
���� ��� ���ð� 5�� �ְ�, A-B, B-C, A-D, B-D, E-A�� ���� �ְ�, �������� ���� ��ȹ�� E C B C D ���
 E-A-B-C-B-C-B-D��� �����θ� ���� ������ �޼��� �� �ִ�.

���õ��� ������ ���õ� ���� ���� ���ΰ� �־��� �ְ�, �������� ���� ��ȹ�� ���� ���õ��� ������� �־����� ��(�ߺ� ����) �������� ���θ� �Ǻ��ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù �ٿ� ������ �� N�� �־�����. N�� 200�����̴�. ��° �ٿ� ���� ��ȹ�� ���� ���õ��� �� M�� �־�����. M�� 1000�����̴�. 
���� N * N ����� ���� ������ �� ���ð� ����Ǿ������� ���� ������ �־�����. 1�̸� ����� ���̰� 0�̸� ������ ���� ���� ���̴�. 
A�� B�� ����Ǿ����� B�� A�� ����Ǿ� �ִ�. ������ �ٿ��� ���� ��ȹ�� �־�����.

[���]
ù �ٿ� �����ϸ� YES �Ұ����ϸ� NO�� ����Ѵ�.

[Ǯ��]
��θ� �ߺ��湮 �����ϰ�, �ܼ��� �� �� �ִ��� ���ο� ���� �͸� �ľ��ϸ� �ǹǷ� disjoint set���� �����Ѵ�.
N*N����� �Է¹��� �� 1�̸� i���� j�� �� �� �ִٴ� ���̹Ƿ� ��带 ���� �������ش�.(union)
ũ�Ⱑ M�� ��� �迭�� ��ȸ�ϸ鼭 ���� ��θ� �� �� �ִ��� ���θ� �����ϸ鼭 �� �� ���ٸ� flag�� false�� �ٲٰ� ��� �ݺ����� Ż���Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;	
		}
		boolean flag = true;
		int[] arr = new int[M + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				if (tmp == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M - 1; i++) {
			if (find(arr[i]) != find(arr[i + 1])) {
				flag = false;
				break;
			}
		}
		
		bw.write(flag ? "YES" : "NO");
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y)
			parent[x] = y;
	}
	
}
