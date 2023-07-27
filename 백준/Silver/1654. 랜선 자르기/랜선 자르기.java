import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static long max;
	static int K;
	static int N;
	
	public static void binarySearch() {
		long start = 1;
		long end = (long)Integer.MAX_VALUE + (long)1;
		
		while(start < end) {
			long mid = start + (end - start) / 2;
			long cnt = 0;
			for(int i = 0; i < K; i++) {
				cnt += arr[i] / mid;
			}
			if(cnt < N) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		if(start-1 > max) {
			max = start-1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[K];
		
		for(int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		binarySearch();
		System.out.println(max);
		br.close();
	}

}
