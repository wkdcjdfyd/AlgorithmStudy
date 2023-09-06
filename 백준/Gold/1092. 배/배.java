import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 6.
@see			https://www.acmicpc.net/problem/1092
@performance	
@category 		#
@note			
*/

public class Main {
	static int N, M;
	static int[] box, startIdx;
	static Integer[] crain;
	static boolean[] moved;
	static int moveCnt;
	
	public static int binarySearch(int x) {
		int s = 0;
		int e = M-1;
		
		while(s < e) {
			int mid = (s + e) / 2;
			
			if(x < box[mid]) {
				e = mid;
			}
			else {
				s = mid + 1;
			}
		}
		if(x < box[s]) {
			return s-1;
		}
		else {
			return s;
		}
	}
	
	public static boolean moveBox(int crainIdx) {
		int nxt = startIdx[crainIdx];
		
		// 현재 크레인보다 무게가 큰 박스만 남아서 못 옮기는 경우
		if(nxt == -1) return false;
		
		int cnt = 0;
		
		while(nxt >= 0) {
			if(!moved[nxt]) {
				moved[nxt] = true;
				moveCnt++;
				return true;
			}
			nxt--;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		crain = new Integer[N];
		startIdx = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			crain[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(crain, Collections.reverseOrder());
		
		M = Integer.parseInt(br.readLine());
		box = new int[M];
		moved = new boolean[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(box);
		for(int i = 0; i < N; i++) {
			startIdx[i] = binarySearch(crain[i]);
		}
		
		int time = 0;
		boolean flag = true;
		
		if(startIdx[0] != M-1) {
			System.out.println(-1);
			br.close();
			return;
		}
		
		Loop:
		while(moveCnt < M) {
			time++;
			for(int i = 0; i < N; i++) {
				moveBox(i);
				if(moveCnt == M) break Loop;
			}
		}
		
		if(!flag) {
			System.out.println(-1);
		}
		else {
			System.out.println(time);
		}
		br.close();
	}

}