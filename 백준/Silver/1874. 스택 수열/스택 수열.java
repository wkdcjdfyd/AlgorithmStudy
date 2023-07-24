import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int num = 0;
		int idx = 0;
		Stack<Integer> q = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		while(true) {
			if(idx >= N) {
				break;
			}
			int now = arr[idx];
			if(num < now) {
				for(int i = num+1; i <= now; i++) {
					sb.append("+\n");
					q.push(i);
					num++;
				}
			}
			else if(num > now) {
				if(q.contains(now)) {
					while(!q.isEmpty()) {
						sb.append("-\n");
						if(q.peek() == now) {
							q.pop();
							idx++;
							break;
						}
						q.pop();
					}
				}
				else {
					sb.delete(0, sb.length());
					sb.append("NO");
					break;
				}
			}
			else {
				while(!q.isEmpty()) {
					int x = q.pop();
					sb.append("-\n");
					if(x == now) {
						idx++;
						break;
					}
				}
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}
