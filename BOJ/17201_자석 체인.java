/*
https://www.acmicpc.net/problem/17201
[문제]
블록체인에 대해 들은 초등학생 관빈이는 자석을 이어 붙여 자석 체인을 만든 다음, 이를 이용한 가상 화폐를 만들고 싶어졌다.

관빈이는 특이한 자석을 가지고 있는데 관빈이가 가지고 있는 자석의 (+)극에는 1이라는 숫자가 쓰여 있고, (-)극에는 2라는 숫자가 쓰여 있다. 
그리고 관빈이의 자석은 막대 모양으로, (+)극과 (-)극이 하나씩 있다.

보통, 자석은 같은 극끼리는 밀어내고 다른 극끼리는 서로 끌어당겨 붙는 성질이 있다. 
관빈이는 이 성질을 이용해 가지고 있는 자석들을 정성스럽게 모두 이어 붙여 연결된 자석 체인을 만든 뒤 자석 코인의 떡상을 꿈꾸며 잠이 들었다. 
하지만 그날 밤에, 자석 코인의 떡상을 우려한 관빈이의 아버지가 연결된 자석 중 하나의 방향을 뒤집어 자석 체인을 분리했을 수도 있다! 
자석 체인의 떡락을 두려워하는 관빈이를 위해 자석 체인이 모두 연결되어 있는지 아닌지 알려주자.



[입력]
첫 번째 줄에 관빈이가 가지고 있는 자석의 개수 N이 주어진다. (3 ≤ N ≤ 5)

두 번째 줄에 관빈이가 가지고 있는 자석의 현재 연결 상태를 나타내는 수열 a1a2a3 ... a2N (ai는 1 또는 2)가 한 줄에 주어진다. 
수열의 맨 왼쪽부터, 순서대로 2개의 인접한 숫자가 하나의 자석을 나타낸다. ( [a1a2][a3a4]...) 수열의 값 중 1은 (+)극, 2는 (-)극을 나타낸다.

단, 자석은 원래 상태(모두 연결된 상태)와 비교했을 때 최대 1개만 반대로 뒤집힐 수 있다.

[출력]
현재 주어진 자석의 상태를 보고 모두 연결되어있으면 "Yes", 모두 연결되어 있지 않은 상태라면 "No"를 한 줄에 출력한다. 
단, 출력할 경우엔 인용 부호("")를 생략해야 하며 대소문자를 맞춰야 한다.

[풀이]
전 값과 비교해서 같은 값이 연속하면 No, 그 외의 경우 Yes를 출력한다.

*/
import java.io.*;

public class Main {
	
	public static void main(String... args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Integer.parseInt(br.readLine());
		String str = br.readLine();
		int len = str.length(), i = 1;
		StringBuilder res = new StringBuilder();
		char before = str.charAt(0);
		
		for (i = 1; i < len; i++) {
			char cur = str.charAt(i);
			
			if (before == cur) {
				res.append("No");
				break;
			}
			
			before = cur;
		}
		
		if (i == len)
			res.append("Yes");
		
		bw.write(res.toString());
		bw.close();
		br.close();
	}
	
}
