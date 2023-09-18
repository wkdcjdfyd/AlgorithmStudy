import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 19.
@see			https://www.acmicpc.net/problem/17836
@performance	
@category 		#bfs
@note			
*/

public class Main {
	static int N, M, T;
	static int[][] graph;
	static int[][] visited;
	static int[] sword = new int[2];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void bfs(int a, int b) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[a][b] = 1;
		q.offer(new int[] {a, b});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == -1 || visited[nx][ny] != 0) {
					continue;
				}
				visited[nx][ny] = visited[now[0]][now[1]] + 1;
				q.offer(new int[] {nx, ny});
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = -Integer.parseInt(st.nextToken());
				if(graph[i][j] == -2) {
					sword[0] = i;
					sword[1] = j;
				}
			}
		}
		
		bfs(0, 0);
		
		int time = -1;
		if(visited[N-1][M-1] != 0 && visited[sword[0]][sword[1]] != 0) {
			time = Math.min(visited[N-1][M-1] - 1, visited[sword[0]][sword[1]] - 1 + N - 1 - sword[0] + M - 1 - sword[1]);
		}
		else if (visited[N-1][M-1] != 0 && visited[sword[0]][sword[1]] == 0){
			time = visited[N-1][M-1] - 1;
		}
		else if(visited[N-1][M-1] == 0 && visited[sword[0]][sword[1]] != 0) {
			time = visited[sword[0]][sword[1]] - 1 + N - 1 - sword[0] + M - 1 - sword[1];
		}
		
		if(time == -1 || time > T) {
			System.out.println("Fail");
		}
		else {
			System.out.println(time);
		}
		br.close();
	}

}