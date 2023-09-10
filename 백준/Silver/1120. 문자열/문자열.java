import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 10.
 * @see  			https://www.acmicpc.net/problem/1120
 * @performance 	
 * @category 		#문자열
 * @note 			
 */

public class Main {
	
	public static int getDiff(String x, String y) {		
		int cnt = 0;
		for(int i = 0; i < x.length(); i++) {
			if(x.charAt(i) != y.charAt(i)) cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		
		int ans = 0;

		int max = 0;
		for(int i = 0; i <= b.length() - a.length(); i++) {
			int cnt = 0;
			for(int j = 0; j < a.length(); j++) {
				if(a.charAt(j) == b.charAt(i+j)){
					cnt++;
				}
			}
			max = Math.max(max, cnt);
		}
		
		ans = a.length() - max;
		
		System.out.println(ans);
		br.close();
	}

}