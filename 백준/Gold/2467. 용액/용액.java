import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 10.
 * @see  			https://www.acmicpc.net/problem/2467
 * @performance 	
 * @category 		#이분탐색
 * @note 			
 */

public class Main {
	static int N;
	static int[] val;
	static int ansX = -1;
	static int ansY = -1;
	static int abs = Integer.MAX_VALUE;
	
	public static int search(int target) {
		int s = 0;
		int e = N-1;
		
		while(s < e) {
			int mid = (s+e) / 2;
			
			if(target <= val[mid]) {
				e = mid;
			}
			else {
				s = mid + 1;
			}
		}
		
		return s;
	}
	
	public static void compareAns(int i, int j) {
		if(i < 0 || j < 0 || i >= N || j >= N) {
			return;
		}
		if(i == j) {
			return;
		}
		int num = Math.abs(val[i] + val[j]);
		
		if(num < abs) {
			ansX = val[i];
			ansY = val[j];
			abs = num;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		val = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			val[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			int idx = search(-val[i]);
			
			if(idx == i) {
				if(idx - 1 >= 0) {
					compareAns(i, idx-1);
				}
				if(idx + 1 < N) {
					compareAns(i, idx+1);
				}
			}
			else if(0 < idx && idx < N) {
				compareAns(i, idx);
				if(idx - 1 == i && idx - 2 >= 0) {
					compareAns(i, idx-2);
				}
				else {
					compareAns(i, idx-1);
				}
			}
			else if(idx == 0) {
				compareAns(i, idx);
			}
			else if(idx == N) {
				if(idx - 1 == i && idx - 2 >= 0) {
					compareAns(i, idx-2);
				}
				else {
					compareAns(i, idx-1);
				}
			}
		}
		
		if(ansX <= ansY){
			System.out.println(ansX + " " + ansY);
		}
		else{
			System.out.println(ansY + " " + ansX);
		}
		
		br.close();
	}

}