import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 1.
 * @see  			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh
 * @performance 
 * @category 		#구현 #시뮬레이션
 * @note 
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] dy = {-1, 1};
		
		for(int t = 1; t < 11; t++) {
			int T = Integer.parseInt(br.readLine());
			int[][] graph = new int[100][100];
			int x = 0, y = 0;
			int nx = 0, ny = 0;
			int d = 2;
			
			for(int i = 0 ; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if(graph[i][j] == 2) {
						x = i;
						y = j;
					}
				}
			}
			
			MainLoop:
			while(x >= 0) {
				if(x == 0) {
					sb.append("#" + t + " " + y + "\n");
					break;
				}
				if(d == 2) {
					nx = x;
					for(int i = 0; i < 2; i++) {
						ny = y + dy[i];
						
						if(ny < 0 || ny >= 100) {
							continue;
						}
						if(graph[nx][ny] == 1) {
							x = nx;
							y = ny;
							d = i;
							continue MainLoop;
						}
					}
					x--;
				}
				else {
					int scale = 0;
					nx = x;
					ny = y;
					
					while(true) {
						scale++;
						ny = y + dy[d] * scale;
						if(ny < 0 || ny >= 100 || graph[nx][ny] == 0) {
							x = nx - 1;
							y = ny - dy[d];
							d = 2;
							continue MainLoop;
						}
					}
				}	
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}