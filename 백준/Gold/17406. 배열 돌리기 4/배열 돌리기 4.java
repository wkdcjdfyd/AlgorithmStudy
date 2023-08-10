import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 10.
 * @see  			https://www.acmicpc.net/problem/17406
 * @performance 		12472kb 176ms
 * @category 			#구현 #시뮬레이션
 * @note 
 */

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int N;
	static int M;
	static int K;
	static int[][] graph;
	static int[][] order;
	static int[] nums;
	
	public static void rotate(int r, int c, int s, int[][] org) {
		int x = r-s;
		int y = c-s;
		int rScale = s * 2 + 1;
		int cScale = s * 2 + 1;
		int upperX = x + s*2;
		int upperY = y + s*2;
		
		while(!(x == r && y == c)) {
			int startX = x;
			int startY = y;
			int roundCnt = (rScale*2) + (cScale-2) * 2;
			int d = 0;
			int cnt = 0;
			int temp = org[x][y];
			int temp2 = 0;
			
			while(cnt < roundCnt) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx < startX || ny < startY || nx > upperX || ny > upperY) {
					d = (d + 1) % 4;
					continue;
				}
				temp2 = org[nx][ny];
				org[nx][ny] = temp;
				temp = temp2;
				cnt++;
				x = nx;
				y = ny;
			}
			
			x = startX + 1;
			y = startY + 1;
			rScale -= 2;
			cScale -= 2;
			upperX -= 1;
			upperY -= 1;
		}
		
	}
	
	public static boolean nextPerm() {
		int lastPeek = nums.length-1;
		while(lastPeek > 0 && nums[lastPeek-1] >= nums[lastPeek]) lastPeek--;
		
		if(lastPeek == 0) return false;
		
		int nxtBoss = nums.length-1;
		while(nums[lastPeek-1] >= nums[nxtBoss]) nxtBoss--;
		swap(nums, nxtBoss, lastPeek-1);
		
		for(int left = lastPeek, right = nums.length-1; left < right; left++, right--) {
			swap(nums, left, right);
		}
		return true;
	}
	
	public static void swap(int[] src, int i, int j) {
		int temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}
	
	public static int calc(int[][] rotated) {
		int min = (int)1e5;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += rotated[i][j];
			}
			min = Math.min(min, sum);
		}
		return min;
	}
	
	public static void copyOriginVal(int[][] target) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				target[i][j] = graph[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new int[K][3];
		nums = new int[K];
		for(int k = 0; k < K; k++) {
			nums[k] = k;
			st = new StringTokenizer(br.readLine());
			order[k][0] = Integer.parseInt(st.nextToken()) - 1;
			order[k][1] = Integer.parseInt(st.nextToken()) - 1;
			order[k][2] = Integer.parseInt(st.nextToken());
		}
		int ans = (int)1e7;
		int[][] rotated = new int[N][M];
		do {
			copyOriginVal(rotated);
			for(int k : nums) {
				rotate(order[k][0], order[k][1], order[k][2], rotated);
			}
			ans = Math.min(ans, calc(rotated));
		} while (nextPerm());
		
		System.out.println(ans);
		br.close();
	}

}
