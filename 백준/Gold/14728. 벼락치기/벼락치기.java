import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-16
@see            https://www.acmicpc.net/problem/14728
@performance
@category       #DP
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][T+1];

        int[][] test = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            test[i][0] = Integer.parseInt(st.nextToken());
            test[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(test, Comparator.comparingInt(o -> o[0]));

        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < T+1; j++){
                if(j < test[i-1][0]){
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - test[i - 1][0]] + test[i - 1][1], dp[i - 1][j]);
                }
            }
        }

        System.out.println(dp[N][T]);
        br.close();
    }
}