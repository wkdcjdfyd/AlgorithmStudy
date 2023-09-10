import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 11.
@see			https://www.acmicpc.net/problem/1245
@performance	
@category 		#그래프탐색
@note			
*/

public class Main {
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M, cnt;
	
	public static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		boolean flag = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0 ; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(!isValid(nx, ny)) continue;
				
				if(graph[now[0]][now[1]] < graph[nx][ny]) {
					flag = false;
					continue;
				}
				if(graph[nx][ny] == 0 || visited[nx][ny]) continue;
				
				if(graph[now[0]][now[1]] == graph[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		if(flag) cnt++;
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && graph[i][j] != 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(cnt);
		br.close();
	}

}