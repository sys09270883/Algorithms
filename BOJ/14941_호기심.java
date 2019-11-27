/*
https://www.acmicpc.net/problem/14941
[����]
���Դ� ȣ����� ����. ȣ����� ���� ���Դ� a�� b ������ �Ҽ����� �հ� ���� �̿��� Ư���� �Լ� F �� �������. ���Դ� �� Ư���� �Լ��� ������� �˰� �ʹ�.

�Լ� F(a,b)�� a�� b ������ �Ҽ����� ������� ������ ���� ��Ģ�� ���� ����ϰ�, �� ���� ��ȯ�Ѵ�.

3��A1 - A2 + 3��A3 - A4 + 3��A5 - A6 ..... An (a �� A1 < �� < An �� b , Ai�� �Ҽ��̴�.)

������ F(3, 7) �̶�� 3�� 7 ���̿��� 3,5,7 �� 3���� �Ҽ��� �ְ�, ��Ģ�� ���� ����� �����

3��3 - 5 + 3��7 = 25 �̴�.

[�Է�]
ù �ٿ��� ������ ���� n�� �־�����. ���� �� ���� ���ʴ�� �Լ��� �Է� a, b�� �־�����. 
(1 �� a �� b �� 10^5) ���� ���Դ� ȣ����� ���� ������ �ſ� ���� ������ �Ѵ�. ���� ������ �� n�� �ִ� 10^5 �� �̴�.

[���]
���԰� ��� ���� ai, bi�� ���� �亯 F(ai,bi)�� �� �ٿ� ����Ѵ�.

[Ǯ��]
�����佺�׳׽��� ü�� 100000������ �Ҽ��� ���ϰ� ����Ʈ�� �־��ش�.
�� ������ ���ؼ� ���� �ε����� binary search�� ã���� ��Ģ�� ���� ó���Ѵ�.
a�� b�� ���� ���� �Ҽ��̸� a * 3, �Ҽ��� �ƴ� ��쿡�� 0�� �ְ� ���� ������ �Ѿ��.

 + �̸� �������� ���� ���� Ȧ¦�� �����Ͽ� ó���ϴ� ���� �� ���� �� ����.

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();
    final static int MAX = 100001;
    
    public static void main(String... args) throws IOException {
    	int n = io.nextInt(), a, b;
    	List<Integer> primeList = new ArrayList<Integer>();
    	boolean[] isPrime = new boolean[100001];
    	Arrays.fill(isPrime, true);
    	isPrime[1] = false;
    	StringBuilder res = new StringBuilder();
    	
    	for (int i = 2; i * i < MAX; i++) {
			if (!isPrime[i])
				continue;
    		
			for (int j = i << 1; j < MAX; j += i) {
				isPrime[j] = false;
			}
		}
    	
    	for (int i = 2; i < MAX; i++) {
			if (isPrime[i])
				primeList.add(i);
		}
    	
    	for (int i = 0; i < n; i++) {
    		long ans = 0;
    		boolean flag = true;
			a = io.nextInt();
			b = io.nextInt();
			int idxA = Collections.binarySearch(primeList, a);
			int idxB = Collections.binarySearch(primeList, b);
			idxA = idxA < 0 ? -idxA - 1 : idxA;
			idxB = idxB < 0 ? -idxB - 2 : idxB;
			
			if (a == b) {
				res.append(isPrime[a] ? primeList.get(idxA) * 3 : 0); 
				res.append('\n');
				continue;
			}
			
			for (int j = idxA; j <= idxB; j++) {
				if (flag) {
					ans += primeList.get(j) * 3;
					flag = false;
				}
				
				else {
					ans -= primeList.get(j);
					flag = true;
				}
			}
			
			res.append(ans).append('\n');
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