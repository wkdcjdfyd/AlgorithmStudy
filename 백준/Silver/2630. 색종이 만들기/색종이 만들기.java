import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 20.
@see			https://www.acmicpc.net/problem/2630
@performance	
@category 		#재귀
@note			
*/

public class Main {
	static int N;
	static int[][] graph;
	static int white;
	static int blue;
	
	public static void counter(int x, int y, int scale) {
		if(scale == 0) {
			return;
		}
		int color = checkColor(x, y, scale);
		if(color == 1) {
			blue++;
			return;
		}
		if(color == 0) {
			white++;
			return;
		}
		
		int nxtScale = scale/2;
		
		counter(x, y, nxtScale);
		counter(x+nxtScale, y, nxtScale);
		counter(x, y+nxtScale, nxtScale);
		counter(x+nxtScale, y+nxtScale, nxtScale);
	}
	
	public static int checkColor(int x, int y, int scale) {
		int color = graph[x][y];
		
		for(int i = x; i < x+scale; i++) {
			for(int j = y; j < y+scale; j++) {
				if(graph[i][j] != color) {
					return -1;
				}
			}
		}
		
		return color;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		blue = 0;
		white = 0;
		counter(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
		br.close();
	}

}