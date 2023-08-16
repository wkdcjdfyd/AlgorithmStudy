import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 8. 16.
@see			https://www.acmicpc.net/problem/1992
@performance	
@category 		#DFS
@note			
*/

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] graph;
	
	public static void dfs(int x, int y, int size) {
		if(size == 1) {
			sb.append(graph[x][y]);
			return;
		}
		if(check(x, y, size)) {
			sb.append(graph[x][y]);
			return;
		}
		int nxtSize = size / 2;
		sb.append("(");
		dfs(x, y, nxtSize);
		dfs(x, y+nxtSize, nxtSize);
		dfs(x+nxtSize, y, nxtSize);
		dfs(x+nxtSize, y+nxtSize, nxtSize);
		sb.append(")");
	}
	
	public static boolean check(int x, int y, int size) {
		int val = graph[x][y];
		for(int i = x; i < x+size; i++) {
			for(int j = y; j < y+size; j++) {
				if(graph[i][j] != val) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				graph[i][j] = s.charAt(j) - '0';
			}
		}
		dfs(0, 0, N);
		
		System.out.println(sb.toString());
		br.close();
	}

}