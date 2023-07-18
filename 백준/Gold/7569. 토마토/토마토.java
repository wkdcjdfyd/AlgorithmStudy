import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Tomato {
	int x;
	int y;
	int z;
	
	public Tomato(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main {
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0 ,-1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		int H = sc.nextInt();
		
		int[][][] box = new int[H][N][M];
		
		Queue<Tomato> q = new LinkedList<Tomato>();
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					box[i][j][k] = sc.nextInt();
					if(box[i][j][k] == 1) {
						q.add(new Tomato(i, j, k));
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
						box[nx][ny][nz] = box[now.x][now.y][now.z] + 1;
						q.add(new Tomato(nx, ny, nz));
					}
				}
			}
		}
		
		int ans = 0;
		
		Loop1: for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(box[i][j][k] == 0) {
						System.out.println(-1);
						ans = -1;
						break Loop1;
					}
					if(box[i][j][k] > ans) {
						ans = box[i][j][k];
					}
				}
			}
		}
		if(ans != -1) {
			System.out.println(ans-1);
		}
	}

}
