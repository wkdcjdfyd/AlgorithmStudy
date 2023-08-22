import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 22.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
@performance	104416kb 519ms
@category 		#집합
@note			
*/

public class Solution {
	static int N, M;
	static int[] parent;
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px != py) {
			parent[py] = px;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N+1];
			
			for(int i = 1; i < N+1; i++) {
				parent[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cmd == 0) {
					union(a, b);
				}
				else {
					if(find(a) == find(b)) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}