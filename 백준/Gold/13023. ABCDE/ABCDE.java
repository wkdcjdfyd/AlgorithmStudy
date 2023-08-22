import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/13023
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static boolean flag;
	
	public static void dfs(int nth, int now) {
		if(nth == 4) {
			flag = true;
			return;
		}
		for(int nxt : graph.get(now)) {
			if(!visited[nxt]) {
				visited[nxt] = true;
				dfs(nth+1, nxt);
				visited[nxt] = false;
				if(flag) {
					return;
				}
			}
		}
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
				
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(0, i);
				visited[i] = false;
				
				if(flag) {
					break;
				}
			}
		}
		
		if(flag) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		br.close();
	}
}