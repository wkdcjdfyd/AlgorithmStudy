import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<int[]> result = new ArrayList<>();
	static int cnt = 0;
	
	public static void hanoi(int n, int from, int to) {
		if(n == 1) {
			result.add(new int[] {from, to});
			cnt++;
			return;
		}
		
		hanoi(n - 1, from, 6-from-to);
		cnt++;
		result.add(new int[] {from, to});
		hanoi(n - 1, 6-from-to, to);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		hanoi(N, 1, 3);
		
		sb.append(cnt + "\n");
		for(int[] now: result) {
			sb.append(now[0] + " " + now[1] + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}