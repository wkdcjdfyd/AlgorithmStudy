import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 8.
@see			https://www.acmicpc.net/problem/16926
@performance
@category 		#구현
@note
*/

public class Main {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	static int M;
	static int R;
	static int[][] graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void rotate() {
		int x = 0;
		int y = 0;
		int rScale = N - x * 2;
		int cScale = M - y * 2;
		int minSide = Math.min(N, M);
		int[][] rotated = new int[N][M];
		
		while(minSide > 0) {
			int roundCnt = (rScale*2) + (cScale-2) * 2;
			int tempR = R % roundCnt;
			int[] moved = getNextStepCord(x, y, tempR);
			int d = moved[2];
			int cnt = 1;
			int nowX = x;
			int nowY = y;
			int nowD = 0;
			
			rotated[moved[0]][moved[1]] = graph[nowX][nowY];
			
			while(cnt < roundCnt) {
				while(true) {
					int nx = moved[0] + dx[d];
					int ny = moved[1] + dy[d];
					
					if(nx < x || ny < y || nx >= N - x || ny >= M - y) {
						d = (d + 1) % 4;
						continue;
					}
					moved[0] = nx;
					moved[1] = ny;
					break;
				}
						
				while(true) {
					int nx = nowX + dx[nowD];
					int ny = nowY + dy[nowD];
					
					if(nx < x || ny < y || nx >= N - x || ny >= M - y) {
						nowD = (nowD + 1) % 4;
						continue;
					}
					nowX = nx;
					nowY = ny;
					break;
				}
				rotated[moved[0]][moved[1]] = graph[nowX][nowY];
				cnt++;
			}
			minSide -= 2;
			rScale -= 2;
			cScale -= 2;
			x += 1;
			y += 1;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(rotated[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
		
	public static int[] getNextStepCord(int x, int y, int step) {
		int cnt = 0;
		int d = 0;
		int movedX = x;
		int movedY = y;
		
		while(cnt != step) {
			int nx = movedX + dx[d];
			int ny = movedY + dy[d];
			
			if(nx < x || ny < y || nx >= N - x || ny >= M - y) {
				d = (d + 1) % 4;
				continue;
			}
			cnt++;
			movedX = nx;
			movedY = ny;
		}
		return new int[] {movedX, movedY, d};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rotate();
		br.close();
	}

}