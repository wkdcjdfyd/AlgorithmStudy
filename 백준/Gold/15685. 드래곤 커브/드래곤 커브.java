import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 21.
@see			https://www.acmicpc.net/problem/15685
@performance	
@category 		#구현
@note			
*/

public class Main {
	static boolean[][] curve = new boolean[101][101];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static class Pos{
		int x, y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static Pos rotate(Pos target, Pos center) {
		int nx = center.x - (target.y - center.y);
		int ny = center.y + (target.x - center.x);
		
		return new Pos(nx, ny);
	}
	
	public static void makeDragonCurve(int x, int y, int dir, int gen) {
		ArrayList<Pos> dc = new ArrayList<>();
		dc.add(new Pos(x, y));
		curve[x][y] = true;
		
		dc.add(new Pos(x + dx[dir], y + dy[dir]));
		curve[x + dx[dir]][y + dy[dir]] = true;
		
		for(int g = 1; g <= gen; g++) {
			Pos point = dc.get(dc.size()-1);
			
			for(int i = dc.size() - 2; i >= 0; i--) {
				Pos nxt = rotate(dc.get(i), point);
				dc.add(nxt);
				curve[nxt.x][nxt.y] = true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			makeDragonCurve(x, y, d, g);
		}
		
		int ans = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(curve[i][j] && curve[i+1][j] && curve[i][j+1] && curve[i+1][j+1]) {
					ans++;
				} 
			}
		}
		
		System.out.println(ans);
		br.close();
	}

}