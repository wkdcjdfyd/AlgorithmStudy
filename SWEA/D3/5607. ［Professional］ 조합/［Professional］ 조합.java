import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo
@performance	
@category 		#
@note			
*/

public class Solution {
	static final long NUM = 1234567891;
	
	public static long pow(long a, long b) {
		if(b == 1) return a;
		
		long div = pow(a, b/2);
		
		if(b % 2 == 0) {
			return div * div % NUM;
		}
		else {
			return div * div % NUM * a % NUM;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			int temp = Math.min(R, N-R);
			long a = 1;
			long b = 1;
			for(int i = 0; i < temp; i++) {
				a = a * (N-i) % NUM;
				b = b * (i+1) % NUM;
			}
			
			long val = (a % NUM * pow(b, NUM-2) % NUM) % NUM;
			
			sb.append("#").append(t).append(" ").append(val).append("\n");
		}
		System.out.println(sb);
		br.close();
	}

}