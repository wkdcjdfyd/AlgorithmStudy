import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 			Ryong
@since 				2023. 8. 4.
@see				https://www.acmicpc.net/problem/1753
@performance
@category 			#다익스트라
@note				
*/

public class Main {
	static final long MAX = 6_000_000_000L;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static long[] d;
	
	static class Node implements Comparable<Node>{
		private int idx;
		private long distance;
		
		public Node(int idx, long distance) {
			this.idx = idx;
			this.distance = distance;
		}
		
		public int getIdx() {
			return this.idx;
		}
		
		public long getDistance() {
			return this.distance;
		}
		
		@Override
		public int compareTo(Node other) {
			return (int)(this.distance - other.distance);
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			long dist = node.getDistance();
			int now = node.getIdx();
			
			if(d[now] < dist) continue;
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				long cost = d[now] + graph.get(now).get(i).getDistance();
				if(cost < d[graph.get(now).get(i).getIdx()]) {
					d[graph.get(now).get(i).getIdx()] = cost;
					pq.offer(new Node(graph.get(now).get(i).getIdx(), cost));
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		d = new long[V+1];
		
		for(int v = 0; v < V+1; v++) {
			graph.add(new ArrayList<Node>());
			d[v] = MAX;
		}
		
		for(int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, w));
		}
		
		dijkstra(K);
		
		for(int v = 1; v < V+1; v++) {
			if(v == K) {
				sb.append(0 + "\n");
			}
			else if(d[v] == MAX) {
				sb.append("INF\n");
			}
			else {
				sb.append(d[v] + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}