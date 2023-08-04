import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int t = 1; t < 11; t++) {
			int T = Integer.parseInt(br.readLine());
			Deque<Integer> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int scale = 1;
			while(!q.isEmpty()) {
				int now = q.pollFirst();
				now -= scale;
				if(now <= 0) {
					now = 0;
					q.offer(now);
					break;
				}
				else {
					q.offer(now);
				}
				if(scale == 5) {
					scale = 0;
				}
				scale++;
			}
			sb.append("#" + T + " ");
			for(int n : q) {
				sb.append(n + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}