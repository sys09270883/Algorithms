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
	static int N, K;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[100001];
		BFS();
		
		bw.write(String.valueOf(map[K]));
		bw.close();
		br.close();
	}
	
	private static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(N);
		
		int[] func = new int[3];
		int tmp = Integer.MIN_VALUE;
		
		while(!queue.isEmpty() && tmp != K) {
			tmp = queue.poll();
			
			func[0] = tmp + 1;
			func[1] = tmp - 1;
			func[2] = tmp * 2;

			for (int i = 0; i < 3; i++) {
				if(func[i] < 0 || func[i] > 100000)
					continue;
				
				if(map[func[i]] == 0) {
					queue.add(func[i]);
					map[func[i]] = map[tmp] + 1;
				}
			}
		}
	}
}

