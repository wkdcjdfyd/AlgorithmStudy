import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
	static ArrayList<ArrayList<Integer>> candi = new ArrayList<>();
	static double sum;
	static int allCnt;
	
	public static void getPossible(int nth) {
		boolean[] possible = new boolean[10];
		Arrays.fill(possible, true);
		
		int y = 4 * nth;
		
		if(graph[1][y+1] == '#') {
			return;
		}
		if(graph[3][y+1] == '#') {
			return;
		}
		
		if(graph[0][y] == '#') {
			possible[1] = false;
		}
		if(graph[0][y+1] == '#') {
			possible[1] = false;
			possible[4] = false;
		}
		if(graph[1][y] == '#') {
			possible[1] = false;
			possible[2] = false;
			possible[3] = false;
			possible[7] = false;
		}
		if(graph[1][y+2] == '#') {
			possible[5] = false;
			possible[6] = false;
		}
		if(graph[2][y] == '#') {
			possible[1] = false;
			possible[7] = false;
		}
		if(graph[2][y+1] == '#') {
			possible[0] = false;
			possible[1] = false;
			possible[7] = false;
		}
		if(graph[3][y] == '#') {
			possible[1] = false;
			possible[3] = false;
			possible[4] = false;
			possible[5] = false;
			possible[7] = false;
			possible[9] = false;
		}
		if(graph[3][y+2] == '#') {
			possible[2] = false;
		}
		if(graph[4][y] == '#') {
			possible[1] = false;
			possible[4] = false;
			possible[7] = false;
		}
		if(graph[4][y+1] == '#') {
			possible[1] = false;
			possible[4] = false;
			possible[7] = false;
		}
		for(int i = 0; i < possible.length; i++) {
			if(possible[i]) candi.get(nth).add(i);
		}
	}
	
	public static void getSum() {
		for(int nth = 0; nth < N; nth++) {
			if(candi.get(nth).size() == 0) continue;
			
			double dec = (int)Math.pow(10, N - 1 - nth);
			double cnt = 1;
			for(int i = 0; i < N; i++) {
				if(i == nth) continue;
				cnt *= candi.get(i).size();
			}
			
			int s = 0;
			for(int n : candi.get(nth)) {
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
			candi.add(new ArrayList<>());
		}
		
		allCnt = 1;
		for(int i = 0; i < N; i++) {
			getPossible(i);
			allCnt *= candi.get(i).size();
		}
		if(allCnt == 0) {
			System.out.println(-1);
			br.close();
			return;
		}
		
		sum = 0;
		getSum();
		
		System.out.printf("%.5f", 1.0 * sum / allCnt);
		br.close();
	}

}