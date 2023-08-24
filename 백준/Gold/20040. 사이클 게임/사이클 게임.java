import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 24.
@see			https://www.acmicpc.net/problem/20040
@performance	
@category 		#Union-Find
@note			
*/

public class Main {
	static int N, M;
	static int[] parents;
	
	public static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px != py) {
			parents[py] = px;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		for(int i = 1; i < N; i++) {
			parents[i] = i;
		}
		
		int num = 0;
		for(int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(!union(x, y)) {
				if(num == 0) {
					num = i;
				}
			}
		}
		System.out.println(num);
		br.close();
	}

}