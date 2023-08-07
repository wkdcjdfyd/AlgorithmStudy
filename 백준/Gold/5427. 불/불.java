import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 7.
 * @see  			https://www.acmicpc.net/problem/5427
 * @performance 
 * @category 		#
 * @note 
 */

public class Main {
	static final int MAX = (int)1e7;
	static StringBuilder sb = new StringBuilder();
	static int R;
	static int C;
	static ArrayList<Loc> fireLoc;
	static Loc person;
	static int[][] fireVisited;
	static int[][] personVisited;
	static int[][] graph;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Loc {
		int x;
		int y;
		
		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(boolean isFire, int[][] visited) {
		Deque<Loc> q = new LinkedList<>();
		
		if(isFire){
			for(Loc fire : fireLoc) {
				q.offerLast(fire);
				visited[fire.x][fire.y] = 1;
			}
		}
		else {
			q.offerLast(person);
			visited[person.x][person.y] = 1;
		}
		
		while(!q.isEmpty()) {
			Loc now = q.pollFirst();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				if(graph[nx][ny] != 0 || visited[nx][ny] != 0) {
					continue;
				}
				visited[nx][ny] = visited[now.x][now.y] + 1;
				q.offerLast(new Loc(nx, ny));
			}
			
		}
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			fireVisited = new int[R][C];
			personVisited = new int[R][C];
			graph = new int[R][C];
			fireLoc = new ArrayList<>();
			
			for(int i = 0; i < R; i++) {
				String s = br.readLine();
				for(int j = 0; j < C; j++) {
					if(s.charAt(j) == '#') {
						graph[i][j] = MAX;
					}
					else if(s.charAt(j) == '@') {
						graph[i][j] = 1;
						person = new Loc(i, j);
					}
					else if(s.charAt(j) == '*') {
						graph[i][j] = -1;
						fireLoc.add(new Loc(i, j));
					}
					else {
						graph[i][j] = 0;
					}
				}
			}
			
			bfs(true, fireVisited);
			bfs(false, personVisited);
			
			int min = MAX;
			
			// 세로 첫번째 확인
			for(int i = 0; i < R; i++) {
				// 탈출 못함
				if(personVisited[i][0] == 0) {
					continue;
				}
				if(fireVisited[i][0] == 0 || fireVisited[i][0] > personVisited[i][0]) {
					min = Math.min(min, personVisited[i][0]);
				}
			}
			
			// 가로 첫번째 확인
			for(int i = 0; i < C; i++) {
				if(personVisited[0][i] == 0) {
					continue;
				}
				if(fireVisited[0][i] == 0 || fireVisited[0][i] > personVisited[0][i]) {
					min = Math.min(min, personVisited[0][i]);
				}
			}
			
			// 세로 마지막 확인
			for(int i = 0; i < R; i++) {
				if(personVisited[i][C-1] == 0) {
					continue;
				}
				if(fireVisited[i][C-1] == 0 || fireVisited[i][C-1] > personVisited[i][C-1]) {
					min = Math.min(min, personVisited[i][C-1]);
				}
			}
			
			// 가로 마지막 확인
			for(int i = 0; i < C; i++) {
				if(personVisited[R-1][i] == 0) {
					continue;
				}
				if(fireVisited[R-1][i] == 0 || fireVisited[R-1][i] > personVisited[R-1][i]) {
					min = Math.min(min, personVisited[R-1][i]);
				}
			}
			
			if(min == MAX) {
				sb.append("IMPOSSIBLE\n");
			}
			else {
				sb.append(min + "\n");
			}
			
		}
		System.out.println(sb.toString());
		br.close();
	}
	
}