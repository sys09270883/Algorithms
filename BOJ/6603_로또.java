/*
https://www.acmicpc.net/problem/6603
[����]
���� �ζǴ� {1, 2, ..., 49}���� �� 6���� ����.

�ζ� ��ȣ�� �����ϴµ� ���Ǵ� ���� ������ ������ 49���� �� �� k(k>6)���� ���� ��� ���� S�� ���� ���� �� ���� ������ ��ȣ�� �����ϴ� ���̴�.

���� ���, k=8, S={1,2,3,5,8,13,21,34}�� ��� �� ���� S���� ���� �� �� �ִ� ����� ���� �� 28�����̴�. 
([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])

���� S�� k�� �־����� ��, ���� ���� ��� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
�Է��� ���� ���� �׽�Ʈ ���̽��� �̷���� �ִ�. �� �׽�Ʈ ���̽��� �� �ٷ� �̷���� �ִ�. 
ù ��° ���� k (6 < k < 13)�̰�, ���� k�� ���� ���� S�� ���ԵǴ� ���̴�. S�� ���Ҵ� ������������ �־�����.

�Է��� ������ �ٿ��� 0�� �ϳ� �־�����. 

[���]
�� �׽�Ʈ ���̽����� ���� ���� ��� ����� ����Ѵ�. �̶�, ���� ������ ����Ѵ�.

�� �׽�Ʈ ���̽� ���̿��� �� ���� �ϳ� ����Ѵ�.

[Ǯ��]
1. depth�� 6�� �� ��� res�� ���� �׾Ƶд�.
2. depth�� 6�� �� ��ĥ ��� ans[depth]�� ����Ʈ�� i�� ° ���̸�, DFS(depth + 1, i + 1)�� ��� ȣ���Ѵ�. (i���� ū ���� ���ؼ���)

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static StringBuilder res = new StringBuilder();
	static int[] ans = new int[6];
	static int length;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input = null;
        StringTokenizer st = null;
        
        while (!(input = br.readLine()).equals("0")) {
        	st = new StringTokenizer(input, " ");
        	list.clear();
        	
        	st.nextToken();
        	
        	length = st.countTokens();
        	
        	while (st.hasMoreTokens()) {
        		list.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	DFS(0, 0);
        	res.append('\n');
        }
        
        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }
    
    private static void DFS(int depth, int n) {
    	if (depth >= 6) {
    		for (int i = 0; i < 6; i++) {
				res.append(ans[i]).append(" ");
			}
    		res.append('\n');
    		
    		return;
    	}
    	
    	for (int i = n; i < length; i++) {
			ans[depth] = list.get(i);
			DFS(depth + 1, i + 1);
		}
    }
}
