import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0 ,-1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	static class Tomato {
		int x;
		int y;
		int z;
		
		Tomato(int x, int y, int z){
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] box = new int[H][N][M];
		
		Queue<Tomato> q = new LinkedList<Tomato>();
		int tomatoCnt = 0;
		int emptyCnt = 0;
		int ans = 1;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				String[] row = br.readLine().split(" ");
				for(int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(row[k]);
					if(box[i][j][k] == 1) {
						q.add(new Tomato(i, j, k));
						tomatoCnt++;
					}
					else if(box[i][j][k] == -1) {
						emptyCnt++;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Tomato now = q.poll();
			
			for(int i = 0; i < 6; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int nz = now.z + dz[i];
				
				if(0 <= nx && nx < H && 0 <= ny && ny < N && 0 <= nz && nz < M) {
					if(box[nx][ny][nz] == 0) {
						ans = box[now.x][now.y][now.z] + 1;
						box[nx][ny][nz] = ans;
						tomatoCnt++;
						q.add(new Tomato(nx, ny, nz));
					}
				}
			}
		}
		
		if(tomatoCnt == (H*N*M) - emptyCnt) {
			System.out.println(ans-1);
		}
		else {
			System.out.println(-1);
		}
	}

}