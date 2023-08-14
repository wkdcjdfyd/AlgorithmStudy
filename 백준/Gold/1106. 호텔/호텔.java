import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 14.
@see			https://www.acmicpc.net/problem/1106
@performance	
@category 		#DP
@note			dp로 풀 생각을 못했다... dp문제 파악 능력이 필요해보인다.
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[C+100];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for(int j = customer; j < C+100; j++) {
				if(dp[j-customer] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], cost + dp[j-customer]);
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for(int i = C; i < C+100; i++) {
			ans = Math.min(ans, dp[i]);
		}
		
		System.out.println(ans);
		br.close();
	}

}