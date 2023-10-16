import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-16
@see            https://www.acmicpc.net/problem/2293
@performance
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        int[] dp = new int[k+1];

        for(int i = 0; i < n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < n; i++){
            if(coin[i] <= k) {
                dp[coin[i]]++;
            }
            for(int j = 0; j < k; j++){
                if(j + coin[i] <= k) {
                    dp[j + coin[i]] += dp[j];
                }
            }
        }

        System.out.println(dp[k]);
        br.close();
    }
}