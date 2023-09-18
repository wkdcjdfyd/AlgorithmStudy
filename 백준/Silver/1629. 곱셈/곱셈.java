import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023. 9. 18.
 * @see  			https://www.acmicpc.net/problem/1629
 * @performance 	
 * @category 		#수학
 * @note 			
 */

public class Main {
	static long C;
	
	public static long pow(long x, long y) {
		if(y == 1) return x % C;
		
		long root = pow(x, y/2);
		
		if(y % 2 == 1) return (root * root % C) * x % C;
		return root * root % C;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		long ans = pow(A, B);
	
		System.out.println(ans);
		br.close();
	}

}