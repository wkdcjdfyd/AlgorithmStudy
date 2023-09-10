import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 10.
 * @see  			https://www.acmicpc.net/problem/1297
 * @performance 	
 * @category 		#수학
 * @note 			
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double D = Integer.parseInt(st.nextToken());
		double H = Integer.parseInt(st.nextToken());
		double W = Integer.parseInt(st.nextToken());
		
		double x = D / Math.sqrt(H*H + W*W);
		int h = (int)Math.floor(H * x);
		int w = (int)Math.floor(W * x);
		
		System.out.println(h + " " + w);
		br.close();
	}

}