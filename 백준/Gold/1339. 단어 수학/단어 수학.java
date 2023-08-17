import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
@author 		Ryong
@since 			2023. 8. 17.
@see			https://www.acmicpc.net/problem/1339
@performance	
@category 		#Greedy
@note			문제 해결 방법을 제대로 떠올리지 못했다. 어떤 시점에서 그리디하게 판단할건지를 잘 생각해보자.
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] alpha = new int[26];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			int temp = (int)Math.pow(10, s.length()-1);
			for(int j = 0; j < s.length(); j++) {
				alpha[(int)s.charAt(j) - 'A'] += temp;
				temp /= 10;
			}
		}
		
		Arrays.sort(alpha);
		int num = 9;
		int sum = 0;
		for(int i = alpha.length-1; i > -1; i--) {
			if(alpha[i] == 0) {
				break;
			}
			sum += alpha[i] * num;
			num--;
		}
		
		System.out.println(sum);
		br.close();
	}

}