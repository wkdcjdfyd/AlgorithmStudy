import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 3.
@see			https://www.acmicpc.net/problem/15652
@performance		21060kb	120ms
@category 		#백트래킹
@note			중복조합으로 시도해봤다.
*/

public class Main {
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	
	public static void makeDupComb(int nth, int startIdx, int[] choosed) {
		if(nth == M) {
			for(int num : choosed) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = startIdx; i <= N; i++) {
				choosed[nth] = i;
				makeDupComb(nth+1, i, choosed);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		makeDupComb(0, 1, new int[M]);
		
		System.out.println(sb.toString());
		br.close();
	}

}
