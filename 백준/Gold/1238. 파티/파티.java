import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 8.
@see			https://www.acmicpc.net/problem/1238
@performance
@category 		#다익스트라
@note
*/

public class Main {
	static final int MAX = (int)1e9 + 1;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static PriorityQueue<Node> q = new PriorityQueue<>();
	static int[] d;
	
	static class Node implements Comparable<Node>{
		int num;
		int dist;
		
		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			if(dist > o.dist) {
				return 1;
			}
			else 
				return -1;
		}
	}
	
	public static void dijkstra(int start) {
		q.clear();
		Arrays.fill(d, MAX);
		
		q.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(d[now.num] < now.dist) continue;
			
			for(Node nxt : graph.get(now.num)) {
				int cost = now.dist + nxt.dist;
				if(cost < d[nxt.num]) {
					q.add(new Node(nxt.num, cost));
					d[nxt.num] = cost;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		d = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, t));
		}
		
		int[] result = new int[N+1];
		
		dijkstra(X);
		for(int i = 1; i < N+1; i++) {
			result[i] = d[i];
		}
		
		int ans = -1;
		for(int i = 1; i < N+1; i++) {
			dijkstra(i);
			ans = Math.max(ans, result[i] + d[X]);
		}
		System.out.println(ans);
		br.close();
	}

}