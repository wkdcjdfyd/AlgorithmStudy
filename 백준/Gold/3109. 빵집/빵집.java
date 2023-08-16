import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 16.
@see			https://www.acmicpc.net/problem/3109
@performance	
@category 		#DFS
@note			
*/

public class Main {
	static int R;
	static int C;
	static int[] dx = {-1, 0, 1};
	static char[][] graph;
	static boolean[][] visited;
	static boolean flag;
	static int ans;
	
	public static void dfs(int nth, int[] choosed, boolean isStart, int startIdx) {
		if(nth == C) {
			flag = true;
			ans++;
			return;
		}
		
		if(isStart) {
			choosed[nth] = startIdx;
			visited[startIdx][nth] = true;
			dfs(nth+1, choosed, false, startIdx);
			return;
		}
		else {
			for(int i = 0; i < 3; i++) {
				int nxt = choosed[nth-1] + dx[i];
				
				if(nxt < 0 || nxt >= R) {
					continue;
				}
				if(graph[nxt][nth] == 'x' || visited[nxt][nth]) {
					continue;
				}
				choosed[nth] = nxt;
				visited[nxt][nth] = true;
				dfs(nth+1, choosed, false, startIdx);
				if(flag) {
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0; i < R; i++) {
			flag = false;
			dfs(0, new int[C], true, i);
		}
		
		System.out.println(ans);
		br.close();
	}

}