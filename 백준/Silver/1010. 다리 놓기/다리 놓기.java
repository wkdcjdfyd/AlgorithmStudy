import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 17.
 * @see  			https://www.acmicpc.net/problem/1010
 * @performance 	
 * @category 		#조합
 * @note 			조합의 성질을 활용해 계산해서 팩토리얼을 사용하지 않고
 * 					int 표현범위 안에서 계산하는 방법을 알 수 있었다.
 * 					재귀호출은 하지 않고 반복문 형태로 구현해야 한다.
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] dp = new int[30][30];
		
		for(int i = 0; i < 30; i++) {
			dp[i][i] = 1;
			dp[i][0] = 1;
		}
		
		for(int i = 2; i < 30; i++) {
			for(int j = 1; j < 30; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(String.format("%d\n", dp[M][N]));
		}
		System.out.println(sb.toString());
		br.close();
	}

}