import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 10.
 * @see  			https://www.acmicpc.net/problem/15651
 * @performance 	
 * @category 		#순열
 * @note 			
 */

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	public static void makeDupPerm(int nth, int[] choosed) {
		if(nth == M) {
			for(int i = 0; i < M; i++) {
				if(i == M-1) {
					sb.append(choosed[i] + "\n");
				}
				else {
					sb.append(choosed[i] + " ");
				}
			}
			return;
		}
		for(int i = 1; i <= N; i++) {
			choosed[nth] = i;
			makeDupPerm(nth+1, choosed);
		}
	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeDupPerm(0, new int[M]);
		
		System.out.println(sb.toString());
		br.close();
	}

}