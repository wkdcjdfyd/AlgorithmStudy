import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 23.
@see			https://www.acmicpc.net/problem/21611
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int[][] idxGraph;
	static int[][] graph;
	static ArrayList<Marble> list;
	static HashMap<Integer, int[]> idxMap;
	static int N;
	static int M;
	static Shark shark;
	static int[] removeCnt = new int[4];
	
	public static void makeIdxGraph() {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int x = 0;
		int y = 0;
		int d = 0;
		int num = N*N - 1;
		idxMap.put(num, new int[] {x, y});
		idxGraph[x][y] = num--;
		
		
		while(num > 0) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(!isValid(nx, ny) || idxGraph[nx][ny] != 0) {
				d = (d+1) % 4;
				continue;
			}
			idxMap.put(num, new int[] {nx, ny});
			idxGraph[nx][ny] = num--;
			x = nx;
			y = ny;
		}
	}
	
	static class Marble{
		int num;
		
		public Marble(int num) {
			this.num = num;
		}
	}
	
	static class Shark{
		int x;
		int y;
		
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void blizzard(int d, int s) {
			if(list.size() <= 1) {
				return;
			}
			ArrayList<Integer> candi = new ArrayList<>();
			int[] dx = {0, -1, 1, 0, 0};
			int[] dy = {0, 0, 0, -1, 1};
			
			for(int i = 1; i <= s; i++) {
				int nx = this.x + dx[d] * i;
				int ny = this.y + dy[d] * i;
				
				if(!isValid(nx, ny)) {
					break;
				}
				if(graph[nx][ny] == 0) {
					continue;
				}
				int idx = idxGraph[nx][ny];
				candi.add(idx);
			}
			
			for(int i = candi.size()-1; i >= 0; i--) {
				list.remove((int)candi.get(i));
			}
		}
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) {
			return false;
		}
		return true;
	}
	
	public static void bomb() {
		ArrayList<int[]> candi = new ArrayList<>();
		
		if(list.size() <= 1) {
			return;
		}
		
		while(true) {
			boolean flag = false;
			int cnt = 1;
			int num = list.get(1).num;
			for(int i = 2; i < list.size(); i++) {
				Marble now = list.get(i);
				if(num != now.num) {
					if(cnt >= 4) {
						candi.add(new int[] {i-1, cnt, num});
						flag = true;
					}
					
					cnt = 1;
					num = now.num;
				}
				else {
					cnt++;
				}
			}
			if(cnt >= 4) {
				candi.add(new int[] {list.size()-1, cnt, num});
				flag = true;
			}
			if(!flag) {
				break;
			}
			
			for(int i = candi.size() - 1; i >= 0; i--) {
				int[] now = candi.get(i);
				removeCnt[now[2]] += now[1];
				for(int j = now[0]; j > now[0] - now[1]; j--) {
					list.remove(j);
				}
			}
			if(list.size() <= 1) {
				return;
			}
			candi.clear();
		}
	}
	
	public static void makeNewMarble() {
		if(list.size() <= 1) {
			return;
		}
		
		ArrayList<Marble> nxtList = new ArrayList<>();
		nxtList.add(new Marble(0));
		
		int cnt = 1;
		int num = list.get(1).num;
		boolean flag = false;
		
		Loop:
		for(int i = 2; i < list.size(); i++) {
			Marble now = list.get(i);
			if(num != now.num) {
				nxtList.add(new Marble(cnt));
				nxtList.add(new Marble(num));
				if(nxtList.size() == N * N) {
					flag = true;
					break Loop;
				}
				
				cnt = 1;
				num = now.num;
			}
			else {
				cnt++;
			}
		}
		if(!flag) {
			nxtList.add(new Marble(cnt));
			nxtList.add(new Marble(num));
		}
		
		list = nxtList;
		
		int i = 1;
		for(; i < nxtList.size(); i++) {
			int[] loc = idxMap.get(i);
			graph[loc[0]][loc[1]] = list.get(i).num;
		}
		for(; i < N * N; i++) {
			int[] loc = idxMap.get(i);
			graph[loc[0]][loc[1]] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		idxGraph = new int[N][N];
		list = new ArrayList<>(N*N);
		idxMap = new HashMap<>();
		shark = new Shark(N/2, N/2);
		
		makeIdxGraph();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		list.add(new Marble(0));
		for(int i = 1; i < N*N; i++) {
			int[] loc = idxMap.get(i);
			if(graph[loc[0]][loc[1]] == 0) {
				break;
			}
			list.add(new Marble(graph[loc[0]][loc[1]]));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			shark.blizzard(d, s);
			bomb();
			makeNewMarble();
		}
		
		int result = removeCnt[1] + 2 * removeCnt[2] + 3 * removeCnt[3];
		System.out.println(result);
		br.close();
	}

}