import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-08
@see            https://www.acmicpc.net/problem/12869
@performance
@category       #DP
@note
*/

public class Main {

    static int N;
    static int[][][] dp;
    static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9 ,1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};

    public static int counter(Integer[] scv, int cnt){
        boolean flag = false;
        for(int i = 0; i < N; i++){
            if(scv[i] != 0){
                flag = true;
                break;
            }
        }
        if(!flag) return cnt;

        Arrays.sort(scv, Collections.reverseOrder());
        if(dp[scv[0]][scv[1]][scv[2]] != Integer.MAX_VALUE){
            return dp[scv[0]][scv[1]][scv[2]];
        }

        for(int i = 0; i < attack.length; i++){
            Integer[] temp = new Integer[3];
            temp[0] = Math.max(scv[0] - attack[i][0], 0);
            temp[1] = Math.max(scv[1] - attack[i][1], 0);
            temp[2] = Math.max(scv[2] - attack[i][2], 0);
            dp[scv[0]][scv[1]][scv[2]] = Math.min(dp[scv[0]][scv[1]][scv[2]], counter(temp, cnt + 1));
        }

        return dp[scv[0]][scv[1]][scv[2]];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Integer[] scv = new Integer[3];
        dp = new int[61][61][61];

        for(int i = 0; i < 61; i++){
            for(int j = 0; j < 61; j++){
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = N; i < 3; i++){
            scv[i] = 0;
        }

        System.out.println(counter(scv.clone(), 0));
        br.close();
    }
}