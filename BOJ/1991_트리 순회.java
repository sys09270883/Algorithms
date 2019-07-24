/*
[문제]
이진 트리를 입력받아 전위 순회(preorder traversal), 중위 순회(inorder traversal), 후위 순회(postorder traversal)한 결과를 출력하는 프로그램을 작성하시오.



예를 들어 위와 같은 이진 트리가 입력되면,

전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
가 된다.

[입력]
첫째 줄에는 이진 트리의 노드의 개수 N(1≤N≤26)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다. 노드의 이름은 A부터 차례대로 영문자 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현된다.

[출력]
첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다. 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.

[풀이]
전위 순회 : 방문하고 재귀
중위 순회 : 왼쪽방문하고 재귀
후위 순회 : 재귀돌고 방문
*/
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
//		Arrays.setAll(node, i -> new Node((char)(i+65));  for문 초기화보다 속도가 많이 느림.
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
