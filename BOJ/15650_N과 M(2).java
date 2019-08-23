/*
https://www.acmicpc.net/problem/15650
[����]
�ڿ��� N�� M�� �־����� ��, �Ʒ� ������ �����ϴ� ���̰� M�� ������ ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

1���� N���� �ڿ��� �߿��� �ߺ� ���� M���� �� ����
�� ������ ���������̾�� �Ѵ�.
[�Է�]
ù° �ٿ� �ڿ��� N�� M�� �־�����. (1 �� M �� N �� 8)

[���]
�� �ٿ� �ϳ��� ������ ������ �����ϴ� ������ ����Ѵ�. �ߺ��Ǵ� ������ ���� �� ����ϸ� �ȵǸ�, �� ������ �������� �����ؼ� ����ؾ� �Ѵ�.

������ ���� ������ �����ϴ� ������ ����ؾ� �Ѵ�.

[Ǯ��]
DFS�� �� �� idx���� �����Ѵ�(���� ���� ���ؼ��� ���� ����).
*/
import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<Integer> list;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<Integer>(8);
		visited = new boolean[N + 1];
		sb = new StringBuilder();

		DFS(1, 0);

		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static void DFS(int idx, int cnt) {
		if (cnt == M) {
			for (int i : list) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = idx; i <= N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			list.add(i);
			DFS(i, cnt + 1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}
}
