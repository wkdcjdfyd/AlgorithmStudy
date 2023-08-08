import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
@author 			Ryong
@since 				2023. 8. 8.
@see				https://www.acmicpc.net/problem/12851
@performance
@category 			#
@note
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0 + "\n" + 1);
			return;
		}
		else if(K < N) {
			System.out.println((N-K) + "\n" + 1);
			return;
		}
		
		int[] dp = new int[(K*2) + 1];
		Arrays.fill(dp, (int)1e7);
		int[] cnt = new int[(K*2) + 1];
		
		Deque<int[]> q = new LinkedList<>();
		q.offerLast(new int[] {N, 0});
		dp[N] = 0;
		cnt[N] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.pollFirst();
			
			if(now[0]-1 >= 0) {
				if(dp[now[0]-1] == dp[now[0]] + 1) {
					cnt[now[0]-1] += cnt[now[0]];
				}
				else if(dp[now[0]-1] > dp[now[0]] + 1) {
					dp[now[0]-1] = dp[now[0]] + 1;
					cnt[now[0]-1] = cnt[now[0]];
					q.offerLast(new int[] {now[0]-1, dp[now[0]] + 1});
				}
			}
			
			if(now[0]+1 <= K*2) {
				if(dp[now[0]+1] == dp[now[0]] + 1) {
					cnt[now[0]+1] += cnt[now[0]];
				}
				else if(dp[now[0]+1] > dp[now[0]] + 1) {
					dp[now[0]+1] = dp[now[0]] + 1;
					cnt[now[0]+1] = cnt[now[0]];
					q.offerLast(new int[] {now[0]+1, dp[now[0]] + 1});
				}
			}
			
			if(now[0]*2 <= K*2) {
				if(dp[now[0]*2] == dp[now[0]] + 1) {
					cnt[now[0]*2] += cnt[now[0]];
				}
				else if(dp[now[0]*2] > dp[now[0]] + 1) {
					dp[now[0]*2] = dp[now[0]] + 1;
					cnt[now[0]*2] = cnt[now[0]];
					q.offerLast(new int[] {now[0]*2, dp[now[0]] + 1});
				}
			}
		}
		
		System.out.println(dp[K] + "\n" + cnt[K]);
		br.close();
	}

}