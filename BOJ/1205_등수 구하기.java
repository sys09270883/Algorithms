import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int newScore = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		if(N == 0){
			System.out.println(1);
			return;
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(N);
		
		st = new StringTokenizer(br.readLine(), " ");
		
		while(st.hasMoreTokens())
			list.add(Integer.parseInt(st.nextToken()));
		
		Collections.sort(list, (o1, o2) -> o2 - o1);
		
		int cntBig = 0;
		int cntEqual = 0;
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i) > newScore)
				cntBig++;
			else if(list.get(i) == newScore)
				cntEqual++;
			else
				break;
		}
		
		System.out.println(cntBig + cntEqual < P ? cntBig + 1 : -1);
		
		br.close();
	}
}

