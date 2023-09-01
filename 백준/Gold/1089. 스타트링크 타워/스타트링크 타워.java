import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
@author 		Ryong
@since 			2023. 9. 1.
@see			https://www.acmicpc.net/problem/1089
@performance	
@category 		#
@note			
*/

public class Main {
	static int N;
	static char[][] graph;
	static ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
	static double sum;
	static int allCnt;
	static final int[][] NUM = {{0, 1, 2, 3, 5, 6, 8, 9, 11, 12, 13, 14},
								{2, 5, 8, 11, 14},
								{0, 1, 2, 5, 6, 7, 8, 9, 12, 13, 14},
								{0, 1, 2, 5, 6, 7, 8, 11, 12, 13, 14},
								{0, 2, 3, 5, 6, 7, 8, 11, 14},
								{0, 1, 2, 3, 6, 7, 8, 11, 12, 13, 14},
								{0, 1, 2, 3, 6, 7, 8, 9, 11, 12, 13, 14},
								{0, 1, 2, 5, 8, 11, 14},
								{0, 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14},
								{0, 1, 2, 3, 5, 6, 7, 8, 11, 12, 13, 14}};
	static HashSet<Integer>[] set = new HashSet[10];

	private static void getPossible(int nth) {
		int n = 0;
		int y = 4 * nth;
		HashSet<Integer> temp = new HashSet<>();
		
		for(int i = 0; i < 5; i++) {
			for(int j = y; j < y+3; j++) {
				if(graph[i][j] == '#') temp.add(n);
				n++;
			}
		}
		
		for(int i = 0; i < 10; i++) {
			if(set[i].containsAll(temp)) {
				nums.get(nth).add(i);
			}
		}
		
	}
	
	public static void getSum() {
		for(int nth = 0; nth < N; nth++) {
			if(nums.get(nth).size() == 0) continue;
			
			double dec = (int)Math.pow(10, N - 1 - nth);
			double cnt = 1;
			for(int i = 0; i < N; i++) {
				if(i == nth) continue;
				cnt *= nums.get(i).size();
			}
			
			int s = 0;
			for(int n : nums.get(nth)) {
				s += n;
			}
			sum += s * dec * cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new char[5][];
		
		for(int i = 0; i < 5; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		for(int i = 0; i < N; i++) {
			nums.add(new ArrayList<>());
		}
		for(int i = 0; i < 10; i++) {
			set[i] = new HashSet<>();
			for(int j = 0; j < NUM[i].length; j++) {
				set[i].add(NUM[i][j]);
			}
		}
		
		allCnt = 1;
		for(int i = 0; i < N; i++) {
			getPossible(i);
			allCnt *= nums.get(i).size();
		}
		if(allCnt == 0) {
			System.out.println(-1);
			br.close();
			return;
		}
		
		sum = 0;
		getSum();

		System.out.printf("%.5f", sum / allCnt);
		br.close();
	}

}