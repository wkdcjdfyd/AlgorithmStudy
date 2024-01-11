import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 @author         Ryong
 @since          2024-01-11
 @see            https://www.acmicpc.net/problem/13164
 @performance
 @category       #그리디
 @note
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[]arr = new int[N];
        int[] cost = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(i > 0){
                cost[i-1] = arr[i] - arr[i-1];
            }
        }

        Arrays.sort(cost);
        int ans = 0;
        for(int i = 0; i < N-K; i++){
            ans += cost[i];
        }

        System.out.println(N == 1 ? arr[0] : ans);
        br.close();
    }
}