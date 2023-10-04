import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 4.
@see			https://www.acmicpc.net/problem/17472
@performance	
@category 		#구현 #MST
@note			
*/

public class Main {
	static int N, M;
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Bridge> bridge;
	static int[] parents;
	
	static class Bridge implements Comparable<Bridge>{
		int v1, v2, length;

		public Bridge(int v1, int v2, int length) {
			this.v1 = v1;
			this.v2 = v2;
			this.length = length;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.length, o.length);
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px != py) {
			parents[px] = py;
			return true;
		}
		return false;
	}
	
	public static void checkIsland(int x, int y, int num) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		graph[x][y] = num;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];
				
				if(!isValid(nx, ny)) continue;
				if(graph[nx][ny] == -1) {
					graph[nx][ny] = num;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static void makeBridge(int x, int y) {
		for(int d = 0; d < dx.length; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isValid(nx, ny)) continue;
			if(graph[nx][ny] == 0) {
				int cnt = 1;
				int a = nx;
				int b = ny;
				int na = a;
				int nb = b;
				boolean valid = false;
				
				while(true) {
					na = a + dx[d];
					nb = b + dy[d];
					
					if(!isValid(na, nb)) break;
					if(graph[na][nb] != 0) {
						if(graph[na][nb] != graph[x][y] && cnt >= 2) {
							valid = true;
						}
						break;
					}
					
					cnt++;
					a = na;
					b = nb;
				}
				
				if(valid) {
					bridge.add(new Bridge(graph[x][y], graph[na][nb], cnt));
				}
			}
			
		}
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		bridge = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					graph[i][j] = -1;
				}
			}
		}
		
		// 각 섬의 영역에 번호를 매김
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] == -1) {
					checkIsland(i, j, ++cnt);
				}
			}
		}
		
		// 만들 수 있는 모든 다리의 목록을 만듬
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(graph[i][j] != 0) {
					makeBridge(i, j);
				}
			}
		}
		
		// 모든 섬 연결하기
		Collections.sort(bridge);
		parents = new int[cnt+1];
		for(int i = 1; i < cnt+1; i++) {
			parents[i] = i;
		}
		
		int total = 0;
		int connectCount = 0;
		
		for(int i = 0; i < bridge.size(); i++) {
			Bridge now = bridge.get(i);
			
			if(union(now.v1, now.v2)) {
				total += now.length;
				if(++connectCount == cnt-1) {
					System.out.println(total);
					return;
				}
			}
		}

		System.out.println(-1);
		br.close();
	}

}