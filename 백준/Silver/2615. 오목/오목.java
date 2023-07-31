import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1};
	static int[][] graph;
	
	public static boolean search(int x, int y, boolean isBlack) {
		// cnt - 연속된 돌 수를 세는 변수
		int cnt = 0;
		for(int d = 0; d < dx.length; d++) {
			for(cnt = 1; cnt < 7; cnt++) {
				int nx = x + dx[d] * cnt;
				int ny = y + dy[d] * cnt;
				
				// 좌표공간을 벗어날 때
				if(nx < 0 || ny < 0 || nx >= 19 || ny >= 19) {
					break;
				}
				// 검은돌 다음 검은돌이 안나오거나 흰돌 다음 흰돌이 안나올 때
				if(!(isBlack && graph[nx][ny] == 1) && !(!isBlack && graph[nx][ny] == 2)) {
					break;
				}
			}
			if(cnt == 5) {
				int nx = x - dx[d];
				int ny = y - dy[d];
				if(0 <= nx  && nx < 19 && 0 <= ny && ny < 19) {
					if((isBlack && graph[nx][ny] == 1) || (!isBlack && graph[nx][ny] == 2)) {
						continue;
					}
				}
				
				if(isBlack) {
					System.out.println(1);
				}
				else {
					System.out.println(2);
				}
				System.out.println((x+1) + " " + (y+1));
				return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		graph = new int[19][19];
		
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = false;
		
		OuterLoop:
		for(int j = 0; j < 19; j++) {
			for(int i = 0; i < 19; i++) {
				// 흰돌일 때
				if(graph[i][j] == 2) {
					if(flag = search(i, j, false)) {
						break OuterLoop;
					}
				}
				// 검은돌일 때
				else if(graph[i][j] == 1) {
					if(flag = search(i, j, true)) {
						break OuterLoop;
					}
				}
			}
		}
		if(!flag) {
			System.out.println(0);
		}
		
		br.close();
	}
}
