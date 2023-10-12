import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 12.
@see			https://www.acmicpc.net/problem/28324
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		int N = Integer.parseInt(br.readLine());
		int[] limit = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i  = 0; i < N; i++) {
			limit[i] = Integer.parseInt(st.nextToken());
		}
		
		long ans = 0;
		int nowMax = 0;
		for(int i = N-1; i >= 0; i--) {
			if(nowMax + 1 <= limit[i]) {
				nowMax++;
				ans += nowMax;
			}
			else {
				ans += limit[i];
				nowMax = limit[i];
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}