import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 6.
@see			https://www.acmicpc.net/problem/1064
@performance	
@category 		#수학
@note			
*/

public class Main {
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Line implements Comparable<Line>{
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

		@Override
		public int compareTo(Line o) {
			return Double.compare(o.scale, this.scale);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		
		Line[] lines = new Line[3];
		lines[0] = new Line(new Point(x1, y1), new Point(x2, y2));
		lines[1] = new Line(new Point(x1, y1), new Point(x3, y3));
		lines[2] = new Line(new Point(x2, y2), new Point(x3, y3));
		Arrays.sort(lines);
		
		if(lines[0].isINF || lines[1].isINF || lines[2].isINF) {
			if(lines[0].isINF && lines[1].isINF && lines[2].isINF) {
				System.out.println(-1.0);
				br.close();
				return;
			}
		}
		else if(lines[0].gradient == lines[1].gradient && lines[1].gradient == lines[2].gradient) {
			System.out.println(-1.0);
			br.close();
			return;
		}
		
		double sum = (lines[0].scale - lines[2].scale) * 2;
		System.out.println(sum);
		br.close();
	}

}