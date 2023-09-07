import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 7.
 * @see  			https://www.acmicpc.net/problem/2812
 * @performance 	
 * @category 		#스택
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N];
		String s = br.readLine();
		for(int i = 0; i < N; i++) {
			num[i] = s.charAt(i) - '0';
		}
		
		Deque<Integer> stack = new ArrayDeque<>();
		int idx = 0;
		int cnt = 0;
		stack.offerLast(num[idx++]);
		
		while(cnt < K) {
			if(!stack.isEmpty()) {
				while(!stack.isEmpty() && stack.peekLast() < num[idx]) {
					stack.pollLast();
					if(++cnt == K) break;
				}
				stack.offerLast(num[idx]);
			}
			else {
				stack.offerLast(num[idx]);
			}
			
			if(++idx == N) {
				break;
			}
		}
		
		if(cnt < K) {
			int numCnt = 0;
			for(int n: stack) {
				sb.append(n);
				if(++numCnt == N - K) break;
			}
			System.out.println(sb.toString());
		}
		else {
			for(int n: stack) {
				sb.append(n);
			}
			for(int i = idx; i < N; i++) {
				sb.append(num[i]);
			}
			System.out.println(sb.toString());
		}
		
		br.close();
	}

}