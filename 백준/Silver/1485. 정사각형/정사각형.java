import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 7.
@see			https://www.acmicpc.net/problem/1485
@performance	
@category 		#수학
@note			
*/

public class Main {
	static Point[] points;
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Line{
		Point start;
		Point end;
		double scale;
		double gradient;
		boolean isINF;
		
		public Line(Point start, Point end) {
			this.start = start;
			this.end = end;
			this.scale = calcScale();
			this.gradient = calcGradient();
		}
		
		public double calcScale() {
			return Math.sqrt(Math.pow((start.x - end.x), 2) + Math.pow((start.y - end.y), 2));
		}
		
		public double calcGradient() {
			if(start.x == end.x) {
				isINF = true;
				return 0;
			}
			else {
				isINF = false;
				return 1.0 * (start.y - end.y) / (start.x - end.x);
			}
		}
	}
	
	public static boolean valid(Line line1, Line line2) {
		if(line1.scale != line2.scale) return false;
		
		Line line3 = new Line(line1.start, line2.start);
		Line line4 = new Line(line1.start, line2.end);
		Line line5 = new Line(line1.end, line2.start);
		Line line6 = new Line(line1.end, line2.end);
		
		if(line3.scale == line4.scale && line4.scale == line5.scale && line5.scale == line6.scale) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		Loop:
		for(int t = 0; t < T; t++) {
			points = new Point[4];
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				points[i] = new Point(x, y);
			}
	
			Line line1 = new Line(points[0], points[1]);
			Line line2 = new Line(points[2], points[3]);
			if(valid(line1, line2)) {
				sb.append(1 + "\n");
				continue Loop;
			}
			
			line1 = new Line(points[0], points[2]);
			line2 = new Line(points[1], points[3]);
			if(valid(line1, line2)) {
				sb.append(1 + "\n");
				continue Loop;
			}
			
			line1 = new Line(points[0], points[3]);
			line2 = new Line(points[1], points[2]);
			if(valid(line1, line2)) {
				sb.append(1 + "\n");
				continue Loop;
			}
			
			sb.append(0 + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}