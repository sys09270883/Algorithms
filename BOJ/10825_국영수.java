/*
[문제]
도현이네 반 학생 N명의 이름과 국어, 영어, 수학 점수가 주어진다. 이때, 다음과 같은 조건으로 학생의 성적을 정렬하는 프로그램을 작성하시오.

1. 국어 점수가 감소하는 순서로
2. 국어 점수가 같으면 영어 점수가 증가하는 순서로
3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
[입력]
첫째 줄에 도현이네 반의 학생의 수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 한 줄에 하나씩 각 학생의 이름, 국어, 영어, 수학 점수가 공백으로 구분해 주어진다. 점수는 1보다 크거나 같고, 100보다 작거나 같은 자연수이다. 이름은 알파벳 대소문자로 이루어진 문자열이고, 길이는 10자리를 넘지 않는다.

[출력]
문제에 나와있는 정렬 기준으로 정렬한 후 첫째 줄부터 N개의 줄에 걸쳐 각 학생의 이름을 출력한다.

[풀이]
StudentInfo 클래스에 데이터를 저장하고 우선순위 큐로 정렬했다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<StudentInfo> pq = new PriorityQueue<>(N, new studentInfoComparator());
		
		while(N-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			StudentInfo si = new StudentInfo(st.nextToken(), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			pq.add(si);
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!pq.isEmpty()){
			StudentInfo tmp = pq.poll();
			sb.append(tmp.name).append('\n');
		}
		
		System.out.println(sb.toString().trim());
		
	}
}

class StudentInfo{
	String name;
	int Kscore;
	int Escore;
	int Mscore;
	
	public StudentInfo(String name, int Kscore, int Escore, int Mscore){
		this.name = name;
		this.Kscore = Kscore;
		this.Escore = Escore;
		this.Mscore = Mscore;
	}
	
	public boolean equalKEM(StudentInfo si){
		if(si.Kscore == this.Kscore && si.Mscore == this.Mscore && si.Escore == this.Escore)
			return true;
		
		return false;
	}
	
	public boolean equalKE(StudentInfo si){
		if(si.Kscore == this.Kscore && si.Escore == this.Escore)
			return true;
		
		return false;
	}
	
	public boolean equalK(StudentInfo si){
		if(si.Kscore == this.Kscore)
			return true;
		
		return false;
		
	}
}

class studentInfoComparator implements Comparator<StudentInfo>{

	@Override
	public int compare(StudentInfo o1, StudentInfo o2) {
		// TODO Auto-generated method stub
		if(o1.equalKEM(o2))
			return o1.name.compareTo(o2.name) > 0 ? 1 : -1;
		
		if(o1.equalKE(o2))
			return o1.Mscore < o2.Mscore ? 1 : -1;
		
		if(o1.equalK(o2))
			return o1.Escore > o2.Escore ? 1 : -1;
		
		return o1.Kscore < o2.Kscore ? 1 : -1;
	}
	
}
