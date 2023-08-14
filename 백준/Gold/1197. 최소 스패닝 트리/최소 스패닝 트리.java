import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 14.
@see			https://www.acmicpc.net/problem/1197
@performance	
@category 		#MST
@note			
*/

public class Main {
	static int V;
	static int E;
	static int[] parent;
	static int[][] graph;
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return find(parent[x]);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x > y) {
			parent[x] = y;
		}
		else {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parent = new int[V+1];
		for(int i = 1; i < V+1; i++) {
			parent[i] = i;
		}
		
		graph = new int[E][3];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(graph, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		int cost = 0;
		for(int i = 0; i < E; i++) {
			if(find(graph[i][0]) != find(graph[i][1])) {
				union(graph[i][0], graph[i][1]);
				cost += graph[i][2];
			}
		}
		System.out.println(cost);
		br.close();
	}

}