/*
https://www.acmicpc.net/problem/11404
[문제]
n(1 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다. 
각 버스는 한 번 사용할 때 필요한 비용이 있다.

모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 도시의 개수 n(1 ≤ n ≤ 100)이 주어지고 둘째 줄에는 버스의 개수 m(1 ≤ m ≤ 100,000)이 주어진다. 
그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 
버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다. 
시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.

시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.

[출력]
N개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다. 
만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

[풀이]
자기 자신으로 가는 경우를 0, 그 외를 INF로 초기화한다.
모든 경로 i, j에 대하여 i -> j로 가는 경로를 i -> k -> j로 가는 경로와 비교하여 더 작은 값으로 갱신한다.

 + 플로이드 알고리즘의 개념 문제

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
