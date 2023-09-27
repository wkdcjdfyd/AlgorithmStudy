import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 10.
 * @see  			https://www.acmicpc.net/problem/3055
 * @performance 
 * @category 		#BFS
 * @note 
 */

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int R;
	static int C;
	static int[][] visited;
	static char[][] graph;
	static Deque<Loc> water = new ArrayDeque<>();
	static Deque<Loc> hedgehog = new ArrayDeque<>();
	static Loc biber;
	
	static class Loc{
		int x;
		int y;
		
		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void spreadWater() {
		int size = water.size();
		
		while(--size >= 0) {
			Loc now = water.pollFirst();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				// 돌이나 비버 굴은 물이 찰 수 없음
				if(graph[nx][ny] == 'X' || graph[nx][ny] == 'D' || visited[nx][ny] == -1) {
					continue;
				}
				visited[nx][ny] = -1;
				water.offerLast(new Loc(nx, ny));
			}
		}
	}
	
	public static void move() {
		int size = hedgehog.size();
		
		while(--size >= 0) {
			Loc now = hedgehog.pollFirst();
			
			// 이동하려고 하는데 물에 잠겼으면 X
			if(visited[now.x][now.y] == -1) {
				continue;
			}
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				// 돌이나 물은 고슴도치가 갈 수 없음
				if(graph[nx][ny] == 'X' || visited[nx][ny] != 0) {
					continue;
				}
				visited[nx][ny] = visited[now.x][now.y] + 1;
				hedgehog.offerLast(new Loc(nx, ny));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		visited = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				char c = s.charAt(j);
				if(c == 'D') {
					biber = new Loc(i, j);
				}
				else if(c == 'S') {
					hedgehog.offerLast(new Loc(i, j));
					visited[i][j] = 1;
				}
				else if(c == '*') {
					water.offerLast(new Loc(i, j));
					visited[i][j] = -1;
				}
				graph[i][j] = c;
			}
		}
		
		while(visited[biber.x][biber.y] == 0) {
			if(water.isEmpty() && hedgehog.isEmpty()) {
				break;
			}
			move();
			spreadWater();
		}
		if(visited[biber.x][biber.y] == 0) {
			System.out.println("KAKTUS");
		}
		else {
			System.out.println(visited[biber.x][biber.y]-1);
		}
		br.close();
	}

}