import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 10.
@see			https://www.acmicpc.net/problem/1991
@performance		11604kb 76ms
@category 		#트리순회
@note
*/

public class Main {
	static StringBuilder sb = new StringBuilder();
	static HashMap<Character, Node> map = new HashMap<>();
	
	static class Node {
		Node left;
		Node right;
		char val;
		
		public Node(Node left, Node right, char val) {
			this.left = left;
			this.right = right;
			this.val = val;
		}
		
	}
	
	public static void preOrder(Node root) {
		sb.append(root.val);
		if(root.left != null) {
			preOrder(root.left);
		}
		if(root.right != null) {
			preOrder(root.right);
		}
	}
	
	public static void inOrder(Node root) {
		if(root.left != null) {
			inOrder(root.left);
		}
		sb.append(root.val);
		if(root.right != null) {
			inOrder(root.right);
		}
	}
	
	public static void postOrder(Node root) {
		if(root.left != null) {
			postOrder(root.left);
		}
		if(root.right != null) {
			postOrder(root.right);
		}
		sb.append(root.val);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node head = null;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			
			Node parent = map.getOrDefault(a, null);
			Node left = null;
			Node right = null;
			if(parent == null) {
				if(b != '.') {
					left = new Node(null, null, b);
					map.put(b, left);
				}
				if(c != '.') {
					right = new Node(null, null, c);
					map.put(c, right);
				}
				parent = new Node(left, right, a);
				map.put(a, parent);
			}
			else {
				if(b != '.') {
					left = map.getOrDefault(b, null);
					if(left == null) {
						left = new Node(null, null, b);
						map.put(b, left);
					}
				}
				if(c != '.') {
					right = map.getOrDefault(c, null);
					if(right == null) {
						right = new Node(null, null, c);
						map.put(c, right);
					}
				}
				parent.left = left;
				parent.right = right;
			}
			
			if(head == null) {
				head = parent;
			}
		}
		preOrder(head);
		sb.append("\n");
		inOrder(head);
		sb.append("\n");
		postOrder(head);
		sb.append("\n");
		System.out.println(sb.toString());
		br.close();
	}

}
