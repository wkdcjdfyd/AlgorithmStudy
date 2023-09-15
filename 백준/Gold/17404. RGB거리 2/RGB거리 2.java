import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 15.
@see			https://www.acmicpc.net/problem/17404
@performance	
@category 		#dp
@note			
*/

public class Main {
	static int[][] graph;
	static int N;
	
	public static int getMinCost(int start) {
		int[][] dp = new int[N][3];
		
		if(start == 0) {
			dp[1][0] = (int)1e8;
			dp[1][1] = graph[0][start] + graph[1][1];
			dp[1][2] = graph[0][start] + graph[1][2];
		}
		else if(start == 1) {
			dp[1][0] = graph[0][start] + graph[1][0];
			dp[1][1] = (int)1e8;
			dp[1][2] = graph[0][start] + graph[1][2];
		}
		else {
			dp[1][0] = graph[0][start] + graph[1][0];
			dp[1][1] = graph[0][start] + graph[1][1];
			dp[1][2] = (int)1e8;
		}
		
		for(int i = 2; i < N; i++) {
			dp[i][0] = graph[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
			dp[i][1] = graph[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
			dp[i][2] = graph[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			if(i == start) continue;
			min = Math.min(min, dp[N-1][i]);
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][3];
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) {
			min = Math.min(min, getMinCost(i));
		}
		
		System.out.println(min);
		br.close();
	}

}