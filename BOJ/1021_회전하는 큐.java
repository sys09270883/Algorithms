import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;

		st = new StringTokenizer(br.readLine(), " ");

		ArrayList<Integer> list = new ArrayList<Integer>(N);
		for (int i = 0; i < N; i++) {
			list.add(i+1);
		}

		for (int i = 0; i < M; i++) {
			boolean flag = false;
			int next = Integer.parseInt(st.nextToken());
			while(!flag) {
				if(list.get(0) == next) {
					list.remove(0);
					flag = true;
				}

				else {
					if(list.indexOf(next) <= list.size() / 2)
						list.add(list.remove(0));
					else 
						list.add(0, list.remove(list.size() - 1));

					cnt++;
				}
			}
		}

		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}

