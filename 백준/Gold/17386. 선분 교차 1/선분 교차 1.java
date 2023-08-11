import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 11.
@see			https://www.acmicpc.net/problem/17386
@performance
@category 		#
@note			CCW 알고리즘을 처음 알았다.
*/

public class Main {
	
	static class Point {
		long x;
		long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static int ccw(Point p1, Point p2, Point p3) {
		long num = (p1.x*p2.y + p2.x*p3.y + p3.x*p1.y) - (p2.x*p1.y + p3.x*p2.y + p1.x*p3.y);
		if(num > 0) {
			return 1;
		}
		else if(num < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long x;
		long y;

		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Point p1 = new Point(x, y);
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Point p2 = new Point(x, y);
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Point p3 = new Point(x, y);
		
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		Point p4 = new Point(x, y);
		
		if((ccw(p1, p2, p3) * ccw(p1, p2, p4) <= 0) && ccw(p3, p4, p1) * ccw(p3, p4, p2) <= 0) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		
		br.close();
	}

}