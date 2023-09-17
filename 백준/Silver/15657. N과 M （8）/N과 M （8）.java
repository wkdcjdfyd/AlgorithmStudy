import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 17.
 * @see  			https://www.acmicpc.net/problem/15657
 * @performance 	
 * @category 		#중복조합
 * @note 			
 */

public class Main {
	static int N, M;
	static int[] num;
	static StringBuilder sb = new StringBuilder();
	
	public static void makeComb(int nth, int startIdx, int[] choosed) {
		if(nth == M) {
			for(int n : choosed) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = startIdx; i < N; i++) {
			choosed[nth] = num[i];
			makeComb(nth+1, i, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		
		makeComb(0, 0, new int[M]);
		
		System.out.println(sb.toString());
		br.close();
	}

}