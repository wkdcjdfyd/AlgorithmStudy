import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int N;
	static int M;
	static int[][] graph;
	static int[][] visited;
	static Queue<int[]> q = new LinkedList<>();
	
	public static void moveRainCloud(int d, int s) {
		for(int[] now: q) {
			now[0] = convLoc(now[0] + dx[d] * s);
			now[1] = convLoc(now[1] + dy[d] * s);
			graph[now[0]][now[1]]++;
		}
	}
	
	public static void copyWaterBug() {
		while(!q.isEmpty()) {
			int[] now = q.poll();
			visited[now[0]][now[1]] = 1;
			int cnt = 0;
			
			for(int k = 2; k < 9; k = k + 2) {
				int nx = now[0] + dx[k];
				int ny = now[1] + dy[k];
				
				if(isValid(nx, ny) && graph[nx][ny] > 0) {
					cnt++;
				}
			}
			graph[now[0]][now[1]] += cnt;
		}
	}
	
	public static int convLoc(int x) {
		int nx = 0;
		if(x < 1) {
			nx = (x % N) + N;
		}
		else if(x > N) {
			nx = x % N;
			if(nx == 0) {
				nx = N;
			}
		}
		else {
			nx = x;
		}
		return nx;
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 1 || x > N || y < 1 || y > N) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N+1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		q.add(new int[] {N, 1});
		q.add(new int[] {N, 2});
		q.add(new int[] {N-1, 1});
		q.add(new int[] {N-1, 2});
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			visited = new int[N+1][N+1];
			
			moveRainCloud(d, s);
			copyWaterBug();
			
			for(int x = 1; x <= N; x++) {
				for(int y = 1; y <= N; y++) {
					if(graph[x][y] >= 2 && visited[x][y] != 1) {
						graph[x][y] -= 2;
						q.add(new int[] {x, y});
					}
				}
			}
		}
		int result = 0;
		
		for(int x = 1; x <= N; x++) {
			for(int y = 1; y <= N; y++) {
				result += graph[x][y];
			}
		}
		System.out.println(result);
		br.close();
	}

}