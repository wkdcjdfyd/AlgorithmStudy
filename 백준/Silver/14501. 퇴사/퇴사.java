import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][3];
		int[] dp = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
			arr[i][0] = i + arr[i][1] - 1;
			arr[i][1] = i;
		}
		Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
		
		int max = 0;
		for(int i = 1; i < arr.length; i++) {
			if(arr[i][0] < N+1) {
				if(arr[i][1] == 1) {
					dp[arr[i][0]] = Math.max(arr[i][2], dp[arr[i][0]]);
					max = Math.max(max, dp[arr[i][0]]);
				}
				else {
					dp[arr[i][0]] = Math.max(arr[i][2] + dp[arr[i][1] - 1], dp[arr[i][0]]);
					max = Math.max(max, dp[arr[i][0]]);
				}
				if(arr[i][0] < arr.length - 1) {
					for(int j = arr[i][0]; j < arr[i+1][0]; j++) {
						if(j > N) {
							break;
						}
						dp[j] = max;
					}
				}
			}
		}
		System.out.println(max);
		br.close();
	}

}
