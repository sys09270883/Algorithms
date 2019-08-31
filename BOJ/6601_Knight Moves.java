/*
https://www.acmicpc.net/problem/6601
[문제]
A friend of you is doing research on the Traveling Knight Problem (TKP) where you are to find the shortest
 closed tour of knight moves that visits each square of a given set of n squares on a chessboard exactly once. 
 He thinks that the most difficult part of the problem is determining the smallest number of knight moves 
 between two given squares and that, once you have accomplished this, finding the tour would be easy.

Of course you know that it is vice versa. So you offer him to write a program that solves the "difficult" part.

Your job is to write a program that takes two squares a and b as input and then determines 
the number of knight moves on a shortest route from a to b.

[입력]
The input file will contain one or more test cases. Each test case consists of one line containing
 two squares separated by one space. A square is a string consisting of a letter (a-h) representing 
 the column and a digit (1-8) representing the row on the chessboard.

[출력]
For each test case, print one line saying "To get from xx to yy takes n knight moves.".

[풀이]
시작위치가 도착위치에 도달할 때까지 BFS를 실행한다.
도달하면 그 즉시 카운트를 반환한다.
*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] chess;
	static boolean[][] visited;
	static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};
	static StringBuilder res;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int move;
        String str = null, start = null, end = null;
        StringTokenizer st = null;
        chess = new int[8][8];
        visited = new boolean[8][8];
        res = new StringBuilder();
        
        while((str = br.readLine()) != null) {
        	st = new StringTokenizer(str, " ");
        	start = st.nextToken();
        	end = st.nextToken();
        	move = BFS(start, end);
        	assemble(start, end, move);
        	
        	for (boolean[] row : visited)
				Arrays.fill(row, false);
        }
        
        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }
    
    private static int BFS(String start, String end) {
    	Queue<Node> queue = new LinkedList<Node>();
    	int startX = (int)(start.charAt(0) - 'a');
    	int startY = (int)(start.charAt(1) - '0') - 1;
    	int endX = (int)(end.charAt(0) - 'a');
    	int endY = (int)(end.charAt(1) - '0') - 1;
    	queue.add(new Node(startX, startY, 0));
    	visited[startX][startY] = true;
    	
    	while(!queue.isEmpty()) {
    		Node cur = queue.poll();
    		int x = cur.x;
    		int y = cur.y;
    		int cnt = cur.cnt;
    		
    		if (x == endX && y == endY)
    			return cnt; 
    		
    		for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
					continue;
				
				if (visited[nx][ny])
					continue;
				
				visited[nx][ny] = true;
				queue.add(new Node(nx, ny, cnt + 1));
			}
    	}
    	return -1;
    }
    
    private static void assemble(String start, String end, int move) {
    	res.append("To get from ").append(start).append(" to ").append(end)
    	.append(" takes ").append(move).append(" knight moves.\n");
    }
}

class Node {
	int x, y, cnt;
	
	Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}