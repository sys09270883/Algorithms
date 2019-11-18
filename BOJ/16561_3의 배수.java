/*
https://www.acmicpc.net/problem/16561
[����]
�����̴� 3�� ��� ���Ͼ��̴�. �״� ��� �ڿ����� 3���� 3�� ����� �ڿ����� �����ϴ� ���� ��̷� ������ �ִ�. 
���� �״� �ڽſ��� �־��� ���� 3���� 3�� ����� �и��ϴ� ����� ���� �� ������ �ñ�������. 
������ �����̴� ������ �б��̱� ������ �̷� ����� �ϱ⿡�� �ʹ� ����������. �׷��� ��ſ��� �� ����� ��Ź�ߴ�.

��, ������ 3�� ��� �ڿ��� n�� �־����� ��, �ش� ���� 3�� ����� �ڿ��� 3���� �и��ϴ� ����� ������ ����ض�. 
�� ������ ���� ������ �ٸ��� �ٸ� ������� �����Ѵ�. ���� ��� 12 = 3 + 6 + 3 �� 12 = 3 + 3 + 6 �� �ٸ� ����̴�.

[�Է�]
������ 3�� ��� �ڿ��� n�� �־�����. (3 �� n �� 3000)

[���]
�ڿ��� n�� �����ϴ� ����� ������ ����϶�.

[Ǯ��]
�ߺ������� ���� ��������, ���������� ǥ���Ǳ� ������ �����ϰ� ǥ�� �����ϴ�. 

*/
import java.io.*;
import java.util.*;

public class Main {

    static FastIO io = new FastIO();

    public static void main(String... args) throws IOException {
        int[] arr = new int[1001];
        int cnt = 2;
        arr[3] = 1;

        for (int i = 4; i < 1001; i++) {
            arr[i] = arr[i - 1] + cnt++;
        }

        io.write(arr[io.nextInt() / 3]);
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