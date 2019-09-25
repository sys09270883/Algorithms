/*
https://www.acmicpc.net/problem/1976
[문제]
동혁이는 친구들과 함께 여행을 가려고 한다. 한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도, 없을 수도 있다. 
동혁이의 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아보자. 물론 중간에 다른 도시를 경유해서 여행을 할 수도 있다. 
예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고, 동혁이의 여행 계획이 E C B C D 라면
 E-A-B-C-B-C-B-D라는 여행경로를 통해 목적을 달성할 수 있다.

도시들의 개수와 도시들 간의 연결 여부가 주어져 있고, 동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때(중복 가능) 가능한지 여부를 판별하는 프로그램을 작성하시오.

[입력]
첫 줄에 도시의 수 N이 주어진다. N은 200이하이다. 둘째 줄에 여행 계획에 속한 도시들의 수 M이 주어진다. M은 1000이하이다. 
다음 N * N 행렬을 통해 임의의 두 도시가 연결되었는지에 관한 정보가 주어진다. 1이면 연결된 것이고 0이면 연결이 되지 않은 것이다. 
A와 B가 연결되었으면 B와 A도 연결되어 있다. 마지막 줄에는 여행 계획이 주어진다.

[출력]
첫 줄에 가능하면 YES 불가능하면 NO를 출력한다.

[풀이]
경로를 중복방문 가능하고, 단순히 갈 수 있는지 여부에 대한 것만 파악하면 되므로 disjoint set으로 접근한다.
N*N행렬을 입력받을 때 1이면 i에서 j로 갈 수 있다는 뜻이므로 노드를 서로 연결해준다.(union)
크기가 M인 경로 배열을 순회하면서 다음 경로를 갈 수 있는지 여부를 조사하면서 갈 수 없다면 flag를 false로 바꾸고 즉시 반복문을 탈출한다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;	
		}
		boolean flag = true;
		int[] arr = new int[M + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				if (tmp == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M - 1; i++) {
			if (find(arr[i]) != find(arr[i + 1])) {
				flag = false;
				break;
			}
		}
		
		bw.write(flag ? "YES" : "NO");
		bw.close();
		br.close();
	}
	
	private static int find(int x) {
		if (x == parent[x])
			return x;
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y)
			parent[x] = y;
	}
	
}
