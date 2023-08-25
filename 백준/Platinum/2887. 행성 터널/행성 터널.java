import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 25.
@see			https://www.acmicpc.net/problem/2887
@performance	
@category 		#
@note			
*/

public class Main {
	static int N;
	static Planet[] planets;
	static ArrayList<Edge> edges;
	static int[] parents;
	
	static class Planet {
		int num, x, y, z;

		public Planet(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
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
	
	public static int dist(Planet p1, Planet p2) {
		return Math.min(Math.min(Math.abs(p1.x-p2.x), Math.abs(p1.y-p2.y)), Math.abs(p1.z-p2.z));
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
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planets = new Planet[N];
		edges = new ArrayList<>(3 * N);
		parents = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			planets[i] = new Planet(i, x, y, z);
			parents[i] = i;
		}
		
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.x, o2.x));
		for(int i = 1; i < N; i++) {
			edges.add(new Edge(planets[i-1].num, planets[i].num, dist(planets[i-1], planets[i])));
		}
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.y, o2.y));
		for(int i = 1; i < N; i++) {
			edges.add(new Edge(planets[i-1].num, planets[i].num, dist(planets[i-1], planets[i])));
		}
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.z, o2.z));
		for(int i = 1; i < N; i++) {
			edges.add(new Edge(planets[i-1].num, planets[i].num, dist(planets[i-1], planets[i])));
		}
		
		Collections.sort(edges);
		
		int total = 0;
		int cnt = 0;
		for(Edge e : edges) {
			if(union(e.to, e.from)) {
				total += e.weight;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(total);
		br.close();
	}

}