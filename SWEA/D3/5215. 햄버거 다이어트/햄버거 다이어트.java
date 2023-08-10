import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 10.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
@performance	20484kb 199ms
@category 		#완전탐색 #부분집합
@note
*/

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int L;
	static ArrayList<Ingre> ingredients;
	static int ans;
	
	static class Ingre{
		int score;
		int cal;
		
		public Ingre(int score, int cal) {
			this.score = score;
			this.cal = cal;
		}
	}
	
	public static void makeSubSet(int nth, int calSum, int scoreSum) {
		if(nth == N) {
			ans = Math.max(ans, scoreSum);
			return;
		}
		int nxtCal = calSum+ingredients.get(nth).cal;
		int nxtScore = scoreSum+ingredients.get(nth).score;
		
		if(nxtCal <= L) {
			makeSubSet(nth+1, nxtCal, nxtScore);
		}

		makeSubSet(nth+1, calSum, scoreSum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			ingredients = new ArrayList<>();
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				ingredients.add(new Ingre(score, cal));
			}
			ans = -100;
			makeSubSet(0, 0, 0);
			sb.append(String.format("#%d %d\n", t, ans));
		}
		System.out.println(sb.toString());
		br.close();
	}

}