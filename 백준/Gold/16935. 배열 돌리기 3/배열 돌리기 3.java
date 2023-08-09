import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 9.
@see			https://www.acmicpc.net/problem/16935
@performance		60028kb	412ms
@category 		#구현
@note
*/

public class Main {
	static int N;
	static int M;
	static int R;
	
	public static int[][] switchUpDown(int[][] src) {
		int[][] target = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				target[N-i-1][j] = src[i][j];
			}
		}
		return target;
	}
	
	public static int[][] switchLeftRight(int[][] src) {
		int[][] target = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				target[i][M-j-1] = src[i][j];
			}
		}
		return target;
	}
	
	public static int[][] rotate90(int[][] src) {
		int[][] target = new int[M][N];
		
		for(int j = 0; j < M; j++) {
			for(int i = N-1; i >= 0; i--) {
				target[j][N-1-i] = src[i][j];
			}
		}
		return target;
	}
	
	public static int[][] counterRotate90(int[][] src) {
		int[][] target = new int[M][N];
		
		for(int j = M-1; j >= 0; j--) {
			for(int i = 0; i < N; i++) {
				target[M-1-j][i] = src[i][j];
			}
		}
		return target;
	}
	
	public static int[][] groupRotate(int[][] src){
		int[][] target = new int[N][M];
		int scaleX = N / 2;
		int scaleY = M / 2;
		
		for(int i = 0; i < scaleX; i++) {
			for(int j = 0; j < scaleY; j++) {
				target[i][j+scaleY] = src[i][j];
			}
		}
		for(int i = 0; i < scaleX; i++) {
			for(int j = scaleY; j < M; j++) {
				target[i+scaleX][j] = src[i][j];
			}
		}
		for(int i = scaleX; i < N; i++) {
			for(int j = scaleY; j < M; j++) {
				target[i][j-scaleY] = src[i][j];
			}
		}
		for(int i = scaleX; i < N; i++) {
			for(int j = 0; j < scaleY; j++) {
				target[i-scaleX][j] = src[i][j];
			}
		}
		
		return target;
	}
	
	public static int[][] groupCounterRotate(int[][] src){
		int[][] target = new int[N][M];
		int scaleX = N / 2;
		int scaleY = M / 2;
		
		for(int i = 0; i < scaleX; i++) {
			for(int j = 0; j < scaleY; j++) {
				target[i+scaleX][j] = src[i][j];
			}
		}
		for(int i = scaleX; i < N; i++) {
			for(int j = 0; j < scaleY; j++) {
				target[i][j+scaleY] = src[i][j];
			}
		}
		for(int i = scaleX; i < N; i++) {
			for(int j = scaleY; j < M; j++) {
				target[i-scaleX][j] = src[i][j];
			}
		}
		for(int i = 0; i < scaleX; i++) {
			for(int j = scaleY; j < M; j++) {
				target[i][j-scaleY] = src[i][j];
			}
		}
		return target;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N][M];
		int[][] now = graph;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int temp = 0;
		st = new StringTokenizer(br.readLine());
		for(int r = 0; r < R; r++) {
			int order = Integer.parseInt(st.nextToken());
			
			switch(order) {
			case 1:
				now = switchUpDown(now);
				break;
			case 2:
				now = switchLeftRight(now);
				break;
			case 3:
				now = rotate90(now);
				temp = N;
				N = M;
				M = temp;
				break;
			case 4:
				now = counterRotate90(now);
				temp = N;
				N = M;
				M = temp;
				break;
			case 5:
				now = groupRotate(now);
				break;
			case 6:
				now = groupCounterRotate(now);
				break;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(now[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
