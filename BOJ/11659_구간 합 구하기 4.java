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