import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		Queue<Document> queue = new LinkedList<Document>();
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>((o1, o2) -> o2 - o1);
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int cnt = 1;
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Document[] doc = new Document[N];
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {
				int priority = Integer.parseInt(st.nextToken());
				
				if(tm.get(priority) == null)
					tm.put(priority, 1);
				
				else
					tm.put(priority, tm.get(priority) + 1);
				
				queue.add(doc[i] = new Document(i, priority));
			}
			
			Iterator<Integer> it = tm.keySet().iterator();
			LOOP: while(it.hasNext()) {
				int tmp = it.next();
				int loop = tm.get(tmp);
				
				while(loop > 0) {
					Document tmpDoc = queue.poll();
					if(tmpDoc.priority == tmp) {
						loop--;
						
						if(tmpDoc.index == M)
							break LOOP;
						
						cnt++;
					}
					
					queue.add(tmpDoc);
				}
			}
			
			sb.append(cnt).append('\n');
			queue.clear();
			tm.clear();
		}
		
		bw.write(sb.toString().trim());
		bw.close();
		br.close();
	}

}

class Document {
	int index;
	int priority;
	
	Document(int index, int priority) {
		this.index = index;
		this.priority = priority;
	}
}