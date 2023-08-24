import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 8. 24.
@see			https://www.acmicpc.net/problem/14939
@performance	
@category 		#그리디
@note			
*/

public class Main {
	static boolean[][] graph;
	static boolean[][] temp;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static final int N = 10;
	static int min = Integer.MAX_VALUE;
	
	public static void pushSwitch(int x, int y, boolean[][] arr) {
		arr[x][y] = !arr[x][y];
		
		for(int d = 0; d < dx.length; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}
			arr[nx][ny] = !arr[nx][ny];
		}
	}
	
	public static boolean[][] copy() {		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[i][j] = graph[i][j];
			}
		}
		return temp;
	}
	
	public static void makeSubset(int nth, boolean[] choosed) {
		if(nth == N) {
			int cnt = check(choosed);
			
			if(cnt != -1) {
				min = Math.min(min, cnt);
			}
			return;
		}
		choosed[nth] = false;
		makeSubset(nth+1, choosed);
		choosed[nth] = true;
		makeSubset(nth+1, choosed);
	}
	
	public static int check(boolean[] choosed) {
		copy();
		int cnt = 0;
		
		for(int j = 0; j < N; j++) {
			if(choosed[j]) {
				pushSwitch(0, j, temp);
				cnt++;
			}
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(temp[i-1][j]) {
					pushSwitch(i, j, temp);
					cnt++;
				}
			}
		}
		
		for(int j = 0; j < N; j++) {
			if(temp[N-1][j]) {
				return -1;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new boolean[N][N];
		temp = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				if(s.charAt(j) == 'O') {
					graph[i][j] = true;
				}
			}
		}
		
		makeSubset(0, new boolean[N]);
		
		if(min != Integer.MAX_VALUE) {
			System.out.println(min);
		}
		else {
			System.out.println(-1);
		}
		
		br.close();
	}

}