import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 21.
@see			https://www.acmicpc.net/problem/2234
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, M;
	static int[][] graph;
	static int[][] visited;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int[] roomSize;
	
	public static int bfs(int x, int y, int cnt) {
		Deque<int[]> q = new ArrayDeque<>();
		visited[x][y] = cnt;
		q.offer(new int[] {x, y});
		int size = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < 4; d++) {
				if(((graph[now[0]][now[1]] >> d) & 1) == 1) continue;
				
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
				if(visited[nx][ny] != 0) continue;
				visited[nx][ny] = cnt;
				size++;
				q.offer(new int[] {nx, ny});
			}
		}
		
		return size;
	}
	
	public static int getMaxMergeSize() {
		int max = -1;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				for(int d = 0; d < 4; d++) {
					if(((graph[i][j] >> d) & 1) == 0) continue;
					
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
					if(visited[i][j] == visited[nx][ny]) continue;
					
					int mergeSize = roomSize[visited[i][j]] + roomSize[visited[nx][ny]];
					max = Math.max(mergeSize, max);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[M][N];
		visited = new int[M][N];
		roomSize = new int[(M*N) + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int roomCnt = 0;
		int maxSize = -1;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] == 0) {
					int size = bfs(i, j, ++roomCnt);
					roomSize[roomCnt] = size;
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		
		int maxMergeSize = getMaxMergeSize();
		
		System.out.println(roomCnt);
		System.out.println(maxSize);
		System.out.println(maxMergeSize);
		br.close();
	}

}