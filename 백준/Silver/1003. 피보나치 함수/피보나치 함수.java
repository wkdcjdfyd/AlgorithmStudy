import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 9. 13.
@see			https://www.acmicpc.net/problem/1003
@performance	
@category 		#
@note			
*/

public class Main {
	static int[][] dp = new int[41][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		dp[0][0] = 1;
		dp[1][1] = 1;
		
		for(int i = 2; i <= 40; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
		
		for(int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n][0] + " " + dp[n][1] + "\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}