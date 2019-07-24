// 1.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int i = 0;
		int N = Integer.parseInt(br.readLine());
		while(N > 0){
			i++;
			if(String.valueOf(i).contains("666"))
				N--;
		}
		
		bw.write(String.valueOf(i));
		bw.close();
		br.close();
	}
}


// 2.
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int i = 0;
		int num = 0;
		while(i < N){
			int tmp = num;
			while(tmp > 0){
				if(tmp%1000 == 666){
					i++;
					break;
				}
				tmp /= 10;
			}
			num++;
		}
		
		bw.write(String.valueOf(num-1));
		bw.close();
		br.close();
	}
}