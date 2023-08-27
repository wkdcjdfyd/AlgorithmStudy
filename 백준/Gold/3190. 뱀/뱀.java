import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/3190
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, K, L;
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Snake snake;
	
	static class Loc{
		int x, y;

		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Snake{
		int dir;
		Deque<Loc> body;
		
		public Snake(Loc start, int dir) {
			this.dir = dir;
			this.body = new ArrayDeque<>();
			this.body.offer(start);
		}
		
		public void turnRight() {
			this.dir = (this.dir + 1) % 4;
		}
		
		public void turnLeft() {
			this.dir = (this.dir + 3) % 4;
		}
		
		public Loc getHead() {
			return body.peekLast();
		}
		
		public Loc getTail() {
			return body.peekFirst();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			graph[x][y] = -1;
		}
		
		L = Integer.parseInt(br.readLine());
		int[][] changeD = new int[L][2];
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			changeD[i][0] = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			if(d == 'D') {
				changeD[i][1] = 0;
			}
			else {
				changeD[i][1] = 1;
			}
		}
		
		snake = new Snake(new Loc(0, 0), 1);
		graph[0][0] = 1;
		
		int time = 0;
		int dirIdx = 0;
		while(++time < N * N) {
			Loc head = snake.getHead();
			int nx = head.x + dx[snake.dir];
			int ny = head.y + dy[snake.dir];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
				break;
			}
			if(graph[nx][ny] == 1) {
				break;
			}
			int nxtVal = graph[nx][ny];
			graph[nx][ny] = 1;
			snake.body.offerLast(new Loc(nx, ny));
			
			if(nxtVal != -1) {
				Loc tail = snake.getTail();
				graph[tail.x][tail.y] = 0;
				snake.body.pollFirst();
			}
			if(dirIdx < L && changeD[dirIdx][0] == time) {
				if(changeD[dirIdx][1] == 0) {
					snake.turnRight();
				}
				else {
					snake.turnLeft();
				}
				dirIdx++;
			}
		}
		System.out.println(time);
		br.close();
	}

}