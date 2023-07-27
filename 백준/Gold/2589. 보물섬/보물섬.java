import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int M;
	static char[][] graph;
	static int ans;
	
	static class Land {
		int x;
		int y;
		
		public Land(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(int x, int y) {
		int[][] visited = new int[N][M];
		Queue<Land> q = new LinkedList<>();
		q.add(new Land(x, y));
		visited[x][y] = 1;
		int max = 0;
		
		while(!q.isEmpty()) {
			Land now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == 'W' || visited[nx][ny] > 0) {
					continue;
				}
				visited[nx][ny] = visited[now.x][now.y] + 1;
				max = visited[now.x][now.y] + 1;
				q.add(new Land(nx, ny));
			}
		}
		if(ans < max) {
			ans = max;
		}
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(ans-1);
		br.close();
	}

}