import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int s = 1;
		int e = 1;
		int sum = 1;
		int cnt = 0;
		
		while(s <= e) {
			if(sum < n) {
				sum += ++e;
				continue;
			}
			else if (sum == n) {
				cnt++;
			}
			sum -= s++;
		}
		System.out.println(cnt);
		br.close();
	}

}
