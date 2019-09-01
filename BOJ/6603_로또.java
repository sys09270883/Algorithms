/*
https://www.acmicpc.net/problem/6603
[문제]
독일 로또는 {1, 2, ..., 49}에서 수 6개를 고른다.

로또 번호를 선택하는데 사용되는 가장 유명한 전략은 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 선택하는 것이다.

예를 들어, k=8, S={1,2,3,5,8,13,21,34}인 경우 이 집합 S에서 수를 고를 수 있는 경우의 수는 총 28가지이다. 
([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])

집합 S와 k가 주어졌을 때, 수를 고르는 모든 방법을 구하는 프로그램을 작성하시오.

[입력]
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스는 한 줄로 이루어져 있다. 
첫 번째 수는 k (6 < k < 13)이고, 다음 k개 수는 집합 S에 포함되는 수이다. S의 원소는 오름차순으로 주어진다.

입력의 마지막 줄에는 0이 하나 주어진다. 

[출력]
각 테스트 케이스마다 수를 고르는 모든 방법을 출력한다. 이때, 사전 순으로 출력한다.

각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.

[풀이]
1. depth가 6이 될 경우 res에 값을 쌓아둔다.
2. depth가 6에 못 미칠 경우 ans[depth]는 리스트의 i번 째 값이며, DFS(depth + 1, i + 1)로 재귀 호출한다. (i보다 큰 값에 대해서만)

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
