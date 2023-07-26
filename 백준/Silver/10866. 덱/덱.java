import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> q = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			if(s.length > 1) {
				int num = Integer.parseInt(s[1]);
				switch(s[0]) {
					case "push_front":
						q.addFirst(num);
						break;
					case "push_back":
						q.addLast(num);
						break;
				}
			}
			else {
				switch(s[0]) {
					case "pop_front":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.removeFirst() + "\n");
						break;
					case "pop_back":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.removeLast() + "\n");
						break;
					case "size":
						sb.append(q.size() + "\n");
						break;
					case "empty":
						if(q.isEmpty()) sb.append(1 + "\n");
						else sb.append(0 + "\n");
						break;
					case "front":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.getFirst() + "\n");
						break;
					case "back":
						if(q.isEmpty()) sb.append(-1 + "\n");
						else sb.append(q.getLast() + "\n");
						break;
				}
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}
