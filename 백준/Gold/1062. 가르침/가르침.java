import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 17.
 * @see  			https://www.acmicpc.net/problem/1062
 * @performance 	
 * @category 		#백트래킹
 * @note 			백트래킹을 할 환경을 못만들었다.
 */

public class Main {
	static int N;
	static int K;
	static String[] word;
	static boolean[] alpha;
	static int ans = 0;
	
	public static void makeComb(int nth, int startIdx) {
		if(nth == K - 5) {
			ans = Math.max(ans, counter());
			return;
		}
		for(int i = startIdx; i < alpha.length; i++) {
			if(!alpha[i]) {
				alpha[i] = true;
				makeComb(nth+1, i+1);
				alpha[i] = false;
			}
		}
	}
	
	public static int counter() {
		int cnt = 0;
		
		MainLoop:
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < word[i].length(); j++) {
				if(!alpha[word[i].charAt(j) - 'a']) {
					continue MainLoop;
				}
			}
			cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K < 5) {
			System.out.println("0\n");
			return;
		}
		else if(K == 26) {
			System.out.println(N + "\n");
			return;
		}
		
		alpha = new boolean['z' - 'a' + 1];
		alpha['a' - 'a'] = true;
		alpha['c' - 'a'] = true;
		alpha['i' - 'a'] = true;
		alpha['n' - 'a'] = true;
		alpha['t' - 'a'] = true;
		
		word = new String[N];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			s = s.replace("anta", "");
			s = s.replace("tica", "");
			word[i] = s;
		}

		makeComb(0, 0);
		
		System.out.println(ans);
		br.close();
	}

}