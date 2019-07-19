/*
https://www.acmicpc.net/problem/5598
[����]
���̿콺 �����콺 ī�̻縣(Gaius Julius Caesar)�� ��� �θ� �������� ��ġ�������ϴ�. ī�̻縣�� ��н����� ������ �� ��, 'A'�� 'D��', 'B'�� 'E'��, 'C'�� 'F'��... �̷� ������ ���ĺ� ���ڸ� 3���� �ǳʶپ� �����ٰ� �մϴ�.

26���� �빮�� ���ĺ����� �̷���� �ܾ ī�̻縣 ��ȣ �������� 3���ڸ� �Ű� ��ġ�� �ʰ� �����Ͽ� ���� ī�̻縣 �ܾ �ֽ��ϴ�. �� ī�̻縣 �ܾ ���� �ܾ�� �������� ���α׷��� �ۼ��ϼ���.

�� ���ں��� ��ȯ ���� ��ȯ �ĸ� ��Ÿ�� �� �Ʒ��� �����ϴ�.

      ��ȯ��    A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 
      ��ȯ��    D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
���� ��, �� ������ �ܾ� 'JOI'�� ī�̻縣 �ܾ� �������� ��ȯ�Ѵٸ� 'MRL'�� ���� �� �ְ�, ���� ���� ���� ������� ���� ī�̻縣 �ܾ� 'FURDWLD'�� ���� �ܾ�� ��ġ�� 'CROATIA'�� �˴ϴ�.

[�Է�]
�Է��� �� �ٷ� �̷���� ������, �� �� �ٿ� �빮�� ���ĺ����� ������ �ܾ 1�� �ֽ��ϴ�.

�Է¹޴� �ܾ�� �ִ� 1000�� �����Դϴ�.

[���]
����� �Է¹��� ī�̻縣 �ܾ ���� �ܾ�� ��ģ �� ����Ͻø� �˴ϴ�.

[Ǯ��]
D~Z������ �Է��� �ƽ�Ű�ڵ忡�� 3�� ���� ���� ����ϰ� A~C������ �Է��� 23�� ���� ���� ����Ѵ�. 
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		Pattern p = Pattern.compile("[D-Z]");
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < str.length(); i++) {
			Matcher m = p.matcher(String.valueOf(str.charAt(i)));
			if(m.matches()){
				sb.append((char)(str.charAt(i)-3));
			}
			else{
				sb.append((char)(str.charAt(i)+23));
			}
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}