import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 29.
@see			https://www.acmicpc.net/problem/1208
@performance	
@category 		#중간에서 만나기
@note			
*/

public class Main {
	static int N, S;
	static int[] num;
	static ArrayList<Integer> leftSum = new ArrayList<>();
	static ArrayList<Integer> rightSum = new ArrayList<>();
	
	public static void makeSumList(int now, int end, int sum, ArrayList<Integer> list){
		if(now == end) {
			list.add(sum);
			return;
		}
		makeSumList(now+1, end, sum, list);
		makeSumList(now+1, end, sum+num[now], list);
	}
	
	public static long counter() {
		long cnt = 0;
		int lIdx = 0;
		int rIdx = 0;
		
		while(lIdx < leftSum.size() && rIdx < rightSum.size()) {
			int lSum = leftSum.get(lIdx);
			int rSum = rightSum.get(rIdx);
			int sum = lSum + rSum;
			
			if(sum < S) {
				lIdx++;
				continue;
			}
			if(sum > S) {
				rIdx++;
				continue;
			}
			if(sum == S) {
				long leftCnt = 1;
				long rightCnt = 1;
				lIdx++;
				rIdx++;
				while(lIdx < leftSum.size() && lSum == leftSum.get(lIdx)) {
					leftCnt++;
					lIdx++;
				}
				while(rIdx < rightSum.size() && rSum == rightSum.get(rIdx)) {
					rightCnt++;
					rIdx++;
				}
				
				cnt += leftCnt * rightCnt;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		makeSumList(0, N/2, 0, leftSum);
		makeSumList(N/2, N, 0, rightSum);
		
		leftSum.sort(Comparator.naturalOrder());
		rightSum.sort(Comparator.reverseOrder());
		
		long cnt = counter();
		
		if(S == 0) {
			cnt--;
		}
		System.out.println(cnt);
	}

}