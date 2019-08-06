/*
https://www.acmicpc.net/problem/1931
[문제]
한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의들에 대하여 회의실 사용표를 만들려고 한다.
각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 최대수의 회의를 찾아라.
단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.

[입력]
첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다.
둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다.
시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.

[출력]
첫째 줄에 최대 사용할 수 있는 회의 수를 출력하여라.

[풀이]
시작시간과 끝나는 시간을 저장하는 Node 클래스를 만들고, 우선순위 큐로 정렬한다.

  1. 끝나는 시간이 작을수록,
  2. 끝나는 시간이 같으면 시작시간이 작을수록
정렬한다.
우선순위 큐에서 poll()한 tmp.start가 curEnd보다 큰 지 확인하고, 크면 curEnd값을 tmp.end로 갱신한다.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, ans, curEnd;
	static BufferedWriter bw;
	static BufferedReader br;
	static StringTokenizer st;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<Node>(new NodeComparator());
		ans = 0;
		curEnd = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		while(!pq.isEmpty()) {
			Node tmp = pq.poll();
			if(tmp.start >= curEnd) {
				ans++;
				curEnd = tmp.end;
			}
		}
		
		bw.write(String.valueOf(ans));
		bw.close();
		br.close();
	}
}

class Node {
	int start, end;
	
	public Node(int start, int end) {
		// TODO Auto-generated constructor stub
		this.start = start;
		this.end = end;
	}
}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		if(o1.end == o2.end)
			return o1.start - o2.start;
		
		return o1.end - o2.end;
	}
}
