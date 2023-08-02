import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int X;
	static int M;
	static int L;
	static int R;
	static int S;
	static int[][] records;
	static int[] nums;
	static int[] result;
	static boolean flag;
	static int max;
	
	public static void makePerm(int nth, int[] choosed) {
		if(nth == N) {
			int[] subSum = new int[N+1];
			for(int i = 1; i < N+1; i++) {
				subSum[i] = subSum[i-1] + choosed[i-1];
			}
			for(int m = 0; m < M; m++) {
				if(subSum[records[m][1]] - subSum[records[m][0]-1] != records[m][2]) {
					return;
				}
			}
			int sum = 0;
			for(int num : choosed) {
				sum += num;
			}
			if(max < sum) {
				flag = true;
				max = sum;
				for(int i = 0; i < choosed.length; i++) {
					result[i] = choosed[i];
				}
			}
			return;
		}
		for(int i = 0; i < nums.length; i++) {
			choosed[nth] = nums[i];
			makePerm(nth+1, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			records = new int[M][3];
			
			nums = new int[X+1];
			for(int i = 0; i < X+1; i++) {
				nums[i] = i;
			}
			
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				records[m][0] = Integer.parseInt(st.nextToken());
				records[m][1] = Integer.parseInt(st.nextToken());
				records[m][2] = Integer.parseInt(st.nextToken());
			}
			max = -100;
			flag = false;
			result = new int[N];
			makePerm(0, new int[N]);
			
			if(flag) {
				for(int num : result) {
					sb.append(num + " ");
				}
				sb.append("\n");
			}
			else {
				sb.append(-1 + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}