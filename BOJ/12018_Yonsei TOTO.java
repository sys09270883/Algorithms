/*
https://www.acmicpc.net/problem/12018
[����]
�������б� ������û�� �� ������ �ٲ��, ���ϸ��� ������ �ٲ����. 
�� ������ ������ �л��鿡�� ���ϸ����� �־� ��� ���� ���� ���ϸ����� ����� 1~36�� �й��Ѵ�. 
�׸��� ��� �й谡 ���� ���� ���� ���ؼ� ���ϸ����� ���� ������ ������ �� ������ �����ο���ŭ ��û�Ǵ� ����̴�.

�����̴� �������б� ���� ���� �л��̴�. 
�����̴� ���� ������û���� �����Ͽ� ������ �߱� ������ �̹� ������û���� �ʻ������� �����Ϸ��� �Ѵ�. 
�׷��� �����̴� �б� Ȩ�������� �վ���ȴ�.

�� ���п� �ٸ� ������� ��û�� ���ϸ����� �� �� �ְ� �Ǿ���. 
�����̴� �־��� ���ϸ����� �ִ��� ���� ������ ��û�ϰ� �;� �Ѵ�. (���� ���ϸ����� �ְ� ���Ŀ� ������ ��û�ϴ� ����� ����) 
���ϸ����� �� ���� 1���� 36���� ���� �� �ִ�.

[�Է�]
ù° �ٿ��� ���� �� n (1 �� n �� 100)�� �־��� ���ϸ��� m (1 �� m �� 100)�� �־�����. �� ���񸶴� 2���� �Է��� �־����µ� ù° �ٿ��� �� ���� ��û�� ��� �� Pi�� ������ �����ο� Li�� �־����� �� ���� �ٿ��� �� ����� ���ϸ����� �󸶳� �־����� �־�����. (1 �� Pi ��100, 1 �� Li �� 100)

(�� ���ϸ����� ���ٸ� �����̿��� �켱������ �־����ٰ� ����.)

[���]
ù° �ٿ� �־��� ���ϸ����� �ִ�� ���� �� �ִ� ���� ������ ����Ѵ�.

[Ǯ��]
����Ʈ�� ���ϸ����� �ְ� ������������ �����Ѵ�.
  1. ��û�� ��� ���� ������ �����ο����� ���ٸ� �ּ�PQ�� 1�� �ִ´�.
  2. ��û�� ��� ���� ������ �����ο����� ���ų� ũ�ٸ� �ּ�PQ�� ����Ʈ�� l - 1��° ���� �ִ´�. (������ ��� �����̰� ��û�ϱ� ������)

PQ���� ���� �����鼭 ī�����Ѵ�. m���� Ŭ ��� �ݺ����� Ż���Ѵ� .

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n, m, p, l, sum = 0, cnt = 0;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> list = new ArrayList<Integer>(101);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < p; j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(list, (o1, o2) -> o2 - o1);
			
			if (p < l)
				pq.add(1);
			
			else
				pq.add(list.get(l - 1));
			
			list.clear();
		}
		
		while (!pq.isEmpty()) {
			if ((sum += pq.poll()) > m)
				break;
			
			cnt++;
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}
