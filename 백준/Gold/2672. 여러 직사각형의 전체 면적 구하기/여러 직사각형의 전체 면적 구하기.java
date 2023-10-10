import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 11.
@see			https://www.acmicpc.net/problem/2672
@performance	
@category 		#스위핑?
@note			스위핑 알고리즘을 처음 알게 됐다.
*/

public class Main {
	
	static class Vertical implements Comparable<Vertical>{
		int x, y1, y2;
		boolean isStart;
		
		public Vertical(int x, int y1, int y2, boolean isStart) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Vertical o) {
			return Integer.compare(x, o.x);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Vertical[] v = new Vertical[N*2];
		
		for(int i = 0; i < N*2; i+=2) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = (int)(Double.parseDouble(st.nextToken()) * 10);
			int y = (int)(Double.parseDouble(st.nextToken()) * 10);
			int w = (int)(Double.parseDouble(st.nextToken()) * 10);
			int h = (int)(Double.parseDouble(st.nextToken()) * 10);
			
			v[i] = new Vertical(x, y, y+h, true);
			v[i+1] = new Vertical(x+w, y, y+h, false);
		}
		Arrays.sort(v);
		
		int[] y = new int[20001];
		int lx = 0;
		int area = 0;
		for(int i = 0; i < N*2; i++) {
			int len = 0;
			for(int j = 1; j < y.length; j++) {
				if(y[j] > 0) len++;
			}
			area += (v[i].x - lx) * len;
			
			for(int j = v[i].y1+1; j <= v[i].y2; j++) {
				if(v[i].isStart) {
					y[j]++;
				}
				else {
					y[j]--;
				}
			}
			lx = v[i].x;
		}
		
		double ans = 1.0 * area / 100;
		if(ans == (int)ans) {
			System.out.println((int)ans);
		}
		else {
			System.out.println(ans);
		}
		br.close();
	}

}