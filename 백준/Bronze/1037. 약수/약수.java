import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 5.
@see			https://www.acmicpc.net/problem/1037
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		if(N == 1) {
			System.out.println(arr[0] * arr[0]);
		}
		else {
			Arrays.sort(arr);
			System.out.println(arr[0] * arr[N-1]);
		}
		br.close();
	}

}