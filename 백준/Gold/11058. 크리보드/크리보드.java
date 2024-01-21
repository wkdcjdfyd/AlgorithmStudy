import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since          2024-01-21
@see            https://www.acmicpc.net/problem/11058
@performance
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N+1];

        for(int i = 1; i < N+1; i++){
            dp[i] = dp[i-1] + 1;
            if(i > 6){
                for(int j = 3; j < 6; j++){
                    dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
                }
            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}