import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	final static int MAX = 1000001;
	static int N;
	static long[] arr;
	static Queue<Long> queue;
	static BufferedWriter bw;
	static BufferedReader br;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		queue = new LinkedList<>();
		arr = new long[MAX];
		flag = false;
		
		for (int i = 0; i < 10; i++) {
			arr[i] = i;
			queue.add(Long.valueOf(i));
		}
		
		func();
		
		bw.write(flag == false ? String.valueOf(arr[N]) : "-1");
		bw.close();
		br.close();
	}
	
	private static void func() throws IOException{
		int idx = 10;
		
		while(idx <= N) {
			if(queue.isEmpty()) 
				return;
			
			long tmp = queue.poll();
			int lastNum;
			try {
				lastNum = Math.toIntExact(tmp) % 10;
			} catch (ArithmeticException e) {
				// TODO: handle exception
				flag = true;
				return;
			}
			
			for (int i = 0; i < lastNum; i++) {
				queue.add(tmp * 10 + i);
				arr[idx++] = tmp * 10 + i;
			}
		}
	}
}
