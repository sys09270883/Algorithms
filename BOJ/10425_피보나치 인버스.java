/*
https://www.acmicpc.net/problem/10425
[����]
�Ǻ���ġ ���� ���п��� ���� ��ȭ������ ���ǵǴ� �����̴�. �Ǻ���ġ ���� 0�� 1�� �����ϸ�, ���� �Ǻ���ġ ���� �ٷ� ���� �� �Ǻ���ġ ���� ���� �ȴ�. 
n = 0, 1,...�� �ش��ϴ� �Ǻ���ġ ���� 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946�� �̴�. 

���α׷��� �ǽ����� ���� �� n�� �Է� �޾��� �� Fn�� ���� ����ϴ� ������ ���� �����Ѵ�. �ǽ��� �ϰ� �ִ� ����̴� ���� �� ������ �ʹ� ���ٰ� �����ߴ�. 
����̴� �ǽ� ���� �ݴ�� Fn�� �־����� �� n�� ����ϴ� ������ ��� �ñ��ߴ�.  �Ǻ���ġ �� Fn�� �־����� �� n�� ����ϴ� ���α׷��� ����� ����.

[�Է�]
ù ���� �ٿ� �׽�Ʈ���̽��� ��Ÿ���� T(1 �� T �� 100)�� �Է����� �־�����. �� ��° �ٺ��� �� �׽�Ʈ���̽����� ���� ���� Fn�� �Է����� �־�����. 
(1 �� Fn �� 1021000, 1 �� N �� 100,000)

[���]
�Ǻ���ġ �� Fn�� �־����� �� n�� ����Ѵ�. ���� ������ ��찡 ���� �� �ִ� ��쿡�� ���� ū �ε����� ����϶�. �Ǻ���ġ ���� �ƴ� ���� ������ ���� ����.

[Ǯ��]
BigInteger�� 100000������ �Ǻ���ġ�� ���� ��, �ش� �Է��� Ž���Ѵ�.(ArrayList.find())
 + ������ �ǵ��� ū ���� ������ ��?

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