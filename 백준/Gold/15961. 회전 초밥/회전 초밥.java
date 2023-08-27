import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 24.
@see			https://www.acmicpc.net/problem/15961
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, D, K, C;
	static int[] sushi;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		visited = new int[D+1];

		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int s = 0;
		int e = 0;
		int diffCnt = 1;
		int cnt = 1;
		int ans = 0;
		visited[sushi[s]]++;
		
		boolean coupon = false;
		if(sushi[s] == C) {
			coupon = true;
		}
		
		while(s < N) {
			if(cnt < K) {
				e = (e+1) % N;
				if(visited[sushi[e]] == 0) {
					diffCnt++;
				}
				if(sushi[e] == C) {
					coupon = true;
				}
				cnt++;
				visited[sushi[e]]++;
				ans = coupon ? Math.max(ans, diffCnt) : Math.max(ans, diffCnt+1);
			}
			else {
				if(visited[sushi[s]] == 1) {
					diffCnt--;
					if(sushi[s] == C) {
						coupon = false;
					}
				}
				visited[sushi[s]]--;
				cnt--;
				s++;
			}
		}
		System.out.println(ans);
		br.close();
	}

}