import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 20.
 * @see  			https://www.acmicpc.net/problem/1865
 * @performance 	
 * @category 		#벨만포드
 * @note 			음수 사이클 판단?
 */

public class Main {
	static int N, M, W;
	static int[] d;
	static Edge[] edges;
	
	static class Edge {
		int s;
		int e;
		int t;
		
		public Edge(int s, int e, int t) {
			super();
			this.s = s;
			this.e = e;
			this.t = t;
		}
	}
	
	public static boolean bellmanFord() {
		Arrays.fill(d, 0);
		boolean flag = false;
		
		for(int i = 1; i < N; i++) {
			flag = false;
			for(Edge edge : edges) {
				if(d[edge.e] > d[edge.s] + edge.t) {
					d[edge.e] = d[edge.s] + edge.t;
					flag = true;
				}
			}
			if(!flag) {
				break;
			}
		}
		
		if(flag) {
			for(Edge edge : edges) {
				if(d[edge.e] > d[edge.s] + edge.t) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for(int z = 0; z < T; z++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			edges = new Edge[(M*2)+W];
			d = new int[N+1];
			
			for(int i = 0; i < M*2; i = i + 2) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(s, e, t);
				edges[i+1] = new Edge(e, s, t);
			}
			
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				edges[i+(M*2)] = new Edge(s, e, -t);
			}
			
			if(bellmanFord()) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}