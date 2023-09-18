import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 18.
 * @see  			https://www.acmicpc.net/problem/11053
 * @performance 	
 * @category 		#dp
 * @note 			
 */


public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		int ans = 1;
		for(int i = 1; i < N; i++) {
			int max = -1;
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j]) {
					max = Math.max(max, dp[j]);
				}
			}
			if(max != -1) {
				dp[i] = max + 1;
				ans = Math.max(ans, dp[i]);
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}