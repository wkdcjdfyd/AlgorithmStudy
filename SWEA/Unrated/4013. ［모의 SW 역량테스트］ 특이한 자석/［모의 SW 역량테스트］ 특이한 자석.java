import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 10. 10.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
@performance	
@category 		#구현
@note			
*/

public class Solution {
	static int K;
	static Magnet[] magnet;
	
	static class Magnet{
		int nth;
		Deque<Character> val;
		
		public Magnet(int nth, char[] arr) {
			this.nth = nth;
			
			val = new ArrayDeque<>();
			for(char c : arr) {
				val.offer(c);
			}
		}
		
		public void rotateClock() {
			val.offerFirst(val.pollLast());
		}
		
		public void rotateCounterClock() {
			val.offerLast(val.pollFirst());
		}
		
		public int getScore() {
			char top = val.peek();
			
			if(top == 'N') {
				return 0;
			}
			else {
				return 1<<nth;
			}
		}
		
		public char getRight() {
			int idx = 0;
			for(char c : val) {
				if(++idx == 3) {
					return c;
				}
			}
			return 0;
		}
		
		public char getLeft() {
			int idx = 0;
			for(char c : val) {
				if(++idx == 7) {
					return c;
				}
			}
			return 0;
		}
	}
	
	public static boolean isDiff(Magnet left, Magnet right) {
		if(left.getRight() != right.getLeft()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T+1; t++) {
			K = Integer.parseInt(br.readLine());
			magnet = new Magnet[4];
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				char[] arr = new char[8];
				for(int j = 0; j < 8; j++) {
					if(st.nextToken().equals("0")) {
						arr[j] = 'N';
					}
					else {
						arr[j] = 'S';
					}
				}
				
				magnet[i] = new Magnet(i, arr);
			}
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int nth = Integer.parseInt(st.nextToken())-1;
				int d = Integer.parseInt(st.nextToken());
				
				int[] rotateInfo = new int[4];
				rotateInfo[nth] = d;
				for(int idx = nth; idx > 0; idx--) {
					if(isDiff(magnet[idx-1], magnet[idx])) {
						if(rotateInfo[idx] == 1) {
							rotateInfo[idx-1] = -1;
						}
						else {
							rotateInfo[idx-1] = 1;
						}
					}
					else {
						break;
					}
				}
				
				for(int idx = nth; idx < 3; idx++) {
					if(isDiff(magnet[idx], magnet[idx+1])) {
						if(rotateInfo[idx] == 1) {
							rotateInfo[idx+1] = -1;
						}
						else {
							rotateInfo[idx+1] = 1;
						}
					}
					else {
						break;
					}
				}
				
				for(int idx = 0; idx < 4; idx++) {
					if(rotateInfo[idx] == 1) {
						magnet[idx].rotateClock();
					}
					else if(rotateInfo[idx] == -1){
						magnet[idx].rotateCounterClock();
					}
				}
			}
			
			int score = 0;
			for(int idx = 0; idx < 4; idx++) {
				score += magnet[idx].getScore();
			}
			
			sb.append("#").append(t).append(" ").append(score).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}

}