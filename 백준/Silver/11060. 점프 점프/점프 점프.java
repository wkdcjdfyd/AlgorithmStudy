import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 29.
@see			https://www.acmicpc.net/problem/11060
@performance	
@category 		#dp
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] graph = new int[N];
		int[] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, 10000);
		dp[0] = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j <= i + graph[i]; j++) {
				if(j >= N) break;
				dp[j] = Math.min(dp[i]+1, dp[j]);
			}
		}
		
		if(dp[N-1] == 10000) {
			System.out.println(-1);
		}
		else {
			System.out.println(dp[N-1]);
		}
		br.close();
	}

}