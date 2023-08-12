import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 8. 12.
@see			
@performance	
@category 		#비트마스킹
@note			확인해야 하는 수가 적을 때 사용가능하다. 64비트 정도까지??일 때 사용하는걸 권장한다고 하셨다.
*/

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int chk = 0;
			// 0 => 2^0 체크
			// 1 => 2^1 체크
			
			int all_checked = (1<<10) - 1;
			
			for(int k = 1;; k++) {
				int num = k * N;
				while(num > 0){
					int digit = num % 10;
					chk |= 1 << digit;
					num /= 10;
				}
				
				if(chk == all_checked) {
					sb.append(String.format("#%d %d\n", t, k * N));
					break;
				}
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}