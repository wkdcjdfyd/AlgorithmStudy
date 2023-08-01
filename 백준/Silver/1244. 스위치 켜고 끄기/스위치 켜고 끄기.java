import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] switches = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			// 남학생일 때
			if(gender == 1) {
				// 뽑은 숫자의 배수 자리의 스위치 값 변경
				for(int j = num; j < N+1; j += num) {
					switches[j] = switches[j] == 1 ? 0 : 1;
				}
			}
			// 여학생일 때
			else {
				// 뽑은 수의 자리는 무조건 변경
				switches[num] = switches[num] == 1 ? 0 : 1;
				int scale = 1;
				while(true) {
					// nx는 줄어들고 ny는 늘어나는 방향
					int nx = num - scale;
					int ny = num + scale;
					// nx, ny가 스위치 범위를 벗어나거나 nx, ny 자리의 스위치 값이 다르다면 break
					if(nx <= 0 || ny > N || switches[nx] != switches[ny] ) {
						break;
					}
					// nx, ny 자리의 스위치 값이 같다면 값 변경
					switches[nx] = switches[nx] == 1 ? 0 : 1;
					switches[ny] = switches[nx];
					scale++;
				}
			}
		}
		for(int i = 1; i < N+1; i++) {
			sb.append(switches[i] + " ");
			if(i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

}
