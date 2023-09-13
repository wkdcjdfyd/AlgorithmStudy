import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 9. 14.
@see			https://www.acmicpc.net/problem/1484
@performance	
@category 		#
@note			
*/

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int G = Integer.parseInt(br.readLine());
		
		boolean flag = false;
		for(int i = (int)(Math.sqrt(G)); i > 0; i--) {
			if(G % i == 0) {
				int x = i;
				int y = G / i;
				if(y == i) continue;
				
				if((x+y) % 2 == 0) {
					sb.append((x+y) / 2 + "\n");
					flag = true;
				}
			}
		}
		if(flag) {
			System.out.println(sb.toString());
		}
		else {
			System.out.println(-1);
		}
		br.close();
	}	
}