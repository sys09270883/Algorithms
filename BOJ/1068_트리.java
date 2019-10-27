/*
https://www.acmicpc.net/problem/1068
[����]
Ʈ������ ���� ����, �ڽ��� ������ 0�� ��带 ���Ѵ�.

Ʈ���� �־����� ��, ��� �� �ϳ��� ������ ���̴�. �� ��, ���� Ʈ������ ���� ����� ������ ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

���� ���, ������ ���� Ʈ���� �ִٰ� ����.



���� ���� ����� ������ 3���̴�. (�ʷϻ� ��ĥ�� ���) �̶�, 1���� �����Ѵٰ� �ϸ�, ������ ���� �ȴ�.



���� ���� ����� ������ 1���̴�.

[�Է�]
ù° �ٿ� Ʈ���� ����� ���� N�� �־�����. N�� 50���� �۰ų� ���� �ڿ����̴�. 
��° �ٿ��� 0�� ������ N-1�� ������, �� ����� �θ� �־�����. ���� �θ� ���ٸ� (��Ʈ) -1�� �־�����. ��° �ٿ��� ���� ����� ��ȣ�� �־�����.

[���]
ù° �ٿ� �Է����� �־��� Ʈ������ �Է����� �־��� ��带 ������ ��, ���� ����� ������ ����Ѵ�.

[Ǯ��]
1. �ڽ� ������ adj, �θ� ������  parent�� �����Ѵ�.
2. �Է��� �����鼭 Ʈ���� �����ϰ� ans�� �̸� ���Ѵ�. 
3. ������� ��忡�� DFS Ž���� �ϸ鼭 ans�� ���ҽ�Ų��.
4. ���� ������� ����� �θ��� �ڽ��� ���� �ϳ�(������� ��� �ϳ� �ۿ� ���� ���)��� �θ� ������尡 �Ǵ� ��쵵 ����ؾ� �Ѵ�.
 
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
