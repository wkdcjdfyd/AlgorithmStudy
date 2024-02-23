import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-15
@see            https://www.acmicpc.net/problem/3067
@performance    
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] dp = new int[M+1];
            dp[0] = 1;

            for(int i = 1; i < N+1; i++){
                for(int j = coins[i-1]; j < M+1; j++) {
                    dp[j] += dp[j - coins[i-1]];
                }
            }
            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}