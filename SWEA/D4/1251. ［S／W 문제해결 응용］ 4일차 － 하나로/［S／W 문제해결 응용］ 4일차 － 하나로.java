import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static Island[] island;
	static double[][] adjMatrix;
	
	static class Island {
		int x, y;

		public Island(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node implements Comparable<Node>{
		int num;
		double weight;
		
		public Node(int num, double weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	public static double prim(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N];
		pq.offer(new Node(start, 0));
		
		double total = 0;
		int cnt = 0;
		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(visited[now.num]) continue;
			
			visited[now.num] = true;
			total += Math.pow(now.weight, 2);
			if(++cnt == N) break;
			
			for(int i = 0; i < N; i++) {
				if(i == now.num) continue;
				if(visited[i]) continue;
				pq.add(new Node(i, adjMatrix[now.num][i]));
			}
		}
		return total;
	}
	
	public static double dist(Island a, Island b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1 ; t++) {
			N = Integer.parseInt(br.readLine());
			island = new Island[N];
			adjMatrix = new double[N][N];
			
			int[] x = new int[N];
			int[] y = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				island[i] = new Island(x[i], y[i]);
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					double dist = dist(island[i], island[j]);
					adjMatrix[i][j] = dist;
					adjMatrix[j][i] = dist;
				}
			}
			
			double E = Double.parseDouble(br.readLine());
			double cost = prim(0);
			cost = cost * E;
			
			sb.append(String.format("#%d %d\n", t, Math.round(cost)));
		}
		System.out.println(sb.toString());
		br.close();
	}

}