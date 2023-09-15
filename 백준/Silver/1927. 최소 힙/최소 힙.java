import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
@author 		Ryong
@since 			2023. 9. 15.
@see			https://www.acmicpc.net/problem/1927
@performance	
@category 		#자료구조
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			if(x == 0) {
				if(!pq.isEmpty()) {
					sb.append(pq.poll() + "\n");
				}
				else {
					sb.append(0 + "\n");
				}
			}
			else {
				pq.offer(x);
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}