import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 28.
 * @see  			https://www.acmicpc.net/problem/1890
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {
	static int[][] graph;
	static int N;
	static long[][] dp; 
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dp = new long[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int d = 0; d < dx.length; d++) {
					if(i == N-1 && j == N-1) continue;
					
					int nx = i + dx[d] * graph[i][j];
					int ny = j + dy[d] * graph[i][j];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}
					dp[nx][ny] += dp[i][j];
				}
			}
		}

		System.out.println(dp[N-1][N-1]);
		br.close();
	}

}