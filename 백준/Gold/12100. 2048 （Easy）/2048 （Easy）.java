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
	static int[][] org;
	static int[][] graph;
	static int max = 2;
	
	public static int[][] down() {
		int[][] nxt = new int[N][N];
		
		for(int j = 0; j < N; j++) {
			int idx = N-1;
			boolean flag = false;
			int temp = 0;
			for(int i = N-1; i >= 0; i--) {
				if(graph[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = graph[i][j];
					flag = true;
				}
				else if(flag) {
					if(graph[i][j] == temp) {
						nxt[idx--][j] = graph[i][j] * 2;
						flag = false;
					}
					else {
						nxt[idx--][j] = temp;
						temp = graph[i][j];
					}
				}
			}
			if(flag) {
				nxt[idx][j] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] up(){
		int[][] nxt = new int[N][N];
		
		for(int j = 0; j < N; j++) {
			int idx = 0;
			boolean flag = false;
			int temp = 0;
			for(int i = 0; i < N; i++) {
				if(graph[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = graph[i][j];
					flag = true;
				}
				else if(flag) {
					if(graph[i][j] == temp) {
						nxt[idx++][j] = graph[i][j] * 2;
						flag = false;
					}
					else {
						nxt[idx++][j] = temp;
						temp = graph[i][j];
					}
				}
			}
			if(flag) {
				nxt[idx][j] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] right() {
		int[][] nxt = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int idx = N-1;
			boolean flag = false;
			int temp = 0;
			for(int j = N-1; j >= 0; j--) {
				if(graph[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = graph[i][j];
					flag = true;
				}
				else if(flag) {
					if(graph[i][j] == temp) {
						nxt[i][idx--] = graph[i][j] * 2;
						flag = false;
					}
					else {
						nxt[i][idx--] = temp;
						temp = graph[i][j];
					}
				}
			}
			if(flag) {
				nxt[i][idx] = temp;
			}
		}
		return nxt;
	}
	
	public static int[][] left() {
		int[][] nxt = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int idx = 0;
			boolean flag = false;
			int temp = 0;
			for(int j = 0; j < N; j++) {
				if(graph[i][j] == 0) {
					continue;
				}
				else if(!flag) {
					temp = graph[i][j];
					flag = true;
				}
				else if(flag) {
					if(graph[i][j] == temp) {
						nxt[i][idx++] = graph[i][j] * 2;
						flag = false;
					}
					else {
						nxt[i][idx++] = temp;
						temp = graph[i][j];
					}
				}
			}
			if(flag) {
				nxt[i][idx] = temp;
			}
		}
		return nxt;
	}
	
	public static void makeComb(int nth, int[] choosed) {
		if(nth == choosed.length) {
			copy();
			for(int cmd : choosed) {
				graph = move(cmd);
			}
			findMax();
			return;
		}
		for(int i = 0; i < 4; i++) {
			choosed[nth] = i;
			makeComb(nth+1, choosed);
		}
	}
	
	public static int[][] move(int cmd){
		
		switch(cmd) {
			case 0:
				return up();
			case 1:
				return down();
			case 2:
				return left();
			case 3:
				return right();
		}
		return graph;
	}
	
	public static void findMax() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, graph[i][j]);
			}
		}
	}
	
	public static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				graph[i][j] = org[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		org = new int[N][N];
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				org[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeComb(0, new int[5]);

		System.out.println(max);
		br.close();
	}

}