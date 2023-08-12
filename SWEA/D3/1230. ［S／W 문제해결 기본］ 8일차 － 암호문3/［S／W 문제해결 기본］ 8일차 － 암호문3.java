import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 12.
@see			
@performance	
@category 		#
@note			
*/

public class Solution {
	static ArrayList<Integer> pwd;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t < 11; t++) {
			int N = Integer.parseInt(br.readLine());
			pwd = new ArrayList<>(N);
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				pwd.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				char cmd = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				func(cmd, x);
			}
			sb.append(String.format("#%d ", t));
			for(int i = 0; i < 10; i++) {
				sb.append(pwd.get(i) + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	public static void func(char cmd, int x) {
		int y;
		switch(cmd) {
		case 'I':
			y = Integer.parseInt(st.nextToken());
			int insertIdx = x;
			for(int i = 0; i < y; i++) {
				pwd.add(insertIdx, Integer.parseInt(st.nextToken()));
				insertIdx++;
			}
			break;
		case 'D':
			y = Integer.parseInt(st.nextToken());
			for(int i = 0; i < y; i++) {
				pwd.remove(x);
			}
			
			break;
		case 'A':
			for(int i = 0; i < x; i++) {
				pwd.add(Integer.parseInt(st.nextToken()));
			}
			break;
		}
	}

}