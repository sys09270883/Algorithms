import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int n, m, a, b, c, s, e;
    static List<List<Node>> adj;
    static int[] dists, trace;

    public static void main(String... args) throws IOException {
    	n = io.nextInt();	m = io.nextInt();
    	adj = new ArrayList<List<Node>>(n + 1);
    	for (int i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<Node>());
		}
    	dists = new int[n + 1];
    	Arrays.fill(dists, INF);
    	trace = new int[n + 1];
    	StringBuilder res = new StringBuilder();
    	for (int i = 0; i < m; i++) {
    		a = io.nextInt();	b = io.nextInt();	c = io.nextInt();
    		adj.get(a).add(new Node(b, c));
		}
    	s = io.nextInt();	e = io.nextInt();
    	
    	res.append(dijkstra()).append('\n');
    	Stack<Integer> stack = new Stack<Integer>();
    	int tracking = trace[e];
    	stack.add(e);
    	while (tracking != s) {
    		stack.add(tracking);
    		tracking = trace[tracking];
		}
    	stack.add(s);
    	res.append(stack.size()).append('\n');
    	while (!stack.isEmpty()) {
    		res.append(stack.pop()).append(' ');
    	}
    	
    	io.write(res);
    }
    
    private static int dijkstra() {
    	PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2) -> n1.dist - n2.dist);
    	pq.add(new Node(s, dists[s] = 0));
    	
    	while (!pq.isEmpty()) {
    		Node tmp = pq.poll();
    		int cur = tmp.idx;
    		int curDist = tmp.dist;
    		
    		if (cur == e)
    			return curDist;
    		if (curDist > dists[cur])
    			continue;
    		
    		for (Node n : adj.get(cur)) {
    			int next = n.idx;
    			int nextDist = n.dist;
				if (dists[next] > dists[cur] + nextDist) {
					trace[next] = cur;
					pq.add(new Node(next, dists[next] = dists[cur] + nextDist));
				}
			}
    	}
    	
    	return -1;
    }
    
}

class Node {
	int idx, dist;

	public Node(int idx, int dist) {
		this.idx = idx;
		this.dist = dist;
	}
}

class FastIO {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }

        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    void write(double d) throws IOException {
        bw.write(String.valueOf(d));
        close();
    }

    void write(char c) throws IOException {
        bw.write(c);
        close();
    }

    void write(int i) throws IOException {
        bw.write(String.valueOf(i));
        close();
    }

    void write(long l) throws IOException {
        bw.write(String.valueOf(l));
        close();
    }

    void write(StringBuilder sb) throws IOException {
        bw.write(sb.toString().trim());
        close();
    }

    void write(String str) throws IOException {
        bw.write(str.trim());
        close();
    }

    void close() throws IOException {
        bw.close();
        br.close();
    }
}