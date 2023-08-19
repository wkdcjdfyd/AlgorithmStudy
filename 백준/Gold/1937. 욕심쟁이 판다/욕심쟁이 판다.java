import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/1937
@performance	
@category 		#DFS
@note			dfs 탐색 순서의 반대 방향으로 메모지에이션을 해주는 방법을 사용하자
*/

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int[][] graph;
	static int[][] visited;
	
	public static void dfs(int x, int y) {
		int moveMax = 0;
		
		for(int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			if(graph[x][y] >= graph[nx][ny]) {
				continue;
			}
			if(visited[nx][ny] != 0) {
				moveMax = Math.max(visited[nx][ny], moveMax);
				continue;
			}
			dfs(nx, ny);
			moveMax = Math.max(visited[nx][ny], moveMax);
		}
		visited[x][y] = moveMax + 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] == 0) {
					dfs(i, j);
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				ans = Math.max(ans, visited[i][j]);
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}