import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] map;
	static int N, K, cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[100001];
		BFS();
		if(cnt == 0)
			cnt++;
		sb.append((map[K]-1)).append("\n").append(cnt);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		map[N] = 1;
		int[] func = new int[3];
		
		while(!queue.isEmpty()) {
			int tmp = queue.poll();
			
			func[0] = tmp + 1;
			func[1] = tmp - 1;
			func[2] = tmp * 2;
			
			for (int i = 0; i < 3; i++) {
				if(func[i] < 0 || func[i] > 100000)
					continue;
				
				if(map[func[i]] == 0) {
					queue.add(func[i]);
					map[func[i]] = map[tmp] + 1;
					if(func[i] == K)
						cnt++;
				}
				
				else if(map[func[i]] != 0 && map[func[i]] == map[tmp] + 1) {
					queue.add(func[i]);
					if(func[i] == K)
						cnt++;
				}
			}
		}
	}
}
