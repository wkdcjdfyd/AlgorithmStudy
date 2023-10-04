import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static ArrayList<ArrayList<Integer>> reverse;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] check;
	
	public static void bfs(int start, boolean isReverse) {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(isReverse) {
				for(int nxt : reverse.get(now)) {
					if(visited[nxt]) continue;
					visited[nxt] = true;
					q.offer(nxt);
				}
			}
			else {
				for(int nxt : graph.get(now)) {
					if(visited[nxt]) continue;
					visited[nxt] = true;
					q.offer(nxt);
				}
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			if(visited[i]) {
				check[i] = visited[i];
			}
		}
	}
	
	public static boolean isValid() {
		for(int i = 1; i < N+1; i++) {
			if(!check[i]) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			reverse = new ArrayList<>();
			graph = new ArrayList<>();
			for(int i = 0; i <= N; i++) {
				reverse.add(new ArrayList<>());
				graph.add(new ArrayList<>());
			}
			
			for(int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				reverse.get(b).add(a);
				graph.get(a).add(b);
			}
			
			int ans = 0;
			for(int i = 1; i < N+1; i++) {
				check = new boolean[N+1];
				bfs(i, false);
				bfs(i, true);
				if(isValid()) ans++;
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}