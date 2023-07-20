import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int i, j;
		
		for(i = 0; i < n; i++) {
			for(j = 0; j < n-i-1; j++) {
				sb.append(" ");
			}
			for(;j < n; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}