import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 8. 2.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
@performance		16888kb 104 ms
@category 		#구현 #시뮬레이션
@note			달팽이 구현
*/

public class Solution {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static StringBuilder sb = new StringBuilder();
	
	public static void makeSnail(int n) {
		int[][] graph = new int[n][n];
		int x = 0, y = 0, d = 0;
		graph[x][y] = 1;
		int cnt = 1;
		
		while(cnt < n * n) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= n || ny >= n || graph[nx][ny] != 0) {
				d = (d+1) % 4;
				continue;
			}
			graph[nx][ny] = ++cnt;
			x = nx;
			y = ny;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(graph[i][j] + " ");
			}
			sb.append("\n");
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			sb.append("#" + t + "\n");
			makeSnail(Integer.parseInt(br.readLine()));
		}
		System.out.println(sb.toString());
		br.close();
	}

}
