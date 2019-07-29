import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < N; i++)  {
			String tmp = br.readLine();
			if(hm.get(tmp) == null)
				hm.put(tmp, 1);
			else
				hm.put(tmp, hm.get(tmp)+1);
		}

		for (int i = 0; i < N-1; i++) {
			String tmp = br.readLine();
			if(hm.containsKey(tmp)) {
				if(hm.get(tmp) == 1)
					hm.remove(tmp);
				else
					hm.put(tmp, hm.get(tmp) - 1);
			}
		}

		bw.write(hm.keySet().iterator().next());
		bw.close();
		br.close();
	}
}
