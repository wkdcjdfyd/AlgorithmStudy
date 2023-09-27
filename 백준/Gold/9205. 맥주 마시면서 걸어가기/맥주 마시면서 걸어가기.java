import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 27.
@see			https://www.acmicpc.net/problem/9205
@performance	
@category 		#
@note			
*/

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Pos> all;
	static final int INF = (int)1e9;
	
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static boolean bfs() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+2];
		visited[0] = true;
		q.offer(0);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int nxt : graph.get(now)) {
				if(!visited[nxt]) {
					visited[nxt] = true;
					
					if(nxt == N+1) return true;
					
					q.offer(nxt);
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
		int x = 0;
		int y = 0;
		
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			all = new ArrayList<>();
			graph = new ArrayList<>();
			
			for(int i = 0; i < N+2; i++) {
				graph.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			all.add(new Pos(x, y));
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				all.add(new Pos(x, y));
			}
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			all.add(new Pos(x, y));
			
			for(int i = 0; i < N+2; i++) {
				Pos s = all.get(i);
				for(int j = i+1; j < N+2; j++) {
					Pos e = all.get(j);
					int dist = Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
					if(dist <= 1000) {
						graph.get(i).add(j);
						graph.get(j).add(i);
					}
				}
			}
			
			if(bfs()) sb.append("happy\n");
			else sb.append("sad\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}