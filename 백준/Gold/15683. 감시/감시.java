import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/15683
@performance		42540kb 328ms
@category 		#구현 #백트래킹 #브루트포스
@note			재귀를 통해 배열 변경 연산을 최소화 하는 방법을 2048 문제에서 배웠었는데 그걸 활용했다!
*/

public class Main {
	static int N, M;
	static int[][] graph;
	static boolean[][] org;
	static int min;
	static ArrayList<CCTV> cctv = new ArrayList<>();
	static ArrayList<CCTV> type5 = new ArrayList<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class CCTV {
		int type;
		int x;
		int y;
		
		public CCTV(int x, int y, int type) {
			this.type = type;
			this.x = x;
			this.y = y;
		}
	}
	
	public static int counter(boolean[][] arr) {
		int cnt = 0;
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!arr[i][j])	cnt++;
			}
		}
		return cnt;
	}
	
	public static void dfs(int nth, boolean[][] arr) {
		if(nth == cctv.size()) {
			min = Math.min(min, counter(arr));
			return;
		}
		for(int i = 0; i < 4; i++) {
			dfs(nth+1, makeNewArea(cctv.get(nth), i, arr));
		}
	}
	
	public static boolean[][] copy(boolean[][] arr){
		boolean[][] nxt = new boolean[N][M];
		
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < M; j++) {
				nxt[i][j] = arr[i][j];
			}
		}
		return nxt;
	}
	
	public static void go(int x, int y, int dir, boolean[][] arr) {
		int nx = x;
		int ny = y;
		
		while(true) {
			nx = nx + dx[dir];
			ny = ny + dy[dir];
			
			if(!isValid(nx, ny) || graph[nx][ny] == 6) {
				break;
			}
			arr[nx][ny] = true;
		}
	}
	
	public static boolean[][] makeNewArea(CCTV cctv, int dir, boolean[][] arr) {
		boolean[][] nxt = copy(arr);
		
		switch(cctv.type) {
			case 1:
				go(cctv.x, cctv.y, dir, nxt);
				break;
			case 2:
				go(cctv.x, cctv.y, dir, nxt);
				go(cctv.x, cctv.y, (dir+2) % 4, nxt);
				break;
			case 3:
				go(cctv.x, cctv.y, dir, nxt);
				go(cctv.x, cctv.y, (dir+1) % 4, nxt);
				break;
			case 4:
				go(cctv.x, cctv.y, dir, nxt);
				go(cctv.x, cctv.y, (dir+1) % 4, nxt);
				go(cctv.x, cctv.y, (dir+2) % 4, nxt);
				break;
			case 5:
				go(cctv.x, cctv.y, dir, nxt);
				go(cctv.x, cctv.y, (dir+1) % 4, nxt);
				go(cctv.x, cctv.y, (dir+2) % 4, nxt);
				go(cctv.x, cctv.y, (dir+3) % 4, nxt);
				break;
		}
		return nxt;
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) {
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
		org = new boolean[N][M];
		min = N * M;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				
				if(graph[i][j] != 0) {
					org[i][j] = true;
				}
				
				if(graph[i][j] == 5) {
					type5.add(new CCTV(i, j, graph[i][j]));
				}
				else if(graph[i][j] != 0  && graph[i][j] != 6) {
					cctv.add(new CCTV(i, j, graph[i][j]));
				}
				
			}
		}
		
		for(int i = 0; i < type5.size(); i++) {
			org = makeNewArea(type5.get(i), 0, org);
		}
		
		dfs(0, copy(org));
		
		System.out.println(min);
		br.close();
	}

}
