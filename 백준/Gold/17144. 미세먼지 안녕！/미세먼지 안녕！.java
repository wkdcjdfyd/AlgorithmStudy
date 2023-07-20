import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] counterClockwise = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] clockwise = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int[][] graph;
	static int[] cleaner = new int[2];
	static int munjiCnt = 0;
	static int R;
	static int C;
	static int T;
	
	static class munji {
		int x;
		int y;
		int munji;
		
		public munji(int x, int y, int munji) {
			this.x = x;
			this.y = y;
			this.munji = munji;
		}
	}
	
	static boolean isValid(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		Queue<munji> q = new LinkedList<munji>();
		int idx = 0;
		graph = new int[R][C];
				
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				if(graph[i][j] > 0) {
					q.add(new munji(i, j, graph[i][j]));
					munjiCnt += graph[i][j];
				}
				
				if(j == 0 && graph[i][j] == -1) {
					cleaner[idx++] = i;
				}
			}
		}
		
		for(int t = 0; t < T; t++) {
			// 확산
			while(!q.isEmpty()) {
				munji now = q.poll();
				int diffVol = now.munji / 5;
				int hitCnt = 0;
				
				for(int i = 0; i < 4; i++) {
					int nx = now.x + clockwise[i][0];
					int ny = now.y + clockwise[i][1];
					
					if (isValid(nx, ny)) {
						// 공기청정기 위치를 만나면 확산X
						if(ny == 0 && (cleaner[0] == nx || cleaner[1] == nx)) {
							continue;
						}
						graph[nx][ny] += diffVol;
						graph[now.x][now.y] -= diffVol;
						hitCnt++;
					}
				}
			}
			
			// 공기청정기 작동
			// 위쪽
			int x = cleaner[0] - 1;
			int y = 0;
			int d = 0;
			munjiCnt -= graph[x][y];
			
			while(!(x == cleaner[0] && y == 0)) {
				int nx = x + counterClockwise[d][0];
				int ny = y + counterClockwise[d][1];
				
				// 공기청정기 위쪽을 만나면 break
				if(nx == cleaner[0] && ny == 0) {
					graph[x][y] = 0;
					break;
				}
				
				if(isValid(nx, ny)) {
					if(nx >= cleaner[1]) {
						d = (d+1) % 4;
						continue;
					}
					graph[x][y] = graph[nx][ny];
					
					x = nx;
					y = ny;
				}
				else {
					d = (d+1) % 4;
				}
			}
			
			// 아래쪽
			x = cleaner[1] + 1;
			y = 0;
			d = 0;
			munjiCnt -= graph[x][y];
			
			while(!(x == cleaner[1] && y == 0)) {
				int nx = x + clockwise[d][0];
				int ny = y + clockwise[d][1];
				
				// 공기청정기 아래쪽을 만나면 break
				if(nx == cleaner[1] && ny == 0) {
					graph[x][y] = 0;
					break;
				}
				
				if(isValid(nx, ny)) {
					if(nx <= cleaner[0]) {
						d = (d+1) % 4;
						continue;
					}
					graph[x][y] = graph[nx][ny];
					
					x = nx;
					y = ny;
				}
				else {
					d = (d+1) % 4;
				}
			}
			
			// 먼지 위치 탐색
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(graph[i][j] > 0) {
						q.add(new munji(i, j, graph[i][j]));
					}
				}
			}
		}
		System.out.println(munjiCnt);
	}
}