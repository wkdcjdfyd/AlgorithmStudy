import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int n = 4 * N - 3;
		
		char[][] graph = new char[n][n];
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		
		int x = 0;
		int y = 0;
		int d = 0;
		int cnt = n * n;
		int round = 1;
		graph[0][0] = '*';
		
		while(cnt > 1) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= n || ny >= n || graph[nx][ny] != '\u0000') {
				if(d == 3) {
					round++;
					d = 0;
				}
				else {
					d++;
				}
				continue;
			}
			graph[nx][ny] = round % 2 != 0 ? '*' : ' ';
			cnt--;

			x = nx;
			y = ny;
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(graph[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}