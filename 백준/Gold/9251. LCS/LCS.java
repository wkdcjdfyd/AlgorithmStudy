import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023. 9. 16.
 * @see  			https://www.acmicpc.net/problem/9251
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		int[][] dp = new int[s1.length+1][s2.length+1];
		
		for(int i = 1; i < s1.length+1; i++) {
			for(int j = 1; j < s2.length+1; j++) {
				if(s1[i-1] == s2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[s1.length][s2.length]);
		br.close();
	}

}