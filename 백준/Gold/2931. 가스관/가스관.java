import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 21.
@see			https://www.acmicpc.net/problem/2931
@performance	11824kb 88ms
@category 		#구현 # 시뮬레이션
@note			+ 일 때 처리방식이 미흡했다.
*/


public class Main {
	static int R, C;
	static char[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] lastM;				// M으로부터 시작한 길의 마지막 위치를 저장
	static int[] lastZ;				// Z으로부터 시작한 길의 마지막 위치를 저장
	
	// 길을 타고 가면서 끝이 어딘지 확인하는 메서드
	public static int[] drive(int x, int y) {
		int nx = x;
		int ny = y;
		int d = 0;
		
		// M이나 Z에서 시작할 때 다음 길이 어디있는지 찾는다
		for(d = 0; d < dx.length; d++) {
			nx = x + dx[d];
			ny = y + dy[d];
			
			if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			}
			if(graph[nx][ny] == '.' || graph[nx][ny] == 'M' || graph[nx][ny] == 'Z') {
				continue;
			}
			break;
		}
		
		// 길을 못찾았으면 제자리 return
		if(nx == x && ny == y) {
			return new int[] {x, y, d};
		}
		
		// 이어진 길이 없을 때까지 계속 길을 타고 이동한다
		while(graph[nx][ny] != '.') {
			d = nxtD(graph[nx][ny], d);
			int nnx = nx + dx[d];
			int nny = ny + dy[d];
			
			if(graph[nnx][nny] == '.') {
				break;
			}
			nx = nnx;
			ny = nny;
		}
		return new int[] {nx, ny, d};
	}
	
	// 길을 타고 이동할 때 현재 방향과 블럭에 따라 다음 방향을 알려주는 메서드
	public static int nxtD(char cmd, int dir) {
		switch(cmd) {
			case '|':
				return dir;
			case '-':
				return dir;
			case '+':
				return dir;
			case '1':
				return dir == 0 ? 1 : 2;
			case '2':
				return dir == 2 ? 1 : 0;
			case '3':
				return dir == 1 ? 0 : 3;
			case '4':
				return dir == 1 ? 2 : 3;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		graph = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				graph[i][j] = s.charAt(j);
				if(graph[i][j] == 'M') {
					lastM = new int[] {i, j, 0};
				}
				else if(graph[i][j] == 'Z') {
					lastZ = new int[] {i, j, 0};
				}
			}
		}
		
		
		lastM = drive(lastM[0], lastM[1]);	// M에서 시작한 길의 마지막 위치
		lastZ = drive(lastZ[0], lastZ[1]);	// Z에서 시작한 길의 마지막 위치
		int d1 = lastM[2];					// M에서 마지막 길을 탐색하던 방향
		int d2 = lastZ[2];					// Z에서 마지막 길을 탐색하던 방향
		
		// 마지막 위치에서 방향을 따라 한칸 더 이동하면 채워야할 빈칸의 위치이다
		int resultX = lastM[0] + dx[lastM[2]];
		int resultY = lastM[1] + dy[lastM[2]];
		
		// 채워야할 빈 칸 주변 4방향 탐색
		// 4방향 모두에 길이 있다면 + 블럭을 설치해야 한다.
		boolean plusFlag = true;
		for(int i = 0; i < dx.length; i++) {
			int nx = resultX + dx[i];
			int ny = resultY + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= R || ny >= C || graph[nx][ny] == '.' || graph[nx][ny] == 'M' || graph[nx][ny] == 'Z') {
				plusFlag = false;
				break;
			}
		}
		
		// 방향에 따라 맞는 블럭을 설치
		if(plusFlag) {
			sb.append(String.format("%d %d +\n", resultX+1, resultY+1));
		}
		else if((d1 == 0 && d2 == 2) || (d1 == 2 && d2 == 0)) {
			sb.append(String.format("%d %d |\n", resultX+1, resultY+1));
		}
		else if((d1 == 1 && d2 == 3) || (d1 == 3 && d2 == 1)) {
			sb.append(String.format("%d %d -\n", resultX+1, resultY+1));
		}
		else if((d1 == 0 && d2 == 3) || (d1 == 3 && d2 == 0)) {
			sb.append(String.format("%d %d 1\n", resultX+1, resultY+1));
		}
		else if((d1 == 2 && d2 == 3) || (d1 == 3 && d2 == 2)) {
			sb.append(String.format("%d %d 2\n", resultX+1, resultY+1));
		}
		else if((d1 == 1 && d2 == 2) || (d1 == 2 && d2 == 1)) {
			sb.append(String.format("%d %d 3\n", resultX+1, resultY+1));
		}
		else if((d1 == 1 && d2 == 0) || (d1 == 0 && d2 == 1)) {
			sb.append(String.format("%d %d 4\n", resultX+1, resultY+1));
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}