import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-11-1
@see            https://www.acmicpc.net/problem/9711
@performance
@category       #
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] pibo = new long[10001];

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t < T+1; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            pibo[1] = 1;
            pibo[2] = 1;
            for(int i = 3; i <= P; i++){
                pibo[i] = (pibo[i-1] + pibo[i-2]) % Q;
            }
            sb.append("Case #").append(t).append(": ").append(pibo[P] % Q).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}