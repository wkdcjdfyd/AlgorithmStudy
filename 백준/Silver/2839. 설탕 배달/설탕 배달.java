import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt5Kg = N / 5;
		int cnt3Kg = 0;
		
		while(cnt5Kg >= 0) {
			int now = N - cnt5Kg * 5;
			if(now % 3 == 0) {
				cnt3Kg = now / 3;
				break;
			}
			cnt5Kg--;
		}
		if(cnt5Kg == -1) {
			System.out.println(-1);
		}
		else {
			System.out.println(cnt5Kg + cnt3Kg);
		}
		br.close();
	}

}