import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 28.
@see			https://www.acmicpc.net/problem/2798
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M;
	static int[] num;
	static int max = 0;
	
	public static void makeComb(int nth, int startIdx, int sum) {
		if(nth == 3) {
			max = Math.max(max, sum);
			return;
		}
		for(int i = startIdx; i < N; i++) {
			if(sum + num[i] > M) {
				continue;
			}
			makeComb(nth+1, i+1, sum + num[i]);
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
		
		makeComb(0, 0, 0);
		System.out.println(max);
		br.close();
	}

}