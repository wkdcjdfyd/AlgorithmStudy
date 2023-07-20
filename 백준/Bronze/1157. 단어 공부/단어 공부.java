import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		s = s.toUpperCase();
		int[][] cnt = new int['Z'-'A' + 1][2];
		
		for(int i = 0; i < cnt.length; i++) {
			cnt[i][0] = i;
			cnt[i][1] = 0;
		}
		
		for(int i = 0; i < s.length(); i++) {
			cnt[s.charAt(i) - 'A'][1]++;
		}
		
		Arrays.sort(cnt, (o1, o2)->{
			return o2[1]-o1[1];
		});
		
		if(cnt[0][1] == cnt[1][1]) {
			System.out.println("?");
		}
		else {
			System.out.println((char)(cnt[0][0] + 'A'));
		}
	}

}