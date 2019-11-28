/*
https://www.acmicpc.net/problem/2251
[문제]
각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다.
처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다.
이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다.
이 과정에서 손실되는 물은 없다고 가정한다.

이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다.
첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

[입력]
첫째 줄에 세 정수 A, B, C가 주어진다.

[출력]
첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.

[풀이]
물통의 높이를 3차원 배열에 저장하면서 BFS로 방문한다.
조건에 따라 a가 0일 때, 그 때의 C값을 TreeSet에 넣는다.
TreeSet을 순회하면서 값을 출력한다.

 */
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    static int A, B, C;
    static Set<Integer> ts;
    static boolean[][][] visited;

    public static void main(String... args) throws IOException {
        A = io.nextInt();
        B = io.nextInt();
        C = io.nextInt();
        visited = new boolean[A + 1][B + 1][C + 1];
        ts = new TreeSet<>();
        StringBuilder res = new StringBuilder();

        BFS();

        Iterator<Integer> it = ts.iterator();

        while (it.hasNext()) {
            res.append(it.next()).append(' ');
        }

        io.write(res);
    }

    private static void BFS() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0 , C));

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            int a = tmp.a;
            int b = tmp.b;
            int c = tmp.c;

            if (visited[a][b][c])
                continue;

            visited[a][b][c] = true;

            if (a == 0)
                ts.add(c);

            if (a + b > B)
                queue.add(new Node(a + b - B, B, c));
            else
                queue.add(new Node(0, a + b, c));

            if (a + c > C)
                queue.add(new Node(a + b - C, b, C));
            else
                queue.add(new Node(0, b, a + c));

            if (b + a > A)
                queue.add(new Node(A , b + a - A, c));
            else
                queue.add(new Node(b + a, 0 , c));

            if (b + c > C)
                queue.add(new Node(a, b + c - C, C));
            else
                queue.add(new Node(a, 0, b + c));

            if (c + a > A)
                queue.add(new Node(A, b, c + a - A));
            else
                queue.add(new Node(c + a, b, 0));

            if (c + b > B)
                queue.add(new Node(a, B, c + b - B));
            else
                queue.add(new Node(a, c + b, 0));
        }
    }
}

class Node {
    int a, b, c;

    public Node(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
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