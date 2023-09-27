import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 27.
@see			https://www.acmicpc.net/problem/13141
@performance	
@category 		#플로이드워셜
@note			
*/

public class Main {
	static int N, M;
	static int[][] dist;
	static final int INF = (int)1e9;
	static Edge[] edges;
	
	static class Edge{
		int v1, v2, w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N+1][N+1];
		edges = new Edge[M];
		
		for(int i = 0; i < N+1; i++) {
			for(int j = 0; j < N+1; j++) {
				dist[i][j] = INF;
			}
			dist[i][i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			dist[S][E] = Math.min(dist[S][E], L);
			dist[E][S] = Math.min(dist[E][S], L);
			edges[i] = new Edge(S, E, L);
		}

		for(int k = 1; k < N+1; k++) {
			for(int i = 1; i < N+1; i++) {
				for(int j = 1; j < N+1; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int ans = INF;
		for(int i = 1; i < N+1; i++) {
			int max = -1;
			for(int j = 0; j < M; j++) {
				int time = dist[i][edges[j].v1] + dist[i][edges[j].v2] + edges[j].w;
				max = Math.max(max, time);
			}
			ans = Math.min(ans, max);
		}
		
		System.out.printf("%.1f", (float)ans / 2);
		br.close();
	}

}