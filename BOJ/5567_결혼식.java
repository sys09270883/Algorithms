/*
https://www.acmicpc.net/problem/5567
[문제]
상근이는 자신의 결혼식에 학교 동기 중 자신의 친구와 친구의 친구를 초대하기로 했다. 
상근이의 동기는 모두 N명이고, 이 학생들의 학번은 모두 1부터 N까지이다. 상근이의 학번은 1이다.

상근이는 동기들의 친구 관계를 모두 조사한 리스트를 가지고 있다. 이 리스트를 바탕으로 결혼식에 초대할 사람의 수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 상근이의 동기의 수 n (2 ≤ n ≤ 500)이 주어진다. 둘째 줄에는 리스트의 길이 m (1 ≤ m ≤ 10000)이 주어진다. 
다음 줄부터 m개 줄에는 친구 관계 ai bi가 주어진다. (1 ≤ ai < bi ≤ n) ai와 bi가 친구라는 뜻이며, bi와 ai도 친구관계이다. 

[출력]
첫째 줄에 상근이의 결혼식에 초대하는 동기의 수를 출력한다.

[풀이]
친구의 친구까지에 대해서만(cnt <= 1) BFS 탐색을 하면서 카운팅한다.
 + 문제 조건을 유의하자.
*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, ans;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        ans = 0;
        adj = new ArrayList<ArrayList<Integer>>(n + 3);
        for (int i = 1; i < n + 3; i++) {
			adj.add(new ArrayList<Integer>());
		}
        visited = new boolean[n + 3];
        StringTokenizer st = null;
        int a, b;
        
        for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
        
        BFS();
        
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
 
    private static void BFS() {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(1, 0));
    	visited[1] = true;
    	
    	while (!queue.isEmpty()) {
    		Node tmp = queue.poll();
    		int cur = tmp.idx;
    		int cnt = tmp.cnt;
    		
    		if (cnt > 1)
    			continue;
    		
    		for (int next : adj.get(cur)) {
				if (!visited[next]) {
					ans++;
					visited[next] = true;
					queue.add(new Node(next, cnt + 1));
				}
			}
    	}
    }
}

class Node {
	int idx, cnt;

	public Node(int idx, int cnt) {
		super();
		this.idx = idx;
		this.cnt = cnt;
	}
	
}