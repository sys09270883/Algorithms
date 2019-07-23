/*
https://www.acmicpc.net/problem/11659

[문제]
수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 수의 개수 N (1 ≤ N ≤ 100,000), 합을 구해야 하는 횟수 M (1 ≤ M ≤ 100,000)이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.

[출력]
총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.

[풀이]
1) 구간합 알고리즘(sum[])
   sum[0] = arr[0], sum[n] = sum[n-1] + arr[n]을 만족하는 sum배열을 만든다.
   (a, b) 구간의 합은 sum[b] - sum[a-1]이다.

2) 세그먼트 트리(구간 트리)
   https://www.crocus.co.kr/648
   https://wondy1128.tistory.com/150
   완전이진트리로 구현, 구간에 대한 정보를 알 수 있으며 합의 시간복잡도는 O(MN)이다.
*/
// 1) 구간합 알고리즘
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		long[] sum = new long[N+1];
		for (int i = 1; i < N+1; i++) {
			if(i==1)
				sum[i] = arr[i];
			else
				sum[i] = sum[i-1] + arr[i];
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(sum[b] - sum[a-1]).append('\n');
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
}

// 2) 세그먼트 트리
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int treeSize = (1 << (int)Math.ceil(log2(N)) + 1);
		long[] tree = new long[treeSize];
		init(arr, tree, 1, 0 , N-1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sb.append(sum(tree, 1, 0, N-1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1)).append('\n');
		}

		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	public static long init(int[] arr, long[] tree, int node, int start, int end){
		if(start == end)
			return tree[node] = arr[start];

		int mid = (start + end) / 2;

		return tree[node] = init(arr, tree, node*2, start, mid) + init(arr, tree, node*2+1, mid+1, end);
	}
	
	private static long sum(long[] tree, int node, int start, int end, int left, int right){
		if(left > end || right < start)
			return 0;

		if(left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(tree, node*2, start, mid, left, right) + sum(tree, node*2+1, mid+1, end, left, right);
	}

	public static double log2(double x){
		return Math.log(x) / Math.log(2);
	}
}
