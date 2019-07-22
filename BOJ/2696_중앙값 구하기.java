import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int M, cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		while(T-- > 0){
			M = Integer.parseInt(br.readLine());
			StringTokenizer[] st = new StringTokenizer[M/10+1];
			for (int i = 0; i < M/10+1; i++) {
				st[i] = new StringTokenizer(br.readLine(), " ");
			}

			System.out.println((M+1)/2);

			for (int i = 0; i < M; i++) {
				list.add(Integer.parseInt(st[i/10].nextToken()));
				Collections.sort(list);
				
				if(i%2 == 0)	
					System.out.print(list.get((i+1)/2)+" ");
				
				cnt++;
				if(cnt == 19) {
					System.out.println();
				}
			}
			cnt = 0;
			System.out.println();
			list.removeAll(list);
		}

		br.close();
	}
}