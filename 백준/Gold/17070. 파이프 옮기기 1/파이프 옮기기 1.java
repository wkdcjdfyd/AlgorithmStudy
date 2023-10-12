import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 12.
@see			https://www.acmicpc.net/problem/17069
@performance	
@category 		#dp
@note			
*/

public class Main {
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};
	static int N;
	static int[][] graph;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][N+1];
		dp = new long[3][N+1][N+1];
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N+1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][1][2] = 1;
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				if((i == 1 && j == 2) || graph[i][j] == 1) {
					continue;
				}
				for(int state = 0; state < 3; state++) {
					if(state == 0) {
						dp[state][i][j] = dp[0][i][j-1] + dp[1][i][j-1];
					}
					else if(state == 1) {
						if(graph[i-1][j] == 1 || graph[i][j-1] == 1) continue;
						dp[state][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
					}
					else {
						dp[state][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
					}
				}
			}
		}
		
		long ans = 0;
		for(int i = 0; i < 3; i++) {
			ans += dp[i][N][N];
		}
		
		System.out.println(ans);
		br.close();
	}

}