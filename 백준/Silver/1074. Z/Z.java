import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 14.
@see			https://www.acmicpc.net/problem/1074
@performance	
@category 		#분할정복
@note			
*/

public class Main {
	static int cnt;
	static int x;
	static int y;
	static int ans;

	public static void search(int R, int C, int size) {
		if(size == 2) {
			if(R <= x && x < R+size && C <= y && y < C+size) {
				for(int i = 0 ; i < size; i++) {
					for(int j = 0; j  < size; j++) {
						int nx = R + i;
						int ny = C + j;
						cnt++;
						if(nx == x && ny == y) {
							ans = cnt;
						}
					}
				}
			}
			else {
				cnt += 4;
			}
			return;
		}
		int nxtSize = size / 2;
		if(isIn(R, C, nxtSize)) {
			search(R, C, nxtSize);
		}
		else {
			cnt += nxtSize * nxtSize;
		}
		if(isIn(R, C+nxtSize, nxtSize)) {
			search(R, C+nxtSize, nxtSize);
		}
		else {
			cnt += nxtSize * nxtSize;
		}
		if(isIn(R+nxtSize, C, nxtSize)) {
			search(R+nxtSize, C, nxtSize);
		}
		else {
			cnt += nxtSize * nxtSize;
		}
		if(isIn(R+nxtSize, C+nxtSize, nxtSize)) {
			search(R+nxtSize, C+nxtSize, nxtSize);
		}
		else {
			cnt += nxtSize * nxtSize;
		}
	}
	
	public static boolean isIn(int R, int C, int size) {
		if(R <= x && x < R+size && C <= y && y < C+size) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		cnt = -1;
		
		search(0, 0, (int)Math.pow(2, N));
		System.out.println(ans);
		br.close();
	}

}