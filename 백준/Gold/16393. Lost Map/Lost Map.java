import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 21.
@see			https://www.acmicpc.net/problem/16393
@performance	
@category 		#MST
@note			
*/

public class Main {
	static int[] parents;
	
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
	
	public static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		parents[px] = py;
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Edge> edges = new ArrayList<>();
		parents = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			parents[i+1] = i+1;
			
			for(int j = 0; j < N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if(i < j) {
					edges.add(new Edge(i+1, j+1, w));
				}
			}
		}
		
		Collections.sort(edges);
		
		int cnt = 0;
		for(Edge e : edges) {
			if(union(e.from, e.to)) {
				sb.append(e.from).append(" ").append(e.to).append("\n");
				if(++cnt == N-1) {
					break;
				}
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}