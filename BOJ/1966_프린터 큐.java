/*
https://www.acmicpc.net/problem/1966
[문제]
여러분도 알다시피 여러분의 프린터 기기는 여러분이 인쇄하고자 하는 문서를 인쇄 명령을 받은 ‘순서대로’, 즉 먼저 요청된 것을 먼저 인쇄한다. 여러 개의 문서가 쌓인다면 Queue 자료구조에 쌓여서 FIFO - First In First Out - 에 따라 인쇄가 되게 된다. 하지만 상근이는 새로운 프린터기 내부 소프트웨어를 개발하였는데, 이 프린터기는 다음과 같은 조건에 따라 인쇄를 하게 된다.

현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
예를 들어 Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.

여러분이 할 일은, 현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다. 예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.

[입력]
첫 줄에 test case의 수가 주어진다. 각 test case에 대해서 문서의 수 N(100이하)와 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue의 어떤 위치에 있는지를 알려주는 M(0이상 N미만)이 주어진다. 다음줄에 N개 문서의 중요도가 주어지는데, 중요도는 1 이상 9 이하이다. 중요도가 같은 문서가 여러 개 있을 수도 있다. 위의 예는 N=4, M=0(A문서가 궁금하다면), 중요도는 2 1 4 3이 된다.

[출력]
각 test case에 대해 문서가 몇 번째로 인쇄되는지 출력한다.

[풀이]
index와 priority를 저장하는 Document 클래스를 만든다.
큐에 Document들을 넣어주고, priority와 그 개수를 가지는 내림차순으로 정렬한 트리맵을 만든다.

트리맵의 key를 순회하면서 해당 key의 value만큼 반복한다. 
큐의 출력 Document의 priority가 트리맵의 key값과 같으면 loop--, 찾으려는 문서가 M인 경우 즉시 반복문을 탈출한다.


*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		Queue<Document> queue = new LinkedList<Document>();
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>((o1, o2) -> o2 - o1);
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int cnt = 1;
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Document[] doc = new Document[N];
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
				
				if(tm.get(priority) == null)
					tm.put(priority, 1);
				
				else
					tm.put(priority, tm.get(priority) + 1);
				
				queue.add(doc[i] = new Document(i, priority));
			}
			
			Iterator<Integer> it = tm.keySet().iterator();
			LOOP: while(it.hasNext()) {
				int tmp = it.next();
				int loop = tm.get(tmp);
				
				while(loop > 0) {
					Document tmpDoc = queue.poll();
					if(tmpDoc.priority == tmp) {
						loop--;
						
						if(tmpDoc.index == M)
							break LOOP;
						
						cnt++;
					}
					
					queue.add(tmpDoc);
				}
			}
			
			sb.append(cnt).append('\n');
			queue.clear();
			tm.clear();
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

}

class Document {
	int index;
	int priority;
	
	Document(int index, int priority) {
		this.index = index;
		this.priority = priority;
	}
}
