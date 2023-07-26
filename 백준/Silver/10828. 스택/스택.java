import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> q = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			if(s.length > 1) {
				int num = Integer.parseInt(s[1]);
				q.push(num);
			}
			else {
				switch(s[0]) {
					case "pop":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.pop() + "\n");
						break;
					case "size":
						sb.append(q.size() + "\n");
						break;
					case "empty":
						if(q.isEmpty()) sb.append(1 + "\n");
						else sb.append(0 + "\n");
						break;
					case "top":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.peek() + "\n");
						break;
				}
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}