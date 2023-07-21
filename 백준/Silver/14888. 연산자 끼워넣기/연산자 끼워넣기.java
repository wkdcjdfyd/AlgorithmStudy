import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<int[]> q = new LinkedList<>();
	
	static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
		if(depth == r) {
			q.add(Arrays.copyOf(output, output.length));
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		int[] op = new int[N-1];
		int idx = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			int temp = Integer.parseInt(st.nextToken());
			for(int j = 0; j < temp; j++) {
				op[idx++] = i;
			}
		}
		
		permutation(op, new int[N-1], new boolean[N-1], 0, N-1, N-1);
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			int temp = arr[0];
			int nowIdx = 1;
			int[] now = q.poll();
			
			for(int nxt: now) {
				if(nxt == 0) {
					temp += arr[nowIdx++];
				}
				else if(nxt == 1) {
					temp -= arr[nowIdx++];
				}
				else if(nxt == 2) {
					temp *= arr[nowIdx++];
				}
				else {
					temp /= arr[nowIdx++];
				}
			}
			
			if(temp > max) {
				max = temp;
			}
			if(temp < min) {
				min = temp;
			}
		}
		
		System.out.println(max);
		System.out.println(min);
	}

}