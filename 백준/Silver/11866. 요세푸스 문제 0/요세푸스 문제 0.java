import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 26.
@see			https://www.acmicpc.net/problem/11866
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Deque<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			q.offer(i);
		}
	
		sb.append("<");
		while(true) {
			for(int i = 0; i < K-1; i++) {
				q.offer(q.poll());
			}
			int n = q.poll();
			sb.append(n);
			
			if(q.isEmpty()) {
				sb.append(">");
				break;
			}
			else {
				sb.append(", ");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}