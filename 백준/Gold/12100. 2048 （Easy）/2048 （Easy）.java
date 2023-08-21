import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 21.
@see			https://www.acmicpc.net/problem/12100
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N;
	static int[][] graph;
	static int max = 2;
	
	public static int[][] down(int[][] arr) {
		int[][] nxt = new int[N][N];
		
		for(int j = 0; j < N; j++) {
			int idx = N-1;
			boolean flag = false;
			int temp = 0;
			for(int i = N-1; i >= 0; i--) {
				if(arr[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = arr[i][j];
					flag = true;
				}
				else if(flag) {
					if(arr[i][j] == temp) {
						nxt[idx--][j] = arr[i][j] * 2;
						flag = false;
					}
					else {
						nxt[idx--][j] = temp;
						temp = arr[i][j];
					}
				}
			}
			if(flag) {
				nxt[idx][j] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] up(int[][] arr){
		int[][] nxt = new int[N][N];
		
		for(int j = 0; j < N; j++) {
			int idx = 0;
			boolean flag = false;
			int temp = 0;
			for(int i = 0; i < N; i++) {
				if(arr[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = arr[i][j];
					flag = true;
				}
				else if(flag) {
					if(arr[i][j] == temp) {
						nxt[idx++][j] = arr[i][j] * 2;
						flag = false;
					}
					else {
						nxt[idx++][j] = temp;
						temp = arr[i][j];
					}
				}
			}
			if(flag) {
				nxt[idx][j] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] right(int[][] arr) {
		int[][] nxt = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int idx = N-1;
			boolean flag = false;
			int temp = 0;
			for(int j = N-1; j >= 0; j--) {
				if(arr[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = arr[i][j];
					flag = true;
				}
				else if(flag) {
					if(arr[i][j] == temp) {
						nxt[i][idx--] = arr[i][j] * 2;
						flag = false;
					}
					else {
						nxt[i][idx--] = temp;
						temp = arr[i][j];
					}
				}
			}
			if(flag) {
				nxt[i][idx] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] left(int[][] arr) {
		int[][] nxt = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int idx = 0;
			boolean flag = false;
			int temp = 0;
			for(int j = 0; j < N; j++) {
				if(arr[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = arr[i][j];
					flag = true;
				}
				else if(flag) {
					if(arr[i][j] == temp) {
						nxt[i][idx++] = arr[i][j] * 2;
						flag = false;
					}
					else {
						nxt[i][idx++] = temp;
						temp = arr[i][j];
					}
				}
			}
			if(flag) {
				nxt[i][idx] = temp;
			}
		}
		return nxt;
	}
	
	public static void dfs(int nth, int[][] arr) {
		if(nth == 5) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					max = Math.max(max, arr[i][j]);
				}
			}
			return;
		}
		for(int i = 0; i < 4; i++) {
			dfs(nth+1, move(arr, i));
		}
	}
	
	public static int[][] move(int[][] arr, int cmd){
		
		switch(cmd) {
			case 0:
				return up(arr);
			case 1:
				return down(arr);
			case 2:
				return left(arr);
			case 3:
				return right(arr);
		}
		return arr;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, graph);

		System.out.println(max);
		br.close();
	}

}