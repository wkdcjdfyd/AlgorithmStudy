import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 			Ryong
@since 				2023. 8. 7.
@see				https://www.acmicpc.net/problem/1158
@performance
@category 			#
@note
*/

public class Main {
	
	static class Node {
		int num;
		Node prev;
		Node nxt;
		
		Node (int num, Node prev, Node nxt) {
			this.num = num;
			this.prev = prev;
			this.nxt = nxt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Node head = new Node(1, null, null);
		
		int cnt = 1;
		Node now = head;
		while(cnt < N) {
			Node nxt = new Node(++cnt, now, null);
			if(cnt == N) {
				nxt.nxt = head;
				head.prev = nxt;
			}
			
			now.nxt = nxt;
			now = now.nxt;
		}
		
		cnt = 1;
		int nodeLeft = N;
		now = head;
		sb.append("<");
		if(nodeLeft == 1) {
			sb.append(now.num);
			nodeLeft--;
		}
		
		while(nodeLeft > 0) {
			if(cnt % K == 0) {
				sb.append(now.num);
				now.prev.nxt = now.nxt;
				now.nxt.prev = now.prev;
				nodeLeft--;
				if(nodeLeft != 0) {
					sb.append(", ");
				}
			}
			now = now.nxt;
			cnt++;
		}
		sb.append(">");
		System.out.println(sb.toString());
		br.close();
	}

}