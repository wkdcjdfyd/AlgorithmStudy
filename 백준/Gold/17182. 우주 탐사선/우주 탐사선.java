import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-20
@see            https://www.acmicpc.net/problem/17182
@performance    
@category       #플로이드워셜
@note          
*/

public class Main {

    static int N, K;
    static int[][] graph;
    static int ans;

    public static void dfs(int now, int nth, int val, boolean[] visited){
        if(nth == N){
            ans = Math.min(ans, val);
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i, nth + 1, val + graph[now][i], visited);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        ans = (int)1e9;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i == j) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        boolean[] visited = new boolean[N];
        visited[K] = true;
        dfs(K, 1, 0, visited);

        System.out.println(ans);
        br.close();
    }
}