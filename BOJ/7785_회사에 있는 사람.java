/*
https://www.acmicpc.net/problem/7785
[����]
����̴� �������� ����Ʈ���� ȸ�� ��ۿ��� ���Ѵ�. �� ȸ���� ���� ū Ư¡�� �����ο� ����� �ð��̴�. 
����, �������� �ݵ�� 9�ú��� 6�ñ��� ȸ�翡 ���� �ʾƵ� �ȴ�.

�� ������ �ڱⰡ ���� �� ����� �� �ְ�, �ƹ����� ����� �� �ִ�.

����̴� ��� ����� ����ī�� �ý����� �α׸� ������ �ִ�. �� �α״� � ����� ȸ�翡 ���Դ���, ���������� ��ϵǾ��� �ִ�. 
�αװ� �־����� ��, ���� ȸ�翡 �ִ� ��� ����� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

[�Է�]
ù° �ٿ� �α׿� ��ϵ� ���� ����� �� n�� �־�����. (2 �� n �� 106) ���� n���� �ٿ��� ���� ����� ������� �־�����, 
�� ����� �̸��� �־����� "enter"�� "leave"�� �־�����. "enter"�� ���� ���, "leave"�� ���� ����̴�.

ȸ�翡�� ���������� ������, ��ҹ��ڰ� �ٸ� ��쿡�� �ٸ� �̸��̴�. ������� �̸��� ���ĺ� ��ҹ��ڷ� ������ 5���� ������ ���ڿ��̴�.

[���]
���� ȸ�翡 �ִ� ����� �̸��� ���� ���� �������� �� �ٿ� �� �� ����Ѵ�.

[Ǯ��]
������ �������� �ִ� treeset�� �ൿ�� ���� �̸��� �ִ´�. (o1, o2) -> o2.compareTo(o1)
���ͷ����ͷ� ��ȸ�ϸ鼭 ����� ����Ѵ�.

 + �ڹ��� TreeSet���� descendingIterator�� �ִ�.

*/
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        String name, action;
        StringBuilder res = new StringBuilder();
        Set<String> ts = new TreeSet<String>((o1, o2) -> o2.compareTo(o1));
        
        while(n-- > 0) {
        	st = new StringTokenizer(br.readLine(), " ");
        	name = st.nextToken();
        	action = st.nextToken();
        	
        	if (action.equals("enter")) {
        		ts.add(name);
        	}
        	
        	else if (action.equals("leave")) {
        		ts.remove(name);
        	}
        }
        
        Iterator<String> it = ts.iterator();
        
        while (it.hasNext()) {
        	String key = it.next();
        	res.append(key).append('\n');
        }
        
        bw.write(res.toString().trim());
        bw.close();
        br.close();
    }
}
