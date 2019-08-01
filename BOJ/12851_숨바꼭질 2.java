/*
https://www.acmicpc.net/problem/12851
[문제]
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 그리고, 가장 빠른 시간으로 찾는 방법이 몇 가지 인지 구하는 프로그램을 작성하시오.

[입력]
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

[출력]
첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

둘째 줄에는 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력한다.

[풀이]
숨바꼭질 문제에서 방법의 수가 추가된 문제이다.
최소방법이 여러가지 있을 수 있으므로 중복에 대해서도 카운팅 해주어야 한다.
N과 K가 같은 경우에 대해서 예외처리 해주어야 한다.

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
