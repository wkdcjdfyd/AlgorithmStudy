import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@author         Ryong
@since          2024-02-01
@see            https://www.acmicpc.net/problem/15989
@performance    
@category       #DP
@note          
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[4][10001];

        for(int num = 1; num <= 3; num++){
            dp[num][0] = 1;
            for(int i = 1; i < 10001; i++){
                if(num == 1){
                    dp[num][i] = 1;
                    continue;
                }

                if(i < num){
                    dp[num][i] = dp[num-1][i];
                }
                else {
                    dp[num][i] = dp[num-1][i] + dp[num][i-num];
                }
            }
        }

        for(int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[3][n]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}