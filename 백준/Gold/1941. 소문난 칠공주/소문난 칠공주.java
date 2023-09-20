import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
@author 		Ryong
@since 			2023. 9. 21.
@see			https://www.acmicpc.net/problem/1941
@performance	
@category 		#완전탐색
@note			
*/

public class Main {
	static char[][] graph = new char[5][5];
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static int ans = 0;
	
	public static void makeComb(int nth, int startIdx, int sCnt, int yCnt, int[] choosed) {
		if(nth == choosed.length) {
			if(linkCheck(choosed)) {
				ans++;
			}
			return;
		}
		for(int i = startIdx; i < 25; i++) {
			choosed[nth] = i;
			int x = i / 5;
			int y = i % 5;
			
			if(graph[x][y] == 'S') {
				makeComb(nth+1, i+1, sCnt+1, yCnt, choosed);
			}
			else {
				if(yCnt == 3) continue;
				makeComb(nth+1, i+1, sCnt, yCnt+1, choosed);
			}
		}
	}
	
	public static boolean linkCheck(int[] choosed) {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[7];
		visited[0] = true;
		q.offer(choosed[0]);
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			int x = now / 5;
			int y = now % 5;
			
			for(int d = 0; d < dx.length; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				int n = (nx * 5) + ny;
				
				if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
			
				for(int i = 0; i < choosed.length; i++) {
					if(!visited[i] && choosed[i] == n) {
						visited[i] = true;
						cnt++;
						q.offer(n);
					}
				}
			}
		}
		if(cnt == 7) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			String s = br.readLine();
			for(int j = 0; j < 5; j++) {
				graph[i][j] = s.charAt(j);
			}
		}
		
		makeComb(0, 0, 0, 0, new int[7]);
		
		System.out.println(ans);
		br.close();
	}

}