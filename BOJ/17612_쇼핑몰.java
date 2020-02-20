import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt(), K = io.nextInt();
        long res = 0;
        PriorityQueue<Casher> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            pq.add(new Casher(i + 1, 0));
        }
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Customer(io.nextInt(), io.nextInt(), 0, 0));
        }
        for (int i = 0; i < N; i++) {
            Casher casher = pq.poll();
            int id = casher.id;
            int tot = casher.tot;
            Customer customer = list.get(i);
            tot += customer.cost;
            customer.tot = tot;
            customer.casher = id;
            pq.add(new Casher(id, tot));
        }
        Collections.sort(list);
        for (int i = 1; i < N + 1; i++) {
            res += i * (long)list.get(i - 1).id;
        }
        io.write(res);
    }

}

class Customer implements Comparable<Customer> {
    int id, cost, tot, casher;

    public Customer(int id, int cost, int tot, int casher) {
        this.id = id;
        this.cost = cost;
        this.tot = tot;
        this.casher = casher;
    }

    @Override
    public int compareTo(Customer customer) {
        if (this.tot == customer.tot)
            return customer.casher - this.casher;
        return this.tot - customer.tot;
    }
}

class Casher implements Comparable<Casher> {
    int id, tot;

    public Casher(int id, int tot) {
        this.id = id;
        this.tot = tot;
    }

    @Override
    public int compareTo(Casher casher) {
        if (this.tot == casher.tot)
            return this.id - casher.id;
        return this.tot - casher.tot;
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