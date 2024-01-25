import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-25
@see            https://www.acmicpc.net/problem/11052
@performance
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N+1];
        int[][] dp = new int[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(j < i){
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - i] + cost[i]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j - i] + cost[i]);
                if(i == N) ans = Math.max(dp[i][j], ans);
            }
        }

        System.out.println(ans);
        br.close();
    }
}