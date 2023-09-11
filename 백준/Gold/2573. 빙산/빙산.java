import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 12.
@see			https://www.acmicpc.net/problem/2573
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int[][] graph;
	static int[][] visited;
	static int iceCnt;
	
	public static void melt() {
		Deque<int[]> q = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] != 0) {
					int cnt = 0;
					for(int d = 0; d < dx.length; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if(!isValid(nx, ny)) continue;
						if(graph[nx][ny] == 0) cnt++;
					}
					q.offer(new int[] {i, j, cnt});
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(graph[now[0]][now[1]] <= now[2]) {
				graph[now[0]][now[1]] = 0;
				iceCnt--;
			}
			else {
				graph[now[0]][now[1]] -= now[2];
			}
		}
	}
	
	public static boolean isTwo() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] != 0 && visited[i][j] == 0) {
					searchIceberg(i, j, ++cnt);
				}
			}
		}
		
		if(cnt >= 2) {
			return true;
		}
		return false;
	}
	
	public static void searchIceberg(int x, int y, int num) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[x][y] = num;
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(!isValid(nx, ny)) continue;
				if(visited[nx][ny] != 0) continue;
				if(graph[nx][ny] == 0) continue;
				
				visited[nx][ny] = num;
				q.offer(new int[] {nx, ny});
			}
		}
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
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] != 0) {
					iceCnt++;
				}
			}
		}
		
		int time = 0;
		while(iceCnt > 0) {
			time++;
			
			melt();
			
			visited = new int[N][M];
			if(isTwo()) {
				System.out.println(time);
				break;
			}
			if(iceCnt == 0) {
				System.out.println(0);
				break;
			}
		}
		
		br.close();
	}

}