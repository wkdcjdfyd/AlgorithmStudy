import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 15.
@see			https://www.acmicpc.net/problem/7662
@performance	
@category 		#자료구조
@note			
*/

public class Main {
	static Queue<Node> minHeap;
	static Queue<RevNode> maxHeap;
	static StringBuilder sb = new StringBuilder();
	
	static class Node implements Comparable<Node>{
		int num;
		boolean isDeleted;
		
		public Node(int num) {
			this.num = num;
			this.isDeleted = false;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.num, o.num);
		}
	}
	
	static class RevNode implements Comparable<RevNode>{
		Node node;
		
		public RevNode(Node node){
			this.node = node; 
		}

		@Override
		public int compareTo(RevNode o) {
			return Integer.compare(o.node.num, this.node.num);
		}
		
	}
	
	public static void insert(int n) {
		Node now = new Node(n);
		
		minHeap.add(now);
		maxHeap.add(new RevNode(now));
	}
	
	public static void deleteMax() {
		if(!maxHeap.isEmpty()) {
			while(!maxHeap.isEmpty() && maxHeap.peek().node.isDeleted) {
				maxHeap.poll();
			}
			if(!maxHeap.isEmpty()) {
				RevNode now = maxHeap.poll();
				now.node.isDeleted = true;
			}
		}
	}
	
	public static void deleteMin() {
		if(!minHeap.isEmpty()) {
			while(!minHeap.isEmpty() && minHeap.peek().isDeleted) {
				minHeap.poll();
			}
			if(!minHeap.isEmpty()) {
				Node now = minHeap.poll();
				now.isDeleted = true;
			}
		}
	}
	
	public static void cmd(char c, int n) {
		switch(c) {
			case 'I':
				insert(n);
				break;
			case 'D':
				if(n == 1) {
					deleteMax();
				}
				else if(n == -1) {
					deleteMin();
				}
				break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			minHeap = new PriorityQueue<>();
			maxHeap = new PriorityQueue<>();
			
			for(int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());
				cmd(c, n);
			}
			
			boolean isVal = false;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			if(!minHeap.isEmpty()) {
				while(!minHeap.isEmpty() && minHeap.peek().isDeleted) {
					minHeap.poll();
				}
				if(!minHeap.isEmpty()) {
					int n = minHeap.poll().num;
					isVal = true;
					min = n;
					max = n;
				}
			}
			while(!minHeap.isEmpty()) {
				Node now = minHeap.poll();
				if(!now.isDeleted) {
					max = now.num;
				}
			}
			
			if(!isVal) {
				sb.append("EMPTY\n");
			}
			else {
				sb.append(String.format("%d %d\n", max, min));
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}