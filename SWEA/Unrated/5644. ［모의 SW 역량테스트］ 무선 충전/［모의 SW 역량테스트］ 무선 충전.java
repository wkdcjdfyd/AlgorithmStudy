import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 17.
@see			
@performance	
@category 		#
@note			
*/

public class Solution {
	static int[] dy = {0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 1, 0, -1};
	static boolean[][] graph = new boolean[11][11];
	static int M, K;
	static Person a;
	static Person b;
	static BC[] bc;
	
	static class Loc{
		int y;
		int x;
		
		public Loc(int x, int y) {
			super();
			this.y = y;
			this.x = x;
		}
	}
	
	static class Person{
		Loc loc;
		int time;
		int[] route;
		int chargeSum;
		
		public Person(Loc loc, int[] route) {
			super();
			this.loc = loc;
			this.time = 1;
			this.route = route;
			this.chargeSum = 0;
		}
		
		public void move() {
			int d = route[-1 + this.time];
			this.loc.x += dx[d];
			this.loc.y += dy[d];
			this.time++;
		}
	}
	
	static class BC implements Comparable<BC>{
		Loc loc;
		int c;
		int p;
		
		public BC(Loc loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
		
		public boolean isIn(Loc loc) {
			int dist = calcDist(this.loc, loc);
			if(dist <= c) {
				return true;
			}
			return false;
		}

		@Override
		public int compareTo(BC o) {
			return Integer.compare(o.p, this.p);
		}

	}
	
	public static int calcDist(Loc loc1, Loc loc2) {
		return Math.abs(loc1.x-loc2.x) + Math.abs(loc1.y-loc2.y);
	}
	
	public static void charge() {
		int chargeA = 0;
		int chargeB = 0;
		int dupIdx = -1;
		boolean isDup = false;
		boolean endFlag = false;
		
		for(int i = 0; i < K; i++) {
			boolean tempA = bc[i].isIn(a.loc);
			boolean tempB = bc[i].isIn(b.loc);
			
			// 중복 충전기 찾기 이전
			if(!isDup) {
				// 처음 중복 충전기를 찾았을 때
				if(tempA && tempB) {
					isDup = true;
					dupIdx = i;
					
					// 이전에 A가 사용가능한 충전기를 찾은 적이 있으면 B가 중복 충전기 사용
					if(chargeA != 0) {
						chargeB = bc[dupIdx].p;
						a.chargeSum += chargeA;
						b.chargeSum += chargeB;
						endFlag = true;
						break;
					}
					// 이전에 B가 사용가능한 충전기를 찾은 적이 있으면 A가 중복 충전기 사용
					else if(chargeB != 0) {
						chargeA = bc[dupIdx].p;
						a.chargeSum += chargeA;
						b.chargeSum += chargeB;
						endFlag = true;
						break;
					}
				}
				// A가 사용가능한 충전기를 찾음
				else if(chargeA == 0 && tempA) {
					chargeA = bc[i].p;
				}
				// B가 사용가능한 충전기를 찾음
				else if(chargeB == 0 && tempB) {
					chargeB = bc[i].p;
				}
				// A와 B 각각 다른 충전기를 찾아서 사용
				if(chargeA != 0 && chargeB != 0) {
					a.chargeSum += chargeA;
					b.chargeSum += chargeB;
					endFlag = true;
					break;
				}
			}
			// 중복 충전기 찾은 후
			else {
				if(!(tempA && tempB)) {
					if(chargeA == 0 && tempA) {
						chargeA = bc[i].p;
						chargeB = bc[dupIdx].p;
						a.chargeSum += chargeA;
						b.chargeSum += chargeB;
						endFlag = true;
						break;
					}
					else if(chargeB == 0 && tempB) {
						chargeA = bc[dupIdx].p;
						chargeB = bc[i].p;
						a.chargeSum += chargeA;
						b.chargeSum += chargeB;
						endFlag = true;
						break;
					}
				}
				else {
					chargeA = bc[i].p;
					chargeB = bc[dupIdx].p;
					a.chargeSum += chargeA;
					b.chargeSum += chargeB;
					endFlag = true;
					break;
				}
			}
		}
		if(!endFlag) {
			if(isDup) {
				a.chargeSum += bc[dupIdx].p / 2;
				b.chargeSum += bc[dupIdx].p / 2;
			}
			else {
				a.chargeSum += chargeA;
				b.chargeSum += chargeB;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int[] route = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				route[i] = Integer.parseInt(st.nextToken());
			}
			a = new Person(new Loc(1, 1), route);
			
			
			route = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				route[i] = Integer.parseInt(st.nextToken());
			}
			b = new Person(new Loc(10, 10), route);
			
			bc = new BC[K];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new BC(new Loc(y, x), c, p);
			}
			
			Arrays.sort(bc);
			charge();
			
			for(int i = 1; i < M+1; i++) {
				a.move();
				b.move();
				charge();
			}
			
			sb.append(String.format("#%d %d\n", t, a.chargeSum+b.chargeSum));
		}
		System.out.println(sb.toString());
		br.close();
	}

}