import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 11.
@see			https://www.acmicpc.net/problem/3040
@performance
@category 		#브루트포스
@note
*/

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int[] nums = new int[9];
	
	public static void makeComb(int nth, int startIdx, int[] choosed, int sum) {
		if(nth == choosed.length) {
			if(sum == 100) {
				for(int idx : choosed) {
					sb.append(String.format("%d\n", nums[idx]));
				}
			}
			return;
		}
		for(int i = startIdx; i < 9; i++) {
			if(sum + nums[i] <= 100) {
				choosed[nth] = i;
				makeComb(nth+1, i+1, choosed, sum+nums[i]);
			}
		}
	}
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		makeComb(0, 0, new int[7], 0);
		System.out.println(sb.toString());
		br.close();
	}

}