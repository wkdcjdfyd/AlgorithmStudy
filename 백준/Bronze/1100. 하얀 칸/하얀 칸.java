import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023. 9. 9.
 * @see  			https://www.acmicpc.net/problem/1100
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = 0;
		for(int i = 0; i < 8; i++) {
			String s = br.readLine();
			for(int j = 0; j < 8; j++) {
				char c = s.charAt(j);
				
				if(c == 'F' && (i+j) % 2 == 0) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		br.close();
	}

}