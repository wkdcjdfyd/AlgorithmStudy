import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 11.
@see			https://www.acmicpc.net/problem/19539
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		int cnt1 = 0;
		int cnt2 = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			if(h[i] % 2 == 1) {
				cnt1++;
			}
			cnt2 += h[i] / 2;
		}
		
		if(cnt1 == cnt2) {
			System.out.println("YES");
			return;
		}
		
		while(cnt1 < cnt2) {
			cnt2--;
			cnt1 += 2;
			if(cnt1 == cnt2) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
		br.close();
	}

}