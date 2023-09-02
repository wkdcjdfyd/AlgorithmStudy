import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 2.
 * @see  			https://www.acmicpc.net/problem/1931
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());			
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(info, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		int time = info[0][1];
		int cnt = 1;
		for(int i = 1; i < N; i++) {
			if(info[i][0] < time) {
				continue;
			}
			cnt++;
			time = info[i][1];
		}
		System.out.println(cnt);
		br.close();
	}

}