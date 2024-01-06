import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-06
@see            https://www.acmicpc.net/problem/1956
@performance
@category       #플로이드-워셜
@note
*/

public class Main {

    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] graph = new int[V+1][V+1];

        for(int i = 1; i < V+1; i++){
            for(int j = 1; j < V+1; j++){
                graph[i][j] = INF;
            }
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        for(int k = 1; k < V+1; k++){
            for(int i = 1; i < V+1; i++){
                for(int j = 1; j < V+1; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int ans = INF;
        for(int i = 1; i < V+1; i++){
            ans = Math.min(ans, graph[i][i]);
        }

        if(ans == INF) ans = -1;
        System.out.println(ans);
        br.close();
    }
}