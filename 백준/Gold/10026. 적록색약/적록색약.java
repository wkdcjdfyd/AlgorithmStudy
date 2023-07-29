import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static char[][] graph;
	static boolean[][] visited;
	
	static class Cor {
		int x;
		int y;
		
		public Cor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(int x, int y, boolean isSick) {
		Queue<Cor> q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new Cor(x, y));	
		
		while(!q.isEmpty()) {
			Cor now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				if(visited[nx][ny]) {
					continue;
				}
				if(!isSick) {
					if(graph[x][y] == graph[nx][ny]) {
						visited[nx][ny] = true;
						q.add(new Cor(nx, ny));
					}
				}
				else {
					if(graph[x][y] == 'R' || graph[x][y] == 'G') {
						if(graph[nx][ny] == 'B') {
							continue;
						}
						visited[nx][ny] = true;
						q.add(new Cor(nx, ny));
					}
					else {
						if(graph[x][y] == graph[nx][ny]) {
							visited[nx][ny] = true;
							q.add(new Cor(nx, ny));
						}
					}
				}
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		graph = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		int cnt = 0;
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					++cnt;
					bfs(i, j, false);
				}
			}
		}
		sb.append(cnt + " ");
		
		cnt = 0;
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					++cnt;
					bfs(i, j, true);
				}
			}
		}
		sb.append(cnt);
		System.out.println(sb.toString());
		br.close();
	}

}