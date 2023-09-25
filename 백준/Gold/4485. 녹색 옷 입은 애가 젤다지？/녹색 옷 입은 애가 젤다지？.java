import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 25.
@see			https://www.acmicpc.net/problem/4485
@performance	
@category 		#다익스트라
@note			출력 부분이 정해져 있을 때 오타를 조심하자...
*/

public class Main {
	static int N;
	static int[][] graph;
	static int[][] dist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Edge implements Comparable<Edge>{
		int x, y, w;

		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
		
	}
	
	public static void dijkstra(int a, int b) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		dist[a][b] = graph[a][b];
		pq.offer(new Edge(a, b, graph[a][b]));
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(dist[now.x][now.y] < now.w) continue;
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				int cost = dist[now.x][now.y] + graph[nx][ny];
				if(cost < dist[nx][ny]) {
					dist[nx][ny] = cost;
					pq.add(new Edge(nx, ny, cost));
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 0;
		
		while(true) {
			t++;
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			graph = new int[N][N];
			dist = new int[N][N];
			
			for(int i = 0; i < N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dijkstra(0, 0);
			sb.append("Problem ").append(t).append(": ").append(dist[N-1][N-1]);
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}

}