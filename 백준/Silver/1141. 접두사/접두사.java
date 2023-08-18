import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/1141
@performance	
@category 		#문자열
@note			
*/

public class Main {
	static int N;
	static String[] word;
	static int ans;
	
	public static void makeSubset() {
		int idx = 0;
		int[] choosed = new int[N];
		
		for(int i = 0; i < N; i++) {
			if(check(i, choosed, idx)) {
				choosed[idx++] = i;
				ans++;
			}
		}
	}
	
	public static boolean check(int nth, int[] choosed, int size) {
		for(int i = 0; i < size; i++) {
			if(isPrefix(word[nth], word[choosed[i]])) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrefix(String s1, String s2) {
		if(s1.length() < s2.length()) {
			return s2.startsWith(s1);
		}
		else {
			return s1.startsWith(s2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		word = new String[N];
		
		for(int i = 0; i < N; i++) {
			word[i] = br.readLine();
		}
		
		Arrays.sort(word, (o1, o2) -> o2.length() - o1.length());
		
		makeSubset();
		System.out.println(ans);
		br.close();
	}

}