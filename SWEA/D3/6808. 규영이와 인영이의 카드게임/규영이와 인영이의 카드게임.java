import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 10.
@see			https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWgv9va6HnkDFAW0
@performance	24500kb 1450ms
@category 		#순열
@note
*/

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int[] arr1 = new int[9];
	static int[] arr2 = new int[9];
	static boolean[] nums = new boolean[19];
	
	public static boolean nextPerm() {
		int lastPeek = arr2.length-1;
		while(lastPeek > 0 && arr2[lastPeek-1] >= arr2[lastPeek]) lastPeek--;
		
		if(lastPeek == 0) return false;
		
		int nxtBoss = arr2.length-1;
		while(arr2[lastPeek-1] >= arr2[nxtBoss]) nxtBoss--;
		swap(arr2, nxtBoss, lastPeek-1);
		
		for(int left = lastPeek, right = arr2.length-1; left < right; left++, right--) {
			swap(arr2, left, right);
		}
		return true;
	}
	
	public static void swap(int[] src, int x, int y) {
		int temp = src[x];
		src[x] = src[y];
		src[y] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(nums, false);
			for(int i = 0; i < 9; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
				nums[arr1[i]] = true;
			}
			
			int idx = 0;
			for(int i = 1; i < nums.length; i++) {
				if(!nums[i]) {
					arr2[idx++] = i;
				}
			}
			
			int win = 0;
			int lose = 0;
			
			do {
				int gyu = 0;
				int in = 0;
				for(int i = 0; i < arr1.length; i++) {
					if(arr1[i] > arr2[i]) {
						gyu += arr1[i] + arr2[i];
					}
					else {
						in += arr1[i] + arr2[i];
					}
				}
				if(gyu > in) {
					win++;
				}
				else if(gyu < in) {
					lose++;
				}
			} while (nextPerm());
			
			sb.append(String.format("#%d %d %d\n", t, win, lose));
		}
		System.out.println(sb.toString());
		br.close();
	}

}