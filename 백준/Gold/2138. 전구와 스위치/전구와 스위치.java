import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023. 8. 6.
 * @see  			https://www.acmicpc.net/problem/2138
 * @performance 		26928kb	148ms
 * @category 			#그리디알고리즘
 * @note 			부분집합을 구해 모든 경우를 탐색했지만 시간초과가 발생했다. 그리디가 어떨 때 가능한지 더 공부해야겠다.
 */

public class Main {
	static int N;
	static boolean[] bulb1;
	static boolean[] bulb2;
	static boolean[] result;
	static int ans = Integer.MAX_VALUE;
	
	public static void pushSwitch (int idx, boolean[] bulb) {
		for(int i = idx - 1; i <= idx + 1; i++) {
			bulb[i] = !bulb[i];
		}
	}
	
	public static void choiceGreedy(int idx, int cnt, boolean[] bulb) {
		if(idx == N+1) {
			if(bulb[idx-1] == result[idx-1]) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		if(bulb[idx-1] != result[idx-1]) {
			pushSwitch(idx, bulb);
			choiceGreedy(idx+1, cnt+1, bulb);
		}
		else {
			choiceGreedy(idx+1, cnt, bulb);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bulb1 = new boolean[N+2];
		bulb2 = new boolean[N+2];
		result = new boolean[N+1];
		
		String s = br.readLine();
		for(int i = 1; i < N+1; i++) {
			if(s.charAt(i-1) == '0') {
				bulb1[i] = true;
				bulb2[i] = true;
			}
			else {
				bulb1[i] = false;
				bulb2[i] = false;
			}
		}
		
		s = br.readLine();
		for(int i = 1; i < N+1; i++) {
			if(s.charAt(i-1) == '0') {
				result[i] = true;
			}
			else {
				result[i] = false;
			}
		}
		
		choiceGreedy(2, 0, bulb1);
		
		pushSwitch(1, bulb2);
		choiceGreedy(2, 1, bulb2);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		br.close();
	}

}
