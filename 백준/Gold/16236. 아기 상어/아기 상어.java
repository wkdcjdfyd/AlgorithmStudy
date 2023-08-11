import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 11.
@see			https://www.acmicpc.net/problem/16236
@performance		12184kb 88ms
@category 		#구현 #시뮬레이션
@note
*/

public class Main {
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	static int fishCnt = 0;
	static BabyShark babyShark;
	static Deque<Loc> q = new ArrayDeque<>();
	static PriorityQueue<Loc> pq = new PriorityQueue<>();
	
	static class Loc implements Comparable<Loc>{
		int x;
		int y;
		int time;
		
		public Loc(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Loc o) {
			if(this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}
	}
	
	static class BabyShark{
		int x;
		int y;
		int scale;
		int eatFishNum;
		int moveTime;
		
		public BabyShark(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.scale = 2;			// 처음 크기는 2
			this.eatFishNum = 0;
			this.moveTime = 0;
		}
		
		public void setLoc(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void increaseEatFishNum() {
			babyShark.eatFishNum++;
			if(babyShark.eatFishNum == babyShark.scale) {
				babyShark.scale++;
				babyShark.eatFishNum = 0;
			}
		}
		
		public void eat(Loc loc) {
			graph[loc.x][loc.y] = 0;
			increaseEatFishNum();
			fishCnt--;
			this.moveTime += loc.time;
			setLoc(loc.x, loc.y);
		}
	}
	
	public static boolean find() {
		int size = q.size();
		boolean canEat = false;
		
		if(!pq.isEmpty()) {
			pq.clear();
		}
		
		while(--size >= 0) {
			Loc now = q.pollFirst();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				// 먹을 수 있는 물고기
				if(graph[nx][ny] != 0 && graph[nx][ny] < babyShark.scale) {
					canEat = true;
					
					// 먹을 수 있는 물고기 후보 저장
					pq.offer(new Loc(nx, ny, now.time+1));
				}
				// 이동 가능한 좌표들 큐에 넣기
				// 빈 공간 or 크기가 같은 물고기 칸
				if(graph[nx][ny] == 0 || graph[nx][ny] == babyShark.scale) {
					if(!visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Loc(nx, ny, now.time+1));
					}
				}					
			}
		}
		return canEat;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 9) {
					babyShark = new BabyShark(i, j);
					graph[i][j] = 0;
				}
				else if(graph[i][j] != 0) {
					fishCnt++;
				}
			}
		}
		if(fishCnt == 0) {
			System.out.println(0);
		}
		else {
			q.offerLast(new Loc(babyShark.x, babyShark.y, 0));
			visited[babyShark.x][babyShark.y] = true;
			int cnt = 0;
			while(fishCnt > 0) {
				if(cnt > N*N) {
					break;
				}
				if(find()) {
					Loc fish = pq.poll();
					babyShark.eat(fish);
					
					for(int i = 0; i < N; i++) {
						for(int j = 0; j < N; j++) {
							visited[i][j] = false;
						}
					}
					q.clear();
					q.offerLast(new Loc(babyShark.x, babyShark.y, 0));
					visited[babyShark.x][babyShark.y] = true;
					cnt = 0;
				}
				else if(q.isEmpty()) {
					break;
				}
				cnt++;
			}
			
			System.out.println(babyShark.moveTime);
		}
		br.close();
	}

}
