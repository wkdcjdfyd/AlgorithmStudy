import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 18.
 * @see  			https://www.acmicpc.net/problem/1700
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {
	static int N, K;
	static ArrayList<ArrayList<Integer>> useIdx = new ArrayList<>();
	static int[] isPluged = new int[101];
	static boolean[] isValid;
	static int[] order;
	
	public static int find(int e) {
		int maxIdx = -1;
		int delIdx = -1;
		for(int i = 0; i <= e; i++) {
			if(!isValid[i]) continue;
			ArrayList<Integer> now = useIdx.get(order[i]);
			
			if(now.get(now.size()-1) == i) return i;
			
			int nxt = -1;
			for(int n : now) {
				if(n > i) {
					nxt = n;
					break;
				}
			}
			
			if(maxIdx < nxt) {
				maxIdx = nxt;
				delIdx = i;
			}
		}
		return delIdx;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		order = new int[K];
		isValid = new boolean[K];
		
		Arrays.fill(isPluged, -1);
		
		for(int i = 0; i < 101; i++) {
			useIdx.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
			useIdx.get(order[i]).add(i);
		}
		
		int pullCnt = 0;
		int idx = 0;
		isPluged[order[idx]] = idx;
		isValid[idx] = true;
		int nowCnt = 1;
		
		while(idx < K-1) {
			boolean flag = false;
			while(idx+1 < K) {
				if(isPluged[order[++idx]] == -1) {
					flag = true;
					break;
				}
				else {
					isValid[isPluged[order[idx]]] = false;
					isPluged[order[idx]] = idx;
					isValid[idx] = true;
				}
			}
			if(flag) {
				isPluged[order[idx]] = idx;
				isValid[idx] = true;
				nowCnt++;
			}
			
			if(nowCnt > N) {
				int delIdx = find(idx-1);
				isPluged[order[delIdx]] = -1;
				isValid[delIdx] = false;
				nowCnt--;
				pullCnt++;
			}
		}
		
		System.out.println(pullCnt);
		br.close();
	}

}