import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int i = 1;
		for(; i < N+1; i++) {
			for(int j = 0; j < N-i; j++) {
				sb.append(" ");
			}
			for(int k = 0; k < i; k++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		for(i = N-1; i > 0; i--) {
			for(int j = N-i; j > 0; j--) {
				sb.append(" ");
			}
			for(int k = 0; k < i; k++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
