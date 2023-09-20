import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 		Ryong
@since 			2023. 9. 20.
@see			https://www.acmicpc.net/problem/11399
@performance	
@category 		#그리디?
@note			
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time);
		
		int sum = time[0];
		for(int i = 1; i < N; i++) {
			time[i] += time[i-1];
			sum += time[i];
		}
		
		System.out.println(sum);
		br.close();
	}

}