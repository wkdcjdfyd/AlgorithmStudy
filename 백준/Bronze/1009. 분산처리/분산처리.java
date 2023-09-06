import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 6.
@see			https://www.acmicpc.net/problem/1009
@performance	
@category 		#
@note			
*/

public class Main {
	static ArrayList<ArrayList<Integer>> cycle = new ArrayList<>();
	
	public static void getCycle(int n) {
		if(cycle.get(n).size() != 0) return;
		
		cycle.get(n).add(n);
		int nxt = lastNum(n * n);
		
		while(n != nxt) {
			cycle.get(n).add(nxt);
			nxt = lastNum(nxt * n);
		}
		
	}
	
	public static int lastNum(int x) {
		String s = Integer.toString(x);
		return s.charAt(s.length()-1) - '0';
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 10; i++) {
			cycle.add(new ArrayList<>());
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			a = lastNum(a);
			getCycle(a);
			int size = cycle.get(a).size();
			int idx = b % size;
			
			idx = (idx == 0) ? size - 1 : idx - 1;
			int ans = cycle.get(a).get(idx);
			if(ans == 0) {
				sb.append(10 + "\n");
			}
			else {
				sb.append(ans + "\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}