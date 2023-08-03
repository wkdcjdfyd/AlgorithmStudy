import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static int N;
	static int[][] graph;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void makeResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(graph[i][j] == -1) {
					sb.append(".");
				}
				else {
					sb.append("O");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		graph = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				if(s.charAt(j) == '.') {
					graph[i][j] = -1;
				}
				else {
					graph[i][j] = 0;
				}
			}
		}
		if(N == 1) {
			makeResult();
			br.close();
			return;
		}
		
		int clock = 1;
		while(clock < N) {
			if(clock % 2 == 1) {
				clock++;
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(graph[i][j] == -1) {
							graph[i][j] = clock;
						}
					}
				}
			}
			else {
				clock++;
				ArrayList<int[]> lastClear = new ArrayList<>();
				for(int i = 0; i < R; i++) {
					for(int j = 0; j < C; j++) {
						if(graph[i][j] == clock-3) {
							lastClear.add(new int[] {i, j});
							for(int d = 0; d < dx.length; d++) {
								int nx = i + dx[d];
								int ny = j + dy[d];
								
								if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
									continue;
								}
								if(graph[nx][ny] != -1) {
									lastClear.add(new int[] {nx, ny});
								}
							}
						}
					}
				}
				for(int[] now : lastClear) {
					graph[now[0]][now[1]] = -1;
				}
			}
		}
		makeResult();
		br.close();
	}

}