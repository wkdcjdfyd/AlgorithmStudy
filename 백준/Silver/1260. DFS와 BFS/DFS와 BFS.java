import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph;
	static int[] visited;
	static int N;
	static int M;
	static int V;
	
	public static String bfs(int start) {
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
		visited = new int[N+1];
		
		q.add(start);
		visited[start] = 1;
		sb.append(start + " ");
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int nxt: graph.get(now)) {
				if(visited[nxt] != 1) {
					visited[nxt] = 1;
					q.add(nxt);
					sb.append(nxt + " ");
				}
			}
		}
		return sb.toString();
	}
	
	public static String dfs(int start) {
		StringBuilder sb = new StringBuilder();
		Stack<Integer> s = new Stack<>();
		visited = new int[N+1];
		
		s.push(start);
		
		while(!s.isEmpty()) {
			int now = s.pop();
			
			if(visited[now] == 1) continue;
			
			visited[now] = 1;
			sb.append(now + " ");
			
			ArrayList<Integer> temp = graph.get(now);
			for(int i = temp.size()-1; i >= 0; i--) {
				int nxt = temp.get(i);
				if(visited[nxt] != 1) {
					s.push(nxt);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		int[][] edges = new int[M][2];
		
		graph = new ArrayList<>();
		for(int i = 0; i < N+1; i++)	graph.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());			
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int[] edge: edges) {
			graph.get(edge[0]).add(edge[1]);
			graph.get(edge[1]).add(edge[0]);
		}
		
		for(int i = 0; i < graph.size(); i++) {
			ArrayList<Integer> temp = graph.get(i);
			Collections.sort(temp);
		}
		
		System.out.println(dfs(V));
		System.out.println(bfs(V));
	}

}
