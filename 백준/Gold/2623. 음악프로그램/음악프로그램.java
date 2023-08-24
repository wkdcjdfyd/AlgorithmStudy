import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 24.
@see			https://www.acmicpc.net/problem/2623
@performance	
@category 		#위상정렬
@note			
*/

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] indegree;
	static StringBuilder sb = new StringBuilder();
	
	public static void topologicalSort() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		int cnt = 0;
		
		for(int i = 1; i < N+1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
				visited[i] = true;
				cnt++;
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + "\n");
			
			for(int nxt : graph.get(now)) {
				if(--indegree[nxt] == 0) {
					if(!visited[nxt]) {
						visited[nxt] = true;
						q.offer(nxt);
						cnt++;
					}
				}
			}
		}
		
		if(cnt == N) {
			System.out.println(sb.toString());
		}
		else {
			System.out.println(0);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		graph = new ArrayList<>(N+1);
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			if(num == 0) continue;
			int n1 = Integer.parseInt(st.nextToken());
			
			for(int j = 1; j < num; j++) {
				int n2 = Integer.parseInt(st.nextToken());
				graph.get(n1).add(n2);
				indegree[n2]++;
				n1 = n2;
			}
		}
		
		topologicalSort();
		br.close();
	}

}