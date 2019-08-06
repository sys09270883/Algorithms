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
