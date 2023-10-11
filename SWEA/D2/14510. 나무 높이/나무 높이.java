import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AYFofW8qpXYDFAR4
@performance	
@category 		#
@note			
*/

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] tree = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int target = 0;
			for(int i = 0; i < N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				target = Math.max(tree[i], target);
			}
			
			int one = 0;
			int two = 0;
			for(int i = 0; i < N; i++) {
				tree[i] = target - tree[i];
				if(tree[i] != 0) {
					one += tree[i] % 2;
					two += tree[i] / 2;
				}
			}
			
			int ans = 0;
			if(one == two) {
				ans += one * 2;
			}
			else if(one > two) {
				ans += two * 2;
				ans +=  2 * (one - two - 1) + 1;
			}
			else if(one < two) {
				ans += one * 2;
				ans += (((two - one)*2) / 3) * 2;
				if(((two - one)*2) % 3 == 1) {
					ans++;
				}
				else if(((two - one)*2) % 3 == 2) {
					ans += 2;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}