/*
https://www.acmicpc.net/problem/1068
[문제]
트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.

트리가 주어졌을 때, 노드 중 하나를 제거할 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.

예를 들어, 다음과 같은 트리가 있다고 하자.



현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 제거한다고 하면, 다음과 같이 된다.



이제 리프 노드의 개수는 1개이다.

[입력]
첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 
둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.

[출력]
첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

[풀이]
1. 자식 정보는 adj, 부모 정보는  parent에 저장한다.
2. 입력을 받으면서 트리를 구성하고 ans를 미리 구한다. 
3. 지우려는 노드에서 DFS 탐색을 하면서 ans를 감소시킨다.
4. 만약 지우려는 노드의 부모의 자식이 오직 하나(지우려는 노드 하나 밖에 없는 경우)라면 부모가 리프노드가 되는 경우도 고려해야 한다.
 
*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Integer>> adj;
	static int ans;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList<ArrayList<Integer>>(N);
        for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Integer>());
		}
        int[] parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			parent[i] = tmp;
			
			if (tmp != -1)
				adj.get(tmp).add(i);
		}
        
        for (int i = 0; i < N; i++) {
			if (adj.get(i).isEmpty())
				ans++;
		}
        
        int target = Integer.parseInt(br.readLine());
        
        DFS(target);
        
        if (parent[target] != -1 && adj.get(parent[target]).size() == 1)
        	ans++;
        
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
    
    private static void DFS(int targetNode) {
    	if (adj.get(targetNode).isEmpty())
    		ans--;
    	
    	for (int nextNode : adj.get(targetNode)) {
			DFS(nextNode);
		}
    }
    
}
