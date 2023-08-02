import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 2.
@see			https://www.acmicpc.net/problem/11659
@performance		70964KB	620ms
@category 		#누적합
@note
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for(int n = 0; n < N; n++) {
			sum += Integer.parseInt(st.nextToken());
			arr[n] = sum;
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			if(i == 1) {
				sb.append(arr[j-1] + "\n");
			}
			else {
				sb.append((arr[j-1] - arr[i-2]) + "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
