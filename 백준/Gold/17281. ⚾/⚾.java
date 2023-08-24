import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] baseball;
	static int N;
	static int ans = 0;
	
	public static void makePerm(int nth, int[] choosed, boolean[] visited) {
		if(nth == 8) {
			int[] order = new int[9];
			
			for(int i = 0; i < 3; i++) {
				order[i] = choosed[i];
			} 
			order[3] = 1;
			for(int i = 3; i < 8; i++) {
				order[i+1] = choosed[i];
			}
			ans = Math.max(ans, calc(order));
			return;
		}
		for(int i = 2; i < 10; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nth] = i;
				makePerm(nth+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	public static int calc(int[] order) {
		int score = 0;
		int lastHitterIdx = -1;
		
		Game:
		for(int inning = 0; inning < N; inning++) {
			int outCnt = 0;
			boolean[] base = new boolean[4];
			int hitterIdx = (lastHitterIdx+1) % 9;
			
			while(true) {
				int hitter = order[hitterIdx];
				switch(baseball[inning][hitter-1]) {
					case 0:
						if(++outCnt == 3) {
							lastHitterIdx = hitterIdx;
							continue Game;
						}
						break;
					case 1:
						if(base[3]) {
							score++;
							base[3] = false;
						}
						for(int i = 2; i > 0; i--) {
							if(base[i]) {
								base[i+1] = base[i];
								base[i] = false;
							}
						}
						base[1] = true;
						break;
					case 2:
						if(base[3]) {
							score++;
							base[3] = false;
						}
						if(base[2]) {
							score++;
							base[2] = false;
						}
						if(base[1]) {
							base[3] = base[1];
							base[1] = false;
						}
						base[2] = true;
						break;
					case 3:
						for(int i = 1; i < 4; i++){
							if(base[i]) score++;
							base[i] = false;
						}
						base[3] = true;
						break;
					case 4:
						for(int i = 1; i < 4; i++){
							if(base[i]) score++;
							base[i] = false;
						}
						score++;
						break;
				}
				hitterIdx = (hitterIdx+1) % 9;
			}
		}
		return score;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		baseball = new int[N][9];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				baseball[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makePerm(0, new int[8], new boolean[10]);
		
		System.out.println(ans);
		br.close();
	}

}