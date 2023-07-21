import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Integer[] nums;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
		if(depth == r) {
			calc(output);
			return;
		}
		for(int i = 0; i < n; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, visited, depth+1, n, r);
				output[depth] = 0;
				visited[i] = false;
			}
		}
	}
	
	public static void calc(int[] nowOp) {
		int result = nums[0];
		int nowIdx = 1;
		
		for(int nxt: nowOp) {
			if(nxt == 0) {
				result += nums[nowIdx++];
			}
			else if(nxt == 1) {
				result -= nums[nowIdx++];
			}
			else if(nxt == 2) {
				result *= nums[nowIdx++];
			}
			else {
				result /= nums[nowIdx++];
			}
		}
		
		if(result > max) {
			max = result;
		}
		if(result < min) {
			min = result;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		nums = new Integer[N];
		int[] op = new int[N-1];
		int idx = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			int temp = Integer.parseInt(st.nextToken());
			for(int j = 0; j < temp; j++) {
				op[idx++] = i;
			}
		}
		
		permutation(op, new int[N-1], new boolean[N-1], 0, N-1, N-1);
		
		System.out.println(max);
		System.out.println(min);
	}

}