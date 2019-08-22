/*
https://www.acmicpc.net/problem/1717
[����]
�ʱ⿡ {0}, {1}, {2}, ... {n} �� ���� n+1���� ������ �̷�� �ִ�. ���⿡ ������ �����, �� ���Ұ� ���� ���տ� ���ԵǾ� �ִ����� Ȯ���ϴ� ������ �����Ϸ��� �Ѵ�.

������ ǥ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� n(1��n��1,000,000), m(1��m��100,000)�� �־�����.
m�� �Է����� �־����� ������ �����̴�. 
���� m���� �ٿ��� ������ ������ �־�����. �������� 0 a b�� ���·� �Է��� �־�����. 
�̴� a�� ���ԵǾ� �ִ� ���հ�, b�� ���ԵǾ� �ִ� ������ ��ģ�ٴ� �ǹ��̴�. 
�� ���Ұ� ���� ���տ� ���ԵǾ� �ִ����� Ȯ���ϴ� ������ 1 a b�� ���·� �Է��� �־�����. 
�̴� a�� b�� ���� ���տ� ���ԵǾ� �ִ����� Ȯ���ϴ� �����̴�. 
a�� b�� n ������ �ڿ����Ǵ� 0�̸� ���� ���� �ִ�.

[���]
1�� �����ϴ� �Է¿� ���ؼ� �� �ٿ� �ϳ��� YES/NO�� ����� ����Ѵ�. (yes/no �� ����ص� �ȴ�)

[Ǯ��]
���Ͽ����ε� ���� : https://mygumi.tistory.com/246

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int func, a, b;
		
		parent = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			func = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if (func == 1) {
				a = find(a);
				b = find(b);
				
				if (a == b)
					sb.append("YES\n");
				
				else
					sb.append("NO\n");
			}
			
			else
				union(a, b);
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
	
	private static int find(int n) {
		if (n == parent[n])
			return n;
		
		return parent[n] = find(parent[n]);
	}
	
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		
		if(u != v)
			parent[u] = v;
	}
}
