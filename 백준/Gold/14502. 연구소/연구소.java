import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 3.
@see			https://www.acmicpc.net/problem/14502
@performance		116796kb 320ms
@category 		#구현 #그래프이론 #브루트포스
@note			새로 만드는 벽의 개수가 3개 밖에 안돼서 for문을 사용해 3중 for문으로 구현하면 더 간단하다. 하지만 조합 연습을 하고 싶었다!
*/


public class Main {
	static int N;
	static int M;
	static int max = 0;
	static int[][] graph;
	static int[][] virusGraph;
	static int[][] blankCords;
	static int blankSize;
	static int[][] virusCords;
	static int virusSize;
	
	public static void makeWall(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			// 선택된 벽을 세운다
			for(int idx: choosed) {
				int[] now = blankCords[idx];
				graph[now[0]][now[1]] = 1;
			}
			
			spreadVirus();
			
			// 세운 벽을 다시 허문다
			for(int idx: choosed) {
				int[] now = blankCords[idx];
				graph[now[0]][now[1]] = 0;
			}
			return;
		}
		for(int i = startIdx; i < blankSize; i++) {
			choosed[nth] = i;
			makeWall(nth+1, i+1, choosed);
		}
	}
	
	public static void spreadVirus() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				virusGraph[i][j] = graph[i][j];
			}
		}
		int cnt = blankSize - 3;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0; i < virusSize; i++) {
			q.add(virusCords[i]);
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				if(virusGraph[nx][ny] == 0) {
					virusGraph[nx][ny] = 2;
					cnt--;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		if(max < cnt) {
			max = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		virusGraph = new int[N][M];
		blankCords = new int[N*M][2];
		virusCords = new int[N*M][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0) {
					blankCords[blankSize][0] = i;
					blankCords[blankSize][1] = j;
					blankSize++;
				}
				else if(graph[i][j] == 2) {
					virusCords[virusSize][0] = i;
					virusCords[virusSize][1] = j;
					virusSize++;
				}
			}
		}
		makeWall(0, 0, new int[3]);
		System.out.println(max);
	}

}
