import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int L;
	static int R;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] graph;
	static int[][] visited;
	static ArrayList<Union> unions;
	
	static class Union {
		int startX;
		int startY;
		int popul;
		int countryCnt;
		int avg;
		ArrayList<int[]> loc = new ArrayList<>();
		
		public void setAvg() {
			this.avg = this.popul / this.countryCnt;
		}
	}
	
	public static void makeUnion(int a, int b, int unionCnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {a, b});
		visited[a][b] = unionCnt;
		Union u = unions.get(unionCnt - 1);
		u.popul += graph[a][b];
		u.countryCnt++;
		u.startX = a;
		u.startY = b;
		u.loc.add(new int[] {a, b});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				// 좌표를 벗어나면
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				// 이미 연합이 있으면
				if(visited[nx][ny] != 0) {
					continue;
				}
				int sub = Math.abs(graph[now[0]][now[1]] - graph[nx][ny]);
				if(L <= sub && sub <= R) {
					visited[nx][ny] = unionCnt;
					u.popul += graph[nx][ny];
					u.countryCnt++;
					u.loc.add(new int[] {nx, ny});
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	public static void movePopul() {
		for(int i = 0; i < unions.size(); i++) {
			Union now = unions.get(i);
			now.setAvg();
			
			for(int j = 0; j < now.countryCnt; j++) {
				int[] cor = now.loc.get(j);
				graph[cor[0]][cor[1]] = now.avg;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int year = 0;
		while(year <= 2000) {
			visited = new int[N][N];
			unions = new ArrayList<>();
			
			// 연합 찾기
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == 0) {
						unions.add(new Union());
						makeUnion(i, j, ++cnt);
					}
				}
			}
			// 연합의 개수가 나라의 개수와 같다면 인구이동이 일어나지 않음
			if(cnt == N * N) {
				System.out.println(year);
				break;
			}
			
			// 인구 이동
			movePopul();
			year++;
		}
		br.close();
	}

}