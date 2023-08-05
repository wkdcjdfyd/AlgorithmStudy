import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 8. 5.
 * @see  			https://www.acmicpc.net/problem/1043
 * @performance 
 * @category 		#
 * @note 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Integer> known = new ArrayList<>();
		Deque<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		boolean[] isValid = new boolean[M];
		
		st = new StringTokenizer(br.readLine());
		int trueNum = Integer.parseInt(st.nextToken());
		for(int i = 0; i < trueNum; i++) {
			int num = Integer.parseInt(st.nextToken());
			known.add(num);
			q.offerLast(num);
		}
			
		ArrayList<int[]> party = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int[] p = new int[num];
			for(int j = 0; j < num; j++) {
				p[j] = Integer.parseInt(st.nextToken());
			}
			party.add(p);
		}
		
		while(!q.isEmpty()) {
			int now = q.pollFirst();
			visited[now] = true;
			
			for(int i = 0; i < M; i++) {
				boolean isIn = false;
				for(int n : party.get(i)) {
					if(n == now) {
						isIn = true;
						isValid[i] = true;
					}
				}
				if(isIn) {
					for(int n : party.get(i)) {
						if(!visited[n])
							q.offerLast(n);
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			if(!isValid[i]) cnt++;
		}
		System.out.println(cnt);
		br.close();
	}

}