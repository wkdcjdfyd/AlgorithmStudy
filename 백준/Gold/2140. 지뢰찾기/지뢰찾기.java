import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023. 9. 4.
 * @see  			https://www.acmicpc.net/problem/2140
 * @performance 	
 * @category 		#greedy
 * @note 			
 */

public class Main {
	static int N;
	static int[][] graph;
	static int[][] check;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void checkPoint(int x, int y, int mineCnt) {
		for(int d = 0 ; d < dx.length; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx <= 0 || ny <= 0 || nx >= N-1 || ny >= N-1) {
				continue;
			}
			if(check[nx][ny] == 1 && mineCnt != 0) {
				mineCnt--;
			}
			else if(check[nx][ny] == 0 && mineCnt != 0) {
				mineCnt--;
				check[nx][ny] = 1;
			}
			else if(check[nx][ny] == 0 && mineCnt == 0) {
				check[nx][ny] = -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		check = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if(c == '#') {
					graph[i][j] = -1;
				}
				else {
					graph[i][j] = c - '0';
				}
				if(i == 0 || j == 0 || i == N-1 || j == N-1) {
					check[i][j] = -1;
				}
			}
		}
		
		for(int j = 0; j < N; j++) {
			checkPoint(0, j, graph[0][j]);
		}
		for(int i = 1; i < N-1; i++) {
			checkPoint(i, 0, graph[i][0]);
			checkPoint(i, N-1, graph[i][N-1]);
		}
		for(int j = 0; j < N; j++) {
			checkPoint(N-1, j, graph[N-1][j]);
		}
		
		int ans = 0;
		for(int i = 1; i < N-1; i++) {
			for(int j = 1; j < N-1; j++) {
				if(check[i][j] == 1 || check[i][j] == 0) {
					ans++;
				}
			}
		}
		System.out.println(ans);
		br.close();
	}

}