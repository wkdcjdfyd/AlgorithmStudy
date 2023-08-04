import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
@author 		Ryong
@since 			2023. 8. 4.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD
@performance
@category 		#스택
@note
*/

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		MainLoop:
		for(int t = 1; t <= 10; t++) {
			Stack<Character> stack = new Stack<>();
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			
			for(int i = 0; i < N; i++) {
				switch(s.charAt(i)) {
				case'{':
				case'[':
				case'(':
				case'<':
					stack.push(s.charAt(i));
					break;
				case'}':
					if(stack.isEmpty() || stack.pop() != '{') {
						sb.append("#" + t + " " + "0\n");
						continue MainLoop;
					}
					break;
				case']':
					if(stack.isEmpty() || stack.pop() != '[') {
						sb.append("#" + t + " " + "0\n");
						continue MainLoop;
					}
					break;
				case')':
					if(stack.isEmpty() || stack.pop() != '(') {
						sb.append("#" + t + " " + "0\n");
						continue MainLoop;
					}
					break;
				case'>':
					if(stack.isEmpty() || stack.pop() != '<') {
						sb.append("#" + t + " " + "0\n");
						continue MainLoop;
					}
					break;
				}
			}
			sb.append("#" + t + " " + "1\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}