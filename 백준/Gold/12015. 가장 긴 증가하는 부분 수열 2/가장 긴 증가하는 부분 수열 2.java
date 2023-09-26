import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 26.
@see			https://www.acmicpc.net/problem/12015
@performance	
@category 		#LIS
@note			
*/

public class Main {
	static int N;
	static int[] arr;
	static int[] dp;
	
	public static int binarySearch(int s, int e, int key) {
		while(s < e) {
			int mid = (s + e) / 2;
			if(dp[mid] < key) {
				s = mid+1;
			}
			else {
				e = mid;
			}
		}
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int len = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] > dp[len]) {
				dp[++len] = arr[i];
			}
			else {
				int idx = binarySearch(0, len, arr[i]);
				dp[idx] = arr[i];
			}
		}
		
		System.out.println(len);
		br.close();
	}

}