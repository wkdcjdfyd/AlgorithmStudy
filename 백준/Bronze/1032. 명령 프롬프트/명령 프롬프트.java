import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author 		Ryong
@since 			2023. 9. 6.
@see			https://www.acmicpc.net/problem/1032
@performance	
@category 		#문자열
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		String[] fileName = new String[N];
		for(int i = 0; i < N; i++) {
			fileName[i] = br.readLine();
		}
		
		Loop:
		for(int i = 0; i < fileName[0].length(); i++) {
			char now = fileName[0].charAt(i);
			for(int j = 1; j < N; j++) {
				if(fileName[j].charAt(i) != now) {
					sb.append("?");
					continue Loop;
				}
			}
			sb.append(now);
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}