import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 3.
 * @see  			https://www.acmicpc.net/problem/2212
 * @performance 	
 * @category 		#greedy
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] sensor = new int[N];
		Integer[] dist = new Integer[N-1];
		
		if(N <= K) {
			System.out.println(0);
			return;	
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sensor[i] = Integer.parseInt(st.nextToken());
		}		
		Arrays.sort(sensor);
		
		int sum = 0;
		for(int i = 0; i < N-1; i++) {
			dist[i] = sensor[i+1] - sensor[i];
			sum += dist[i];
		}
		
		Arrays.sort(dist, Collections.reverseOrder());
				
		for(int i = 0; i < K-1; i++) {
			sum -= dist[i];
		}
		
		System.out.println(sum);
		br.close();
	}

}