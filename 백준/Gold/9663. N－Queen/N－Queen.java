import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 8. 10.
@see			https://www.acmicpc.net/problem/9663
@performance
@category 		#백트래킹
@note
*/

public class Main {
	static int N;
	static int cnt;
	static int[] queen;
	
	public static void find(int nth) {
		if(nth == N) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			queen[nth] = i;
			if(check(nth)) {
				find(nth+1);
			}
		}
	}
	
	public static boolean check(int idx) {
		for(int i = 0; i < idx; i++) {
			if(queen[idx] == queen[i]) {
				return false;
			}
			if(Math.abs(idx - i) == Math.abs(queen[idx] - queen[i])) {
				return false;
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queen = new int[N];
		cnt = 0;
		
		find(0);
		System.out.println(cnt);
		br.close();
	}

}