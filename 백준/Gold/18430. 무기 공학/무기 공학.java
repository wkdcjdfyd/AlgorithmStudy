import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-27
@see            https://www.acmicpc.net/problem/18430
@performance
@category       #백트래킹
@note
*/

public class Main {

    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[][] dx = {{0, 1}, {-1, 0}, {-1, 0}, {0, 1}};
    static int[][] dy = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int ans;

    public static void dfs(int x, int y, int power){
        if(y == M){
            x++;
            y = 0;
        }

        if(x == N){
            ans = Math.max(ans, power);
            return;
        }

        if(!visited[x][y]) {
            for (int type = 0; type < dx.length; type++) {
                int nx1 = x + dx[type][0];
                int ny1 = y + dy[type][0];
                if (!isValid(nx1, ny1) || visited[nx1][ny1]) continue;

                int nx2 = x + dx[type][1];
                int ny2 = y + dy[type][1];
                if (!isValid(nx2, ny2) || visited[nx2][ny2]) continue;

                visited[x][y] = true;
                visited[nx1][ny1] = true;
                visited[nx2][ny2] = true;
                dfs(x, y + 1, power + graph[nx1][ny1] + graph[nx2][ny2] + (graph[x][y] * 2));
                visited[x][y] = false;
                visited[nx1][ny1] = false;
                visited[nx2][ny2] = false;
            }
        }

        dfs(x, y+1, power);
    }

    public static boolean isValid(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];
        ans = 0;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans);
        br.close();
    }
}