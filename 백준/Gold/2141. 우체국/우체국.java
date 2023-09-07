import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 8.
@see			https://www.acmicpc.net/problem/2141
@performance	
@category 		#greedy
@note			
*/

public class Main {
	static int N;
	static Town[] town;
	static long totalPopul;
	
	static class Town implements Comparable<Town>{
		int idx;
		int popul;
		
		public Town(int idx, int popul) {
			this.idx = idx;
			this.popul = popul;
		}
		
		@Override
		public int compareTo(Town o) {
			return Integer.compare(this.idx, o.idx);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		town = new Town[N];
		
		for(int i = 0 ; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			 int x = Integer.parseInt(st.nextToken());
			 int a = Integer.parseInt(st.nextToken());
			 town[i] = new Town(x, a);
			 totalPopul += a;
		}
		
		Arrays.sort(town);
		long mid = (totalPopul + 1) / 2;
		long sum = 0;
		
		for(int i = 0 ; i < N; i++) {
			 sum += town[i].popul;
			 if(mid <= sum) {
				 System.out.println(town[i].idx);
				 break;
			 }
		}
		br.close();
	}

}