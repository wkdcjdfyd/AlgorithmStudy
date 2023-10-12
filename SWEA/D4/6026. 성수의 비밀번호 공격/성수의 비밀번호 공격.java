import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 12.
@see			
@performance	
@category 		#수학
@note			참고 https://www.youtube.com/watch?v=wWZMYVSep0U
*/

public class Solution {
	
	static final int NUM =  1_000_000_007;
	static long[] factorial = new long[101];
	
	public static long pow(long a, long b) {
		if(b == 1) {
			return a;
		}
		
		long div = pow(a, b / 2);
		
		if(b % 2 == 0) {
			return div * div % NUM;
		}
		else {
			return (div * div % NUM) * a % NUM;
		}
	}
	
	public static void initFactorial() {
		factorial[0] = 1;
		factorial[1] = 1;
		
		for(int i = 2; i < factorial.length; i++) {
			factorial[i] = factorial[i-1] * i % NUM;
		}
	}
	
	public static long nCr(int n, int r) {
		if(r == n || r == 0) return 1;
		
		long n1 = pow(factorial[n-r], NUM-2);
		long n2 = pow(factorial[r], NUM-2);
		
		return factorial[n] * (n1 * n2 % NUM) % NUM;
	}
	
	// 포함 배제의 원리 : ∑(-1)^i * kCi * (k-i)^n
	public static int counter(int M, int N) {
		int cnt = 0;
		for(int i = 0; i <= M; i++) {
			int sign = (i % 2 == 0)?1:-1;
			long val1 = nCr(M, i);
			long val2 = pow(M-i, N);
			long val = (sign * val1 * val2) % NUM;
			cnt = (int)((cnt + val + NUM) % NUM);
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		initFactorial();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int ans = counter(M, N);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}