import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 22.
@see			https://www.acmicpc.net/problem/6091
@performance	
@category 		#MST
@note			
*/

public class Main {
	static int[] parents;
	static int N;
	static ArrayList<Edge> edges;
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		parents[py] = px;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		edges = new ArrayList<>((N-1) * (N-2) / 2);
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
			parents[i] = i;
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = i+1; j <= N; j++) {
				edges.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
			}
		}
		
		Collections.sort(edges);
		
		int cnt = 0;
		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				graph.get(e.from).add(e.to);
				graph.get(e.to).add(e.from);
				
				if(++cnt == N-1) break;
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			ArrayList<Integer> now = graph.get(i);
			Collections.sort(now);
			sb.append(now.size()).append(" ");
			
			for(int n : now) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}