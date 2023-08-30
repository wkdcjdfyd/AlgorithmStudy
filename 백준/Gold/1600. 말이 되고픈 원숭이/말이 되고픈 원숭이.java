import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 30.
@see			https://www.acmicpc.net/problem/1600
@performance	
@category 		#
@note			
*/

public class Main {
	static int[] dx = {-1, 0, 1, 0, -2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {0, 1, 0, -1, 1, 2, 2, 1, -1, -2, -2, -1};
	static int K, W, H;
	static int[][] graph;
	static int[][][] visited;
	
	static class Loc{
		int x, y, cnt, k;
		
		public Loc (int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= H || y >= W) {
			return false;
		}
		return true;
	}
	
	public static void bfs() {
		Deque<Loc> q = new ArrayDeque<>();
		q.offer(new Loc(0, 0, 0));
		visited[0][0][0] = 0;
		
		while(!q.isEmpty()) {
			Loc now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				if(now.k == K && d >= 4) break;
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(!isValid(nx, ny)) continue;
				if(graph[nx][ny] == 1) continue;
				
				// 일반 이동
				if(d < 4) {
					if(visited[now.k][nx][ny] == -1 || visited[now.k][now.x][now.y] + 1 < visited[now.k][nx][ny]) {
						visited[now.k][nx][ny] = visited[now.k][now.x][now.y] + 1;
						q.offer(new Loc(nx, ny, now.k));
					}
				}
				// 말 이동
				else {
					if(visited[now.k+1][nx][ny] == -1 || visited[now.k][now.x][now.y] + 1 < visited[now.k+1][nx][ny]) {
						visited[now.k+1][nx][ny] = visited[now.k][now.x][now.y] + 1;
						q.offer(new Loc(nx, ny, now.k+1));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		graph = new int[H][W];
		visited = new int[K+1][H][W];
		
		for(int k = 0; k < K+1; k++) {
			for(int i = 0; i < H; i++) {
				Arrays.fill(visited[k][i], -1);
			}
		}
	
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		int ans = Integer.MAX_VALUE;
		for(int k = 0; k < K+1; k++) {
			if(visited[k][H-1][W-1] != -1) {
				ans = Math.min(ans, visited[k][H-1][W-1]);
			}
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
	}

}