import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 5.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
@performance	
@category 		#구현
@note			
*/

public class Solution {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] org;
	static int[][] graph;
	static int N, W, H, orgBrickCnt, brickCnt, ans;
	
	public static void shoot(int y) {
		for(int x = 0; x < H; x++) {
			if(graph[x][y] != 0) {
				breakBrick(x, y);
				fallDown();
				break;
			}
		}
	}
	
	public static void breakBrick(int x, int y) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, graph[x][y]});
		graph[x][y] = 0;
		brickCnt--;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int s = 1; s < now[2]; s++) {
				for(int d = 0; d < dx.length; d++) {
					int nx = now[0] + dx[d] * s;
					int ny = now[1] + dy[d] * s;
					
					if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
					if(graph[nx][ny] == 0) continue;
					
					if(graph[nx][ny] == 1) {
						graph[nx][ny] = 0;
						brickCnt--;
					}
					else if(graph[nx][ny] > 1) {
						q.offer(new int[] {nx, ny, graph[nx][ny]});
						graph[nx][ny] = 0;
						brickCnt--;
					}
				}
			}
		}
	}
	
	public static void fallDown() {
		for(int j = 0; j < W; j++) {
			Loop:
			for(int i = H-2; i >= 0; i--) {
				int ni = i;
				
				while(true) {
					if(ni+1 >= H || graph[ni+1][j] != 0) {
						if(graph[ni][j] == 0) {
							graph[ni][j] = graph[i][j];
							graph[i][j] = 0;
						}
						continue Loop;
					}
					
					ni++;
				}
			}
		}
	}
	
	public static void copyOrg() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				graph[i][j] = org[i][j];	
			}
		}
	}
	
	public static void makeDupPerm(int nth, int[] choosed) {
		if(nth == choosed.length) {
			copyOrg();
			brickCnt = orgBrickCnt;
			for(int y : choosed) {
				shoot(y);
			}
			ans = Math.min(ans, brickCnt);
			return;
		}
		for(int i = 0; i < W; i++) {
			choosed[nth] = i;
			makeDupPerm(nth+1, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			org = new int[H][W];
			graph = new int[H][W];
			orgBrickCnt = 0;
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					org[i][j] = Integer.parseInt(st.nextToken());
					if(org[i][j] != 0) orgBrickCnt++;
				}
			}
			ans = orgBrickCnt;
			
			makeDupPerm(0, new int[N]);
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}