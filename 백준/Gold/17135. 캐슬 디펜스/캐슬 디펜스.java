import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/17135
@performance	
@category 		#구현 #BFS
@note			
*/

public class Main {
	static int N, M, D;
	static int[] dx = {0, -1, 0};
	static int[] dy = {-1, 0, 1};
	static int[][] graph;
	static int[][] origin;
	static Deque<int[]> enemy = new ArrayDeque<>();
	static ArrayList<int[]> archerComb = new ArrayList<>();
	static Archer[] archers = new Archer[3];
	static int enemyCnt = 0;
	
	static class Archer {
		int x;
		int y;
		int killCnt;
		
		public Archer(int x, int y) {
			this.x = x;
			this.y = y;
			this.killCnt = 0;
		}
		
		public void findNearEnemy() {
			if(graph[x-1][y] == 1) {
				enemy.offer(new int[] {x-1, y});
				return;
			}
			
			Deque<int[]> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][M];
			q.offer(new int[] {x-1, y, 1});
			visited[x-1][y] = true;
			
			while(!q.isEmpty()) {
				int[] now = q.poll();
				if(now[2] == D) {
					continue;
				}
				
				for(int i = 0; i < dx.length; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
						continue;
					}
					if(graph[nx][ny] == 1 && now[2] + 1 <= D) {
						enemy.offer(new int[] {nx, ny});
						return;
					}
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny, now[2] + 1});
				}
			}
		}
	}
	
	public static int attack() {
		int killCnt = 0;
		
		while(!enemy.isEmpty()) {
			int[] now = enemy.poll();
			
			if(graph[now[0]][now[1]] == 1) {
				graph[now[0]][now[1]] = 0;
				killCnt++;
			}
		}
		return killCnt;
	}
	
	public static void makeArcherComb(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			archerComb.add(Arrays.copyOf(choosed, choosed.length));
			return;
		}
		for(int i = startIdx; i < M; i++) {
			choosed[nth] = i;
			makeArcherComb(nth+1, i+1, choosed);
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				graph[i][j] = origin[i][j];
			}
		}
	}
	
	public static void moveEnemy() {
		for(int i = N-2; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				graph[i+1][j] = graph[i][j];
			}
		}
		for(int j = 0; j < M; j++) {
			graph[0][j] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		origin = new int[N+1][M];
		graph = new int[N+1][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				if(origin[i][j] == 1) {
					enemyCnt++;
				}
			}
		}
		
		// 가능한 궁수 위치 조합 찾기
		makeArcherComb(0, 0, new int[3]);
		
		int ans = 0;
		for(int[] loc : archerComb) {
			copy();
			for(int i = 0; i < 3; i++) {
				archers[i] = new Archer(N, loc[i]);
			}
			
			int killCounter = 0;
			int turn = 0;
			while(killCounter < enemyCnt) {
				// 적 찾기
				for(int i = 0; i < 3; i++) {
					archers[i].findNearEnemy();
				}
				// 공격
				killCounter += attack();
				turn++;
				if(turn > N) {
					break;
				}
				
				// 적 이동
				moveEnemy();
			}
			ans = Math.max(ans, killCounter);
		}
		System.out.println(ans);
		br.close();
	}

}