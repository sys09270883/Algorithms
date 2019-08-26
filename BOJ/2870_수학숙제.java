/*
https://www.acmicpc.net/problem/2870
[����]
����̴� ���нð��� �� ���� �ϴٰ� �����Բ� �ɷȴ�. �������� ����̿��� �̹� �ָ����� �ݼ��϶�� ��û�� ������ ���־���.

�������� ����̿��� �� ���̿��� ���ڿ� ���ĺ� �ҹ��ڷ� �Ǿ��ִ� ���ڰ� N���ִ�. 
����̴� ���⼭ ���ڸ� ��� ã�� ��, �� ���ڸ� �񳻸��������� �����ؾ��Ѵ�. ��
���� �տ� 0�� �ִ� ��쿡�� �����ϸ鼭 ������ �� �ִ�.

���ڸ� ���캸�ٰ� ���ڰ� ������ ��쿡��, ������ ���� ū ���ڸ� ã�ƾ� �Ѵ�. ��, ��� ������ �հ� �ڿ� ���ڰ� �ְų�, ���� ���� �Ǵ� ���̾�� �Ѵ�.

���� ���, 01a2b3456cde478���� ���ڸ� ã���� 1, 2, 3456, 478�̴�.

�������� �� ������ ������ �־����� ��, ������� ������ ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� ���� N�� �־�����. (1 �� N �� 100)

���� N���� �ٿ��� �� ���� ������ �־�����. �� ���� �ִ� 100�����̰�, �׻� ���ĺ� �ҹ��ڿ� ���ڷθ� �̷���� �ִ�.

[���]
���̿��� ã�� ������ ������ M�̶�� �ϸ�, ����� M�ٷ� �̷������ �Ѵ�. �� �ٿ��� ���̿��� ã�� ���ڸ� �ϳ��� ����ؾ� �Ѵ�. 
�̶�, �񳻸��������� ����ؾ� �Ѵ�. �񳻸������� ���������� �ݴ��� ����ε�, ���� ���� ���� ������ ũ�ų� ���� ��츦 ���Ѵ�.

[Ǯ��]
���ڰ� Long�� ǥ�� ������ �پ� ���� �� �ֱ⶧���� String���� ó���ؾ� �Ѵ�.
��Ʈ�� ������ ���۷����ͷ� ���� �켱���� ť�� ������ �ִ´�.
0, 00, 000�� ���� ���ڴ� 0���� �־�� �ϰ�, 0003�� 3, 03420�� 3420���� �־�� �ϴ� ���� �����Ѵ�.


*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		PriorityQueue<String> pq = new PriorityQueue<String>(new StringComparator());
		String str = null;
		char tmp;
		
		int N = Integer.parseInt(br.readLine());
		
		while (N-- > 0) {
			str = br.readLine();
			for (int i = 0; i < str.length(); i++) {
				tmp = str.charAt(i);
				if (Character.isDigit(tmp)) {
					sb.append(tmp);
					
					if (i == str.length() - 1) {
						if (sb.toString().equals(""))
							break;
						
						removeZero(sb);
						pq.add(sb.toString());
						sb.setLength(0);
					}
				}
				
				else {
					if (sb.toString().equals(""))
						continue;
					
					removeZero(sb);
					pq.add(sb.toString());
					sb.setLength(0);
				}
			}
		}
		
		while(!pq.isEmpty()) {
			res.append(pq.poll()).append('\n');
		}
		
		bw.write(res.toString().trim());
		bw.close();
		br.close();
	}
	
	private static void removeZero(StringBuilder sb) {
		boolean check = true;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) != '0')
				check = false;
		}
		
		if (check) {
			sb.setLength(0);
			sb.append('0');
			return;
		}
		
		int idx;
		for (idx = 0; idx < sb.length(); idx++) {
			if (sb.charAt(idx) != '0')
				break;
		}
		
		sb.delete(0, idx);
	}
}

class StringComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		if (o1.length() == o2.length()) {
			if (o1.equals(o2))
				return 0;
			
			for (int i = 0; i < o1.length(); i++) {
				if (o1.charAt(i) != o2.charAt(i))
					return o1.charAt(i) - o2.charAt(i);
			}
		}
		
		return o1.length() - o2.length();
	}
	
}