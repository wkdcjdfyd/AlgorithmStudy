import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 30.
@see			https://www.acmicpc.net/problem/2098
@performance	
@category 		#dp #외판원순회
@note			
*/

public class Main {
	static final int MAX = 17 * (int)1e6;
	static int N;
	static int[][] graph;
	static int[][] dp;
	static int ans = MAX;
	
	public static void tsp(int now, int visited) {
		if(visited == (1 << N) - 1) {
			if(graph[now][0] == 0) return;
			ans = Math.min(ans, dp[now][visited] + graph[now][0]);
			return;
		}
		for(int i = 0; i < N; i++) {
			int nxt = (1 << i);
			int nxtVisited = (visited | nxt);
			if(nxtVisited == visited) continue;
			if(graph[now][i] == 0) continue;
			if(dp[now][visited] + graph[now][i] < dp[i][nxtVisited]) {
				dp[i][nxtVisited] = dp[now][visited] + graph[now][i];
				tsp(i, nxtVisited);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dp = new int[N][1 << N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], MAX);
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][1] = 0;
		tsp(0, 1);
		
		System.out.println(ans);
		br.close();
	}

}