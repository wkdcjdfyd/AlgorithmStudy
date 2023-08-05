import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 5.
 * @see  			https://www.acmicpc.net/problem/11404
 * @performance 
 * @category		#플로이드워셜
 * @note 
 */

public class Main {
	public static final int INF = (int)1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] graph = new int[N+1][N+1];
		
		for(int a = 1; a < N+1; a++) {
			for(int b = 1; b < N+1; b++) {
				graph[a][b] = (a == b) ? 0 : INF;
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(graph[a][b] < c) {
				continue;
			}
			graph[a][b] = c;
		}
		
		for(int k = 1; k < N+1; k++) {
			for(int a = 1; a < N+1; a++) {
				for(int b = 1; b < N+1; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}
		
		for(int a = 1; a < N+1; a++) {
			for(int b = 1; b < N+1; b++) {
				if(graph[a][b] != INF) {
					sb.append(graph[a][b] + " ");
				}
				else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}