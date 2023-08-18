import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/2178
@performance	
@category 		#그래프탐색
@note			
*/

public class Main {
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static int[][] graph;
	static int[][] visited;
	
	public static void bfs(int a, int b) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {a, b});
		visited[a][b] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] != 0) {
					continue;
				}
				if(graph[nx][ny] == 1) {
					visited[nx][ny] = visited[now[0]][now[1]] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = s.charAt(j) - '0';
			}
		}
		bfs(0, 0);
		System.out.println(visited[N-1][M-1]);
		br.close();
	}

}