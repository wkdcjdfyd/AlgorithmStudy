import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 31.
@see			https://www.acmicpc.net/problem/1863
@performance	
@category 		#Stack
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(!stack.isEmpty()) {
				if(stack.peek() < y) {
					stack.push(y);
				}
				else {
					while(!stack.isEmpty() && stack.peek() > y) {
						cnt++;
						stack.pop();
					}
					if(stack.isEmpty() || (!stack.isEmpty() && stack.peek() != y)){
						stack.push(y);
					}
				}
			}
			else {
				stack.push(y);
			}
		}
		while(!stack.isEmpty()) {
			if(stack.pop() > 0) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}

}