/*
https://www.acmicpc.net/problem/17830
[문제]
이진수 씨는 이진수를 사랑한다. 그의 하루 일과는 하루 종일 이진수 두 개를 생각해놓고, 그 두 수의 곱을 "오늘의 이진수"로 선정한다. 
그리고 예쁜 종이를 한 장 사와 "오늘의 이진수"를 적은 후 액자에 전시한다. 
그러나 그런 진수씨에게도 시련이 찾아왔으니, 종이를 사기 위해 나온 도중에 "오늘의 이진수"를 잊어버리고 만 것이다. 
하지만, 진수 씨가 오늘 하루 생각해 놓은 두 이진수에 대해서는 어렴풋이 기억하고 있다.

  

그 두 이진수를 A와 B라고 하자. 진수 씨가 기억하는 사실은 다음과 같다. A와 B는 N개의 비트로 이루어져 있다.
A의 모든 비트는 1이다. 하지만 B의 일부 비트는 기억하지 못한다. 여기서 "오늘의 이진수"를 어느 정도 추측해 볼 수 있다.

예를 들어, N = 4라면 A = 1111이다. 여기서 B = ?00?라고 해보자. ?는 기억하지 못하는 비트를 의미한다. 
그렇다면 B는 0000, 0001, 1000, 1001중 하나일 것이다. 따라서 이 경우 "오늘의 이진수"는 A×B, 즉 0, 1111, 1111000, 10000111중 하나일 것이다. 
B는 leading zero를 포함할 수 있지만, "오늘의 이진수"는 leading zero를 생략한다. 
즉, B는 맨 앞 자리가 1이 아닐 수 있지만, 진수씨가 "오늘의 이진수"를 적을 때에는 A×B=0일 때를 제외하면 맨 앞자리가 반드시 1이 되도록 0을 생략한다.

진수 씨는 "오늘의 이진수"에 비해 너무 크거나 작은 종이를 사고 싶지 않다. 
따라서 "오늘의 이진수"를 쓸 때, 가능한 최대 자릿수와 최소 자릿수를 구해보고자 한다. 
하지만, 진수 씨는 그의 일생 동안의 경험으로 큰 이진수를 빠르게 곱하는 것은 매우 어렵다는 것을 알고 있다. 따라서 여러분이 그 답을 대신 구해주자.

[입력]
첫 번째 줄에 테스트케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 

두 번째 줄부터 T개의 줄에 걸쳐, A와 B의 길이 N(1 ≤ N ≤ 1,000,000)과 B가 공백으로 구분되어 주어진다. B는 0, 1, ?로 이루어져 있으며, ?는 해당 비트를 기억하지 못함을 의미한다.

[출력]
T개의 줄에 걸쳐, 각 테스트케이스에 대해 "오늘의 이진수"의 최대 자릿수와 최소 자릿수를 공백으로 구분하여 출력한다.

[풀이]
1. 문자열의 모든 숫자가 0인 경우에는 항상 1이다.
2. 문자열의 처음 1이 나온 자리 이후로 0만 있다면 N + M - 1이다. (M은 처음 나온 1 이후 숫자의 길이)
3. 그 외의 경우에는 N + M이다.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int T = io.nextInt();
        StringBuilder res = new StringBuilder();

        while (T-- > 0) {
            int N = io.nextInt();
            String B = io.next();

            String bigB = B.replace('?', '1');
            String smallB = B.replace('?', '0');

            res.append(getSize(bigB, N)).append(' ').append(getSize(smallB, N)).append('\n');
        }

        io.write(res);
    }

    private static int getSize(String str, int N) {
        int firstIdx = str.indexOf('1');
        int lastIdx = str.lastIndexOf('1');

        if (firstIdx < 0)
            return 1;

        else if (firstIdx == lastIdx)
            return N + (N - firstIdx - 1);

        else
            return N + (N - firstIdx);
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