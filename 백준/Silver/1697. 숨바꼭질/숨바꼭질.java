import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 21.
@see			https://www.acmicpc.net/problem/1697
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0);
			return;
		}

		int[] visited = new int[100001];

		Deque<Integer> q = new ArrayDeque<>();
		
		q.offer(N);
		visited[N] = 1;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			
			for(int i=0; i<3; i++) {
				int next;
				if(i == 0) {
					next = num + 1;
				}
				else if(i == 1) {
					next = num - 1;
				}
				else {
					next = num * 2;
				}
				if(next == K) {
					System.out.println(visited[num]);
					return;
				}
				if(next >=0 && next < visited.length && visited[next] == 0) {
					q.offer(next);
					visited[next] = visited[num] + 1;
				}
			}
		}
			
	}

}