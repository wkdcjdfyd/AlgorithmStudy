import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			https://www.acmicpc.net/problem/11051
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][K+1];
		
		for(int i = 0; i <= K; i++) {
			dp[i][i] = 1;
		}
		
		for(int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % 10007;
			}
		}
		
		System.out.println(dp[N][K]);
		br.close();
	}

}