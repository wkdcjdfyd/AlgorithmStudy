import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 31.
@see			https://www.acmicpc.net/problem/2294
@performance	
@category 		#dp
@note			
*/

public class Main {
	static final int INF = (int)1e9+1;
	static int N, K;
	static int[] coin;
	static int[] dp;
	
	public static int counter(int cost) {
		if(cost <= 0) {
			return INF;
		}
		if(dp[cost] != INF) return dp[cost];
		
		for(int i = 0; i < N; i++) {
			dp[cost] = Math.min(counter(cost-coin[i]) + 1, dp[cost]);
		}
		return dp[cost];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N];
		dp = new int[K+1];
		
		Arrays.fill(dp, INF);
		for(int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			if(coin[i] < K+1) {
				dp[coin[i]] = 1;
			}
		}
		
		for(int cost = 1; cost < K+1; cost++) {
			if(dp[cost] == INF) continue;
			for(int c : coin) {
				if(cost + c < K+1) {
					dp[cost + c] = Math.min(dp[cost + c], dp[cost] + 1); 
				}
			}
		}
		
		if(dp[K] == INF) System.out.println(-1);
		else System.out.println(dp[K]);
		
		br.close();
	}

}