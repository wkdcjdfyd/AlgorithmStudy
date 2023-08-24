import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 25.
@see			https://www.acmicpc.net/problem/1647
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M;
	static int[][] edge;
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
		
		if(px == py) return false;
		parents[py] = px;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edge = new int[M][3];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edge[i][0] = a;
			edge[i][1] = b;
			edge[i][2] = c;
		}
		
		parents = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parents[i] = i;
		}
		
		Arrays.sort(edge, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		int cost = 0;
		int lastCost = 0;
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			if(union(edge[i][0], edge[i][1])) {
				cost += edge[i][2];
				if(++cnt == N-1) {
					lastCost = edge[i][2];
					break;
				}
			}
		}
		
		System.out.println(cost - lastCost);
		br.close();
	}

}