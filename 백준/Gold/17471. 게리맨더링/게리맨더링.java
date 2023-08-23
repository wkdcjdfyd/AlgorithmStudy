import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/17471
@performance	
@category 		#
@note			
*/

public class Main {
	static int N;
	static int[] popul;
	static int[][] graph;
	static HashSet<Integer> group3 = new HashSet<>();
	static int min = 10000;
	
	public static void makeSubset(int nth, boolean[] choosed) {		
		if(nth == N+1) {
			HashSet<Integer> g1 = new HashSet<>();
			HashSet<Integer> g2 = new HashSet<>();
			int sum1 = 0;
			int sum2 = 0;
			
			for(int i = 1; i < N+1; i++) {
				if(choosed[i]) {
					g1.add(i);
					sum1 += popul[i];
				}
				else {
					g2.add(i);
					sum2 += popul[i];
				}
			}
			
			if(check(g1) && check(g2)) {
				min = Math.min(min, Math.abs(sum1-sum2));
			}
			
			return;
		}
		choosed[nth] = true;
		makeSubset(nth+1, choosed);
		choosed[nth] = false;
		makeSubset(nth+1, choosed);
	}
	
	public static boolean check(HashSet<Integer> group) {
		boolean[] visited = new boolean[N+1];
		Deque<Integer> q = new ArrayDeque<>();
		for(int n : group) {
			q.offer(n);
			visited[n] = true;
			break;
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for(int nxt : graph[now]) {
				if(visited[nxt]) {
					continue;
				}
				if(group.contains(nxt)) {
					visited[nxt] = true;
					q.offer(nxt);
				}
			}
		}
		
		for(int n : group) {
			if(!visited[n]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][];
		
		popul = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			graph[i] = new int[len];
			
			for(int j = 0; j < len; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeSubset(1, new boolean[N+1]);
		
		if(min == 10000) {
			System.out.println(-1);
		}
		else {
			System.out.println(min);
		}
		br.close();
	}

}