import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	public static long binarySearch(long x) {
		long s = 1;
		long e = 20_000_000_000L;
		long res = 0;
		
		while(s <= e) {
			long mid = (s + e) / 2;
			long val = mid * (mid + 1) / 2;
			
			if(val > x) {
				e = mid - 1;
			}
			else{
				res = mid;
				s = mid + 1;
			}
		}
		
		long val = res * (res + 1) / 2;
		
		if(val != x) {
			return -1;
		}
		
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			long N = Long.parseLong(br.readLine());
			
			sb.append(String.format("#%d %d\n", t, binarySearch(N)));
		}
		
		System.out.print(sb.toString());
		br.close();
	}

}