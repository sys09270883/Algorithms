import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
    	int N = io.nextInt(), total = 0;
    	List<Ball> list = new ArrayList<Ball>();
    	int[] ans = new int[N];
    	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    	StringBuilder res = new StringBuilder();
    	for (int i = 0; i < N; i++) {
    		list.add(new Ball(i, io.nextInt(), io.nextInt()));
		}
    	
    	Collections.sort(list);
    	
    	for (int i = 0, j = 0; i < N; i++) {
			Ball a = list.get(i);
			Ball b = list.get(j);
			
			while (b.size < a.size) {
	    		total += b.size;
	    		hm.put(b.color, hm.get(b.color) == null ? b.size : hm.get(b.color) + b.size);
	    		b = list.get(++j);
			}
			
			ans[a.idx] += hm.get(a.color) == null ? total : total - hm.get(a.color);
		}
    	
    	for (int i : ans) {
			res.append(i).append('\n');
		}
    	
    	io.write(res);
    }
    
}

class Ball implements Comparable<Ball> {
	int idx, color, size;

	public Ball(int idx, int color, int size) {
		this.idx = idx;
		this.color = color;
		this.size = size;
	}
	
	@Override
	public int compareTo(Ball o) {
		// TODO Auto-generated method stub
		if (this.size == o.size)
			return this.color - o.color;
		return this.size - o.size;
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
