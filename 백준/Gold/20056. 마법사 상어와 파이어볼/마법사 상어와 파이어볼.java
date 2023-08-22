import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/20056
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int N, M, K;
	static ArrayList<FireBall> fireBall;
	static int[][] graph;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static Deque<int[]> q = new ArrayDeque<>();
	
	static class FireBall {
		int x;
		int y;
		int m;
		int s;
		int d;
		
		public FireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
		public void move() {
			int nx = x + dx[d] * s;
			int ny = y + dy[d] * s;
			
			if(nx >= N) {
				nx = nx % N;
			}
			else if(nx < 0) {
				nx = (nx % N) + N;
				if(nx == N) {
					nx = 0;
				}
			}
			
			if(ny >= N) {
				ny = ny % N;
			}
			else if(ny < 0) {
				ny = (ny % N) + N;
				if(ny == N) {
					ny = 0;
				}
			}
			
			this.x = nx;
			this.y = ny;
		}
	}
	
	public static void moveFireBall() {
		for(FireBall now : fireBall) {
			graph[now.x][now.y]--;
			now.move();
			graph[now.x][now.y]++;
		}
	}
	
	public static void findDup() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(graph[i][j] >= 2) {
					q.offer(new int[] {i, j});
				}
			}
		}
	}
	
	public static void split() {
		while(!q.isEmpty()) {
			int[] loc = q.poll();
			ArrayList<Integer> dupBall = new ArrayList<>();
			int mSum = 0;
			int sSum = 0;
			boolean allEven = true;
			boolean allOdd = true;
			
			for(int i = 0; i < fireBall.size(); i++) {
				FireBall temp = fireBall.get(i);
				if(temp.x == loc[0] && temp.y == loc[1]) {
					mSum += temp.m;
					sSum += temp.s;
					dupBall.add(i);
					if(temp.d % 2 == 0) {
						allOdd = false;
					}
					else {
						allEven = false;
					}
				}
			}
			
			int m = (int)(mSum / 5);
			int s = (int)(sSum / dupBall.size());
			if(m != 0) {		
				if(allEven || allOdd) {
					for(int d = 0; d < 8; d = d+2) {
						fireBall.add(new FireBall(loc[0], loc[1], m, s, d));
					}
				}
				else {
					for(int d = 1; d < 9; d = d+2) {
						fireBall.add(new FireBall(loc[0], loc[1], m, s, d));
					}
				}
				graph[loc[0]][loc[1]] = 4;
			}
			else {
				graph[loc[0]][loc[1]] = 0;
			}
			
			for(int i = dupBall.size()-1; i >= 0; i--) {
				fireBall.remove((int)(dupBall.get(i)));
			}
		}
	}
	public static int messSum() {
		int sum = 0;
		for(FireBall fb : fireBall) {
			sum += fb.m;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new int[N][N];
		fireBall = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireBall.add(new FireBall(x-1, y-1, m, s, d));
			graph[x-1][y-1]++;
		}
		
		for(int i = 0; i < K; i++) {
			moveFireBall();
			findDup();
			split();
		}
		
		System.out.println(messSum());
		br.close();
	}

}