import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 30.
 * @see  			https://www.acmicpc.net/problem/11048
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		dp[0][0] = graph[0][0];
		for(int j = 1; j < M; j++) {
			dp[0][j] = dp[0][j-1] + graph[0][j];
		}
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0] + graph[i][0];
			for(int j = 1; j < M; j++) {
				dp[i][j] = graph[i][j] + Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		System.out.println(dp[N-1][M-1]);
		br.close();
	}

}