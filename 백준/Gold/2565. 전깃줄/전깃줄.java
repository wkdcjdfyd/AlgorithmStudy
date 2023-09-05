import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 5.
 * @see  			https://www.acmicpc.net/problem/2565
 * @performance 	
 * @category 		#dp
 * @note 			
 */

public class Main {

	static class Line implements Comparable<Line>{
		int from;
		int to;
		
		public Line(int from, int to) {
			this.from = from;
			this.to = to;
		}

		@Override
		public int compareTo(Line o) {
			if(this.from == o.from) {
				return Integer.compare(this.to, o.to);
			}
			return Integer.compare(this.from, o.from);
		}				
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Line[] lines = new Line[N];
		int[] dp = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			lines[i] = new Line(from, to);
		}
		
		Arrays.sort(lines);
		Arrays.fill(dp, 1);
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(lines[i].to > lines[j].to) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(N - ans);
		br.close();
	}

}