import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 13.
@see			https://www.acmicpc.net/problem/16931
@performance	
@category 		#
@note			
*/

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] graph;
	static int N, M;
	
	public static int counter(int x, int y) {
		int cnt = 0;
		
		for(int d = 0; d < dx.length; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
				cnt += graph[x][y];
			}
			else if(graph[x][y] > graph[nx][ny]){
				cnt += graph[x][y] - graph[nx][ny];
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] != 0) cnt++;
			}
		}
		
		int ans = cnt * 2;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] != 0) {
					ans += counter(i, j);
				}
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}