import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 19.
@see			https://www.acmicpc.net/problem/20058
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, Q;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int iceCnt = 0;
	
	public static void fireStorm(int L) {
		int scale = (int)Math.pow(2, L);
		
		for(int x = 0; x < N; x += scale) {
			for(int y = 0; y < N; y += scale) {
				rotate(x, y, scale);
			}
		}
		
		melt();
	}
	
	public static void rotate(int x, int y, int scale) {
		int[][] temp = new int[scale][scale];
		
		for(int i = 0; i < scale; i++) {
			for(int j = 0; j < scale; j++) {
				temp[j][scale- 1 - i] = graph[i+x][j+y];
			}
		}
		
		for(int i = x; i < x+scale; i++) {
			for(int j = y; j < y+scale; j++) {
				graph[i][j] = temp[i-x][j-y];
			}
		}
	}
	
	public static void melt() {
		Deque<int[]> q = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(graph[i][j] <= 0) continue;
				
				int cnt = 0;
				for(int d = 0; d < dx.length; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}
					if(graph[nx][ny] > 0) cnt++;
				}
				
				if(cnt < 3) q.offer(new int[] {i, j});
			}
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			graph[now[0]][now[1]]--;
			iceCnt--;
		}
	}
	
	public static int getIceSize(int a, int b) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[a][b] = true;
		q.offer(new int[] {a,  b});
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				if(graph[nx][ny] <= 0 || visited[nx][ny]) {
					continue;
				}
				visited[nx][ny] = true;
				cnt++;
				q.offer(new int[] {nx, ny});
			}
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		N = (int)Math.pow(2, N);
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				iceCnt += graph[i][j];
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			fireStorm(L);
		}
		
		int maxSize = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && graph[i][j] > 0) {
					int size = getIceSize(i, j);
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		
		System.out.println(iceCnt);
		System.out.println(maxSize);
		br.close();
	}
	
}