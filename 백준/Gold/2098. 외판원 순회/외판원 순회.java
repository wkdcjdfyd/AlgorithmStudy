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
	
	public static int tsp(int now, int visited) {
		if(visited == (1 << N) - 1) {
			if(graph[now][0] == 0) return MAX;
			return graph[now][0];
		}
		if(dp[now][visited] != -1) return dp[now][visited];
		dp[now][visited] = MAX;
		
		for(int i = 0; i < N; i++) {
			if(graph[now][i] == 0) continue;

			int nxtVisited = (visited | (1 << i));
			if(nxtVisited == visited) continue;
			
			int temp = tsp(i, nxtVisited) + graph[now][i];
			if(dp[now][visited] > temp) {
				dp[now][visited] = temp;
			}
		}
		return dp[now][visited];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dp = new int[N][1 << N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0, 1));
		br.close();
	}

}