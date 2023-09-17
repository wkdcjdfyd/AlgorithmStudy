import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 18.
@see			https://www.acmicpc.net/problem/1932
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j <= i; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				if(j-1 >= 0) {
					graph[i][j] += Math.max(graph[i-1][j-1], graph[i-1][j]);
				}
				else {
					graph[i][j] += graph[i-1][j];
				}
			}
		}
		
		int ans = -1;
		for(int j = 0; j < n; j++) {
			ans = Math.max(ans, graph[n-1][j]);
		}
		
		System.out.println(ans);
		br.close();
	}

}