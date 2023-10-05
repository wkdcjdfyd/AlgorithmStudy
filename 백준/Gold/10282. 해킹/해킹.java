import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 6.
@see			https://www.acmicpc.net/problem/10282
@performance	
@category 		#다익스트라
@note			
*/

public class Main {
	static int n, d, c;
	static ArrayList<ArrayList<Node>> graph;
	static int[] dist;
	static final int INF = (int)1e8;
	
	static class Node implements Comparable<Node>{
		int num, time;

		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			if(dist[now.num] < now.time) continue;
			
			for(Node nxt : graph.get(now.num)) {
				int cost = dist[now.num] + nxt.time;
				if(cost < dist[nxt.num]) {
					dist[nxt.num] = cost;
					pq.offer(new Node(nxt.num, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			dist = new int[n+1];
			
			for(int i = 0; i < n+1; i++) {
				graph.add(new ArrayList<>());
				dist[i] = INF;
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				graph.get(b).add(new Node(a, s));
			}
			
			dijkstra(c);
			
			int cnt = 0;
			int max = 0;
			for(int i = 1; i < n+1; i++) {
				if(dist[i] != INF) {
					cnt++;
					max = Math.max(max, dist[i]);
				}
			}
			
			
			sb.append(cnt).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}