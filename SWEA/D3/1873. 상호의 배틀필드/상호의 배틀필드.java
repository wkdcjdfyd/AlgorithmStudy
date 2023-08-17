import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static final String DIRECTION = "^v<>";
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int H;
	static int W;
	static int N;
	static char[][] graph;
	static Tank tank;
	
	static class Tank{
		int x;
		int y;
		int d;
		
		public Tank(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
		public void turnAndMove(int sign) {
			this.d = sign;
			graph[this.x][this.y] = DIRECTION.charAt(this.d);
			int nx = this.x + dx[d];
			int ny = this.y + dy[d];
			
			if(!isValid(nx, ny)) {
				return;
			}
			if(graph[nx][ny] == '.') {
				graph[this.x][this.y] = '.';
				this.x = nx;
				this.y = ny;
				graph[this.x][this.y] = DIRECTION.charAt(this.d);
			}
		}
		
		public void shoot() {
			int nx = 0;
			int ny = 0;
			int scale = 1;
					
			while(true) {
				nx = this.x + dx[d] * scale;
				ny = this.y + dy[d] * scale;
				
				// 밖으로 나가면
				if(!isValid(nx, ny)) {
					return;
				}
				// 강철벽 못 부셔
				if(graph[nx][ny] == '#') {
					return;
				}
				// 벽돌 벽 부셔
				if(graph[nx][ny] == '*') {
					graph[nx][ny] = '.';
					return;
				}
				scale++;
			}
		}
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= H || y >= W) {
			return false;
		}
		return true;
	}
	
	public static void initTank(int x, int y, char d) {
		switch(d) {
			case '^':
				tank = new Tank(x, y, UP);
				break;
			case 'v':
				tank = new Tank(x, y, DOWN);
				break;
			case '<':
				tank = new Tank(x, y, LEFT);
				break;
			case '>':
				tank = new Tank(x, y, RIGHT);
				break;
		}
	}
	
	public static void cmd(char command) {
		switch(command) {
			case 'U':
				tank.turnAndMove(UP);
				break;
			case 'D':
				tank.turnAndMove(DOWN);
				break;
			case 'L':
				tank.turnAndMove(LEFT);
				break;
			case 'R':
				tank.turnAndMove(RIGHT);
				break;
			case 'S':
				tank.shoot();
				break;
		}
	}
	
	public static void printGraph() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				sb.append(graph[i][j]);
			}
			sb.append("\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			graph = new char[H][W];
			
			for(int i = 0; i < H; i++) {
				String s = br.readLine();
				for(int j = 0; j < W; j++) {
					graph[i][j] = s.charAt(j);
					if(DIRECTION.contains(graph[i][j]+"")) {
						initTank(i, j, graph[i][j]);
					}
				}
			}
			N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for(int i = 0; i < N; i++) {
				cmd(s.charAt(i));
			}
			
			sb.append(String.format("#%d ", t));
			printGraph();
		}
		System.out.println(sb.toString());
		br.close();
	}

}