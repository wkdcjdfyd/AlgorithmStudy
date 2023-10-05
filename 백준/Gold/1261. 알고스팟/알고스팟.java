import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 5.
@see			https://www.acmicpc.net/problem/1261
@performance	
@category 		#다익스트라
@note			
*/

public class Main {
	static int N, M;
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] counter;
	static final int INF = (int)1e7;
	
	static class Pos implements Comparable<Pos>{
		int x, y, breakCnt;

		public Pos(int x, int y, int breakCnt) {
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.breakCnt, o.breakCnt);
		}
	}
	
	public static void dijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		pq.offer(new Pos(0, 0, 0));
		counter[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Pos now = pq.poll();
			
			if(counter[now.x][now.y] < now.breakCnt) continue;
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(graph[nx][ny] == 0) {
					if(now.breakCnt < counter[nx][ny]) {
						counter[nx][ny] = now.breakCnt;
						pq.offer(new Pos(nx, ny, now.breakCnt));
					}
				}
				else {
					if(now.breakCnt + 1 < counter[nx][ny]) {
						counter[nx][ny] = now.breakCnt + 1;
						pq.offer(new Pos(nx, ny, now.breakCnt + 1));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		counter = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				graph[i][j] = s.charAt(j) - '0';
				counter[i][j] = INF;
			}
		}
		
		dijkstra();
		
		System.out.println(counter[N-1][M-1]);
		br.close();
	}

}