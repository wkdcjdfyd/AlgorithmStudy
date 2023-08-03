import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 3.
@see			https://www.acmicpc.net/problem/2961
@performance
@category 		#부분집합
@note			자료구조 생성할 때 초기화 까먹지 말기
*/

public class Main {
	static ArrayList<Ingre> ingredients = new ArrayList<>();
	static int N;
	static int min = 1_000_000_000;
	
	static class Ingre {
		int sour;
		int bitter;
	
		Ingre(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}
	
	public static void makeSubset(int nth, int sourSum, int bitterSum) {
		if(nth == N) {
			int sub = Math.abs(sourSum - bitterSum);
			if(bitterSum != 0 && min > sub) {
				min = sub;
			}
			return;
		}
		makeSubset(nth+1, sourSum * ingredients.get(nth).sour, bitterSum + ingredients.get(nth).bitter);
		makeSubset(nth+1, sourSum, bitterSum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ingredients.add(new Ingre(s, b));
		}

		makeSubset(0, 1, 0);
		System.out.println(min);
		br.close();
	}

}