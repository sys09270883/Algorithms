import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int n;
    static int[] arr, trace;
    static boolean[] visited;

    public static void main(String... args) throws IOException {
    	n = io.nextInt();
    	arr = new int[n + 1];
    	trace = new int[n + 1];
    	visited = new boolean[n + 1];
    	StringBuilder res = new StringBuilder();
    	
    	res.append(BFS()).append('\n');
    	Stack<Integer> stack = new Stack<Integer>();
    	int tmp = trace[1];
    	while (tmp != n) {
    		stack.add(tmp);
    		tmp = trace[tmp];
    	}
    	res.append(n).append(' ');
    	while (!stack.isEmpty()) {
    		res.append(stack.pop()).append(' ');
    	}
    	res.append(1);
    	
    	io.write(res);
    }
    
    private static int BFS() {
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.add(new Node(n, 0));
    	visited[n] = true;
    	
    	while (!queue.isEmpty()) {
    		Node tmp = queue.poll();
    		int cur = tmp.idx;
    		int cnt = tmp.cnt;
    		
    		if (cur == 1)
    			return cnt;
    		
    		int next;
    		if (cur % 3 == 0) {
    			next = cur / 3;
    			if (1 <= next && !visited[next]) {
    				visited[next] = true;
    				trace[next] = cur;
    				queue.add(new Node(next, cnt + 1));
    			}
    		}
    		if ((cur & 1) == 0) {
    			next = cur >> 1;
    			if (1 <= next && !visited[next]) {
    				visited[next] = true;
    				trace[next] = cur;
    				queue.add(new Node(next, cnt + 1));
    			}
    		}
    		next = cur - 1;
    		if (1 <= next && !visited[next]) {
    			visited[next] = true;
    			trace[next] = cur;
    			queue.add(new Node(next, cnt + 1));
    		}
    	}
    	
    	return -1;	
    }
    
}

class Node {
	int idx, cnt;

	public Node(int idx, int cnt) {
		this.idx = idx;
		this.cnt = cnt;
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