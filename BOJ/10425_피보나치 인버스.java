/*
https://www.acmicpc.net/problem/10425
[문제]
피보나치 수는 수학에서 위의 점화식으로 정의되는 수열이다. 피보나치 수는 0과 1로 시작하며, 다음 피보나치 수는 바로 앞의 두 피보나치 수의 합이 된다. 
n = 0, 1,...에 해당하는 피보나치 수는 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946… 이다. 

프로그래밍 실습에서 문제 중 n을 입력 받았을 때 Fn의 값을 출력하는 문제가 자주 등장한다. 실습을 하고 있던 희원이는 문득 이 문제가 너무 쉽다고 생각했다. 
희원이는 실습 도중 반대로 Fn이 주어졌을 때 n을 출력하는 문제는 어떨지 궁금했다.  피보나치 수 Fn이 주어졌을 때 n을 출력하는 프로그램을 만들어 보자.

[입력]
첫 번재 줄에 테스트케이스를 나타내는 T(1 ≤ T ≤ 100)가 입력으로 주어진다. 두 번째 줄부터 각 테스트케이스마다 양의 정수 Fn이 입력으로 주어진다. 
(1 ≤ Fn ≤ 1021000, 1 ≤ N ≤ 100,000)

[출력]
피보나치 수 Fn이 주어졌을 때 n을 출력한다. 만약 가능한 경우가 여러 개 있는 경우에는 가장 큰 인덱스를 출력하라. 피보나치 수가 아닌 수가 들어오는 경우는 없다.

[풀이]
BigInteger로 100000까지의 피보나치를 구한 후, 해당 입력을 탐색한다.(ArrayList.find())
 + 문제의 의도는 큰 수의 구현인 듯?

*/

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
    	int N = io.nextInt();
    	ArrayList<BigInteger> fibo = new ArrayList<BigInteger>();
    	fibo.add(BigInteger.ZERO);
    	fibo.add(BigInteger.ONE);
    	for (int i = 2; i < 100001; i++) {
    		fibo.add(fibo.get(i - 1).add(fibo.get(i - 2)));
    	}
    	StringBuilder res = new StringBuilder();
    	
    	for (int i = 0; i < N; i++) {
    		BigInteger target = new BigInteger(io.next());
    		
    		if (target.equals(BigInteger.ONE))
    			res.append(2).append('\n');
    		
    		else
    			res.append(fibo.indexOf(target)).append('\n');
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