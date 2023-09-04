import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 4.
@see			https://www.acmicpc.net/problem/1052
@performance	
@category 		#greedy
@note			
*/

public class Main {
	
	public static int calc(int n, int k) {
		if(n <= k) return 0;
		
		for(int i = 0 ; i < k-1; i++) {
			int e = 0;
			while(Math.pow(2, e) < n) {
				e++;
			}
			n -= Math.pow(2, e-1);
			
			if(n == 0) return 0;
		}
		
		int e = 0;
		while(Math.pow(2, e) < n) {
			e++;
		}
		return (int)Math.pow(2, e) - n;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int ans = calc(N, K);
		
		System.out.println(ans);
		br.close();
	}

}