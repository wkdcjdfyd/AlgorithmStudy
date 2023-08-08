import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 8.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
@performance	26020kb 173ms
@category 		#부르트포스 #조합
@note
*/

public class Solution {
	static int N;
	static int M;
	static int[] snacks;
	static StringBuilder sb = new StringBuilder();
	static int ans;
	
	public static void makeComb(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			int sum = 0;
			for(int idx : choosed) {
				sum += snacks[idx];
			}
			if(sum <= M && ans < sum) {
				ans = sum;
			}
			return;
		}
		for(int i = startIdx; i < N; i++) {
			choosed[nth] = i;
			makeComb(nth+1, i+1, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			snacks = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = -1;
			makeComb(0, 0, new int[2]);
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}