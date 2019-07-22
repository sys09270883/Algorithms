import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Student[] s = new Student[N];
		StringBuilder sb = new StringBuilder();
		int rank = 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			s[i] = new Student(st.nextToken(), st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j)
					continue;
				
				if(s[i].weight < s[j].weight && s[i].height < s[j].height)
					rank++;
			}
			sb.append(rank).append(" ");
			rank = 1;
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}
}

class Student{
	int weight;
	int height;
	public Student(String w, String h){
		this.weight = Integer.parseInt(w);
		this.height = Integer.parseInt(h);
	}
}
