import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://www.acmicpc.net/problem/6051
@performance	
@category 		#스택
@note			
*/

public class Main {
	static Deque<Integer> stack = new ArrayDeque<>();
	static int[][] log;
	static int[][] order;
	static HashSet<Integer> set = new HashSet<>();
	static HashMap<Integer, int[]> map = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	
	static void printLast() {
		if(stack.peekLast() != null) {
			sb.append(stack.peekLast() + "\n");
		}
		else {
			sb.append(-1 + "\n");
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		order = new int[N][];
		map.put(0, new int[0]);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			if(cmd == 'a') {
				order[i] = new int[2];
				order[i][0] = 0;
				order[i][1] = Integer.parseInt(st.nextToken());
			}
			else if(cmd == 't') {
				order[i] = new int[2];
				order[i][0] = 1;
				order[i][1] = Integer.parseInt(st.nextToken());
				set.add(order[i][1]-1);
			}
			else {
				order[i] = new int[1];
				order[i][0] = 2;
			}
		}
				
		for(int i = 0; i < N; i++) {
			switch(order[i][0]) {
				case 0:
					stack.offerLast(order[i][1]);
					break;
				case 1:
					stack.clear();
					for(int n : map.get(order[i][1]-1)) {
						stack.offerLast(n);
					}
					break;
				case 2:
					if(!stack.isEmpty()) {
						stack.pollLast();
					}
					break;
			}
			printLast();
			
			if(set.contains(i+1)) {
				int[] arr = new int[stack.size()];
				int idx = 0;
				for(int n : stack) {
					arr[idx++] = n;
				}
				map.put(i+1, arr);
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}