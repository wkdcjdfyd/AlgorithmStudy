import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 12.
@see			https://www.acmicpc.net/problem/1358
@performance	
@category 		#기하학
@note			
*/

public class Main {
	static int[] circle1;
	static int[] circle2;
	static int W, H, X, Y, P, R;
	
	public static boolean isIn(int x, int y) {
		double dist1 = Math.sqrt(Math.pow(circle1[0] - x, 2) + Math.pow(circle1[1] - y, 2));
		if(dist1 <= R) return true;
		
		double dist2 = Math.sqrt(Math.pow(circle2[0] - x, 2) + Math.pow(circle2[1] - y, 2));
		if(dist2 <= R) return true;
		
		if(X <= x && x <= X + W && Y <= y && y <= Y + H) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		R = H / 2;
		
		circle1 = new int[] {X, Y + R};
		circle2 = new int[] {X + W, Y + R};
		
		int cnt = 0;
		for(int p = 0; p < P; p++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(isIn(x, y)) cnt++;
		}
		
		System.out.println(cnt);
		br.close();
	}

}