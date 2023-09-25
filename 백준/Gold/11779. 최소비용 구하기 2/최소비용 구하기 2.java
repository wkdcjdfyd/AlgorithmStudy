import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 25.
@see			https://www.acmicpc.net/problem/11779
@performance	
@category 		#다익스트라
@note			
*/

public class Main {
	static int N, M, start, end;
	static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
	static int[] visited;
	static int[] dist;
	
	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
		dist[start] = 0;
		visited[start] = start;
		pq.offer(new int[] {0, start});
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if(dist[now[1]] < now[0]) continue;
			
			for(int[] nxt : graph.get(now[1])) {
				int cost = now[0] + nxt[1];
				if(cost < dist[nxt[0]]) {
					dist[nxt[0]] = cost;
					visited[nxt[0]] = now[1];
					pq.offer(new int[] {cost, nxt[0]});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		visited = new int[N+1];
		dist = new int[N+1];
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
			dist[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new int[] {v, w});
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		
		ArrayList<Integer> route = new ArrayList<>();
		
		int x = end;
		route.add(end);
		while(visited[x] != x) {
			route.add(visited[x]);
			x = visited[x];
		}
		
		sb.append(dist[end]).append("\n");
		sb.append(route.size()).append("\n");
		
		for(int i = route.size() - 1; i >= 0; i--) {
			sb.append(route.get(i)).append(" ");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}