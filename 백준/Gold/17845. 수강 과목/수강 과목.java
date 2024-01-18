import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-18
@see            https://www.acmicpc.net/problem/17845
@performance
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int imp = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            for(int j = N; j >= time; j--){
                dp[j] = Math.max(dp[j-time] + imp, dp[j]);
            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}