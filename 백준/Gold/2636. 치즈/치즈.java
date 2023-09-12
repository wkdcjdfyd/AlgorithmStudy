import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 13.
@see			https://www.acmicpc.net/problem/2636
@performance	
@category 		#
@note			
*/

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] graph;
	static int[][] visited;
	static Deque<int[]> meltReady = new ArrayDeque<>();
	static int R, C, cheeseCnt;
	
	public static void findMelt(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			boolean meltFlag = false;
			for(int d = 0 ; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				if(graph[nx][ny] == 0 && visited[nx][ny] == -1) {
					meltFlag = true;
				}
				if(graph[nx][ny] == 1 && visited[nx][ny] == 0) {
					visited[nx][ny] = 1;
					q.offer(new int[] {nx, ny});
				}
			}
			if(meltFlag) {
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
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				if(graph[nx][ny] == 0 && visited[nx][ny] == 0) {
					visited[nx][ny] = -1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 1) cheeseCnt++;
			}
		}
		
		int prevCnt = cheeseCnt;
		int time = 0;
		
		while(cheeseCnt > 0) {
			prevCnt = cheeseCnt;
			time++;
			
			visited = new int[R][C];
			check_air(0, 0);

			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(graph[i][j] == 1 && visited[i][j] == 0) {
						findMelt(i, j);
					}
				}
			}
			melt();
		}
		
		System.out.println(time);
		System.out.println(prevCnt);
		br.close();
	}

}