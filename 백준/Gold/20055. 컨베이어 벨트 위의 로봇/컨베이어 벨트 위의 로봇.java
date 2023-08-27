import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 27.
 * @see  			https://www.acmicpc.net/problem/20055
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
	static int N, K;
	static int[] belt;
	static boolean[] isRobot;
	static int beltSize;
	static Deque<Robot> robots = new ArrayDeque<>();
	
	static class Robot{
		int idx;
		
		public Robot(int idx) {
			this.idx = idx;
		}
	}
	
	public static void rotateBelt() {
		int last = belt[beltSize];
		for(int i = beltSize; i > 1; i--) {
			belt[i] = belt[i-1];
		}
		belt[1] = last;
		
		for(int i = N; i > 1; i--) {
			isRobot[i] = isRobot[i-1];
		}
		isRobot[1] = false;
		isRobot[N] = false;
		
		Robot remove = null;
		for(Robot r : robots) {
			if(++r.idx == N) {
				remove = r;
			}
		}
		if(remove != null) {
			robots.remove(remove);
		}
	}
	
	public static int moveRobot() {
		int size = robots.size();
		int cnt = 0;
		
		while(--size >= 0) {
			Robot now = robots.poll();
			
			if(belt[now.idx+1] == 0) {
				robots.offer(now);
				continue;
			}
			if(isRobot[now.idx + 1]) {
				robots.offer(now);
				continue;
			}
			if(now.idx + 1 == N) {
				isRobot[now.idx] = false;
				if(--belt[now.idx+1] == 0) {
					cnt++;
				}
				continue;
			}
			isRobot[now.idx++] = false;
			isRobot[now.idx] = true;
			if(--belt[now.idx] == 0) {
				cnt++;
			}
			robots.offer(now);
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		beltSize = 2 * N;
		belt = new int[beltSize + 1];
		isRobot = new boolean[N + 1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < beltSize + 1; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		int stage = 0;
		int emptyCnt = 0;
		while(emptyCnt < K) {
			stage++;
			rotateBelt();
			emptyCnt += moveRobot();
			if(belt[1] != 0) {
				robots.offer(new Robot(1));
				isRobot[1] = true;
				if(--belt[1] == 0) {
					emptyCnt++;
				}
			}
		}
		System.out.println(stage);
		br.close();
	}

}