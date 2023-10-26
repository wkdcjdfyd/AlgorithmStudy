import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-10-27
@see            https://www.acmicpc.net/problem/27211
@performance
@category       #BFS
@note
*/

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] graph;
    static int[][] visited;
    static int N, M;

    public static void bfs(int x, int y, int num){
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = num;

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < dx.length; d++){
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx == -1) nx = N-1;
                if(ny == -1) ny = M-1;
                if(nx == N) nx = 0;
                if(ny == M) ny = 0;

                if(graph[nx][ny] != 0 || visited[nx][ny] != 0) continue;
                visited[nx][ny] = num;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(graph[i][j] ==0 && visited[i][j] == 0){
                    bfs(i, j, ++cnt);
                }
            }
        }

        System.out.println(cnt);
        br.close();
    }
}