import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
@author 		Ryong
@since 			2023. 9. 26.
@see			https://www.acmicpc.net/problem/2239
@performance	
@category 		#백트래킹
@note			
*/

public class Main {
	static int[][] graph = new int[9][9];
	static boolean[][] row = new boolean[9][10];
	static boolean[][] col = new boolean[9][10];
	static boolean[][] area = new boolean[9][10];
	static StringBuilder sb = new StringBuilder();
	static ArrayList<int[]> zero = new ArrayList<>();
	
	public static int getArea(int x, int y) {
		return (x / 3) * 3 + (y / 3);
	}
	
	public static void dfs(int nth) {
		if(nth == zero.size()) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(graph[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
			return;
		}
		
		int[] now = zero.get(nth);
		
		for(int k = 1; k < 10; k++) {

			if(!row[now[0]][k] && !col[now[1]][k] && !area[getArea(now[0], now[1])][k]) {
				row[now[0]][k] = true;
				col[now[1]][k] = true;
				area[getArea(now[0], now[1])][k] = true;
				
				graph[now[0]][now[1]] = k;
				dfs(nth+1);
				graph[now[0]][now[1]] = 0;
				
				row[now[0]][k] = false;
				col[now[1]][k] = false;
				area[getArea(now[0], now[1])][k] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			String s = br.readLine();
			for(int j = 0; j < 9; j++) {
				graph[i][j] = s.charAt(j) - '0';
				if(graph[i][j] != 0) {
					row[i][graph[i][j]] = true;
					col[j][graph[i][j]] = true;
					area[getArea(i, j)][graph[i][j]] = true;
				}
				else {
					zero.add(new int[] {i, j});
				}
			}
		}
		
		dfs(0);
	}

}