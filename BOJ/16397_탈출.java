/*
https://www.acmicpc.net/problem/16397
[����]
ȫ���̴� ȫ�ʹ��б� ���α׷��� ������ȸ�� �������̴�. ȫ���̴� ������ ������ ����� ���� ������� �������� ����� �Բ� ������ �Ҿ���.

ȫ���̴� ���� �濡�� ���� ����. �ֺ��� ���캸�� ���鿡�� LED�� �� �ټ� �ڸ� ������ N��, �� ���� T, G��� ���ĺ��� �Բ� �� �ٸ� ���� �� ���� ���� �־���,
 �� �տ��� ��ư A, B �� ���� �־���.



��ư�� �̸����� �������� �ȶ��� ȫ���̴� ��� �ؾ� ���� Ż���� �� ������ �ݹ� ��ġë��.

��ư�� ���� ���� ȫ���̰� �˾Ƴ� ���� ������ ����.

��ư A�� ������ N�� 1 �����Ѵ�.
��ư B�� ������ N�� 2�� ������ ��, 0�� �ƴ� ���� ���� �ڸ����� ���ڰ� 1 �پ���. ���� ��� 123��146����, 5��0����, 3��5�� ���Ѵ�.
 ��, N�� 0�̸� ��ư B�� ������ ���� ������ �ʴ´�.
LED�� �ټ� �ڸ������ۿ� ���� ������ N�� 99,999�� �Ѿ�� ���� Ż�⿡ �����ϰ� �ȴ�.
��ư B�� ���� N�� 2�� ���� ���� ���� 99,999�� �Ѿ�ٸ�, ���� �ڸ����� ���� 1 �������� 99,999�� ���� �ʴ´ٰ� �ص� Ż�⿡ �����ϰ� �ȴ�.
���� ȫ���̴� �ִ� Tȸ ��ư�� ���� �� �ְ�, �� Ƚ�� �ȿ� LED�� ǥ���� N�� G�� ���� ������ Ż���� �� �ִٴ� ����� �˾Ƴ´�.

�ȶ��� ȫ���̴� �̿��߿� �������� �ߵ��� ��ư ������ Ƚ���� �ּҷ� �Ͽ� ���� Ż���ϱ�� �ߴ�.

ȫ������ �� Ż���� ����ϸ�, Ż�⿡ �ʿ��� �ּ��� ��ư Ƚ���� ������.

[�Է�]
ù ��° �ٿ� N (0 �� N �� 99,999), T (1 �� T �� 99,999), G (0 �� G �� 99,999)�� ���� �ϳ��� ���̿� �ΰ� �־�����.

���� N�� LED�� ǥ���� ��, T�� ��ư�� ���� �� �ִ� �ִ� Ƚ��, G�� Ż���� ���� �Ȱ��� ������ �ϴ� ���� ���Ѵ�.

[���]
ù ��° �ٿ� Ż�⿡ �ʿ��� �ּ��� ��ư Ƚ���� ����Ѵ�.

���� Ż���� �� ���ٸ� ��ANG���� ����ǥ ���� ����Ѵ�.

[Ǯ��]
�������� BFS�����ε� T�� �־����� �湮 üũ�� ���� �� �ʿ� ���ٰ� �����ؼ� �����ߴµ� �ð��ʰ�����.
 + ��ư B�� ���� N�� 2�� ���� ���� ���� 99,999�� �Ѿ�ٸ�, ���� �ڸ����� ���� 1 �������� 99,999�� ���� �ʴ´ٰ� �ص� Ż�⿡ �����ϰ� �ȴ�.
 + ���� Ȯ���� ������!
 + if(B < MAX && !visited[B]) -> if(tmp.num < 50000 && !visited[B])�� �����ؾ� �ߴ�.

*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	final static int MAX = 100000;
	static int N, T, G;
	static StringBuilder sb;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		visited = new boolean[MAX];
		sb = new StringBuilder("ANG");

		BFS();

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void BFS() {
		int A, B;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(N, 0));
		visited[N] = true;

		while(!queue.isEmpty()) {
			Node tmp = queue.poll();

			if(tmp.btnCnt > T)
				break;

			if(tmp.num == G) {
				sb.setLength(0);
				sb.append(tmp.btnCnt);
				return;
			}

			A = tmp.num + 1;
			if(A < MAX && !visited[A]) {
				visited[A] = true;
				queue.add(new Node(A, tmp.btnCnt + 1));
			}

			B = BFunc(tmp.num);
			if(tmp.num < 50000 && !visited[B]) {
				visited[B] = true;
				queue.add(new Node(B, tmp.btnCnt + 1));
			}
		}
	}

	private static int BFunc(int n) {
		int tmp = n * 2, i = 0;
		while(tmp > 0) {
			tmp /= 10;
			i++;
		}

		return n * 2 - (int)Math.pow(10, i - 1);
	}
}

class Node {
	int num, btnCnt;

	public Node(int num, int btnCnt) {
		// TODO Auto-generated constructor stub
		this.num = num;
		this.btnCnt = btnCnt;
	}
}