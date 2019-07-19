/*
https://www.acmicpc.net/problem/3985

[문제]
인기 티비 프로그램 "나는 요리사 인가?"의 새 시즌이 시작한다. 이번 시즌은 기네스북에 등재될 만한 음식을 만드는 것을 목표로 진행한다.

첫 번째 에피소드에 출연하는 요리사는 전설의 요리사 김상근이고, 길이 L미터의 롤 케이크를 만들 것이다.

상근은 몇 시간동안 집중해서 케이크를 만들었고, 이제 스튜디오의 방청객 N명에게 케이크를 나누어 주려고 한다.

상근이는 롤 케이크를 펼쳐서 1미터 단위로 잘라 놓았다. 가장 왼쪽 조각이 1번, 오른쪽 조각이 L번 조각이다. 방청객은 1번부터 N번까지 번호가 매겨져 있다. 각 방청객은 종이에 자신이 원하는 조각을 적어서 낸다. 이때, 두 숫자 P와 K를 적어서 내며, P번 조각부터 K번 조각을 원한다는 뜻이다.

프로그램의 진행자 고창영은 1번 방청객의 종이부터 순서대로 펼쳐서 해당하는 조각에 그 사람의 번호를 적을 것이다. 이때, 이미 번호가 적혀있는 조각은 번호를 적지 못하고 넘어간다. 이런 방식을 이용해서 방청객에게 조각을 주다보니, 자신이 원래 원했던 조각을 받지 못하는 경우가 생길 수 있다.

아래 그림은 이 문제의 예제를 나타낸 것이다.



가장 많은 케이크 조각을 받을 것으로 기대한 방청객의 번호와 실제로 가장 많은 케이크 조각을 받는 방청객의 번호를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 롤 케이크의 길이 L (1 ≤ L ≤ 1000)이 주어진다. 둘째 줄에는 방청객의 수 N (1 ≤ N ≤ 1000)이 주어진다. 다음 N개 줄에는 각 방청객 i가 종이에 적어낸 숫자 Pi와 Ki가 주어진다. (1 ≤ Pi ≤ Ki ≤ L, i = 1..N)

[출력]
첫째 줄에 가장 많은 조각을 받을 것으로 기대하고 있던 방청객의 번호를 출력한다.

둘째 줄에 실제로 가장 많은 조각을 받은 방청객의 번호를 출력한다.

가장 많은 조각을 받도록 예상되는 방청객이 여러 명인 경우에는 번호가 작은 사람을 출력한다.

[풀이]
먼저 뽑는 방청객에 우선권을 주기 위하여 케이크에 번호를 적는 방향을 역순(마지막 방청객에서 처음 방청객 순)으로 했다. 이렇게 하니 전체 데이터에 대해서 반복을 2번하기 때문에 좋지 않은 알고리즘인 것 같다. 
-> 한 번의 반복문으로 처리할 수 있도록 수정하기
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int L = Integer.parseInt(br.readLine());

		int[] cake = new int[L];
		Arrays.fill(cake, -1);

		int n = Integer.parseInt(br.readLine());
		Audience[] audience = new Audience[n];
		
		int predictMax = Integer.MIN_VALUE;
		int predictMode = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			if(predictMax < b-a){
				predictMax = b-a;
				predictMode = i;
			}
			
			audience[i] = new Audience(a, b);
		}

		for (int i = 0; i < n; i++) {
			while(!audience[n-i-1].stack.isEmpty()){
				int tmp = audience[n-i-1].stack.pop();
				cake[tmp] = n-i-1;
			}
		}

		int mode = 0;
		int max = Integer.MIN_VALUE;
		int[] index = new int[n];
		
		for (int i = 0; i < L; i++) {
			if(cake[i] != -1)
				index[cake[i]]++;
		}
		
		for (int i = 0; i < n; i++) {
			if(max < index[i]){
				max = index[i];
				mode = i;
			}
		}
		
		System.out.println(predictMode + 1);
		System.out.println(mode + 1);
		
		br.close();
	}
}

class Audience {
	Stack<Integer> stack;
	Audience(int start, int end){
		stack = new Stack<>();

		for (int i = start; i <= end; i++) {
			stack.add(i);
		}
	}
}
