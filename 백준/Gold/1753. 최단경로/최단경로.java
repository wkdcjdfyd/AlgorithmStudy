import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = (int)1e9;
	static int V;
	static int E;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();	
	static long[] d;
	
	static class Node implements Comparable<Node>{
		int num;
		long dist;
		
		public Node(int num, long dist) {
			this.num = num;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.dist, o.dist);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(d[now.num] < now.dist) continue;
			
			for(Node nxt : graph.get(now.num)) {
				long cost = d[now.num] + nxt.dist;
				
				if(cost < d[nxt.num]) {
					d[nxt.num] = cost;
					pq.offer(new Node(nxt.num, cost));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		d = new long[V+1];
		for(int i = 0; i < V+1; i++) {
			graph.add(new ArrayList<Node>());
			d[i] = INF;
		}
		
		int K = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for(int i = 1; i < V+1; i++) {
			if(i == K) {
				sb.append(0 + "\n");
			}
			else if(d[i] == INF) {
				sb.append("INF\n");
			}
			else {
				sb.append(d[i] + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}