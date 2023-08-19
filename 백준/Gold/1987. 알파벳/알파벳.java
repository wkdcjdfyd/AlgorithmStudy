import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] graph;
	static boolean[][] visited;
	static boolean[] alpha = new boolean['Z' - 'A' + 1];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int ans;
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= R || y >= C) {
			return false;
		}
		return true;
	}
	
	public static void dfs(int x, int y, int cnt) {
		ans = Math.max(ans, cnt);
		
		for(int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!isValid(nx, ny)) {
				continue;
			}
			if(visited[nx][ny]) {
				continue;
			}
			if(alpha[graph[nx][ny] - 'A']) {
				continue;
			}
			alpha[graph[nx][ny] - 'A'] = true;
			visited[nx][ny] = true;
			dfs(nx, ny, cnt+1);
			alpha[graph[nx][ny] - 'A'] = false;
			visited[nx][ny] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		visited[0][0] = true;
		alpha[graph[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		
		System.out.println(ans);
		br.close();
	}

}