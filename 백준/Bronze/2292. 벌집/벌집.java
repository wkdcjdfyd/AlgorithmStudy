import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int min = 2;
		int max = 7;
		int cnt = 6;
		int ans = 2;
		
		if(n == 1) {
			System.out.println(1);
			return;
		}
		while(true) {
			if(min <= n && n <= max) {
				System.out.println(ans);
				return;
			}
			min += cnt;
			max += cnt + 6;
			cnt += 6;
			ans += 1;
		}
	}

}
