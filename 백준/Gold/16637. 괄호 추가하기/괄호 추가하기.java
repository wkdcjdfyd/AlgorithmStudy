import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023. 8. 27.
 * @see  			https://www.acmicpc.net/problem/16637
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {
	static int[] nums;
	static char[] ops; 
	static int N;
	static int max = Integer.MIN_VALUE;
	
	public static void dfs(int nth, int sum, boolean before) {
		if(nth == (N/2)+1) {
			if(before) {
				sum = calc(ops[nth-2], sum, nums[nth-1]);
			}
			max = Math.max(max, sum);
			return;
		}
		
		if(!before) {
			dfs(nth+1, sum, true);
			dfs(nth+1, calc(ops[nth-1], sum, nums[nth]), false);
		}
		else {
			if(nth != 1) {
				dfs(nth+1, calc(ops[nth-2], sum, calc(ops[nth-1], nums[nth-1], nums[nth])), false);
			}
			else {
				dfs(nth+1, calc(ops[nth-1], nums[nth-1], nums[nth]), false);
			}
		}
	}
	
	public static int calc(char op, int x, int y) {
		switch(op){
			case '+':
				return x + y;
			case '*':
				return x * y;
			case '-':
				return x - y;
		}
		return x;
			
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[(N/2)+1];
		ops = new char[N/2];
		
		String s = br.readLine();
		for(int i = 0 ; i < N-1; i=i+2) {
			nums[i/2] = s.charAt(i) - '0';
			ops[i/2] = s.charAt(i+1);
		}
		nums[N/2] = s.charAt(N-1) - '0';
		
		if(N == 1) {
			max = nums[0];
		}
		else if(N == 3) {
			max = calc(ops[0], nums[0], nums[1]);
		}
		else {
			dfs(1, nums[0], false);
			dfs(1, nums[1], true);
		}
		
		System.out.println(max);
		br.close();
	}

}