/*
https://www.acmicpc.net/problem/5558
[문제]
今年も JOI 町のチーズ工場がチーズの生産を始め，ねずみが巣から顔を出した．
JOI 町は東西南北に区画整理されていて，各区画は巣，チーズ工場，障害物，空き地のいずれかである．
ねずみは巣から出発して全てのチーズ工場を訪れチーズを 1 個ずつ食べる．

この町には，N 個のチーズ工場があり，どの工場も１種類のチーズだけを生産している．
チーズの硬さは工場によって異なっており，硬さ 1 から N までのチーズを生産するチーズ工場がちょうど 1 つずつある．

ねずみの最初の体力は 1 であり，チーズを 1 個食べるごとに体力が 1 増える．
ただし，ねずみは自分の体力よりも硬いチーズを食べることはできない．

ねずみは，東西南北に隣り合う区画に 1 分で移動することができるが，障害物の区画には入ることができない．
チーズ工場をチーズを食べずに通り過ぎることもできる．すべてのチーズを食べ終えるまでにかかる最短時間を求めるプログラムを書け．
ただし，ねずみがチーズを食べるのにかかる時間は無視できる．

[입력]
入力は H+1 行ある．1 行目には 3 つの整数 H，W，N (1 ≦ H ≦ 1000，1 ≦ W ≦ 1000，1 ≦ N ≦ 9) 
がこの順に空白で区切られて書かれている．2 行目から H+1 行目までの各行には，
'S'，'1', '2', ..., '9'，'X'，'.' からなる W 文字の文字列が書かれており，
各々が各区画の状態を表している．北から i 番目，西から j 番目の区画を (i,j) と記述することにすると (1 ≦ i ≦ H, 1 ≦ j ≦ W)，
第 i+1 行目の j 番目の文字は，区画 (i,j) が巣である場合は 'S' となり，障害物である場合は 'X' となり，
空き地である場合は '.' となり，硬さ 1, 2, ..., 9 のチーズを生産する工場である場合はそれぞれ '1', '2', ..., '9' となる．
入力には巣と硬さ 1, 2, ..., N のチーズを生産する工場がそれぞれ 1 つずつある．他のマスは障害物または空き地であることが保証されている．
ねずみは全てのチーズを食べられることが保証されている．

[출력]
すべてのチーズを食べ終えるまでにかかる最短時間（分）を表す整数を 1 行で出力せよ．

[풀이]
1부터 n까지의 치즈를 차례대로 찾아 나간다. (S -> 1, 1 -> 2, 2 -> 3, ... , n - 1 -> n)
구간을 BFS로 처리하며 누적 이동거리를 더해준다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int r, c, n, startX, startY;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        visited = new boolean[r][c];
        String input = null;
        int ans = 0;
        
        for (int i = 0; i < r; i++) {
			input = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = input.charAt(j);
				
				if (map[i][j] == 'S') {
					startX = i;
					startY = j;
				}
			}
		}
        
        for (int i = 1; i <= n; i++) {
        	for (boolean[] row : visited) {
        		Arrays.fill(row, false);
        	}
        	
			ans += BFS(i);
		}
        
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
    
    private static int BFS(int target) {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(startX, startY, 0));
    	visited[startX][startY] = true;
    	
    	while (!queue.isEmpty()) {
    		Node tmp = queue.poll();
    		int x = tmp.x;
    		int y = tmp.y;
    		int cnt = tmp.cnt;
    		
    		if (map[x][y] - '0' == target) {
    			map[x][y] = '.';
    			startX = x;
    			startY = y;
    			return cnt;
    		}
    		
    		for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= r || ny >= c)
					continue;
				
				if (visited[nx][ny] || map[nx][ny] == 'X')
					continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny, cnt + 1));
			}
    	}
    	
    	return 0;
    }
    
}

class Node {
	int x, y, cnt;

	public Node(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
}
