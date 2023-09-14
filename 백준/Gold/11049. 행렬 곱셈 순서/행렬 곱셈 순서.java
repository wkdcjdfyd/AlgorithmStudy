import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 15.
@see			https://www.acmicpc.net/problem/11049
@performance	
@category 		#dp
@note			
*/

public class Main {
	static int[][] dp = new int[501][501];
	static Matrix[] matrix = new Matrix[501];
	
	static class Matrix{
		int r, c;
		
		public Matrix(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static int dfs(int left, int right) {
		if(left == right - 1) {
			return dp[left][right] = matrix[left].r * matrix[left].c * matrix[right].c;
		}
		if(left == right) return dp[left][right] = 0;
		if(dp[left][right] != -1) return dp[left][right];
		
		dp[left][right] = Integer.MAX_VALUE;
		for(int i = left; i < right; i++) {
			dp[left][right] = Math.min(dfs(left, i) + dfs(i+1, right) + matrix[left].r * matrix[i].c * matrix[right].c, dp[left][right]);
		}
		
		return dp[left][right];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			matrix[i] = new Matrix(r, c);
		}
		
		for(int i = 0 ; i < 501; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		int result = dfs(1, N);
		
		System.out.println(result);
		br.close();
	}

}