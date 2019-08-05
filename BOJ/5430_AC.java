import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Main {
	static LinkedList<Integer> list;
	static int flag;
	static StringBuilder sb;
	static StringBuilder result;
	static StringTokenizer st;
	static String funcStr;
	static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		result = new StringBuilder();
		list = new LinkedList<Integer>();
		st = null;
		funcStr = null;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			flag = 1;
			funcStr = br.readLine();
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "[,]");

			for (int i = 0; i < n; i++)
				list.add(Integer.parseInt(st.nextToken()));

			try {
				func();
			} catch (NoSuchElementException e) {
				// TODO: handle exception
				result.append("error\n");
				continue;
			}
			
			assemble();
			
			try {
				sb.replace(sb.length() - 1, sb.length(), "]\n");
			} catch (StringIndexOutOfBoundsException e) {
				// TODO: handle exception
				result.append("[]\n");
				continue;
			}
			
			list.clear();
			result.append('[').append(sb);
			sb.setLength(0);
		}

		bw.write(result.toString().trim());
		bw.close();
		br.close();
	}

	private static void func() {
		for (int i = 0; i < funcStr.length(); i++) {
			if(funcStr.charAt(i) == 'R')
				// function R
				flag *= -1;

			else {
				// function D
				if(flag == 1)
					list.removeFirst();
				
				else
					list.removeLast();
			}
		}
	}
	
	private static void assemble() {
		if(flag == 1) {
			while(!list.isEmpty()) 
				sb.append(list.pollFirst()).append(',');
		}
		
		else {
			while(!list.isEmpty())
				sb.append(list.pollLast()).append(',');
		}
	}
}
