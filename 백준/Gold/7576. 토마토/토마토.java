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
	static int unRipenCnt = 0;
	static int[][] graph;
	static Queue<Pair> q = new LinkedList<>();
	
	static class Pair {
		int x, y;
		public Pair(int a, int b) {
			x = a;
			y = b;
		}
	}
	
	public static void ripening() {
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M || graph[nx][ny] != 0) {
					continue;
				}
				graph[nx][ny] = graph[now.x][now.y] + 1;
				day = graph[nx][ny];
				unRipenCnt--;
				q.add(new Pair(nx, ny));
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
				if(graph[i][j] == 1) q.add(new Pair(i, j));
				if(graph[i][j] == 0) unRipenCnt++;
			}
		}
		
		if(unRipenCnt == 0) {
			System.out.println(0);
		}
		else {
			ripening();
			if(unRipenCnt != 0) {
				System.out.println(-1);
			}
			else {
				System.out.println(day-1);
			}
		}
		br.close();
	}

}