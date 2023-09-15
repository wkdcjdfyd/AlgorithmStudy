import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 15.
@see			https://www.acmicpc.net/problem/4386
@performance	
@category 		#MST
@note			
*/

public class Main {
	static int N;
	static Point[] point;
	static int[] parents;
	
	static class Point {
		int idx;
		float x, y;
		
		public Point(int idx, float x, float y) {
			this.idx = idx;
			this.x = x;
			this.y = y;
		}
	}
	
	static class Line implements Comparable<Line>{
		int from;
		int to;
		float dist;
		
		public Line(int from, int to) {
			this.from = from;
			this.to = to;
			this.dist = getDist(from, to);
		}
		
		public float getDist(int p1, int p2) {
			return (float)Math.sqrt(Math.pow(point[p1].x-point[p2].x, 2) + Math.pow(point[p1].y-point[p2].y, 2));
		}

		@Override
		public int compareTo(Line o) {
			return Float.compare(this.dist, o.dist);
		}
	}
	
	public static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		parents[px] = py;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		point = new Point[N];
		parents = new int[N];
		ArrayList<Line> lines = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());
			point[i] = new Point(i, x, y);
			parents[i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				lines.add(new Line(point[i].idx, point[j].idx));
			}
		}
		
		Collections.sort(lines);
		float total = 0;
		int cnt = 0;
		for(Line line : lines) {
			if(union(line.to, line.from)) {
				total += line.dist;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.printf("%.2f", total);
		br.close();
	}

}