import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 2.
 * @see  			https://www.acmicpc.net/problem/2240
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[][][] dp = new int[W+1][T+1][2];
		int tree = Integer.parseInt(br.readLine()) - 1;
		
		if(tree == 0) {
			dp[0][1][0] = 1;
		}
		else {
			if(W >= 1) {
				dp[1][1][1] = 1;
			}
		}
		
		for(int t = 2; t < T+1; t++) {
			tree = Integer.parseInt(br.readLine()) - 1;
			
			if(tree == 0) {
				dp[0][t][0] = dp[0][t-1][0] + 1;
				dp[0][t][1] = dp[0][t-1][0];
				for(int w = 1; w < W+1; w++) {
					dp[w][t][0] = Math.max(dp[w-1][t-1][1], dp[w][t-1][0])+1;
					dp[w][t][1] = Math.max(dp[w-1][t-1][0], dp[w][t-1][1]);
				}
			}
			else {
				for(int w = 1; w < W+1; w++) {
					dp[w][t][0] = Math.max(dp[w-1][t-1][1], dp[w][t-1][0]);
					dp[w][t][1] = Math.max(dp[w-1][t-1][0], dp[w][t-1][1])+1;				}
			}
		}
		
		int ans = 0;
		for(int w = 0; w < W+1; w++) {
			for(int i = 0; i < 2; i++) {
				ans = Math.max(ans, dp[w][T][i]);
			}
		}
		System.out.println(ans);
		br.close();
	}

}