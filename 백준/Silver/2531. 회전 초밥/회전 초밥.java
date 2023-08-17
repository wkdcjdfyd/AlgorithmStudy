import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/2531
@performance	
@category 		#투포인터
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] visited = new int[d+1];
		int[] sushi = new int[N];
		for(int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		int s = 0;
		int e = 0;
		int diffCnt = 1;
		int cnt = 1;
		visited[sushi[s]]++;
		
		boolean coupon = false;
		if(sushi[e] == c) {
			coupon = true;
		}
		
		int ans = 0;
		while(s < N) {
			// k보다 종류가 적으면 e 전진
			if(cnt < k) {
				e = (e+1) % N;
				if(visited[sushi[e]] == 0) {
					diffCnt++;
				}
				if(sushi[e] == c) {
					coupon = true;
				}
				cnt++;
				visited[sushi[e]]++;
				ans = coupon ? Math.max(ans, diffCnt) : Math.max(ans, diffCnt+1);
			}
			// k만큼 있으면 s전진
			else {
				if(visited[sushi[s]] == 1) {
					diffCnt--;
					if(sushi[s] == c) {
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