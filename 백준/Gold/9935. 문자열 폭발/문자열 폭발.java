import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
@author 		Ryong
@since 			2023. 8. 9.
@see			https://www.acmicpc.net/problem/9935
@performance		38864kb	392ms
@category 		#문자열 #스택
@note			#String의 replace는 메모리를 많이 잡아먹는다!
*/

public class Main {
	static Stack<Character> stack = new Stack<>();
	static String s = "";
	static String bomb = "";
	
	public static int check() {
		int idx = 0;
		for(char c : stack) {
			if(c == bomb.charAt(idx)) {
				idx++;
			}
			else {
				idx = 0;
			}
		}
		return idx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		s = br.readLine();
		bomb = br.readLine();
		
		for(int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
			if(s.charAt(i) == bomb.charAt(bomb.length()-1)) {
				boolean isEqual = true;
				int stackIdx = stack.size()-1;
				
				if(stack.size() >= bomb.length()) {
					for(int j = bomb.length()-1; j >= 0; j--, stackIdx--) {
						
						if(stack.get(stackIdx) != bomb.charAt(j)) {
							isEqual = false;
							break;
						}
					}
					if(isEqual) {
						for(int j = 0; j < bomb.length(); j++) {
							stack.pop();
						}
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			sb.append("FRULA");
		}
		else {
			for(char c : stack) {
				sb.append(c);
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}
