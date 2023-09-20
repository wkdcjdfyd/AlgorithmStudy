import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 20.
 * @see  			https://www.acmicpc.net/problem/1194
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
	static char[][] graph;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Set<Character> key = new HashSet<>(Arrays.asList(new Character[] {'a', 'b', 'c', 'd', 'e', 'f'}));
	static Set<Character> door = new HashSet<>(Arrays.asList(new Character[] {'A', 'B', 'C', 'D', 'E', 'F'}));
	static int N, M;
	static int ans = Integer.MAX_VALUE;
	
	static class Loc{
		int x, y, moveCnt;
		int haskey;
		
		public Loc(int x, int y, int moveCnt, int haskey) {
			this.x = x;
			this.y = y;
			this.moveCnt = moveCnt;
			this.haskey = haskey;
		}
	}
	
	public static void bfs(Loc start) {
		Deque<Loc> q = new ArrayDeque<>();
		visited[0][start.x][start.y] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			Loc now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(graph[nx][ny] == '1') {
					ans = Math.min(ans, now.moveCnt + 1);
					continue;
				}
				if(graph[nx][ny] == '#') continue;
				if(visited[now.haskey][nx][ny]) continue;

				if(key.contains(graph[nx][ny])) {
					int k = graph[nx][ny] - 'a';
					if(((now.haskey>>k) & 1) == 0) {
						int newkey = ((now.haskey) | (1<<k));
						visited[newkey][nx][ny] = true;
						q.offer(new Loc(nx, ny, now.moveCnt+1, newkey));
						continue;
					}
				}
				else if(door.contains(graph[nx][ny])) {
					char key = Character.toLowerCase(graph[nx][ny]);
					int k = key - 'a';

					if(((now.haskey>>k) & 1) == 0) continue;
				}
				visited[now.haskey][nx][ny] = true;
				q.offer(new Loc(nx, ny, now.moveCnt+1, now.haskey));
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		visited = new boolean[1<<6][N][M];
		
		Loc start = null;
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = s.charAt(j);
				if(graph[i][j] == '0') {
					start = new Loc(i, j, 0, 0);
				}
			}
		}
		
		bfs(start);
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
		
		br.close();
	}
}