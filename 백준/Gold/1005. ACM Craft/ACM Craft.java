import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/1005
@performance	
@category 		#위상정렬
@note			
*/

public class Main {
	static int N, K, W;
	static ArrayList<ArrayList<Integer>> indegree;
	static ArrayList<ArrayList<Integer>> outdegree;
	static HashMap<Integer, Building> building;
	static PriorityQueue<Building> q;
	
	static class Building implements Comparable<Building>{
		int num;
		int time;
		int indegreeCnt;
		
		public Building(int num, int time) {
			this.num = num;
			this.time = time;
			this.indegreeCnt = 0;
		}

		@Override
		public int compareTo(Building o) {
			return Integer.compare(this.time, o.time);
		}
	}
	
	public static void findFirstBuildings(int start) {
		for(int i = 1; i < N+1; i++) {
			if(indegree.get(i).size() == 0) {
				q.add(building.get(i));
			}
		}
	}
	
	public static int build() {
		int time = 0;
		while(!q.isEmpty()) {
			Building now = q.poll();
			
			time += now.time;
			
			for(Building b : q) {
				b.time -= now.time;
			}
			now.time = 0;
			
			for(int nxt : outdegree.get(now.num)) {
				if(!building.containsKey(nxt)) {
					continue;
				}
				Building next = building.get(nxt);
				next.indegreeCnt--;
				
				if(next.indegreeCnt == 0) {
					q.offer(building.get(nxt));
				}	
			}
		}
		return time;
	}

	public static void removeOtherBuilding(int start) {
		Deque<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(int nxt : indegree.get(now)) {
				if(!visited[nxt]) {
					visited[nxt] = true;
					queue.offer(nxt);
				}
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			if(!visited[i]) {
				building.remove(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			building = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N+1; i++) {
				building.put(i, new Building(i, Integer.parseInt(st.nextToken())));
			}
			
			indegree = new ArrayList<>();
			outdegree = new ArrayList<>();
			for(int i = 0; i < N+1; i++) {
				indegree.add(new ArrayList<>());
				outdegree.add(new ArrayList<>());
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				outdegree.get(x).add(y);
				indegree.get(y).add(x);
				building.get(y).indegreeCnt++;
			}
			q = new PriorityQueue<>();
			
			W = Integer.parseInt(br.readLine());
			
			findFirstBuildings(W);
			removeOtherBuilding(W);
			int time = build();
			
			sb.append(time + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}