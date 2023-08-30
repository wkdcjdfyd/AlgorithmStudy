import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 30.
 * @see  			https://www.acmicpc.net/problem/2096
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {
	static int N;
	static int[][] graph;
	static int[][] dp1;
	static int[][] dp2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][3];
		dp1 = new int[N][3];
		dp2 = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 3; i++) {
			dp1[0][i] = graph[0][i];
			dp2[0][i] = graph[0][i];
		}
		
		for(int i = 1; i < N; i++) {
			dp1[i][0] = graph[i][0] + Math.max(dp1[i-1][0], dp1[i-1][1]);
			dp1[i][1] = graph[i][1] + Math.max(dp1[i-1][0], Math.max(dp1[i-1][1], dp1[i-1][2]));
			dp1[i][2] = graph[i][2] + Math.max(dp1[i-1][1], dp1[i-1][2]);
			
			dp2[i][0] = graph[i][0] + Math.min(dp2[i-1][0], dp2[i-1][1]);
			dp2[i][1] = graph[i][1] + Math.min(dp2[i-1][0], Math.min(dp2[i-1][1], dp2[i-1][2]));
			dp2[i][2] = graph[i][2] + Math.min(dp2[i-1][1], dp2[i-1][2]);
		}
		
		int max = Math.max(dp1[N-1][0], Math.max(dp1[N-1][1], dp1[N-1][2]));
		int min = Math.min(dp2[N-1][0], Math.min(dp2[N-1][1], dp2[N-1][2]));
		
		System.out.println(max + " " + min);
		br.close();
	}

}