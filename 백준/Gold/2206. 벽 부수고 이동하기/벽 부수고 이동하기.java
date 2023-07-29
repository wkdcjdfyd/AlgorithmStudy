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
	static boolean[][][] visited;
	
	static class Loc {
		int x;
		int y;
		int cnt;
		boolean isBreak;
		
		public Loc(int x, int y, int cnt, boolean isBreak) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isBreak = isBreak;
		}
	}

	public static void bfs() {
		Queue<Loc> q = new LinkedList<>();
		q.add(new Loc(0, 0, 1, false));
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		
		while(!q.isEmpty()) {
			Loc now = q.poll();
			
			if(now.x == N-1 && now.y == M-1) {
				System.out.println(now.cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(graph[nx][ny] == '0') {
					if(!now.isBreak && !visited[nx][ny][0]) {
						q.add(new Loc(nx, ny, now.cnt + 1, false));
						visited[nx][ny][0] = true;
					}
					else if(now.isBreak && !visited[nx][ny][1]) {
						q.add(new Loc(nx, ny, now.cnt + 1, true));
						visited[nx][ny][1] = true;
					}
				}
				else if(graph[nx][ny] == '1') {
					if(!now.isBreak) {
						q.add(new Loc(nx, ny, now.cnt + 1, true));
						visited[nx][ny][1] = true;
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		bfs();
		br.close();
	}

}