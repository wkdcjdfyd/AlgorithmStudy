import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author 			Ryong
 * @since 			2023-10-9
 * @see             https://www.acmicpc.net/problem/29792
 * @performance
 * @category 		#dp
 * @note
 */

public class Main {

    public static final int MAXTIME = 15 * 60;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] damage = new long[N];
        long[][] boss = new long[K][2];

        for(int i = 0; i < N; i++){
            damage[i] = Long.parseLong(br.readLine());
        }

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            boss[i][0] = Long.parseLong(st.nextToken());
            boss[i][1] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(boss, Comparator.comparingLong(o -> o[0]));

        long[][] dp = new long[K+1][MAXTIME + 1];

        ArrayList<Long> money = new ArrayList<>();
        for(int n = 0; n < N; n++) {
            for (int i = 0; i < K; i++) {
                long huntingTime = boss[i][0] / damage[n];
                if (boss[i][0] % damage[n] != 0) huntingTime++;

                for (int j = 1; j < MAXTIME + 1; j++) {
                    if (j >= huntingTime) {
                        dp[i + 1][j] = Math.max(dp[i][(int) (j - huntingTime)] + boss[i][1], dp[i][j]);
                    } else {
                        dp[i + 1][j] = dp[i][j];
                    }
                }
            }
            money.add(dp[K][MAXTIME]);
        }

        Collections.sort(money, Collections.reverseOrder());

        int ans = 0;
        for(int m = 0; m < M; m++){
            ans += money.get(m);
        }
        System.out.println(ans);
        br.close();
    }
}