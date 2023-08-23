import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 24.
@see			https://www.acmicpc.net/problem/21608
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<HashSet<Integer>> like;
	static int[][] seat;
	static int[] order;
	static int N;
	
	static class Seat implements Comparable<Seat>{
		int x, y, likeCnt, blankCnt;

		public Seat(int x, int y, int likeCnt, int blankCnt) {
			this.x = x;
			this.y = y;
			this.likeCnt = likeCnt;
			this.blankCnt = blankCnt;
		}

		@Override
		public int compareTo(Seat o) {
			if(this.likeCnt == o.likeCnt) {
				if(this.blankCnt == o.blankCnt) {
					if(this.x == o.x) {
						return Integer.compare(this.y, o.y);
					}
					return Integer.compare(this.x, o.x);
				}
				return Integer.compare(o.blankCnt, this.blankCnt);
			}
			
			return Integer.compare(o.likeCnt, this.likeCnt);
		}
	}
	
	public static void select(int n) {
		PriorityQueue<Seat> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(seat[i][j] != 0) {
					continue;
				}
				int likeCnt = 0;
				int blankCnt = 0;
				for(int d = 0; d < dx.length; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(!isValid(nx, ny)) continue;
					if(seat[nx][ny] == 0) blankCnt++;
					else if(like.get(n).contains(seat[nx][ny])) likeCnt++;
				}
				pq.offer(new Seat(i, j, likeCnt, blankCnt));
			}
		}
		if(!pq.isEmpty()) {
			Seat now = pq.poll();
			seat[now.x][now.y] = n;
		}
	}
	
	public static int calcScore() {
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int likeCnt = 0;
				for(int d = 0; d < dx.length; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if(!isValid(nx, ny)) continue;
					if(like.get(seat[i][j]).contains(seat[nx][ny])) likeCnt++;
				}
				
				switch(likeCnt) {
					case 1:
						sum += 1;
						break;
					case 2:
						sum += 10;
						break;
					case 3:
						sum += 100;
						break;
					case 4:
						sum += 1000;
						break;
				}
			}
		}
		
		return sum;
	}
	
	public static boolean isValid(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		seat = new int[N][N];
		order = new int[N*N];
		like = new ArrayList<>();
		
		for(int i = 0; i < N*N+1; i++) {
			like.add(new HashSet<>());
		}
		
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 4; j++) {
				like.get(order[i]).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i = 0; i < N*N; i++) {
			select(order[i]);
		}
		
		System.out.println(calcScore());
		br.close();
	}

}