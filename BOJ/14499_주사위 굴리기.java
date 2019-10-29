/*
https://www.acmicpc.net/problem/14499
[����]
ũ�Ⱑ N��M�� ������ �����Ѵ�. ������ �������� ����, ������ �����̴�. �� ������ ���� �ֻ����� �ϳ� ������ ������, �ֻ����� �������� �Ʒ��� ����. 
������ ��ǥ�� (r, c)�� ��Ÿ����, r�� �������κ��� ������ ĭ�� ����, c�� �������κ��� ������ ĭ�� �����̴�. 

  2
4 1 3
  5
  6
�ֻ����� ���� ���� �� ���� 1�̰�, ������ �ٶ󺸴� ������ 3�� ���·� ������ ������, ������ �ִ� ���� ��ǥ�� (x, y) �̴�. 
���� ó���� �ֻ������� ��� �鿡 0�� ������ �ִ�.

������ �� ĭ���� ������ �ϳ��� ������ �ִ�. �ֻ����� ������ ��, �̵��� ĭ�� ���� �ִ� ���� 0�̸�, �ֻ����� �ٴڸ鿡 ���� �ִ� ���� ĭ�� ����ȴ�.
0�� �ƴ� ��쿡�� ĭ�� ���� �ִ� ���� �ֻ����� �ٴڸ����� ����Ǹ�, ĭ�� ���� �ִ� ���� 0�� �ȴ�.

�ֻ����� ���� ���� ��ǥ�� �̵���Ű�� ����� �־����� ��, �ֻ����� �̵����� �� ���� ��ܿ� ���� �ִ� ���� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

�ֻ����� ������ �ٱ����� �̵���ų �� ����. ���� �ٱ����� �̵���Ű���� �ϴ� ��쿡�� �ش� ����� �����ؾ� �ϸ�, ��µ� �ϸ� �� �ȴ�.

[�Է�]
ù° �ٿ� ������ ���� ũ�� N, ���� ũ�� M (1 �� N, M �� 20), �ֻ����� ���� ���� ��ǥ x y(0 �� x �� N-1, 0 �� y �� M-1), 
�׸��� ����� ���� K (1 �� K �� 1,000)�� �־�����.

��° �ٺ��� N���� �ٿ� ������ ���� �ִ� ���� ���ʺ��� ��������, �� ���� ���ʺ��� ���� ������� �־�����. �ֻ����� ���� ĭ�� ���� �ִ� ���� �׻� 0�̴�. 
������ �� ĭ�� ���� �ִ� ���� 10�� ���� �ʴ� �ڿ��� �Ǵ� 0�̴�.

������ �ٿ��� �̵��ϴ� ����� ������� �־�����. ������ 1, ������ 2, ������ 3, ������ 4�� �־�����.

[���]
�̵��� ������ �ֻ����� �� �鿡 ���� �ִ� ���� ����Ѵ�. ���� �ٱ����� �̵���Ű���� �ϴ� ��쿡�� �ش� ����� �����ؾ� �ϸ�, ��µ� �ϸ� �� �ȴ�.

[Ǯ��]
������������ ���ǿ� ���� ����� �����ϸ� �Ǵ� ��������...
��, ��, ��, �Ͽ����� ������ ���⿡ ���Ͽ� �� �� �ִ��� Ȯ���ϰ�, �� �� ������ �ֻ����� ������ ����Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	final static int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4; 
	static int[][] map;
	static int[] cube;
	static int N, M, x, y;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cube = new int[6];	// BNEWST
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < K; i++) {
        	int dir = Integer.parseInt(st.nextToken());
        	
        	switch (dir) {
    		case EAST:
    			if (y + 1 < 0 || y + 1 >= M)
    				continue;
    			
    			y++;
    			swap(new int[]{0, 2, 5, 3});
    			break;
    			
    		case WEST:
    			if (y - 1 < 0 || y - 1 >= M)
    				continue;
    			
    			y--;
    			swap(new int[]{0, 3, 5, 2});
    			break;
    			
    		case NORTH:
    			if (x - 1 < 0 || x - 1 >= N)
    				continue;
    			
    			x--;
    			swap(new int[]{0, 1, 5, 4});
    			break;
    			
    		case SOUTH:
    			if (x + 1 < 0 || x + 1 >= N)
    				continue;
    			
    			x++;
    			swap(new int[]{0, 4, 5, 1});
    			break;
    		}
        	
        	if (map[x][y] == 0)
        		map[x][y] = cube[0];
        	
        	else {
        		cube[0] = map[x][y];
        		map[x][y] = 0;
        	}
        	
        	res.append(cube[5]).append('\n');
		}
        
        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }
    
    private static void swap(int[] arr) {
    	int tmp = cube[arr[0]];
    	cube[arr[0]] = cube[arr[1]];
    	cube[arr[1]] = cube[arr[2]];
    	cube[arr[2]] = cube[arr[3]];
    	cube[arr[3]] = tmp;
    }
    
}
