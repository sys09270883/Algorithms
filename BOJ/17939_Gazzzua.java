import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
    	int N = io.nextInt(), res = 0, pointer = 0;
    	Node[] arr = new Node[N];
    	PriorityQueue<Node> pq = new PriorityQueue<Node>();
    	for (int i = 0; i < N; i++) {
    		Node tmp = new Node(io.nextInt(), i, true);
    		arr[i] = tmp;
			pq.add(tmp);
		}
    	
    	while (!pq.isEmpty()) {
    		Node tmp = pq.poll();
    		int curNum = tmp.num;
    		int curIdx = tmp.idx;
    		boolean curFlag = tmp.flag;
    		if (!curFlag)
    			continue;
    		for (int i = pointer; i <= curIdx; i++) {
				if (arr[i].flag) {
					res += curNum - arr[i].num;
					arr[i].flag = false;
				}
			}
    		pointer = curIdx + 1;
    	}
    	
    	io.write(res);
    }

}

class Node implements Comparable<Node>{
	int num, idx;
	boolean flag;
	
	Node(int num, int idx, boolean flag) {
		this.num = num;
		this.idx = idx;
		this.flag = flag;
	}
	
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return o.num - this.num;
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

    String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str;
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
