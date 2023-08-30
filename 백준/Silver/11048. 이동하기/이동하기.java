import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 30.
 * @see  			https://www.acmicpc.net/problem/11048
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] visited;
	static int[] dx = {1, 0, 1};
	static int[] dy = {0, 1, 1};
	
	public static void bfs() {
		Deque<int[]> q = new ArrayDeque<>();
		visited[0][0] = graph[0][0];
		q.offer(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(visited[nx][ny] == -1 || visited[nx][ny] < visited[now[0]][now[1]] + graph[nx][ny]) {
					visited[nx][ny] = visited[now[0]][now[1]] + graph[nx][ny];
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
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = -1;
			}
		}
		
		bfs();
		
		System.out.println(visited[N-1][M-1]);
		br.close();
	}

}