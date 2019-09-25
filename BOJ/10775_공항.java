/*

[����]
������ �Ž¿��� �����̴�.

�ڽ¿��� ������ �¾� �Ž¿����� ��õ���������� ������ ���.

���׿��� G���� ����Ʈ�� ������ ������ 1���� G������ ��ȣ�� ������ �ִ�.

���׿��� P���� ����Ⱑ ������� ������ �����̸�, ����� i��° ����⸦ 1������ gi (1 �� gi �� G) ��° ����Ʈ�� �ϳ��� ���������� ��ŷ�Ϸ� �Ѵ�. 
����Ⱑ ��ŷ�� ����Ʈ���� �ٸ� ����Ⱑ ������ �� ����.

�̷��� ������ ��� ��� ��Ȥ ����Ⱑ � ������ ��ŷ���� ���ϴ� ��� �߻��Ѵ�. 
�̷��� ��� �Ͼ�� ������ ������ �޼��� ������, ���� � ����⵵ �������� ������ �� ���̴�.

�Ž¿��� ���� ���� ����⸦ ���׿� ��ŷ���Ѽ� �ڽ¿��� �ູ�ϰ� �ϰ� �;��Ѵ�. �¿��̴� ����⸦ �ִ� �� �� ��ŷ��ų �� �ִ°�?

[�Է�]
ù ��° �ٿ��� ����Ʈ�� �� G (1 �� G �� 105)�� �־�����.

�� ��° �ٿ��� ������� �� P (1 �� P �� 105)�� �־�����.

���� P���� �ٿ� gi (1 �� gi �� G) �� �־�����.

[���]
�¿��̰� ��ŷ��ų �� �ִ� �ִ��� ����� ���� ����Ѵ�.

[Ǯ��]
�ڽ��� ��ŷ�� �� �ִ� ���� ū ���� ����Ʈ���� ��ŷ�Ѵ�.
�׸��� �ڽ��� ��� ����Ʈ���� ��ŷ�� �� ���ٸ� �ݺ����� Ż���Ѵ�.
�θ� ���� �������� 1�� ���� �����ϸ� ���Ͽ°����� �ʿ� ����.

*/
import java.io.*;

public class Main {
	
	static int G, P;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parent = new int[G + 1];
		for (int i = 1; i <= G; i++) {
			parent[i] = i;
		}
		int res = 0;
		
		for (int i = 1; i <= P; i++) {
			int tmp = Integer.parseInt(br.readLine());
			int d = find(tmp);
			
			if (d == 0)
				break;
			
			parent[d] = d - 1;
			res++;
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
}