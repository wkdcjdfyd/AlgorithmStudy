import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			https://www.acmicpc.net/problem/13334
@performance	
@category 		#
@note			
*/

public class Main {
	
	static class Route implements Comparable<Route>{
		int s, e;
		
		public Route(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Route o) {
			return Integer.compare(e, o.e);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Route[] route = new Route[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(s < e) {
				route[i] = new Route(s, e);
			}
			else {
				route[i] = new Route(e, s);
			}
			
		}
		
		int D = Integer.parseInt(br.readLine());
		
		Arrays.sort(route);
		
		int cnt = 0;
		int ans = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(Route r : route) {
			while(!pq.isEmpty() && D < r.e - pq.peek()) {
				pq.poll();
				cnt--;
			}
			
			if(r.e - r.s <= D) {
				cnt++;
				pq.add(r.s);
			}
			
			ans = Math.max(ans, cnt);
		}
		
		System.out.println(ans);
		br.close();
	}

}