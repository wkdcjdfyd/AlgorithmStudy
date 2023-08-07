import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 			Ryong
@since 				2023. 8. 7.
@see				https://www.acmicpc.net/problem/2493
@performance
@category 			#
@note
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] top = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			top[i] = Integer.parseInt(st.nextToken());
		}
		
		Deque<int[]> deque = new ArrayDeque<>();
		deque.offerLast(new int[] {1, top[1]});
		sb.append(0 + " ");
		
		MainLoop:
		for(int i = 2; i < N+1; i++) {
			while(!deque.isEmpty()) {
				int[] now = deque.pollLast();
				
				if(now[1] >= top[i]) {
					sb.append(now[0] + " ");
					deque.offerLast(now);
					deque.offerLast(new int[] {i, top[i]});
					continue MainLoop;
				}
			}
			sb.append(0 + " ");
			deque.offerLast(new int[] {i, top[i]});
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}