import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 16.
@see			https://www.acmicpc.net/problem/15903
@performance	
@category 		#Greedy
@note			자료형 범위 확인하기
*/

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}
		
		for(int i = 0; i < M; i++) {
			long n1 = pq.poll();
			long n2 = pq.poll();
			long num = n1 + n2;
			pq.offer(num);
			pq.offer(num);
		}
		
		long sum = 0;
		for(long num : pq) {
			sum += num;
		}
		System.out.println(sum);
		br.close();
	}

}