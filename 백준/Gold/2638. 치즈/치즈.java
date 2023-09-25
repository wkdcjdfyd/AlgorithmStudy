import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 26.
@see			https://www.acmicpc.net/problem/2638
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M, cheeseCnt;
	static int[][] graph;
	static int[][] visited;
	static Deque<int[]> meltReady = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void findMelt(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			int airCnt = 0;
			for(int d = 0 ; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == 0 && visited[nx][ny] == -1) {
					airCnt++;
				}
				if(graph[nx][ny] == 1 && visited[nx][ny] == 0) {
					visited[nx][ny] = 1;
					q.offer(new int[] {nx, ny});
				}
			}
			if(airCnt >= 2) {
				meltReady.offer(new int[] {now[0], now[1]});
			}
		}
	}
	
	public static void melt() {
		while(!meltReady.isEmpty()) {
			int[] now = meltReady.poll();
			
			if(graph[now[0]][now[1]] == 1) {
				graph[now[0]][now[1]] = 0;
				cheeseCnt--;
			}
		}
	}
	
	public static void check_air(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = -1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0 ; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == 0 && visited[nx][ny] == 0) {
					visited[nx][ny] = -1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 1) cheeseCnt++;
			}
		}
		
		int time = 0;
		
		while(cheeseCnt > 0) {
			time++;
			
			visited = new int[N][M];
			check_air(0, 0);

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(graph[i][j] == 1 && visited[i][j] == 0) {
						findMelt(i, j);
					}
				}
			}
			melt();
		}
		
		System.out.println(time);
		br.close();
	}

}