import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 3.
@see			https://www.acmicpc.net/problem/12891
@performance		24384kb	224ms
@category 		#슬라이딩윈도우
@note
*/

public class Main {
	static int[] cnt = new int[4];
	static int[] counter = new int[4];
	
	public static boolean check() {
		for(int i = 0; i < 4; i++) {
			if(counter[i] < cnt[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		String dna = br.readLine();
		// dna를 0, 1, 2, 3의 int[]로 변경
		int[] dnaToInt = new int[S];
		for(int i = 0; i < S; i++) {
			switch(dna.charAt(i)){
				case 'A':
					dnaToInt[i] = 0;
					break;
				case 'C':
					dnaToInt[i] = 1;
					break;
				case 'G':
					dnaToInt[i] = 2;
					break;
				case 'T':
					dnaToInt[i] = 3;
					break;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < cnt.length; i++) {
			cnt[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		// P 크기의 슬라이딩 윈도우를 이동시키며 탐색
		for(int i = 0; i < P; i++) {
			counter[dnaToInt[i]]++;
		}
		if(check()) {
			result += 1;
		}
		
		MainLoop:
		for(int s = 1; s <= S-P; s++) {
			// 개수 조건 충족하는지 검사
			counter[dnaToInt[s-1]]--;
			counter[dnaToInt[s+P-1]]++;
			
			if(check()) {
				result += 1;
			}
		}
		System.out.println(result);
		br.close();
	}

}
