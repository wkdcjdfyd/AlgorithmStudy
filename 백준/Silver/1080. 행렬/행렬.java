import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 16.
@see			https://www.acmicpc.net/problem/1080
@performance	
@category 		#Greedy
@note			아직 어떤 기준으로 그리디를 진행해야 하는지 익숙하지 않은 것 같다	
*/

public class Main {
	static boolean[][] A;
	static boolean[][] B;
	static int N, M;
	
	public static void operation(int x, int y) {
		for(int i = x; i < x+3; i++) {
			for(int j = y; j < y+3; j++) {
				A[i][j] = !A[i][j];
			}
		}
	}
	
	public static boolean isEqual() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(A[i][j] != B[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A = new boolean[N][M];
		B = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				if(s.charAt(j) == '0') {
					A[i][j] = false;
				}
				else {
					A[i][j] = true;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				if(s.charAt(j) == '0') {
					B[i][j] = false;
				}
				else {
					B[i][j] = true;
				}
			}
		}

		if(isEqual()) {
			System.out.println(0);
		}
		else if(N < 3 || M < 3) {
			System.out.println(-1);
		}
		else {
			int cnt = 0;
			boolean flag = false;
			
			MainLoop:
			for(int x = 0; x <= N-3; x++) {
				for(int y = 0; y <= M-3; y++) {
					if(A[x][y] != B[x][y]) {
						operation(x, y);
						cnt++;
						if(isEqual()) {
							System.out.println(cnt);
							flag = true;
							break MainLoop;
						}
					}
				}
			}
			if(!flag) {
				System.out.println(-1);
			}
		}
		br.close();
	}

}