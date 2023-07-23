import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			int point = 0;
			int total = 0;
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == 'X') {
					point = 0;
				}
				else {
					point++;
					total += point;
				}
			}
			sb.append(total + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}