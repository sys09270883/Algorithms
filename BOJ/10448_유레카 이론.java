import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		int n = 1;
		ArrayList<Integer> triList = new ArrayList<Integer>(45);
		while(n*(n+1)/2 < 1000){
			triList.add(n*(n++ + 1)/2);
		}
		
		int len = triList.size();
		while(T-- > 0){
			boolean flag = false;
			int num = Integer.parseInt(br.readLine());
			Loop: for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					for (int k = 0; k < len; k++) {
						if(triList.get(i) + triList.get(j) + triList.get(k) == num){
							flag = true;
							break Loop;
						}
					}
				}
			}
			System.out.println(flag == true ? 1 : 0);
		}
		
		bw.close();
		br.close();
	}
}
