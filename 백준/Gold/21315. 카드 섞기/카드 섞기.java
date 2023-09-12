import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 12.
@see			https://www.acmicpc.net/problem/21315
@performance	
@category 		#구현
@note			
*/

public class Main {
	static CardList org;
	static int N, maxK;
	static int[] ans;
	
	static class Node {
		int n;
		Node prev;
		Node next;
		
		public Node() {
			this.n = 0;
			this.prev = null;
			this.next = null;
		}
		
		public Node(int n, Node prev, Node next) {
			this.n = n;
			this.prev = prev;
			this.next = next;
		}
	}
	
	static class CardList {
		Node head;
		Node tail;
		Node[] nodes;
		
		public CardList() {
			head = new Node();
			tail = new Node();
			nodes = new Node[N];
			
			for(int i = 0; i < N; i++) {
				nodes[i] = new Node();
			}
		}
		
		public void init() {
			for(int i = 0; i < N; i++) {
				this.nodes[i].n = i+1;
				
				if(i != 0) {
					this.nodes[i].prev = this.nodes[i-1];
				}
				if(i != N-1) {
					this.nodes[i].next = this.nodes[i+1];
				}
			}
			
			this.head.next = this.nodes[0];
			this.nodes[0].prev = this.head;
			this.tail.prev = this.nodes[N-1];
			this.nodes[N-1].next = this.tail;
		}
		
		public void print() {
			Node now = head.next;
			
			while(now.next != null) {
				System.out.print(now.n + " ");
				now = now.next;
			}
			System.out.println();
		}
	}
	
	// head 뒤에 start ~ end 까지의 노드를 연결
	public static void link(Node start, Node end, Node head) {
		start.prev.next = end.next;
		end.next.prev = start.prev;
		
		head.next.prev = end;
		end.next = head.next;
		head.next = start;
		start.prev = head;
	}
	
	public static void shuffle(CardList cl, int k) {
		int stage = 0;
		Node end = cl.tail.prev;
		while(++stage <= k+1) {
			int movedCardNum = (int)Math.pow(2, k+1 - stage);
			
			int cnt = 1;
			Node start = end;
			Node now = end;
			
			while(now.prev != null) {
				if(movedCardNum == cnt) {
					start = now;
					break;
				}
				
				now = now.prev;
				cnt++;
			}
			link(start, end, cl.head);
		}
	}
	
	public static boolean check(CardList cl) {
		Node now = cl.head.next;
		
		for(int i = 0; i < N; i++, now = now.next) {
			if(now.n != ans[i]) return false;
		}
		return true;
	}
	
	public static void makeDupPerm(int nth, int[] choosed) {
		if(nth == 2) {
			CardList cl = new CardList();
			cl.init();
			
			shuffle(cl, choosed[0]);
			shuffle(cl, choosed[1]);
			
			if(check(cl)) {
				System.out.println(choosed[0] + " " + choosed[1]);
				System.exit(0);
			}
			
			return; 
		}
		for(int i = 1; i <= maxK; i++) {
			choosed[nth] = i;
			makeDupPerm(nth+1, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		maxK = (int)(Math.log(N) / Math.log(2));
		ans = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			ans[i] = Integer.parseInt(st.nextToken());
		}
		
		makeDupPerm(0, new int[2]);
		
		br.close();
	}

}