import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 			Ryong
 * @since 			2023-10-23
 * @see  			https://www.acmicpc.net/problem/2877
 * @performance
 * @category 		#구현
 * @note
 */

public class Main {

    public static int counter(int numLen){
        return 2 * ((int)Math.pow(2, numLen) - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        int numLen = 1;
        while(counter(numLen) < K){
            numLen++;
        }

        int nth = counter(numLen-1);
        int partition = (int)Math.pow(2, numLen-1);
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < numLen; i++){
            if(K <= nth + partition){
                ans.append(4);
                partition /= 2;
            }
            else{
                ans.append(7);
                nth += partition;
                partition /= 2;
            }
        }

        System.out.println(ans);
        br.close();
    }
}