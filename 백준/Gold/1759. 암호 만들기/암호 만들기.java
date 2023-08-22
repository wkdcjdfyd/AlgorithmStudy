import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/1759
@performance		12616kb 84ms
@category 		#백트래킹
@note			
*/

public class Main {
	static int L, C;
	static char[] c;
	static String v = "aeiou";
	static StringBuilder sb = new StringBuilder();
	
	public static void comb(int nth, int startIdx, int[] choosed) {
		if(nth == L) {
			int vCnt = 0;
			int cCnt = 0;
			for(int i = 0; i < L; i++) {
				if(v.contains(c[choosed[i]]+"")) {
					vCnt++;
				}
				else {
					cCnt++;
				}
			}
			if(vCnt > 0 && cCnt > 1) {
				for(int i = 0; i < L; i++) {
					sb.append(c[choosed[i]]);
				}
				sb.append("\n");
			}
			return;
		}
		for(int i = startIdx; i < C; i++) {
			choosed[nth] = i;
			comb(nth+1, i+1, choosed);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		c = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			c[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(c);
		
		comb(0, 0, new int[L]);
		
		System.out.println(sb.toString());
		br.close();
	}

}
