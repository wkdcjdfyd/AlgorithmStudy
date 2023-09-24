import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023-09-24
 * @see  			https://www.acmicpc.net/problem/2577
 * @performance 	
 * @category 		#
 * @note 			
 */

public class Main {
    static int[] count = new int[10];
    public static void counter(int x){
        String s = String.valueOf(x);

        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - '0']++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        counter(A * B * C);

        for(int i = 0; i < count.length; i++) {
           sb.append(count[i]).append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}