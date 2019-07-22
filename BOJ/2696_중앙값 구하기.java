/*
https://www.acmicpc.net/problem/2696
[문제]
어떤 수열을 읽고, 홀수번째 수를 읽을 때 마다, 지금까지 입력받은 값의 중앙값을 출력하는 프로그램을 작성하시오.

예를 들어, 수열이 1,5,4,3,2 이면, 홀수번째 수는 1번째 수, 3번째 수, 5번째 수이고, 1번째 수를 읽었을 때 중앙값은 1, 3번째 수를 읽었을 때는 4, 5번째 수를 읽었을 때는 3이다.

[입력]
첫째 줄에 테스트 케이스의 개수 T(1<=T<=1,000)가 주어진다. 각 테스트 케이스의 첫째 줄에는 수열의 크기 M(1<=M<=9999, M=홀수)이 주어지고, 그 다음 줄부터 이 수열의 원소가 차례대로 주어진다. 원소는 한 줄에 10개씩 나누어져있고, 32비트 부호있는 정수이다. (대부분의 언어에서 int)

[출력]
각 테스트 케이스에 대해 첫째 줄에 출력하는 중앙값의 개수를 출력하고, 둘째 줄에는 홀수 번째 수를 읽을 때 마다 구한 중앙값을 차례대로 공백으로 구분하여 출력한다. 이때, 한 줄에 10개씩 출력해야 한다.

[풀이]
1)  리스트에 값을 넣으면서 홀수 번째에서 리스트를 정렬하고 중앙값((i+1)/2)을 출력하는 방법
		-> 이 방법도 가능하긴 하지만 최대힙과 최소힙을 이용해서 하는 것이 더 좋은 알고리즘이다.
		* 26488KB	900ms
		
2)	maxPQ와 minPQ를 이용해서 값을 분류하면서 넣고 maxPQ의 top을 출력하는 방법(최대힙과 최소힙)
		* 22644KB	380ms
		
*/

//	1) 리스트에 받아서 정렬
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int M, cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		while(T-- > 0){
			M = Integer.parseInt(br.readLine());
			StringTokenizer[] st = new StringTokenizer[M/10+1];
			for (int i = 0; i < M/10+1; i++) {
				st[i] = new StringTokenizer(br.readLine(), " ");
			}

			System.out.println((M+1)/2);

			for (int i = 0; i < M; i++) {
				list.add(Integer.parseInt(st[i/10].nextToken()));
				
				if(i%2 == 0){
					Collections.sort(list);
					System.out.print(list.get((i+1)/2)+" ");
				}
				
				cnt++;
				if(cnt == 19) {
					System.out.println();
				}
			}
			cnt = 0;
			System.out.println();
			list.removeAll(list);
		}

		br.close();
	}
}

// 2) 최대힙과 최소힙
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int M, idx = 0;
		
		PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();

		while(T-- > 0){
			M = Integer.parseInt(br.readLine());
			StringTokenizer st = null;

			System.out.println((M+1)/2);

			for (int i = 0; i < M; i++) {
				if(i%10 == 0) 
					st = new StringTokenizer(br.readLine());
				
				int n = Integer.parseInt(st.nextToken());
				
				if(maxPQ.size() > minPQ.size())
					minPQ.add(n);
				else 
					maxPQ.add(n);
				
				if(!minPQ.isEmpty() && maxPQ.peek() > minPQ.peek()){
					int tmp1 = maxPQ.poll();
					int tmp2 = minPQ.poll();
					maxPQ.add(tmp2);
					minPQ.add(tmp1);
				}
				
				if(i%2 == 0)	
					System.out.print(maxPQ.peek()+" ");
				
				if(++idx == 19) {
					System.out.println();
				}
			}
			System.out.println();
			idx = 0;
			maxPQ.removeAll(maxPQ);
			minPQ.removeAll(minPQ);
		}

		br.close();
	}
}
