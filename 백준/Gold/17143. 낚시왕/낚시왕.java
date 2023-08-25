import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 25.
@see			https://www.acmicpc.net/problem/17143
@performance	
@category 		#구현
@note			
*/

public class Main {
	static HashMap<Integer, Shark> shark = new HashMap<>();
	static int[][] graph;
	static int[] dx = {0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 1, -1};
	static int R, C, M;
	
	static class Shark {
		int num, x, y, s, d, z;

		public Shark(int num, int x, int y, int s, int d, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
		public void move() {
			int nx = this.x + (s % (2 * R - 2)) * dx[d];
			int ny = this.y + (s % (2 * C - 2)) * dy[d];
			
			if(nx >= R) {
				if((((s % (2 * R - 2)) - (R - 1 - this.x)) / R) % 2 == 0) {
					turn();
				}
			}
			else if(nx < 0) {
				if((((s % (2 * R - 2)) - this.x) / R) % 2 == 0) {
					turn();
				}
			}
			
			if(ny >= C) {
				if((((s % (2 * C - 2)) - (C - 1 - this.y)) / C) % 2 == 0) {
					turn();
				}
			}
			else if(ny < 0) {
				if((((s % (2 * C - 2)) - this.y) / C) % 2 == 0) {
					turn();
				}
			}
			
			int nnx = slice(nx, true);
			int nny = slice(ny, false);
			
			this.x = slice(nnx, true);
			this.y = slice(nny, false);
		}
		
		public void turn() {
			switch(this.d) {
				case 1:
					this.d = 2;
					break;
				case 2:
					this.d = 1;
					break;
				case 3:
					this.d = 4;
					break;
				case 4:
					this.d = 3;
					break;
			}
		}
		
	}
	
	public static int slice(int x , boolean row) {
		int K = row ? R : C;

		if(x >= K) {
			x = 2 * K - 2 - x;
		}
		if(x < 0) {
			x = -x;
		}
		return x;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[R][C];
		
		for(int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			graph[r][c] = i;
			shark.put(i, new Shark(i, r, c, s, d, z));
		}
		
		int total = 0;
		for(int j = 0; j < C; j++) {
			// 상어 잡기
			for(int i = 0; i < R; i++) {
				if(graph[i][j] != 0) {
					total += shark.get(graph[i][j]).z;
					shark.remove(graph[i][j]);
					graph[i][j] = 0;
					break;
				}
			}
				
			// 상어 이동
			int[][] nxt = new int[R][C];
			ArrayList<Integer> candi = new ArrayList<>();
			
			for(Shark s : shark.values()) {
				s.move();
				
				if(nxt[s.x][s.y] != 0) {
					Shark s2 = shark.get(nxt[s.x][s.y]);
					if(s.z < s2.z) {
						candi.add(s.num);
					}
					else {
						candi.add(s2.num);
						nxt[s.x][s.y] = s.num;
					}
				}
				else {
					nxt[s.x][s.y] = s.num;
				}
			}
			for(int key : candi) {
				shark.remove(key);
			}
			graph = nxt;
		}
		System.out.println(total);
		br.close();
	}

}