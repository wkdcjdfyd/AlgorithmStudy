import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 12.
@see			
@performance	
@category 		#비트마스킹
@note			마스크 만드는 방법 기억해두기
*/

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int mask = (1 << N) - 1;
			int chk = M & mask;
			
			if(chk == mask) {
				sb.append(String.format("#%d ON\n", t));
			}
			else {
				sb.append(String.format("#%d OFF\n", t));
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}