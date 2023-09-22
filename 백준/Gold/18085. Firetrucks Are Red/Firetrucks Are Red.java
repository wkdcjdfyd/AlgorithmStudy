import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 22.
@see			https://www.acmicpc.net/problem/18085
@performance	
@category 		#
@note			
*/

public class Main {
	static int[] parents;
	static int N;
	static Map<Integer, ArrayList<Integer>> map = new HashMap<>();
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		parents[py] = px;
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			parents[i] = i;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				ArrayList<Integer> list = map.getOrDefault(num, null);
				if(list != null) {
					list.add(i);
				}
				else {
					map.put(num, new ArrayList<>());
					map.get(num).add(i);
				}
			}
		}
		
		for(int key : map.keySet()) {
			ArrayList<Integer> list = map.get(key);
			int n1 = list.get(0);
			for(int j = 1; j < list.size(); j++) {
				int n2 = list.get(j);
				if(union(n1, n2)) {
					sb.append(n1).append(" ")
					.append(n2).append(" ")
					.append(key).append("\n");
				}
			}
		}
		
		int x = find(1);
		boolean flag = true;
		
		for(int i = 2; i < N+1; i++) {
			if(x != find(i)) {
				flag = false;
				break;
			}
		}
		
		if(!flag) {
			System.out.println("impossible");
		}
		else {
			System.out.println(sb.toString());
		}
		br.close();
	}

}