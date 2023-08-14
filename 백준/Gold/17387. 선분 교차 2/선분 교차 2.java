import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 14.
@see			https://www.acmicpc.net/problem/17387
@performance	
@category 		#선분교차검증
@note			
*/

public class Main {
	
	static class Point implements Comparable<Point>{
		long x;
		long y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if(this.x == o.x) {
				if(this.y < o.y) {
					return -1;
				}
				else if(this.y == o.y){
					return 0;
				}
				else {
					return 1;
				}
			}
			else if(this.x < o.x){
				return -1;
			}
			else {
				return 1;
			}
		}	
	}
	
	static class Line {
		Point p1;
		Point p2;
		
		public Line(Point p1, Point p2) {
			if(p1.compareTo(p2) == 1) {
				this.p1 = p2;
				this.p2 = p1;
			}
			else {
				this.p1 = p1;
				this.p2 = p2;
			}
			
		}
	}
	
	public static int ccw(Point p1, Point p2, Point p3) {
		long op = (p1.x*p2.y + p2.x*p3.y + p3.x*p1.y) - (p2.x*p1.y + p3.x*p2.y + p1.x*p3.y);
		if(op > 0) {
			return 1;
		}
		else if(op < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	public static boolean isCross(Line line1, Line line2) {
		int op1 = ccw(line1.p1, line1.p2, line2.p1) * ccw(line1.p1, line1.p2, line2.p2);
		int op2 = ccw(line2.p1, line2.p2, line1.p1) * ccw(line2.p1, line2.p2, line1.p2);
	
		if(op1 == 0 && op2 == 0) {
			if(line1.p1.compareTo(line2.p1) != 1 && line1.p2.compareTo(line2.p1) != -1) {
				return true;
			}
			if(line2.p1.compareTo(line1.p1) != 1 && line2.p2.compareTo(line1.p1) != -1) {
				return true;
			}
			return false;
		}
		else if (op1 <= 0 && op2 <= 0){
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		Line line1 = new Line(new Point(x1, y1), new Point(x2, y2));
		
		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int x4 = Integer.parseInt(st.nextToken());
		int y4 = Integer.parseInt(st.nextToken());
		Line line2 = new Line(new Point(x3, y3), new Point(x4, y4));
		
		if(isCross(line1, line2)) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		br.close();
	}

}