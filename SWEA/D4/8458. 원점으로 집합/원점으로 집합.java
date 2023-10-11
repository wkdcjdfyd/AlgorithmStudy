import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			
@performance	
@category 		#
@note			저는 스스로 구현하지 못하고 민우햄의 코드를 참조했습니다...
				많이 배워갑니다...
*/

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			int N = Integer.parseInt(br.readLine());
			int dist = Integer.MIN_VALUE;
			int odd = 0;
			int even = 0;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Math.abs(x) + Math.abs(y);
				dist = Math.max(dist, d);
				
				if(d % 2 == 0) even++;
				else odd++;
			}
			
			if(even != 0 && odd != 0) {
				sb.append("#").append(t).append(" ").append(-1).append("\n");
				continue;
			}
			
			int nth = 0;
			while(dist != 0) {
				nth++;
				if(dist >= nth) {
					dist -= nth;
				}
				else {
					dist = (nth - dist) % 2;
				}
			}
			
			sb.append("#").append(t).append(" ").append(nth).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}