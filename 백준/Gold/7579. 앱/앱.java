import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 15.
 * @see  			https://www.acmicpc.net/problem/7579
 * @performance 	
 * @category 		#DP
 * @note 			dp 기본부터 공부해보자.
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] memories = new int[N];
		int[] costs = new int[N];
		int[][] dp = new int[N][N*100+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			memories[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int cost = costs[i];
			int memory = memories[i];
			
			for(int j = 0; j < N*100 + 1; j++) {
				if(i == 0) {
					if(j >= cost) {
						dp[i][j] = memory;
					}
				}
				else {
					if(j >= cost) {
						dp[i][j] = Math.max(dp[i-1][j-cost] + memory, dp[i-1][j]);
					}
					else {
						dp[i][j] = dp[i-1][j];
					}
				}
				if(dp[i][j] >= M) {
					ans = Math.min(ans, j);
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}