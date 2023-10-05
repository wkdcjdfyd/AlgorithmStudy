import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
@author 		Ryong
@since 			2023. 10. 5.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo
@performance	
@category 		#구현
@note			
*/

public class Solution {
	static int N, K;
	static char[] cover;
	static TreeSet<Long> pwd;
	
	public static void makePwd() {
		int pwdLen = N / 4;
		
		// 시작 인덱스(start)로 회전 구분
		for(int start = 0; start < pwdLen; start++) {
			int cnt = 0;
			int nowIdx = start;
			StringBuilder now = new StringBuilder();

			while(cnt < N) {
				now.append(cover[nowIdx]);
				cnt++;
				if(++nowIdx == N) nowIdx = 0;
				if(now.length() == pwdLen) {
					long num = Long.parseLong(now.toString(), 16);
					pwd.add(-num);
					now = new StringBuilder();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cover = br.readLine().toCharArray();
			pwd = new TreeSet<>();
			
			makePwd();
			long ans = 0;
			int nth = 0;
			for(long num : pwd) {
				if(nth++ == K-1) {
					ans = -num;
					break;
				}
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}