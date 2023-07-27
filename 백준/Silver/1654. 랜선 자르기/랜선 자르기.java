import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int arr[];
	static int max;
	static int K;
	static int N;
	
	public static long binarySearch() {
		long start = 1;
		long end = arr[K-1];
		long max = 0;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			long cnt = 0;
			for(int i = 0; i < K; i++) {
				cnt += arr[i] / mid;
			}
			if(cnt < N) {
				end = mid - 1;
			}
			else {
				start = mid + 1;
				if(mid > max) {
					max = mid;
				}
			}
		}
		return max;
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
		Arrays.sort(arr);
		System.out.println(binarySearch());
		br.close();
	}

}
