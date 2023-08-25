import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 8. 21.
@see			https://www.acmicpc.net/problem/17952
@performance	
@category 		#구현 #스택
@note			
*/

public class Main {

	static class Work{
		int score;
		int time;
		
		public Work(int score, int time) {
			this.score = score;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Deque<Work> stack = new ArrayDeque<>(); // 업무 순서를 관리할 스택 선언
		int N = Integer.parseInt(br.readLine());
		int totalScore = 0;		// 이번 분기 총 업무 평가 점수
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			// 새로운 업무가 주어지지 않았을 때
			if(cmd == 0) {
				// 가장 최근 작업 조회
				Work now = stack.peek();
				if(now == null) {
					continue;
				}
				
				// 바로 완료할 수 있다면 score를 totalScore에 더해주고 종료
				if(now.time == 1) {
					totalScore += now.score;
					stack.pop();
				}
				// 바로 완료할 수 없다면 종료시간 1감소
				else {
					now.time--;
				}
			}
			// 새로운 업무가 주어졌을 때
			else {
				int score = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				// 1분만에 끝낼 수 있다면 score를 totalScore에 더해주고 종료
				if(time == 1) {
					totalScore += score;
				}
				// 1분만에 끝낼 수 없다면 stack에 push
				else {
					stack.push(new Work(score, time-1));
				}
			}
		}
		System.out.println(totalScore);
		br.close();
	}

}