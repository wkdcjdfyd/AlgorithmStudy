import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 20.
@see			https://www.acmicpc.net/problem/11724
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	
	public static void bfs(int x) {
		Deque<Integer> q = new ArrayDeque<>();
		visited[x] = true;
		q.offer(x);
		
		while(!q.isEmpty()){
			int now = q.poll();
			
			for(int nxt : graph.get(now)) {
				if(visited[nxt]) continue;
				visited[nxt] = true;
				q.offer(nxt);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		int cnt = 0;
		for(int i = 1; i < N+1; i++) {
			if(!visited[i]) {
				bfs(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}

}