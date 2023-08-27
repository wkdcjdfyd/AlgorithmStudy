import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 28.
@see			https://www.acmicpc.net/problem/20057
@performance	35604kb 468ms
@category 		#구현
@note			
*/

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] graph;
	static int N;
	static int total = 0;
	
	public static void moveTornado() {
		int scale = 1;
		int moveCnt = 0;
		int x = N / 2;
		int y = N / 2;
		int d = 0;
		int cnt = 1;
		
		while(cnt < N*N) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			move(nx, ny, d);
			
			moveCnt++;
			if(scale == moveCnt) {
				d = (d+1) % 4;
			}
			else if(scale * 2 == moveCnt) {
				d = (d+1) % 4;
				scale++;
				moveCnt = 0;
			}
			x = nx;
			y = ny;
			cnt++;
		}
	}
	
	public static void move(int x, int y, int d) {
		int spreadSum = 0;
		
		int nx = x + dx[d] * 2;
		int ny = y + dy[d] * 2;
		int sand = (int)(graph[x][y] * 0.05);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[d] + dx[(d+1) % 4];
		ny = y + dy[d] + dy[(d+1) % 4];
		sand = (int)(graph[x][y] * 0.1);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+1) % 4];
		ny = y + dy[(d+1) % 4];
		sand = (int)(graph[x][y] * 0.07);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+1) % 4] * 2;
		ny = y + dy[(d+1) % 4] * 2;
		sand = (int)(graph[x][y] * 0.02);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+1) % 4] + dx[(d+2) % 4];
		ny = y + dy[(d+1) % 4] + dy[(d+2) % 4];
		sand = (int)(graph[x][y] * 0.01);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+3) % 4];
		ny = y + dy[(d+3) % 4];
		sand = (int)(graph[x][y] * 0.07);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+3) % 4] * 2;
		ny = y + dy[(d+3) % 4] * 2;
		sand = (int)(graph[x][y] * 0.02);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[d] + dx[(d+3) % 4];
		ny = y + dy[d] + dy[(d+3) % 4];
		sand = (int)(graph[x][y] * 0.1);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[(d+3) % 4] + dx[(d+2) % 4];
		ny = y + dy[(d+3) % 4] + dy[(d+2) % 4];
		sand = (int)(graph[x][y] * 0.01);
		moveSand(nx, ny, sand);
		spreadSum += sand;
		
		nx = x + dx[d];
		ny = y + dy[d];
		sand = graph[x][y] - spreadSum;
		moveSand(nx, ny, sand);
	}
	
	public static void moveSand(int x, int y, int sand) {
		if(isOut(x, y)) {
			total += sand;
		}
		else {
			graph[x][y] += sand;
		}
	}
	
	public static boolean isOut(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		moveTornado();
		
		System.out.println(total);
		br.close();
	}

}