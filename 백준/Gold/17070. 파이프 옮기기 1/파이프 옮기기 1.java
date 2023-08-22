import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/17070
@performance	
@category 		#dfs
@note			
*/

public class Main {
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int ans = 0;
	
	public static void dfs(int x, int y, int state) {
		if(x == N-1 && y == N-1) {
			ans++;
			return;
		}
		if(x == N-1 && state == 2) {
			return;
		}
		if(y == N-1 && state == 0) {
			return;
		}
		
		for(int d = 0; d < dx.length; d++) {
			if(Math.abs(state - d) == 2) {
				continue;
			}
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
				continue;
			}
			if(graph[nx][ny] == 1) {
				continue;
			}
			if(d == 1) {
				if(graph[nx-1][ny] == 1 || graph[nx][ny-1] == 1) {
					continue;
				}
			}

			visited[nx][ny] = true;
			dfs(nx, ny, d);
			visited[nx][ny] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0][0] = true;
		visited[0][1] = true;
		dfs(0, 1, 0);
		
		System.out.println(ans);
		br.close();
	}

}