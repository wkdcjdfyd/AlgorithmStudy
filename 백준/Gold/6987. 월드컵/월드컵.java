import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 16.
@see			https://www.acmicpc.net/problem/6987
@performance		11604kb 80ms
@category 		#백트래킹
@note			무작정 구현으로 하려고 했더니 고려해야하는게 엄청 많았다. 시간 초과가 안나게 백트래킹으로 가지치기를 하는 것도 고민을 잘 해야겠다.
*/

public class Main {
	static int[][] result;
	static ArrayList<int[]> game = new ArrayList<>();
	static boolean ans;
	
	public static void makeComb(int nth, int startIdx, int[] choosed) {
		if(nth == choosed.length) {
			game.add(new int[] {choosed[0], choosed[1]});
			return;
		}
		for(int i = startIdx; i < 7; i++) {
			choosed[nth] = i;
			makeComb(nth+1, i+1, choosed);
		}
	}
	
	public static boolean isAllZero() {
		for(int i = 1; i < 7; i++) {
			for(int j = 1; j < 4; j++) {
				if(result[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void search(int nth) {
		if(nth == game.size()) {
			if(isAllZero()) {
				ans = true;
			}
			return;
		}
		int[] now = game.get(nth);
		
		// 해당 게임이 이겼을 때
		if(result[now[0]][1] > 0 && result[now[1]][3] > 0) {
			result[now[0]][1]--;
			result[now[1]][3]--;
			search(nth+1);
			result[now[0]][1]++;
			result[now[1]][3]++;
		}
		
		// 해당 게임이 비겼을 때
		if(result[now[0]][2] > 0 && result[now[1]][2] > 0) {
			result[now[0]][2]--;
			result[now[1]][2]--;
			search(nth+1);
			result[now[0]][2]++;
			result[now[1]][2]++;
		}
		
		// 해당 게임이 졌을 때
		if(result[now[0]][3] > 0 && result[now[1]][1] > 0) {
			result[now[0]][3]--;
			result[now[1]][1]--;
			search(nth+1);
			result[now[0]][3]++;
			result[now[1]][1]++;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		makeComb(0, 1, new int[2]);
		
		for(int t = 0; t < 4; t++) {
			st = new StringTokenizer(br.readLine());
			result = new int[7][4];
			
			for(int i = 1; i < 7; i++) {
				for(int j = 1; j < 4; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = false;
			search(0);
			
			if(ans) {
				sb.append(1 + " ");
			}
			else {
				sb.append(0 + " ");
			}
		}
			
		System.out.println(sb.toString());
		br.close();
	}

}
