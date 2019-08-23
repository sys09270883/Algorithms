/*
https://www.acmicpc.net/problem/12018
[문제]
연세대학교 수강신청이 얼마 전부터 바뀌어, 마일리지 제도로 바뀌었다. 
이 제도는 각각의 학생들에게 마일리지를 주어 듣고 싶은 과목에 마일리지를 과목당 1~36을 분배한다. 
그리고 모두 분배가 끝이 나면 과목에 대해서 마일리지를 많이 투자한 순으로 그 과목의 수강인원만큼 신청되는 방식이다.

성준이는 연세대학교 재학 중인 학생이다. 
성준이는 저번 수강신청에서 실패하여 휴학을 했기 때문에 이번 수강신청만은 필사적으로 성공하려고 한다. 
그래서 성준이는 학교 홈페이지를 뚫어버렸다.

그 덕분에 다른 사람들이 신청한 마일리지를 볼 수 있게 되었다. 
성준이는 주어진 마일리지로 최대한 많은 과목을 신청하고 싶어 한다. (내가 마일리지를 넣고 이후에 과목을 신청하는 사람은 없다) 
마일리지는 한 과목에 1에서 36까지 넣을 수 있다.

[입력]
첫째 줄에는 과목 수 n (1 ≤ n ≤ 100)과 주어진 마일리지 m (1 ≤ m ≤ 100)이 주어진다. 각 과목마다 2줄의 입력이 주어지는데 첫째 줄에는 각 과목에 신청한 사람 수 Pi과 과목의 수강인원 Li이 주어지고 그 다음 줄에는 각 사람이 마일리지를 얼마나 넣었는지 주어진다. (1 ≤ Pi ≤100, 1 ≤ Li ≤ 100)

(단 마일리지가 같다면 성준이에게 우선순위가 주어진다고 하자.)

[출력]
첫째 줄에 주어진 마일리지로 최대로 들을 수 있는 과목 개수를 출력한다.

[풀이]
리스트에 마일리지를 넣고 내림차순으로 정렬한다.
  1. 신청한 사람 수가 과목의 수강인원보다 적다면 최소PQ에 1을 넣는다.
  2. 신청한 사람 수가 과목의 수강인원보다 같거나 크다면 최소PQ에 리스트의 l - 1번째 항을 넣는다. (동점일 경우 성준이가 신청하기 때문에)

PQ에서 값을 빼내면서 카운팅한다. m보다 클 경우 반복문을 탈출한다 .

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n, m, p, l, sum = 0, cnt = 0;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<Integer>(101);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < p; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(list, (o1, o2) -> o2 - o1);
			
			if (p < l)
				pq.add(1);
			
			else
				pq.add(list.get(l - 1));
			
			list.clear();
		}
		
		while (!pq.isEmpty()) {
			if ((sum += pq.poll()) > m)
				break;
			
			cnt++;
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}
