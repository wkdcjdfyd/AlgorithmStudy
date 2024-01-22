import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-23
@see            https://www.acmicpc.net/problem/1138
@performance
@category       #구현
@note
*/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] h = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            h[i] = Integer.parseInt(st.nextToken());
        }

        int zeroIdx = N;
        boolean[] visited = new boolean[N];

        for(int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if(visited[i]) continue;
                if (h[i] == 0) {
                    visited[i] = true;
                    sb.append(i + 1).append(" ");
                    break;
                }
                h[i]--;
            }
        }

        System.out.println(sb);
        br.close();
    }
}