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
	static int[][] graph;
	static Queue<int[]> q = new LinkedList<>();
	static int ans;
	
	public static void bfs(int x, int y) {
		int[][] visited = new int[N][M];
		Queue<int[]> bfsQ = new LinkedList<>();
		bfsQ.add(new int[] {x, y});
		visited[x][y] = 1;
		int max = 0;
		
		while(!bfsQ.isEmpty()) {
			int[] now = bfsQ.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == -1 || visited[nx][ny] > 0) {
					continue;
				}
				visited[nx][ny] = visited[now[0]][now[1]] + 1;
				max = visited[now[0]][now[1]] + 1;
				bfsQ.add(new int[] {nx, ny});
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
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M;j++) {
				if(s.charAt(j) == 'W') {
					graph[i][j] = -1;
				}
				else {
					graph[i][j] = 0;
					q.add(new int[] {i, j});
				}
			}
		}
		while(!q.isEmpty()) {
			int[] now = q.poll();
			bfs(now[0], now[1]);
		}
		System.out.println(ans-1);
		br.close();
	}

}