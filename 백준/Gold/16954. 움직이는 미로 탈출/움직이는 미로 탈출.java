import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author 			Ryong
 * @since 			2023. 8. 8.
 * @see  			https://www.acmicpc.net/problem/16954
 * @performance 
 * @category 		#구현
 * @note 
 */

public class Main {
	static char[][][] graph;
	static boolean isValid = false;
	
	static class Loc{
		int x;
		int y;
		
		Loc (int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void dfs(int now, int nth, int[] choosed) {
		if(nth == 8) {
			isValid = true;
			return;
		}
		
		choosed[nth] = now;
		
		if(graph[nth+1][7][now] != '#') {
			dfs(now, nth+1, choosed);
		}
		if(now+1 < 8) {
			if(graph[nth][7][now+1] != '#' && graph[nth+1][7][now+1] != '#') {
				dfs(now+1, nth+1, choosed);
			}
			else if(graph[nth][6][now+1] != '#' && graph[nth][5][now+1] != '#') {
				dfs(now+1, nth+1, choosed);
			}
		}
		if(0 <= now-1) {
			if(graph[nth][7][now-1] != '#' && graph[nth+1][7][now-1] != '#') {
				dfs(now-1, nth+1, choosed);
			}
			else if(graph[nth][6][now-1] != '#' && graph[nth][5][now-1] != '#') {
				dfs(now-1, nth+1, choosed);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		graph = new char[9][8][8];
		Deque<Loc> wall = new LinkedList<>();
		
		
		for(int i = 0; i < 8; i++) {
			graph[0][i] = br.readLine().toCharArray();
			for(int j = 0; j < 8; j++) {
				if(graph[0][i][j] == '#') {
					wall.offerLast(new Loc(i, j));
				}
			}
		}
		
		for(int i = 1; i < 8; i++) {
			int size = wall.size();
			for(int j = 0; j < size; j++) {
				Loc now = wall.pollFirst();
				if(now.x + 1 >= 8) {
					continue;
				}
				graph[i][now.x+1][now.y] = '#';
				wall.offerLast(new Loc(now.x+1, now.y));
			}
		}
		dfs(0, 0, new int[8]);
		System.out.println(isValid? 1 : 0);
		br.close();
	}

}