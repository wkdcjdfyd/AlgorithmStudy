import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 9.
@see			https://www.acmicpc.net/problem/14938
@performance
@category 		#다익스트라
@note
*/

public class Main {
	static final int MAX = (int)1e9;
	static int N;
	static int M;
	static int R;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] d;
	
	static class Node implements Comparable<Node> {
		int num;
		int dist;
		
		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	public static void dijkstra(int start) {
		Arrays.fill(d, MAX);
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(d[now.num] < now.dist) continue;
			
			for(Node nxt : graph.get(now.num)) {
				int cost = d[now.num] + nxt.dist;
				if(cost < d[nxt.num]) {
					d[nxt.num] = cost;
					q.add(new Node(nxt.num, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		d = new int[N+1];
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		st = new StringTokenizer(br.readLine());
		int[] itemNum = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			itemNum[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		int ans = -1;
		for(int i = 1; i < N+1; i++) {
			dijkstra(i);
			int sum = 0;
			for(int j = 1; j < N+1; j++) {
				if(d[j] == MAX) continue;
				if(d[j] > M) continue;
				sum += itemNum[j];
			}
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
		br.close();
	}

}