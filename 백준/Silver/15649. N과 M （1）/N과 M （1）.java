import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int[] nums;
	
	public static void makePermutation(int depth, int[] choosed, boolean[] visited) {
		if(depth == choosed.length) {
			for(int n : choosed) {
				sb.append(n + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i < N+1; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[depth] = nums[i];
				makePermutation(depth+1, choosed, visited);
				visited[i] = false;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			nums[i] = i;
		}
		makePermutation(0, new int[M], new boolean[N+1]);
		System.out.println(sb.toString());
		br.close();
	}

}
