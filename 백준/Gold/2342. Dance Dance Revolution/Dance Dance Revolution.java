import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 14.
 * @see  			https://www.acmicpc.net/problem/2342
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = st.countTokens();
		
		if(N == 1) {
			System.out.println(0);
			br.close();
			return;
		}
		
		int[] num = new int[N-1];
		int[][][] dp = new int[N][5][5];
		
		for(int i = 0; i < N-1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1][num[0]][0] = 2;
		dp[1][0][num[0]] = 2;
		
		for(int n = 1; n < N-1; n++) {
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					if(dp[n][i][j] != 0) {
						if(num[n] != j) {
							if(num[n] == i) {
								if(dp[n+1][num[n]][j] == 0) {
									dp[n+1][num[n]][j] = dp[n][i][j] + 1;
								}
								else {
									dp[n+1][num[n]][j] = Math.min(dp[n+1][num[n]][j], dp[n][i][j] + 1);
								}
							}
							else if(i == 0) {
								if(dp[n+1][num[n]][j] == 0) {
									dp[n+1][num[n]][j] = dp[n][i][j] + 2;
								}
								else {
									dp[n+1][num[n]][j] = Math.min(dp[n+1][num[n]][j], dp[n][i][j] + 2);
								}
							}
							else if(Math.abs(num[n] - i) == 1 || Math.abs(num[n] - i) == 3) {
								if(dp[n+1][num[n]][j] == 0) {
									dp[n+1][num[n]][j] = dp[n][i][j] + 3;
								}
								else {
									dp[n+1][num[n]][j] = Math.min(dp[n+1][num[n]][j], dp[n][i][j] + 3);
								}
							}
							else if(Math.abs(num[n] - i) == 2) {
								if(dp[n+1][num[n]][j] == 0) {
									dp[n+1][num[n]][j] = dp[n][i][j] + 4;
								}
								else {
									dp[n+1][num[n]][j] = Math.min(dp[n+1][num[n]][j], dp[n][i][j] + 4);
								}
							}
						}
						if(num[n] != i) {
							if(num[n] == j) {
								if(dp[n+1][i][num[n]] == 0) {
									dp[n+1][i][num[n]] = dp[n][i][j] + 1;
								}
								else {
									dp[n+1][i][num[n]] = Math.min(dp[n+1][i][num[n]], dp[n][i][j] + 1);
								}
							}
							else if(j == 0) {
								if(dp[n+1][i][num[n]] == 0) {
									dp[n+1][i][num[n]] = dp[n][i][j] + 2;
								}
								else {
									dp[n+1][i][num[n]] = Math.min(dp[n+1][i][num[n]], dp[n][i][j] + 2);
								}
							}
							else if(Math.abs(num[n] - j) == 1 || Math.abs(num[n] - j) == 3) {
								if(dp[n+1][i][num[n]] == 0) {
									dp[n+1][i][num[n]] = dp[n][i][j] + 3;
								}
								else {
									dp[n+1][i][num[n]] = Math.min(dp[n+1][i][num[n]], dp[n][i][j] + 3);
								}
							}
							else if(Math.abs(num[n] - j) == 2) {
								if(dp[n+1][i][num[n]] == 0) {
									dp[n+1][i][num[n]] = dp[n][i][j] + 4;
								}
								else {
									dp[n+1][i][num[n]] = Math.min(dp[n+1][i][num[n]], dp[n][i][j] + 4);
								}
							}
						}
					}
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(dp[N-1][i][j] != 0) {
					ans = Math.min(ans, dp[N-1][i][j]);
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}