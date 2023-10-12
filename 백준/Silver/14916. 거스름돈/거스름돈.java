import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 10. 12.
@see			https://www.acmicpc.net/problem/14916
@performance	
@category 		#
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = (N / 5); i >= 0; i--) {
			if((N - 5 * i) % 2 == 0) {
				int val = (N - 5 * i) / 2;
				System.out.println(val+i);
				br.close();
				return;
			}
		}
		
		System.out.println(-1);
		br.close();
	}

}