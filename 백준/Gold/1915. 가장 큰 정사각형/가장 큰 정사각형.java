import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author         Ryong
* @since          2024-01-26
* @see            https://www.acmicpc.net/problem/1915
* @performance
* @category       #DP
* @note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] graph = new boolean[n+1][m+1];
        int[][] dp = new int[n+1][m+1];
        int ans = 0;

        for(int i = 1; i < n+1; i++){
            String s = br.readLine();
            for(int j = 1; j < m+1; j++){
                if(s.charAt(j-1) == '1') graph[i][j] = true;
            }
        }

        for(int i = 1; i < n+1; i++){
            for(int j = 1; j < m+1; j++){
                if(graph[i][j]){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
                else{
                    dp[i][j] = 0;
                }
            }
        }

        System.out.println(ans * ans);
        br.close();
    }
}