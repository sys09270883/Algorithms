import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int d;
		StringBuilder sb = new StringBuilder();
		for (int i = 1000; i < 10000; i++) {
			d = func(i, 10);
			if(d == func(i, 12) && d == func(i, 16))
				sb.append(i).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

	private static int func(int n, int base) {
		int ret = 0;
		
		while(n > 0) {
			ret += n % base;
			n /= base;
		}
		
		return ret;
	}
}
