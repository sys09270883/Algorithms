import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int nV = Integer.parseInt(br.readLine());
		int nE = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		Vertex[] v = new Vertex[nV];
		for (int i = 0; i < nV; i++) {
			v[i] = new Vertex(i);
		}
		
		Graph g = new Graph(nV);
		
		for (int i = 0; i < nE; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			g.addEdge(v, a, b);
			g.addEdge(v, b, a);
		}
		
		g.DFS(v, 0);
		
		int cnt = 0;
		for (int i = 1; i < nV; i++) {
			if(g.visited[i] == true) {
				cnt++;
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.close();
		br.close();
	}
}

class Graph {
	int totalSize;
	ArrayList<ArrayList<Vertex>> adjList;
	boolean[] visited;
	
	Graph(int nV) {
		this.totalSize = nV; 
		this.adjList = new ArrayList<ArrayList<Vertex>>(totalSize);
		for (int i = 0; i < totalSize; i++) 
			adjList.add(new ArrayList<Vertex>());
		
		this.visited = new boolean[nV];
	}
	
	public void addEdge(Vertex[] vertex, int fromNodeIdx, int toNodeIdx) {
		this.adjList.get(fromNodeIdx).add(vertex[toNodeIdx]);
	}
	
	public void DFS(Vertex[] vertex, int root) {
		visited[root] = true;
		
		for (Vertex v : adjList.get(root)) {
			if(!visited[v.value]) {
				DFS(vertex, v.value);
			}
		}
	}
}

class Vertex {
	int value;
	
	Vertex(int value) {
		this.value = value;
	}
}

