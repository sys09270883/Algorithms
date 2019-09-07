/*
https://www.acmicpc.net/problem/2947
[����]
�����̴� ���� ������ 5�� ������ �ִ�. ���� �������� 1���� 5���� ���� �� �ϳ��� ������ �ִ�. ��, ��� ���ڴ� �ټ� ���� �� �ϳ����� ���� �ִ�.

�����̴� ���� ������ ������ ���� ������ ���ļ� 1, 2, 3, 4, 5 ������ ������� �Ѵ�.

ù ��° ������ ���� �� ��° ������ ũ�ٸ�, ���� ��ġ�� ���� �ٲ۴�.
�� ��° ������ ���� �� ��° ������ ũ�ٸ�, ���� ��ġ�� ���� �ٲ۴�.
�� ��° ������ ���� �� ��° ������ ũ�ٸ�, ���� ��ġ�� ���� �ٲ۴�.
�� ��° ������ ���� �ټ� ��° ������ ũ�ٸ�, ���� ��ġ�� ���� �ٲ۴�.
���� ������ 1, 2, 3, 4, 5 ������ �ƴ϶�� 1 �ܰ�� �ٽ� ����.
ó�� ������ ������ �־����� ��, ��ġ�� �ٲ� �� ���� ������ ������ ����ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� ������ ���� �ִ� ���� ������� �־�����. ���ڴ� 1���� ũ�ų� ����, 5���� �۰ų� ������, �ߺ����� �ʴ´�. ó�� ������ 1, 2, 3, 4, 5�� �ƴϴ�.

[���]
�� ������ ������ �ٲ� ���� ������ ������ ����Ѵ�.

[Ǯ��]
��������...

*/
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[5];
		for (int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		bw.write(bubbleSort(arr).toString().trim());
		bw.close();
		br.close();
	}
	
	private static StringBuilder bubbleSort(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					
					for (int k = 0; k < 5; k++) {
						sb.append(arr[k]).append(' ');
					}
					sb.append('\n');
				}
			}
		}
		
		return sb;
	}
}
