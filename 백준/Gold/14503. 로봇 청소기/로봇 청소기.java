import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	static int M;
	static int[][] graph;
	
	public static int cleaning(int x, int y, int d) {
		int cleanCnt = 0;
		int nx, ny, nd;
		
		MainLoop: while(true) {
			// 현재 칸이 청소되지 않은 경우
			if(graph[x][y] == 0) {
				graph[x][y] = -1;
				cleanCnt++;
			}
			
			// 주변에 청소 안된 곳이 있는지 확인
			nd = d;
			for(int i = 0; i < 4; i++) {
				nd--;
				if(nd == -1) nd = 3;
				nx = x + dx[nd];
				ny = y + dy[nd];
				
				if(isValid(nx, ny) && graph[nx][ny] == 0) {
					x = nx;
					y = ny;
					d = nd;
					continue MainLoop;
				}
			}
			
			// 뒤쪽 칸 확인
			nd = (d+2) % 4;
			nx = x + dx[nd];
			ny = y + dy[nd];
			// 뒤쪽 칸이 벽이라면 정지
			if(graph[nx][ny] == 1) {
				break MainLoop;
			}
			// 후진 가능하면 1칸 후진 후 다시 맨 처음부터
			else if(isValid(nx, ny)) {
				x = nx;
				y = ny;
				continue MainLoop;
			}
		}
		
		return cleanCnt;
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || x >= N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		int startD = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(cleaning(startX, startY, startD));
		br.close();
	}

}