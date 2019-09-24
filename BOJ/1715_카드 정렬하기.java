/*
https://www.acmicpc.net/problem/1715
[����]
���ĵ� �� ������ ���� ī�尡 �ִٰ� ����. �� ������ ī���� ���� A, B�� �ϸ� ���� �� ������ ���ļ� �ϳ��� ����� ������ A+B ���� �񱳸� �ؾ� �Ѵ�. 
�̸��׸�, 20���� ���� ī�� ������ 30���� ���� ī�� ������ ��ġ���� 50���� �񱳰� �ʿ��ϴ�.

�ſ� ���� ���� ī�� ������ å�� ���� ���� �ִ�. �̵��� �� ������ ��� ���� ���ĳ����ٸ�, ���� ������ ���� �� Ƚ���� �ſ� �޶�����. 
���� ��� 10��, 20��, 40���� ������ �ִٸ� 10��� 20���� ��ģ ��, ��ģ 30�� ������ 40���� ��ģ�ٸ� (10+20)+(30+40) = 100���� �񱳰� �ʿ��ϴ�. 
�׷��� 10��� 40���� ��ģ ��, ��ģ 50�� ������ 20���� ��ģ�ٸ� (10+40)+(50+20) = 120 ���� �񱳰� �ʿ��ϹǷ� �� ȿ������ ����̴�.

N���� ���� ī�� ������ ������ ũ�Ⱑ �־��� ��, �ּ��� �� ���� �񱳰� �ʿ������� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� N�� �־�����. (1��N��100,000) �̾ N���� �ٿ� ���� ���� ī�� ������ ������ ũ�Ⱑ �־�����.

[���]
ù° �ٿ� �ּ� �� Ƚ���� ����Ѵ�. (21�� ����)

[Ǯ��]
���� ������ ���� ���� �ؾ��ϹǷ� �켱����ť�� ���������� �����ְ� ������� ���ϰ�, �ٽ� �켱����ť�� �־��ش�.
�켱����ť�� ũ�Ⱑ 1�� ������ �ݺ��Ѵ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()), res = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		for (int i = 0; i < N - 1; i++) {
			int tmp = pq.poll();
			tmp += pq.poll();
			res += tmp;
			pq.add(tmp);
		}
		
		bw.write(String.valueOf(res));
		bw.close();
		br.close();
	}
	
}
