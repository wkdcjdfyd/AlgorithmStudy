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
@see			https://www.acmicpc.net/problem/1504
@performance		67348kb	648ms
@category 		#다익스트라
@note
*/

public class Main {
	static final int MAX = (int)1e9;
	static int N;
	static int E;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static int[] d;
	
	static class Node implements Comparable<Node> {
		int num;
		int dist;
		
		Node(int num, int dist){
			this.num = num;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		Arrays.fill(d, MAX);
		q.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if(d[now.num] < now.dist)	continue;
			
			for(Node nxt: graph.get(now.num)) {
				int cost = d[now.num] + nxt.dist;
				
				if(cost < d[nxt.num]) {
					d[nxt.num] = cost;
					q.offer(new Node(nxt.num, cost));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		d = new int[N+1];
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		int ans = -1;
		
		dijkstra(1);
		int startToV1 = d[v1];
		int startToV2 = d[v2];
		
		dijkstra(v1);
		int v1ToV2 = d[v2];
		int v1ToN = d[N];
		
		dijkstra(v2);
		int v2ToV1 = d[v1];
		int v2ToN = d[N];
		
		if(startToV1 == MAX || startToV2 == MAX || v1ToV2 == MAX || v1ToN == MAX || v2ToV1 == MAX || v2ToN == MAX) {
			System.out.println(ans);
		}
		else {
			int route1 = startToV1 + v1ToV2 + v2ToN;
			int route2 = startToV2 + v2ToV1 + v1ToN;
			ans = Math.min(route1, route2);
			System.out.println(ans);
		}

		br.close();
	}

}
