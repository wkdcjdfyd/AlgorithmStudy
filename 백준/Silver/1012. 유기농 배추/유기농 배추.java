import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	static int M;
	static int N;
	static int K;
	static int[][] graph;
	static int[][] visited;
	
	static void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = 1;
		graph[x][y] = cnt;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(graph[nx][ny] == -1 && visited[nx][ny] == 0) {
						visited[nx][ny] = 1;
						graph[nx][ny] = cnt;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			graph = new int[N][M];
			visited = new int[N][M];
			int[][] loc = new int[K][2];
			int cnt = 0;
			
			// 배추의 위치 표시
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph[y][x] = -1;
				loc[k][0] = y;
				loc[k][1] = x;
			}
			
			for(int[] cord: loc) {
				if(graph[cord[0]][cord[1]] == -1) {
					bfs(cord[0], cord[1], ++cnt);
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}