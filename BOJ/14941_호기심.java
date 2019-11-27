/*
https://www.acmicpc.net/problem/14941
[문제]
남규는 호기심이 많다. 호기심이 많은 남규는 a와 b 사이의 소수들의 합과 차를 이용한 특수한 함수 F 를 만들었다. 남규는 이 특수한 함수의 결과값을 알고 싶다.

함수 F(a,b)는 a와 b 사이의 소수들을 순서대로 다음과 같은 규칙에 따라 계산하고, 그 값을 반환한다.

3×A1 - A2 + 3×A3 - A4 + 3×A5 - A6 ..... An (a ≤ A1 < … < An ≤ b , Ai는 소수이다.)

질문이 F(3, 7) 이라면 3과 7 사이에는 3,5,7 총 3개의 소수가 있고, 규칙에 따라 계산한 결과는

3×3 - 5 + 3×7 = 25 이다.

[입력]
첫 줄에는 질문의 개수 n이 주어진다. 다음 줄 부터 차례대로 함수의 입력 a, b가 주어진다. 
(1 ≤ a ≤ b ≤ 10^5) 또한 남규는 호기심이 많기 때문에 매우 많은 질문을 한다. 따라서 질문의 수 n은 최대 10^5 개 이다.

[출력]
남규가 물어본 질문 ai, bi에 대한 답변 F(ai,bi)을 각 줄에 출력한다.

[풀이]
에라토스테네스의 체로 100000까지의 소수를 구하고 리스트에 넣어준다.
각 쿼리에 대해서 구간 인덱스를 binary search로 찾으며 규칙에 따라 처리한다.
a와 b가 같을 때는 소수이면 a * 3, 소수가 아닐 경우에는 0을 넣고 다음 쿼리로 넘어간다.

 + 미리 구간합을 구해 놓고 홀짝을 구분하여 처리하는 것이 더 좋은 것 같다.

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