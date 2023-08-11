import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 11.
@see			https://www.acmicpc.net/problem/1027
@performance
@category 		#기하학
@note
*/

public class Main {
	
	public static boolean check(int[] h, int x, int y) {
		if(h[y] > h[x]) {
			double scale = (double)(h[y] - h[x]) / (double)(y - x);
			for(int i = x+1; i < y; i++) {
				if(h[i] >= h[x] + scale * (i - x)) {
					return false;
				}
			}
			return true;
		}
		else {
			double scale = (double)(h[x] - h[y]) / (double)(y - x);
			for(int i = y-1; i > x; i--) {
				if(h[i] >= h[y] + scale * (y - i)) {
					return false;
				}
			}
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] h = new int[N];
		boolean[][] isSee = new boolean[N][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean val;
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(j - i == 1) {
					val = true;
				}
				else {
					val = check(h, i, j);
				}
				
				isSee[i][j] = val;
				isSee[j][i] = val;
			}
		}
		int max = 0;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if(isSee[i][j]) sum++;
			}
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
		br.close();
	}

}