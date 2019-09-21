/*
https://www.acmicpc.net/problem/10282
[문제]
최흉최악의 해커 yum3이 네트워크 시설의 한 컴퓨터를 해킹했다! 이제 서로에 의존하는 컴퓨터들은 점차 하나둘 전염되기 시작한다. 
어떤 컴퓨터 a가 다른 컴퓨터 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염되고 만다. 이때 b가 a를 의존하지 않는다면, a가 감염되더라도 b는 안전하다.

최흉최악의 해커 yum3이 해킹한 컴퓨터 번호와 각 의존성이 주어질 때, 
해킹당한 컴퓨터까지 포함하여 총 몇 대의 컴퓨터가 감염되며 그에 걸리는 시간이 얼마인지 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스의 개수는 최대 100개이다. 각 테스트 케이스는 다음과 같이 이루어져 있다.

첫째 줄에 컴퓨터 개수 n, 의존성 개수 d, 해킹당한 컴퓨터의 번호 c가 주어진다(1 ≤ n ≤ 10,000, 1 ≤ d ≤ 100,000, 1 ≤ c ≤ n).
이어서 d개의 줄에 각 의존성을 나타내는 정수 a, b, s가 주어진다(1 ≤ a, b ≤ n, a ≠ b, 0 ≤ s ≤ 1,000). 
이는 컴퓨터 a가 컴퓨터 b를 의존하며, 컴퓨터 b가 감염되면 s초 후 컴퓨터 a도 감염됨을 뜻한다.
각 테스트 케이스에서 같은 의존성 (a, b)가 두 번 이상 존재하지 않는다.

[출력]
각 테스트 케이스마다 한 줄에 걸쳐 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간을 공백으로 구분지어 출력한다.

[풀이]
다익스트라로 시간들을 갱신하면서 초기값(최대값)이 아니면 카운팅을 하고(감염된 컴퓨터 수) 초기값이 아닌 값 중 최댓값이 걸리는 시간이다.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<ArrayList<Node>> adj;
	static int[] times;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		StringBuilder res = new StringBuilder();
		
		while (T-- > 0){
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int cnt = 0, max = 0;
			adj = new ArrayList<ArrayList<Node>>(n + 1);
			for (int i = 0; i < n + 1; i++) {
				adj.add(new ArrayList<Node>());
			}
			times = new int[n + 1];
			Arrays.fill(times, Integer.MAX_VALUE);
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adj.get(b).add(new Node(a, s));
			}
			
			dijkstra(c);
			
			for (int i = 1; i <= n; i++) {
				if (times[i] != Integer.MAX_VALUE) {
					cnt++;
					max = max < times[i] ? times[i] : max;
				}
			}
			
			res.append(cnt).append(" ").append(max).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(new Node(start, times[start] = 0));
		
		while (!pq.isEmpty()) {
			Node tmp = pq.poll();
			int cur = tmp.num;
			int time = tmp.time;
			
			if (time > times[cur])
				continue;
			
			for (Node n : adj.get(cur)) {
				int next = n.num;
				int ntime = n.time;
				
				if (times[next] > ntime + times[cur]) {
					times[next] = ntime + times[cur];
					pq.add(new Node(next, times[next]));
				}
			}
		}
	}
	
}

class Node implements Comparable<Node>{
	int num, time;

	public Node(int num, int time) {
		super();
		this.num = num;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}
	
}
