import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int[] counter = new int[3]; // 각각의 버튼을 누를 횟수를 담을 배열;
		int[] buttons = {300, 60, 10}; // A, B, C 버튼을 누를 때 지정되는 초(300초, 60초, 10초)
		
		// 각각의 버튼의 초가 배수이므로
		// 큰 버튼부터 확인하면서 가장 많이 몇번까지 누를 수 있는지 확인 후
		// 누른 초 만큼 빼준다.
		for(int i = 0; i < buttons.length; i++) {
			counter[i] = T / buttons[i];
			T -= buttons[i] * counter[i];
		}
		
		// 출력
		// T초를 맞추지 못했을 경우 -1 출력
		if(T != 0) {
			sb.append(-1);
		}
		else {
			for(int cnt : counter) {
				sb.append(cnt + " ");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
		}
}