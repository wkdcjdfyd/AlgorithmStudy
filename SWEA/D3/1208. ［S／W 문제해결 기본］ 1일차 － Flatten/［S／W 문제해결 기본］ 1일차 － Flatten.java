import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 1.
 * @see  			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
 * @performance 
 * @category 		#구현
 * @note 
 */

public class Solution {
	final static int SIZE = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t < 11; t++) {
			int dump = Integer.parseInt(br.readLine());
			int[] height = new int[SIZE];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(height);
			
			while(dump > 0) {
				height[SIZE-1]--;
				height[0]++;
				dump--;
				Arrays.sort(height);
			}
			sb.append("#" + t + " " + (height[SIZE-1] - height[0]) + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}