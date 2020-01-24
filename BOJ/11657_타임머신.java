import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int INF = Integer.MAX_VALUE;
    static int N, M;
    static List<Edge> edges;
    static int[] costs;

    public static void main(String... args) throws IOException {
    	N = io.nextInt();	M = io.nextInt();
    	edges = new ArrayList<Edge>();
    	costs = new int[N + 1];
    	Arrays.fill(costs, INF);
    	StringBuilder res = new StringBuilder();
    	for (int i = 0; i < M; i++) {
    		edges.add(new Edge(io.nextInt(), io.nextInt(), io.nextInt()));
		}
    	
    	if (bellman())
    		io.write(-1);
    	else {
    		for (int i = 2; i < N + 1; i++) {
				res.append(costs[i] == INF ? -1 : costs[i]).append('\n');
			}
    		io.write(res);
    	}
    }
    
    private static boolean bellman() {
    	boolean negCycle = false;
    	costs[1] = 0;
    	
    	for (int i = 1; i < N + 1; i++) {
			for (Edge e : edges) {
				int cur = e.n1;
				int next = e.n2;
				int cost = e.cost;
				if (costs[cur] == INF)
					continue;
				if (costs[next] > costs[cur] + cost) {
					costs[next] = costs[cur] + cost;
					if (i == N)
						negCycle = true;
				}
			}
		}
    	
    	return negCycle;
    }
    
}

class Edge {
	int n1, n2, cost;

	public Edge(int n1, int n2, int cost) {
		this.n1 = n1;
		this.n2 = n2;
		this.cost = cost;
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