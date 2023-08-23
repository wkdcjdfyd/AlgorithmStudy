import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static int N, S;
	static ArrayList<ArrayList<Integer>> graph;
	
	public static int bfs(int start) {
		Deque<Integer> q = new ArrayDeque<>();
		int[] visited = new int[101];
		visited[start] = 1;
		q.offer(start);
		int max = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int nxt : graph.get(now)){
				if(visited[nxt] != 0) {
					continue;
				}
				visited[nxt] = visited[now] + 1;
				max = visited[nxt];
				q.offer(nxt);
			}
		}
		
		for(int i = visited.length - 1; i > 0; i--) {
			if(visited[i] == max) {
				return i;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int t = 1; t < 11; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for(int i = 0; i < 101; i++) {
				graph.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph.get(from).add(to);
			}
			int result = bfs(S);
			sb.append(String.format("#%d %d\n", t, result));
		}
		System.out.println(sb.toString());
		br.close();
	}

}