/*
https://www.acmicpc.net/problem/3985

����
�α� Ƽ�� ���α׷� "���� �丮�� �ΰ�?"�� �� ������ �����Ѵ�. �̹� ������ ��׽��Ͽ� ����� ���� ������ ����� ���� ��ǥ�� �����Ѵ�.

ù ��° ���Ǽҵ忡 �⿬�ϴ� �丮��� ������ �丮�� �����̰�, ���� L������ �� ����ũ�� ���� ���̴�.

����� �� �ð����� �����ؼ� ����ũ�� �������, ���� ��Ʃ����� ��û�� N���� ����ũ�� ������ �ַ��� �Ѵ�.

����̴� �� ����ũ�� ���ļ� 1���� ������ �߶� ���Ҵ�. ���� ���� ������ 1��, ������ ������ L�� �����̴�. ��û���� 1������ N������ ��ȣ�� �Ű��� �ִ�. �� ��û���� ���̿� �ڽ��� ���ϴ� ������ ��� ����. �̶�, �� ���� P�� K�� ��� ����, P�� �������� K�� ������ ���Ѵٴ� ���̴�.

���α׷��� ������ ��â���� 1�� ��û���� ���̺��� ������� ���ļ� �ش��ϴ� ������ �� ����� ��ȣ�� ���� ���̴�. �̶�, �̹� ��ȣ�� �����ִ� ������ ��ȣ�� ���� ���ϰ� �Ѿ��. �̷� ����� �̿��ؼ� ��û������ ������ �ִٺ���, �ڽ��� ���� ���ߴ� ������ ���� ���ϴ� ��찡 ���� �� �ִ�.

�Ʒ� �׸��� �� ������ ������ ��Ÿ�� ���̴�.



���� ���� ����ũ ������ ���� ������ ����� ��û���� ��ȣ�� ������ ���� ���� ����ũ ������ �޴� ��û���� ��ȣ�� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �� ����ũ�� ���� L (1 �� L �� 1000)�� �־�����. ��° �ٿ��� ��û���� �� N (1 �� N �� 1000)�� �־�����. ���� N�� �ٿ��� �� ��û�� i�� ���̿� ��� ���� Pi�� Ki�� �־�����. (1 �� Pi �� Ki �� L, i = 1..N)

[���]
ù° �ٿ� ���� ���� ������ ���� ������ ����ϰ� �ִ� ��û���� ��ȣ�� ����Ѵ�.

��° �ٿ� ������ ���� ���� ������ ���� ��û���� ��ȣ�� ����Ѵ�.

���� ���� ������ �޵��� ����Ǵ� ��û���� ���� ���� ��쿡�� ��ȣ�� ���� ����� ����Ѵ�.

[Ǯ��]
���� �̴� ��û���� �켱���� �ֱ� ���Ͽ� ����ũ�� ��ȣ�� ���� ������ ����(������ ��û������ ó�� ��û�� ��)���� �ߴ�. �̷��� �ϴ� ��ü �����Ϳ� ���ؼ� �ݺ��� 2���ϱ� ������ ���� ���� �˰����� �� ����. 
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