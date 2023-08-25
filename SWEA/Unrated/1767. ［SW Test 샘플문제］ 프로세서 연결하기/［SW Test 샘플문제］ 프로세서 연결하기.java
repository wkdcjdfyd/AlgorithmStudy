import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 25.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
@performance	
@category 		#
@note			
*/

public class Solution {
	static int[][] graph;
	static int N;
	static ArrayList<int[]> core;
	static int min;
	static int activeCnt;
	static boolean[] activate;
	
	public static int[][] copy(int[][] org) {
		int[][] arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = org[i][j];
			}
		}
		return arr;
	}
	
	public static int up(int x, int y, int[][] arr){
		int cnt = 0;
		for(int i = x-1; i >= 0; i--) {
			if(arr[i][y] != 0) {
				return -1;
			}
			arr[i][y] = -1;
			cnt++;
		}
		return cnt;
	}
	
	public static int down(int x, int y, int[][] arr){
		int cnt = 0;
		for(int i = x+1; i < N; i++) {
			if(arr[i][y] != 0) {
				return -1;
			}
			arr[i][y] = -1;
			cnt++;
		}
		return cnt;
	}
	
	public static int left(int x, int y, int[][] arr){
		int cnt = 0;
		for(int j = y-1; j >= 0; j--) {
			if(arr[x][j] != 0) {
				return -1;
			}
			arr[x][j] = -1;
			cnt++;
		}
		return cnt;
	}
	
	public static int right(int x, int y, int[][] arr){
		int cnt = 0;
		for(int j = y+1; j < N; j++) {
			if(arr[x][j] != 0) {
				return -1;
			}
			arr[x][j] = -1;
			cnt++;
		}
		return cnt;
	}
	
	public static void dfs(int nth, int cnt, int[][] arr) {
		if(nth == core.size()) {
			int active = 0;
			for(int i = 0; i < core.size(); i++) {
				if(activate[i]) active++;
			}
			if(active > activeCnt) {
				activeCnt = active;
				min = cnt;
			}
			else if(active != 0 && active == activeCnt){
				min = Math.min(min, cnt);
			}
			return;
		}
		
		int[] now = core.get(nth);
		
		dfs(nth+1, cnt, arr);
		
		int[][] arrUp = copy(arr);
		int upCnt = up(now[0], now[1], arrUp);
		if(upCnt != -1) {
			activate[nth] = true;
			dfs(nth+1, cnt + upCnt, arrUp);
			activate[nth] = false;
		}
		
		int[][] arrDown = copy(arr);
		int downCnt = down(now[0], now[1], arrDown);
		if(downCnt != -1) {
			activate[nth] = true;
			dfs(nth+1, cnt + downCnt, arrDown);
			activate[nth] = false;
		}
		
		int[][] arrLeft = copy(arr);
		int leftCnt = left(now[0], now[1], arrLeft);
		if(leftCnt != -1) {
			activate[nth] = true;
			dfs(nth+1, cnt + leftCnt, arrLeft);
			activate[nth] = false;
		}
		
		int[][] arrRight = copy(arr);
		int rightCnt = right(now[0], now[1], arrRight);
		if(rightCnt != -1) {
			activate[nth] = true;
			dfs(nth+1, cnt + rightCnt, arrRight);
			activate[nth] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			core = new ArrayList<>(12);
			min = Integer.MAX_VALUE;
			activeCnt = 0;
				
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if(graph[i][j] == 1) {
						if(i != 0 && i != N-1 && j != 0 && j != N-1) {
							core.add(new int[] {i, j});
						}
					}
				}
			}
			activate = new boolean[core.size()];
			dfs(0, 0, copy(graph));
			
			sb.append(String.format("#%d %d\n", t, min));
		}
		System.out.println(sb.toString());
		br.close();
	}

}