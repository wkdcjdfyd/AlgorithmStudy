import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 10.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
@performance	
@category 		#Union-Find
@note			
*/

public class Solution {
	static int N, M;
	static int[] parents;
	
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
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			for(int i = 1; i < N+1; i++) {
				parents[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				union(x, y);
			}
			
			for(int i = 1; i < N+1; i++) {
				parents[i] = find(i);
			}
			
			Set<Integer> set = new HashSet<>();
			int cnt = 0;
			for(int i = 1; i < N+1; i++) {
				if(!set.contains(parents[i])) {
					cnt++;
					set.add(parents[i]);
				}
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		
		System.out.print(sb);
		br.close();
	}

}