import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Doc{
		int pri;
		boolean isTarget;
		
		Doc(int pri, boolean isTarget){
			this.pri = pri;
			this.isTarget = isTarget;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Integer[] arr = new Integer[N];
			Queue<Doc> q = new LinkedList<>(); 
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i] = num;
				if(i == M) q.add(new Doc(num, true));
				else q.add(new Doc(num, false));
			}
			
			Arrays.sort(arr, Comparator.reverseOrder());
			int idx = 0;
			int cnt = 0;
			
			while(!q.isEmpty()) {
				Doc now = q.poll();
				
				if(arr[idx] == now.pri) {
					cnt++;
					idx++;
					
					if(now.isTarget) {
						sb.append(cnt + "\n");
						break;
					}
				}
				else {
					q.add(now);
				}
			}
			
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}