/*
https://www.acmicpc.net/problem/11404
[����]
n(1 �� n �� 100)���� ���ð� �ִ�. �׸��� �� ���ÿ��� ����Ͽ� �ٸ� ���ÿ� �����ϴ� m(1 �� m �� 100,000)���� ������ �ִ�. 
�� ������ �� �� ����� �� �ʿ��� ����� �ִ�.

��� ������ �� (A, B)�� ���ؼ� ���� A���� B�� ���µ� �ʿ��� ����� �ּڰ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� n(1 �� n �� 100)�� �־����� ��° �ٿ��� ������ ���� m(1 �� m �� 100,000)�� �־�����. 
�׸��� ��° �ٺ��� m+2�ٱ��� ������ ���� ������ ������ �־�����. ���� ó������ �� ������ ��� ������ ��ȣ�� �־�����. 
������ ������ ������ ���� ���� a, ���� ���� b, �� �� Ÿ�µ� �ʿ��� ��� c�� �̷���� �ִ�. 
���� ���ÿ� ���� ���ð� ���� ���� ����. ����� 100,000���� �۰ų� ���� �ڿ����̴�.

���� ���ÿ� ���� ���ø� �����ϴ� �뼱�� �ϳ��� �ƴ� �� �ִ�.

[���]
N���� ���� ����ؾ� �Ѵ�. i��° �ٿ� ����ϴ� j��° ���ڴ� ���� i���� j�� ���µ� �ʿ��� �ּ� ����̴�. 
����, i���� j�� �� �� ���� ��쿡�� �� �ڸ��� 0�� ����Ѵ�.

[Ǯ��]
�ڱ� �ڽ����� ���� ��츦 0, �� �ܸ� INF�� �ʱ�ȭ�Ѵ�.
��� ��� i, j�� ���Ͽ� i -> j�� ���� ��θ� i -> k -> j�� ���� ��ο� ���Ͽ� �� ���� ������ �����Ѵ�.

 + �÷��̵� �˰����� ���� ����

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] map;
	static int n, m;
	static StringBuilder res;
	final static int INF = 987654321;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        res = new StringBuilder();
        StringTokenizer st = null;
        int a, b, c;
        
        for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					map[i][j] = 0;
				
				else 
					map[i][j] = INF;
			}
		}
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			map[a][b] = Math.min(map[a][b], c);
		}
        
        floyd();
        
        update();
        
        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }
    
    private static void floyd() {
    	for (int k = 1; k <= n; k++) {
    		for (int i = 1; i <= n; i++) {
    			for (int j = 1; j <= n; j++) {
    				if (map[i][j] > map[i][k] + map[k][j])
    					map[i][j] = map[i][k] + map[k][j];
    			}
    		}
		}
    }
    
    private static void update() {
    	for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				res.append(map[i][j] == INF ? 0 : map[i][j]).append(' ');
			}
			res.append('\n');
		}
    }
    
}
