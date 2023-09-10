import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 10.
 * @see  			https://www.acmicpc.net/problem/2467
 * @performance 	
 * @category 		#이분탐색
 * @note 			
 */

public class Main {
	static int N;
	static int[] val;
	static int ansX = -1;
	static int ansY = -1;
	static int abs = Integer.MAX_VALUE;
	
	public static void search() {
		int s = 0;
		int e = N-1;
		
		while(s < e) {
			int sum = val[s] + val[e];
			
			if(Math.abs(sum) < abs) {
				abs = Math.abs(sum);
				ansX = val[s];
				ansY = val[e];
			}
			
			if(sum >= 0) e--;
			else s++;
		}
	}	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		val = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			val[i] = Integer.parseInt(st.nextToken());
		}
		
		search();
		
		System.out.println(ansX + " " + ansY);
		br.close();
	}

}