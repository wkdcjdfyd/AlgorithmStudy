import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		int K = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<>();
		int sum = 0;
		
		for(int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());

			if(num == 0) {
				sum -= s.pop();
			}
			else {
				s.add(num);
				sum += num;
			}
		}
		System.out.println(sum);
		br.close();
	}

}
