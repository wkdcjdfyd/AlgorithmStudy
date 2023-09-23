import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 23.
 * @see  			https://www.acmicpc.net/problem/1113
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	
	public static void bfs(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		Deque<int[]> change = new ArrayDeque<>();
		visited[x][y] = true;
		q.offer(new int[] {x, y});
		change.offer(new int[] {x, y});
		int height = 10;
		boolean isWater = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					isWater = false;
					continue;
				}
				if(graph[now[0]][now[1]] < graph[nx][ny]) {
					height = Math.min(graph[nx][ny], height);
					continue;
				}
				if(graph[now[0]][now[1]] > graph[nx][ny]) {
					isWater = false;
					continue;
				}
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					cnt++;
					q.offer(new int[] {nx, ny});
					change.offer(new int[] {nx, ny});
				}
			}
		}
		
		if(isWater) {
			ans += (height - graph[x][y]) * cnt;
			
			while(!change.isEmpty()) {
				int[] now = change.poll();
				graph[now[0]][now[1]] = height;
				visited[now[0]][now[1]] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = s.charAt(j) - '0';
			}
		}
		
		for(int i = 1; i < 8; i++) {
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < M; y++) {
					if(graph[x][y] == i && !visited[x][y]) {
						bfs(x, y);
					}
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}