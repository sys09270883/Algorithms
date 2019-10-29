/*
https://www.acmicpc.net/problem/14499
[문제]
크기가 N×M인 지도가 존재한다. 지도의 오른쪽은 동쪽, 위쪽은 북쪽이다. 이 지도의 위에 주사위가 하나 놓여져 있으며, 주사위의 전개도는 아래와 같다. 
지도의 좌표는 (r, c)로 나타내며, r는 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로부터 떨어진 칸의 개수이다. 

  2
4 1 3
  5
  6
주사위는 지도 위에 윗 면이 1이고, 동쪽을 바라보는 방향이 3인 상태로 놓여져 있으며, 놓여져 있는 곳의 좌표는 (x, y) 이다. 
가장 처음에 주사위에는 모든 면에 0이 적혀져 있다.

지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.

주사위를 놓은 곳의 좌표와 이동시키는 명령이 주어졌을 때, 주사위가 이동했을 때 마다 상단에 쓰여 있는 값을 구하는 프로그램을 작성하시오.

주사위는 지도의 바깥으로 이동시킬 수 없다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.

[입력]
첫째 줄에 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20), 주사위를 놓은 곳의 좌표 x y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1), 
그리고 명령의 개수 K (1 ≤ K ≤ 1,000)가 주어진다.

둘째 줄부터 N개의 줄에 지도에 쓰여 있는 수가 북쪽부터 남쪽으로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 주사위를 놓은 칸에 쓰여 있는 수는 항상 0이다. 
지도의 각 칸에 쓰여 있는 수는 10을 넘지 않는 자연수 또는 0이다.

마지막 줄에는 이동하는 명령이 순서대로 주어진다. 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.

[출력]
이동할 때마다 주사위의 윗 면에 쓰여 있는 수를 출력한다. 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.

[풀이]
구현문제여서 조건에 따라 충실히 구현하면 되는 문제였다...
동, 서, 남, 북에따라 굴리는 방향에 대하여 갈 수 있는지 확인하고, 갈 수 있으면 주사위를 굴리고 출력한다.

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
