/*
https://www.acmicpc.net/problem/1697
[문제]
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

[출력]
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

[풀이]
N이 K일 때 까지 +1, -1, *2 연산을 반복한다.
BFS로 맵을 갱신하면 최단거리이므로 map[K]를 갱신할 때까지 실행하고 갱신하면 반복문을 탈출한다.
+ while(!queue.isEmpty() && tmp != K) -> tmp != K가 꼭 있어야 함.


*/
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

