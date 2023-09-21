import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 21.
@see			https://www.acmicpc.net/problem/14890
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, L;
	static int[][] graph;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	
	public static boolean isRoad(int startX, int startY, int dir) {
		int x = startX;
		int y = startY;
		int downCnt = 0;
		int nowSameCnt = 1;
		
		while(true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;
			if(Math.abs(graph[x][y] - graph[nx][ny]) > 1) return false;
			
			if(graph[x][y] == graph[nx][ny]) {
				if(downCnt > 0) {
					if(++downCnt == L) {
						downCnt = 0;
						nowSameCnt = 0;
					}
				}
				else {
					nowSameCnt++;
				}
			}
			// 내리막
			else if (graph[x][y] > graph[nx][ny]){
				if(downCnt > 0) return false;
				if(L == 1) {
					downCnt = 0;
					nowSameCnt = 0;
				}
				else {
					downCnt = 1;
					nowSameCnt = 1;
				}
			}
			// 오르막
			else {
				if(nowSameCnt < L) return false;
				if(downCnt > 0) return false;
				nowSameCnt = 1;
			}
			x = nx;
			y = ny;
		}
		if(downCnt > 0) return false;
		
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			if(isRoad(i, 0, 1)) ans++;
			if(isRoad(0, i, 0)) ans++;
		}
		
		System.out.println(ans);
		br.close();
	}

}