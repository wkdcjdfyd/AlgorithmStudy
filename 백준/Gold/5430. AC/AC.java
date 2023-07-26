import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			String p = br.readLine();
			int N = Integer.parseInt(br.readLine());
			Deque<Integer> deque = new LinkedList<>();
			boolean isReverse = false;
			boolean isError = false;
			st = new StringTokenizer(br.readLine(), "[|,|]");
			
			for(int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				deque.add(num);
			}
			
			MainLoop: for(int i = 0; i < p.length(); i++) {
				switch(p.charAt(i)) {
					case 'R':
						isReverse = isReverse?false:true;
						break;
					case 'D':
						if(deque.isEmpty()) {
							isError = true;
							break MainLoop;
						}
						if(isReverse) deque.removeLast();
						else deque.removeFirst();
						break;
				}
			}
			if(isError) {
				sb.append("error\n");
			}
			else if (deque.isEmpty()) {
				sb.append("[]\n");
			}
			else {
				sb.append("[");
				if(!isReverse) {
					for(int x: deque) {
						sb.append(x+",");
					}
				}
				else {
					while(!deque.isEmpty()) {
						sb.append(deque.removeLast() + ",");
					}
				}
				sb.deleteCharAt(sb.length()-1);
				sb.append("]\n");
			}
			
		}
		System.out.println(sb.toString());
		br.close();	
	}

}
