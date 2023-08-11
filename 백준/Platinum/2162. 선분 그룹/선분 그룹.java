import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 11.
@see			https://www.acmicpc.net/problem/2162
@performance	
@category 		#선분교차판정 #Union-Find
@note			선분 교차 판정 응용하기
*/

public class Main {
	static int N;
	static ArrayList<Line> lines = new ArrayList<>();
	static int[] parent;
	
	static class Point implements Comparable<Point>{
		long x;
		long y;
		
		public Point(long x, long y) {
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
		Point start;
		Point end;
		
		public Line(Point start, Point end) {
			if(start.compareTo(end) == 1) {
				this.start = end;
				this.end = start;
			}
			else {
				this.start = start;
				this.end = end;
			}
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
	
	public static boolean isCross(Line line1, Line line2) {
		int val1 = ccw(line1.start, line1.end, line2.start) * ccw(line1.start, line1.end, line2.end);
		int val2 = ccw(line2.start, line2.end, line1.start) * ccw(line2.start, line2.end, line1.end);
	
		if(val1 == 0 && val2 == 0) {
			if(line1.start.compareTo(line2.start) != 1 && line1.end.compareTo(line2.start) != -1) {
				return true;
			}
			if(line2.start.compareTo(line1.start) != 1 && line2.end.compareTo(line1.start) != -1) {
				return true;
			}
			return false;
		}
		else if (val1 <= 0 && val2 <= 0){
			return true;
		}
		return false;
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		int nx = find(x);
		int ny = find(y);
		
		if(nx != ny) {
			parent[nx] = ny;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			lines.add(new Line(new Point(x1, y1), new Point(x2, y2)));
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				if(find(i) == find(j)) {
					continue;
				}
				if(isCross(lines.get(i), lines.get(j))) {
					union(i, j);
				}
			}
		}
		for(int i = 0; i < N; i++) {
			parent[i] = find(parent[i]);
		}
		
		int[] cntNums = new int[N];
		for(int idx : parent) {
			cntNums[idx]++;
		}
		int max = 0;
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(cntNums[i] != 0) {
				cnt++;
			}
			max = Math.max(max, cntNums[i]);
		}
		
		System.out.println(String.format("%d\n%d", cnt, max));
		br.close();
	}

}