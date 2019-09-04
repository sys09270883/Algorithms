/*
https://www.acmicpc.net/problem/5014
[����]
��ȣ�� �ڵ� ������ �ϴ� ��ŸƮ�� ��ŸƮ��ũ�� �����ߴ�. ������ ��ȣ�� �������̴�. ������, ������ �� ��ȣ�� ��ŸƮ��ũ�� �ִ� �ǹ��� �ʰ� �����ϰ� ���Ҵ�.

��ŸƮ��ũ�� �� F������ �̷���� ���� �ǹ��� �繫���� �ְ�, ��ŸƮ��ũ�� �ִ� ���� ��ġ�� G���̴�. 
��ȣ�� ���� �ִ� ���� S���̰�, ���� ���������͸� Ÿ�� G������ �̵��Ϸ��� �Ѵ�.

���� ���������Ϳ��� � ������ �̵��� �� �ִ� ��ư�� ������, ��ȣ�� ź ���������ʹ� ��ư�� 2���ۿ� ����. 
U��ư�� ���� U���� ���� ��ư, D��ư�� �Ʒ��� D���� ���� ��ư�̴�. (����, U�� ��, �Ǵ� D�� �Ʒ��� �ش��ϴ� ���� ���� ����, ���������ʹ� �������� �ʴ´�)

��ȣ�� G���� �����Ϸ���, ��ư�� ��� �� �� ������ �ϴ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�. 
����, ���������͸� �̿��ؼ� G���� �� �� ���ٸ�, "use the stairs"�� ����Ѵ�.

[�Է�]
ù° �ٿ� F, S, G, U, D�� �־�����. (1 �� S, G �� F �� 1000000, 0 �� U, D �� 1000000) �ǹ��� 1������ �����ϰ�, ���� ���� ���� F���̴�.

[���]
ù° �ٿ� ��ȣ�� S������ G������ ���� ���� ������ �ϴ� ��ư�� ���� �ּڰ��� ����Ѵ�. ����, ���������ͷ� �̵��� �� ���� ���� "use the stairs"�� ����Ѵ�.

[Ǯ��]
1�� ���� ������ �� ���� F�� ���� �ö� �� ���� ���� �����Ѵ�.
U��ŭ �ö󰡰� D��ŭ �������� BFS�Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int F, S, G, U, D;
	static boolean[] visited;
	static int[] df = new int[2];
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        visited = new boolean[F + 1];
        df[0] = U;
        df[1] = -D;
        int res = BFS();
        
        bw.write(res != -1 ? String.valueOf(res) : "use the stairs");
        bw.close();
        br.close();
    }
    
    private static int BFS() {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(S, 0));
    	visited[S] = true;
    	
    	while (!queue.isEmpty()) {
    		Node cur = queue.poll();
    		int curFloor = cur.floor;
    		int curCnt = cur.cnt;
    		
    		if (curFloor == G)
    			return curCnt;
    		
    		for (int i = 0; i < 2; i++) {
				int nextFloor = curFloor + df[i];
				
				if (nextFloor < 1 || nextFloor > F)
					continue;
				
				if (visited[nextFloor])
					continue;
				
				visited[nextFloor] = true;
				queue.add(new Node(nextFloor, curCnt + 1));
			}
    	}
    	
    	return -1;
    }
    
}

class Node {
	int floor, cnt;
	
	public Node(int floor, int cnt) {
		this.floor = floor;
		this.cnt = cnt;
	}
}
