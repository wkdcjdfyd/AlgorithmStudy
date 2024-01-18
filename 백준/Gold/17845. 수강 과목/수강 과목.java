import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        int[][] subjects = new int[K][2];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            subjects[i][0] = Integer.parseInt(st.nextToken());
            subjects[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(subjects, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        loop:
        for(int i = 0; i < K; i++){
            int imp = subjects[i][0];
            int time = subjects[i][1];
            for(int j = N; j > 0; j--){
                if(j-time < 0) continue loop;
                dp[j] = Math.max(dp[j-time] + imp, dp[j]);
            }
        }

        System.out.println(dp[N]);
        br.close();
    }
}