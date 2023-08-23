import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/6497
@performance	
@category 		#MST
@note			
*/

public class Main {
	static int M, N;
	static int[] parents;
	static Edge[] edges;
	
	static class Edge implements Comparable<Edge>{
		int x, y, z;

		public Edge(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.z, o.z);
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px != py) {
			parents[py] = px;
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
	
			if(M == 0 && N == 0) {
				break;
			}
			
			parents = new int[M];
			for(int i = 0; i < M; i++) {
				parents[i] = i;
			}
			
			int sum = 0;
			edges = new Edge[N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(x, y, z);
				sum += z;
			}
			Arrays.sort(edges);
			
			int total = 0;
			int cnt = 0;
			
			for(Edge edge : edges) {
				if(union(edge.x, edge.y)) {
					total += edge.z;
					if(++cnt == N-1) break;
				}
			}
			sb.append(sum - total + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}