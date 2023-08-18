import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 18.
@see			https://www.acmicpc.net/problem/6593
@performance	
@category 		#BFS
@note			
*/

public class Main {
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 0, -1, 1};
	static int L, R, C;
	static char[][][] graph;
	static boolean[][][] visited;
	static Deque<Loc> q;
	static int ans;
	
	static class Loc{
		int z;
		int x;
		int y;
		int num;
		
		public Loc(int z, int x, int y, int num) {
			this.z = z;
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	
	public static boolean bfs() {
		while(!q.isEmpty()) {
			Loc now = q.pollFirst();
			
			for(int i = 0; i < dx.length; i++) {
				int nz = now.z + dz[i];
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nz < 0 || nx < 0 || ny < 0 || nz >= L || nx >= R || ny >= C) {
					continue;
				}
				if(graph[nz][nx][ny] == '#' || visited[nz][nx][ny]) {
					continue;
				}
				if(graph[nz][nx][ny] == 'E') {
					ans = now.num + 1;
					return true;
				}
				
				visited[nz][nx][ny] = true;
				q.offerLast(new Loc(nz, nx, ny, now.num + 1));
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0) {
				break;
			}
			
			graph = new char[L][R][C];
			visited = new boolean[L][R][C];
			q = new ArrayDeque<>();
			
			for(int k = 0; k < L; k++) {
				for(int i = 0; i < R; i++) {
					String s = br.readLine();
					for(int j = 0; j < C; j++) {
						graph[k][i][j] = s.charAt(j);
						if(graph[k][i][j] == 'S') {
							q.offerLast(new Loc(k, i, j, 0));
							visited[k][i][k] = true;
						}
					}
				}
				br.readLine();
			}
			if(bfs()) {
				sb.append(String.format("Escaped in %d minute(s).\n", ans));
			}
			else {
				sb.append("Trapped!\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}