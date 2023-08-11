import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 4.
@see			https://www.acmicpc.net/problem/15686
@performance	
@category 		#브루트포스 #조합
@note
*/

public class Main {
	static int N;
	static int M;
	static int[][] graph;
	static int[][] chickLoc = new int[13][2];
	static int chickLocNum = 0;
	static ArrayList<int[]> houseLoc = new ArrayList<>();
	static int ans = Integer.MAX_VALUE;
	
	public static int calcDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static int cntAllChickDist(int[] choose) {
		int sum = 0;
		for(int[] loc : houseLoc) {
			int dist = Integer.MAX_VALUE;
			for(int idx : choose) {
				dist = Math.min(dist, calcDist(loc[0], loc[1], chickLoc[idx][0], chickLoc[idx][1]));
			}
			sum += dist;
		}
		
		return sum;
	}
	
	public static void makeComb(int nth, int startIdx, int[] choose) {
		if(nth == choose.length) {
			ans = Math.min(ans, cntAllChickDist(choose));
			return;
		}
		for(int i = startIdx; i < chickLocNum; i++) {
			choose[nth] = i;
			makeComb(nth+1, i+1, choose);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 2) {
					chickLoc[chickLocNum][0] = i;
					chickLoc[chickLocNum][1] = j;
					chickLocNum++;
				}
				else if(graph[i][j] == 1) {
					houseLoc.add(new int[] {i, j});
				}
			}
		}
		makeComb(0, 0, new int[M]);
		System.out.println(ans);
		br.close();
	}

}