import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		
		for(int i = 'a'; i <= 'z'; i++) {
			int idx = s.indexOf(Character.toString((char)i));
			sb.append(idx + " ");
		}
		System.out.println(sb.toString());
		br.close();
	}

}