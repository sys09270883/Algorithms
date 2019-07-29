/*
https://www.acmicpc.net/problem/10546
[문제]
마라토너라면 국적과 나이를 불문하고 누구나 참가하고 싶어하는 백준 마라톤 대회가 열린다. 42.195km를 달리는 이 마라톤은 모두가 참가하고 싶어했던 만큼 매년 모두가 완주해왔다. 단, 한 명만 빼고! 

모두가 참가하고 싶어서 안달인데 이런 백준 마라톤 대회에 참가해 놓고 완주하지 못한 배부른 참가자 한 명은 누굴까?

[입력]
첫째 줄에는 참가자 수 N이 주어진다. (1 ≤ N ≤ 105)

N개의 줄에는 참가자의 이름이 주어진다.

추가적으로 주어지는 N-1개의 줄에는 완주한 참가자의 이름이 쓰여져 있다. 

참가자들의 이름은 길이가 1보다 크거나 같고, 20보다 작거나 같은 문자열이고, 알파벳 소문자로만 이루어져 있다.

참가자들 중엔 동명이인이 있을 수도 있다. 

[출력]
마라톤을 완주하지 못한 참가자의 이름을 출력한다.

[풀이]
참가자의 이름으로 검색을 하고 삭제하는 연산이 많으므로 맵을 쓴다.
중복이 있을 경우 value값을 +1 해주고 제거할 때도 value값이 1보다 크면 -1을 한다.
value가 1일 경우에는 지운다.
이터레이터로 맵에 하나남은 키값을 출력한다.
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (int i = 0; i < N; i++)  {
			String tmp = br.readLine();
			if(hm.get(tmp) == null)
				hm.put(tmp, 1);
			else
				hm.put(tmp, hm.get(tmp)+1);
		}

		for (int i = 0; i < N-1; i++) {
			String tmp = br.readLine();
			if(hm.containsKey(tmp)) {
				if(hm.get(tmp) == 1)
					hm.remove(tmp);
				else
					hm.put(tmp, hm.get(tmp) - 1);
			}
		}

		bw.write(hm.keySet().iterator().next());
		bw.close();
		br.close();
	}
}
