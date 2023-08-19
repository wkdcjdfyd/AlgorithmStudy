import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/1446
@performance	
@category 		#DP
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		HashMap<Integer, List<int[]>> shortcut = new HashMap<>();
		int[] dp = new int[D+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if(!shortcut.containsKey(to)) {
				shortcut.put(to, new ArrayList<>());
			}
			shortcut.get(to).add(new int[] {from, dist});
		}
		
		for(int i = 1; i <= D; i++) {
			int d = dp[i-1] + 1;
			
			if(shortcut.containsKey(i)) {
				for(int[] nxt : shortcut.get(i)) {
					d = Math.min(d, dp[nxt[0]] + nxt[1]);
				}
			}
			dp[i] = d;
		}
		
		System.out.println(dp[D]);
		br.close();
	}

}