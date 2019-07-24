import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		Node[] node = new Node[N];
		for (int j = 0; j < N; j++) {
			node[j] = new Node((char)(j+65));
		}
//		Arrays.setAll(node, i -> new Node((char)(i+65)));
		Tree tree = new Tree(node[0]);

		while(N-- > 0){
			st = new StringTokenizer(br.readLine());
			char n1 = st.nextToken().charAt(0);
			char n2 = st.nextToken().charAt(0);
			char n3 = st.nextToken().charAt(0);

			if(n2 != '.'){
				node[n1-65].left = node[n2-65];
				node[n2-65].parent = node[n1-65];
			}

			if(n3 != '.'){
				node[n1-65].right = node[n3-65];
				node[n3-65].parent = node[n1-65];
			}
		}

		tree.preOrder(tree.root, sb);
		sb.append('\n');
		tree.inOrder(tree.root, sb);
		sb.append('\n');
		tree.postOrder(tree.root, sb);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
class Node{
	char element;
	Node parent, left, right;
	public Node(char e){
		this.element = e;
		parent = null;
		left = null;
		right = null;
	}
}
class Tree{
	Node root;
	Tree(Node v){
		root = v;
	}

	public void preOrder(Node v, StringBuilder sb){
		sb.append(v.element);
		if(v.left != null)
			preOrder(v.left, sb);

		if(v.right != null)
			preOrder(v.right, sb);
	}

	public void inOrder(Node v, StringBuilder sb){
		if(v.left != null)
			inOrder(v.left, sb);

		sb.append(v.element);

		if(v.right != null)
			inOrder(v.right, sb);
	}

	public void postOrder(Node v, StringBuilder sb){
		if(v.left != null)
			postOrder(v.left, sb);

		if(v.right != null)
			postOrder(v.right, sb);
		sb.append(v.element);
	}
}
