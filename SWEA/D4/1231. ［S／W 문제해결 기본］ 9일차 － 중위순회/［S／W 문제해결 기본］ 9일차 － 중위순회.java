import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static Node[] nodes;
	static StringBuilder sb = new StringBuilder();

	static class Node{
		int num;
		char c;
		Node leftChild;
		Node rightChild;	
	}
	
	public static void inOrder(Node x) {
		if(x.leftChild != null) {
			inOrder(x.leftChild);
		}
		sb.append(x.c);
		if(x.rightChild != null) {
			inOrder(x.rightChild);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t = 1; t < 11; t++) {
			N = Integer.parseInt(br.readLine());
			nodes = new Node[N+1];
			for(int i = 0; i < N+1; i++) {
				nodes[i] = new Node();
			}
			
			for(int i = 1; i < N+1; i++) {
				st = new StringTokenizer(br.readLine());
				int cnt = st.countTokens();
				
				int num = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				
				nodes[i].num = num;
				nodes[i].c = c;
				
				if(cnt == 3 || cnt == 4) {
					int left = Integer.parseInt(st.nextToken());
					nodes[i].leftChild = nodes[left];
				}
				if(cnt == 4) {
					int right = Integer.parseInt(st.nextToken());
					nodes[i].rightChild = nodes[right];
				}
			}
			
			sb.append("#" + t + " ");
			inOrder(nodes[1]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}