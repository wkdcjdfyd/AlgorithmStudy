import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 9.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc
@performance	38488kb 182ms
@category 		#BFS
@note			map을 사용해서 특정 객체 정보 관리 -> 접근 속도 향상
*/

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;
	static int[][] graph;
	static int[][] visited;
	static HashMap<Integer, Loc> map = new HashMap<>();
	static int maxRoomNum;
	static int maxRoomCnt;
	
	public static void bfs(int roomNum, Loc start, int val) {
		Deque<Loc> q = new LinkedList<>();
		q.offerLast(start);
		visited[start.x][start.y] = val;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Loc now = q.pollFirst();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				if(visited[nx][ny] != 0) {
					continue;
				}
				if(graph[now.x][now.y] + 1 == graph[nx][ny]) {
					visited[nx][ny] = val;
					cnt++;
					q.offerLast(new Loc(nx, ny));
				}
			}
		}
		if(maxRoomCnt < cnt) {
			maxRoomCnt = cnt;
			maxRoomNum = roomNum;
		}
	}
	
	static class Loc{
		int x;
		int y;
		
		public Loc (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			visited = new int[N][N];
			maxRoomNum = -1;
			maxRoomCnt = 1;
		
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					map.put(graph[i][j], new Loc(i, j));
				}
			}
			int val = 0;
			for(int i = 1; i < N*N + 1; i++) {
				Loc now = map.get(i);
				if(visited[now.x][now.y] == 0) {
					bfs(i, now, ++val);
				}
			}
			sb.append("#" + t + " " + maxRoomNum + " " + maxRoomCnt + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}