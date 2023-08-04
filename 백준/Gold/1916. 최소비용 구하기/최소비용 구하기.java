import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 4.
@see			https://www.acmicpc.net/problem/1916
@performance
@category 		#다익스트라
@note
*/

public class Main {
	static final int MAX = (int)1e9;
	static int N;
	static int M;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] d;
	
	static class Node implements Comparable<Node>{
		private int idx;
		private int distance;
		
		public Node(int idx, int distance) {
			this.idx = idx;
			this.distance = distance;
		}
		
		public int getIdx() {
			return this.idx;
		}
		public int getDistance() {
			return this.distance;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
		
		
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		d[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.getIdx();
			int dist = node.getDistance();
			
			if(d[now] < dist) continue;
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				int cost = dist + graph.get(now).get(i).getDistance();
				if(cost < d[graph.get(now).get(i).getIdx()]) {
					d[graph.get(now).get(i).getIdx()] = cost;
					pq.add(new Node(graph.get(now).get(i).getIdx(), cost));
				}
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		d = new int[N+1];
		Arrays.fill(d, MAX);
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		System.out.println(d[end]);
		br.close();
	}

}