import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/17142
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static ArrayList<Loc> virusLoc = new ArrayList<>();
	static int blankCnt = 0;
	static int ans = 10000;
	
	static class Loc {
		int x, y;

		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void makeVirus(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			spread(choosed);
			return;
		}
		for(int i = startIdx; i < virusLoc.size(); i++) {
			choosed[nth] = i;
			makeVirus(nth+1, i+1, choosed);
		}
	}
	
	public static void spread(int[] virusIdx) {
		Deque<Loc> q = new ArrayDeque<>();
		visited = new int[N][N];
		int cnt = blankCnt;
		int time = 1;
		
		for(int idx : virusIdx) {
			Loc loc = virusLoc.get(idx);
			q.offer(loc);
			visited[loc.x][loc.y] = 1;
		}

		Loop:
		while(!q.isEmpty()) {
			Loc now = q.poll();
			for(int d = 0; d < dx.length; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				if(graph[nx][ny] == 1 || visited[nx][ny] != 0) {
					continue;
				}
				if(graph[nx][ny] == 0) {
					cnt--;
					time = visited[now.x][now.y] + 1;
				}
				if(cnt == 0) {
					break Loop;
				}
				
				visited[nx][ny] = visited[now.x][now.y] + 1;
				q.offer(new Loc(nx, ny));
			}
		}
		
		if(cnt == 0) {
			ans = Math.min(ans, time-1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 2) {
					virusLoc.add(new Loc(i, j));
				}
				else if(graph[i][j] == 0) {
					blankCnt++;
				}
			}
		}
		
		makeVirus(0, 0, new int[M]);
		
		if(ans == 10000) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
		br.close();
	}

}