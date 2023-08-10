import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 10.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeUtVakTMDFAVH
@performance	39912kb 180ms
@category 		#조합
@note
*/

public class Solution {
	static int N;
	static int[][] graph;
	static int min;
	
	public static void makeComb(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			int idx = 0;
			int[] A = new int[N/2];
			int[] B = new int[N/2];
			int idxA = 0;
			int idxB = 0;
			for(int i = 0; i < N; i++) {
				if(idx < N/2 && choosed[idx] == i) {
					idx++;
					A[idxA++] = i;
				}
				else {
					B[idxB++] = i;
				}
			}
			
			min = Math.min(min, Math.abs(calcDish(A) - calcDish(B)));
			return;
		}
		for(int i = startIdx; i < N; i++) {
			choosed[nth] = i;
			makeComb(nth+1, i+1, choosed);
		}
	}
	
	public static int calcDish(int[] A) {
		int sum = 0;
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				sum += graph[A[i]][A[j]];
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			min = (int)1e9;
			makeComb(0, 0, new int[N/2]);
			sb.append(String.format("#%d %d\n", t, min));
		}
		System.out.println(sb.toString());
		br.close();
	}

}