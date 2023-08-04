import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
@author 		Ryong
@since 			2023. 8. 4.
@see			https://www.acmicpc.net/problem/2164
@performance		51184kb	152ms
@category 		#큐
@note
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> q = new LinkedList<>();
		
		for(int i = 1; i <= N; i++) {
			q.offer(i);
		}
		
		boolean turn = false;
		int cnt = N;
		while(cnt > 1) {
			if(!turn) {
				q.pollFirst();
				cnt--;
			}
			else {
				int num = q.pollFirst();
				q.offer(num);
			}
			turn = !turn;
		}
		System.out.println(q.pollFirst());
		br.close();
	}

}
