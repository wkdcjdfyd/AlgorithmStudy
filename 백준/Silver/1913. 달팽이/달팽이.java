import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] graph = new int[N][N];
		
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		int x = 0;
		int y = 0;
		int[] loc = {1, 1};
		int d = 0;
		
		int cnt = N*N;
		graph[0][0] = N*N;
		
		while(cnt > 1) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N || graph[nx][ny] != 0) {
				d = (d+1) % 4;
				continue;
			}
			
			graph[nx][ny] = --cnt;
			if(cnt == M) {
				loc[0] = nx + 1;
				loc[1] = ny + 1;
			}
			x = nx;
			y = ny;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(graph[i][j] + " ");
			}
			sb.append("\n");
		}
		sb.append(loc[0] + " " + loc[1]);
		System.out.println(sb.toString());
		br.close();
	}

}