import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 25.
@see			https://www.acmicpc.net/problem/21609
@performance	
@category 		#구현
@note			
*/

public class Main {
	static int[][] graph;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static PriorityQueue<BlockGroup> pq = new PriorityQueue<>();
	
	static class Block{
		int x, y;

		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class BlockGroup implements Comparable<BlockGroup>{
		int rainbowCnt;
		ArrayList<Block> block;
		int baseX, baseY;
		
		public BlockGroup(int baseX, int baseY,int rainbowCnt, ArrayList<Block> block) {
			this.baseX = baseX;
			this.baseY = baseY;
			this.rainbowCnt = rainbowCnt;
			this.block = block;
		}
		
		public int score() {
			return block.size() * block.size();
		}
		
		public void remove() {
			for(int i = 0; i < block.size(); i++) {
				Block b = block.get(i);
				graph[b.x][b.y] = -2; 
			}
		}

		@Override
		public int compareTo(BlockGroup o) {
			if(this.block.size() == o.block.size()) {
				if(this.rainbowCnt == o.rainbowCnt) {
					if(o.baseX == this.baseX) {
						return Integer.compare(o.baseY, this.baseY);
					}
					return Integer.compare(o.baseX, this.baseX);
				}
				return Integer.compare(o.rainbowCnt, this.rainbowCnt);
			}
			
			return Integer.compare(o.block.size(), this.block.size());
		}
	}
	
	public static void findBlockGroup(int x, int y) {
		Deque<Block> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		q.offer(new Block(x, y));
		int rainbowCnt = 0;
		
		ArrayList<Block> group = new ArrayList<>();
		group.add(new Block(x, y));
		int baseX = x;
		int baseY = y;
		
		while(!q.isEmpty()) {
			Block now = q.poll();
			
			for(int d = 0; d < dx.length; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny] || graph[nx][ny] == -1) continue;
				if(graph[nx][ny] == 0 || graph[nx][ny] == graph[x][y]) {
					if(graph[nx][ny] == 0) rainbowCnt++;
					else {
						if(baseX > nx) {
							baseX = nx;
							baseY = ny;
						}
						else if(baseX == nx && baseY > ny) {
							baseY = ny;
						}
					}
					
					visited[nx][ny] = true;
					group.add(new Block(nx, ny));
					q.offer(new Block(nx, ny));
				}
			}
		}
		if(group.size() >= 2) {
			pq.add(new BlockGroup(baseX, baseY, rainbowCnt, group));
		}
	}
	
	public static void setGravity() {
		for(int j = 0; j < N; j++) {
			for(int i = N-1; i > 0; i--) {
				if(graph[i][j] == -2) {
					for(int k = i-1; k >= 0; k--) {
						if(graph[k][j] == -1) break;
						if(graph[k][j] != -2) {
							graph[i][j] = graph[k][j];
							graph[k][j] = -2;
							break;
						}
					}
				}
			}
		}
	}
	
	public static void rotate() {
		int[][] temp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[i][j] = graph[j][N-1-i];
			}
		}
		
		graph = temp;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while(true) {
			if(!pq.isEmpty()) {
				pq.clear();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(graph[i][j] > 0) {
						findBlockGroup(i, j);
					}
				}
			}
			
			if(pq.isEmpty()) {
				break;
			}
			
			BlockGroup group = pq.poll();
			score += group.score();
			
			group.remove();
			setGravity();
			rotate();
			setGravity();
		}
		
		System.out.println(score);
		br.close();
	}

}