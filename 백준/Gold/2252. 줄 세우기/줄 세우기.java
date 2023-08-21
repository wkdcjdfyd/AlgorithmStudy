import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 21.
@see			https://www.acmicpc.net/problem/2252
@performance	
@category 		#위상정렬
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] indegree = new int[N+1];
		Deque<Integer> q = new ArrayDeque<>();
		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		
		for(int i = 0; i < N+1; i++) {
			edge.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edge.get(from).add(to);
			indegree[to]++;
		}
		
		for(int i = 1; i < N+1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			sb.append(now + " ");
			
			for(int nxt : edge.get(now)) {
				indegree[nxt]--;
				if(indegree[nxt] == 0) {
					q.offer(nxt);
				}
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}