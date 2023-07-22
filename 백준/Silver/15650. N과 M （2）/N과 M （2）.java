import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			saveString(arr, visited);
			return;
		}
		for(int i = start; i < n; i++) {
			visited[i] = true;
			combination(arr, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	
	static void saveString(int[] arr, boolean[] visited) {
		for(int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				sb.append(arr[i] + " ");
			}
		}
		sb.append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = i+1;
		}
		combination(nums, new boolean[N], 0, N, M);
		System.out.println(sb.toString());
	}

}