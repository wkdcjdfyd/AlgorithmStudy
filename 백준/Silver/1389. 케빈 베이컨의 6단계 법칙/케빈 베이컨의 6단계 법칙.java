import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-04
@see            https://www.acmicpc.net/problem/1389
@performance    
@category      #플로이드-워셜 
@note          
*/

public class Main {

    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j) continue;
                graph[i][j] = INF;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int ans = 0;
        int min = INF;

        for(int i = 1; i <= N; i++){
            int kevin = 0;
            for(int j = 1; j <= N; j++){
                kevin += graph[i][j];
            }
            if(kevin < min){
                min = kevin;
                ans = i;
            }
        }

        System.out.println(ans);
        br.close();
    }
}