/*
https://www.acmicpc.net/problem/1021
[문제]
지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다. 지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.

지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.

첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다. 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.

[출력]
첫째 줄에 문제의 정답을 출력한다.

[풀이]
값을 찾는 것이 아니라 처음 위치를 찾는 문제였다...
Deque로 시도하다가 ArrayList가 더 간단할 것 같아서 ArrayList로 접근했다.
ArrayList의 indexOf를 이용해서 간단하게 해결 가능한 문제였다.

*/
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

