import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-13
 * @see             https://www.acmicpc.net/problem/1946
 * @performance
 * @category 		#
 * @note
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] val = new int[N][2];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                val[i][0] = Integer.parseInt(st.nextToken());
                val[i][1] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(val, (o1, o2) -> Integer.compare(o1[0], o2[0]));

            int cnt = 1;
            int highRank = val[0][1];

            for(int i = 1; i < N; i++){
                if(val[i][1] < highRank){
                    cnt++;
                    highRank = val[i][1];
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}