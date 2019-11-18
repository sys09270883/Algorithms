/*
https://www.acmicpc.net/problem/16563
[문제]
지원이는 대회에 출제할 문제에 대해서 고민하다가 소인수분해 문제를 출제해야겠다고 마음을 먹었다. 그러나 그 이야기를 들은 동생의 반응은 지원이의 기분을 상하게 했다.

"소인수분해? 그거 너무 쉬운 거 아니야?"

지원이는 소인수분해의 어려움을 알려주고자 엄청난 자신감을 가진 동생에게 2와 500만 사이의 자연수 N개를 주고 소인수분해를 시켰다. 
그러자 지원이의 동생은 기겁하며 쓰러졌다. 힘들어하는 지원이의 동생을 대신해서 여러분이 이것도 쉽다는 것을 보여주자!

[입력]
첫째 줄에는 자연수의 개수 N (1 ≤ N ≤ 1,000,000)이 주어진다.

둘째 줄에는 자연수 ki (2 ≤ ki ≤ 5,000,000, 1 ≤ i ≤ N)가 N개 주어진다.

[출력]
N줄에 걸쳐서 자연수 ki의 소인수들을 오름차순으로 출력하라.

[풀이]
1. 에라토스테네스의 체로 5,000,000의 제곱근까지의 소수를 구한다.
2. 각 쿼리에 대해서 5,000,000의 제곱근까지의 소수들로 소인수 분해를 한다.
  i. 이 과정에서 소수로 나누어 주면서 출력한다.
  ii. 더 이상 나누어지지 않거나 해당 수가 5,000,000의 제곱근보다 클 경우, 해당 수는 무조건 소수이기 때문에 해당 수를 출력한다.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int N = io.nextInt();
        int sqrtSize = (int) Math.sqrt(5000000);
        boolean[] isPrime = new boolean[sqrtSize + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primeList = new ArrayList<Integer>();
        StringBuilder res = new StringBuilder();

        for (int i = 2; i <= sqrtSize; i++) {
            if (!isPrime[i])
                continue;

            for (int j = i << 1; j <= sqrtSize; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = 2; i <= sqrtSize; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }

        for (int i = 0; i < N; i++) {
            int num = io.nextInt();

            for (int p : primeList) {
                while (num % p == 0) {
                    num /= p;
                    res.append(p).append(' ');
                }
            }

            if (num >= sqrtSize)
                res.append(num);

            res.append('\n');
        }

        io.write(res);
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