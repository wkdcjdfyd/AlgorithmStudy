import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 23.
 * @see  			https://www.acmicpc.net/problem/16985
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
	static int[][][] pan = new int[5][5][5];
	static int[] dz = {1, -1, 0, 0, 0, 0};
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dy = {0, 0, 0, 0, 1, -1};
	static int ans = 100;
	
	public static int bfs(int[][][] cube) {
		if(cube[0][0][0] == 0) return -1;
		
		boolean[][][] visited = new boolean[5][5][5];
		Deque<int[]> q = new ArrayDeque<>();
		visited[0][0][0] = true;
		q.offer(new int[] {0, 0, 0, 0});
		int moveCnt = -1;
		
		Loop:
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dz.length; d++) {
				int nz = now[0] + dz[d];
				int nx = now[1] + dx[d];
				int ny = now[2] + dy[d];
				
				if(nz < 0 || nx < 0 || ny < 0 || nz >= 5 || nx >= 5 || ny >= 5) {
					continue;
				}
				if(cube[nz][nx][ny] == 0 || visited[nz][nx][ny]) continue;
				if(nz == 4 && nx == 4 && ny == 4) {
					moveCnt = now[3] + 1;
					break Loop;
				}
				visited[nz][nx][ny] = true;
				q.offer(new int[] {nz, nx, ny, now[3] + 1});
			}
		}
		
		return moveCnt;
	}
	
	public static void makePanOrder(int nth, int[] choosed, boolean[] visited) {
		if(nth == 5) {
			dfs(0, choosed, new int[5][][]);
			return;
		}
		for(int i = 0; i < 5; i++) {
			if(!visited[i]) {
				choosed[nth] = i;
				visited[i] = true;
				makePanOrder(nth+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	public static void dfs(int nth, int[] order, int[][][] cube) {
		if(nth == 5) {
			int cnt = bfs(cube);
			
			if(cnt != -1) {
				ans = Math.min(ans, cnt);
			}
			
			return;
		}
		
		cube[nth] = pan[order[nth]];
		dfs(nth+1, order, cube);
		
		int[][] rotated = pan[order[nth]];
		for(int i = 0; i < 3; i++) {
			rotated = rotate(rotated);
			cube[nth] = rotated;
			dfs(nth+1, order, cube);
		}
	}
	
	public static int[][] rotate(int[][] arr){
		int[][] temp = new int[5][5];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				temp[j][4-i] = arr[i][j];
			}
		}
		return temp;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int k = 0; k < 5; k++) {
			for(int i = 0; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 5; j++) {
					pan[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		makePanOrder(0, new int[5], new boolean[5]);
		
		if(ans == 100) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
		br.close();
	}

}