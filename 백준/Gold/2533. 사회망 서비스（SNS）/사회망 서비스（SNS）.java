import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 16.
 * @see  			https://www.acmicpc.net/problem/2533
 * @performance 	
 * @category 		#트리dp
 * @note 			
 */

public class Main {
	static int N;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[][] dp;
	static boolean[] visited;
	
	public static void find(int x) {
		visited[x] = true;
		dp[x][0] = 1;
		
		for(int child : graph.get(x)) {
			if(visited[child]) continue;
			
			find(child);
			dp[x][1] += dp[child][0];
			dp[x][0] += Math.min(dp[child][0], dp[child][1]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		
		for(int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		find(1);
		int ans = Math.min(dp[1][0], dp[1][1]);
		
		System.out.println(ans);
		br.close();
	}
	
}