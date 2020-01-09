import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), M = io.nextInt(), K = io.nextInt(), val, a, b;
        Set<Integer> hs = new HashSet<>();
        List<Integer> list = new ArrayList<>(2 * N - 2);
        for (int i = 1; i < 4 * N + 1; i++) {
            hs.add(i);
        }
        for (int i = 0; i < 2 * M; i++) {
            hs.remove(io.nextInt());
        }
        a = io.nextInt();   b = io.nextInt();
        val = Math.abs(a % K - b % K);
        hs.remove(a);   hs.remove(b);

        Iterator<Integer> it = hs.iterator();
        while (it.hasNext()) {
            list.add(it.next() % K);
        }
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list);

        int len = list.size(), left = 0, right = 1, cnt = 0, turn = 0;
        while (right < len && list.get(left) >= val && turn < M - 1) {
            while (list.get(left) - list.get(right) <= val) {
                right++;
                if (right >= len)
                    break;
            }
            if (right >= len)
                break;
            turn++;
            cnt++;
            left++;
            right++;
        }

        io.write(cnt);
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
