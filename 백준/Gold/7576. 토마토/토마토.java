import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	static int M;
	static int N;
	static int day;
	static int ripenCnt = 0;
	static int emptyCnt = 0;
	static int[][] graph;
	static Queue<int[]> q = new LinkedList<>();
	
	public static void ripening() {
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M) {
					if(graph[nx][ny] == 0) {
						graph[nx][ny] = graph[now[0]][now[1]] + 1;
						day = graph[nx][ny];
						ripenCnt++;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 1) {
					ripenCnt++;
					q.add(new int[] {i, j});
				}
				else if(graph[i][j] == -1) emptyCnt++;
			}
		}
		
		if(N * M - emptyCnt == ripenCnt) {
			System.out.println(0);
		}
		else {
			ripening();
			if(N * M - emptyCnt != ripenCnt) {
				System.out.println(-1);
			}
			else {
				System.out.println(day-1);
			}
		}
		br.close();
	}

}
