import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
* @author         Ryong
* @since          2024-01-24
* @see            https://www.acmicpc.net/problem/14940
* @performance
* @category       #BFS
* @note
*/

public class Main {

    static int n, m;
    static int[][] graph;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void bfs(int a, int b){
        Deque<int[]> q = new ArrayDeque<>();
        visited[a][b] = 1;
        q.offer(new int[]{a, b});

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int d = 0; d < dx.length; d++){
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(visited[nx][ny] != 0 || graph[nx][ny] == 0) continue;
                visited[nx][ny] = visited[now[0]][now[1]] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new int[n][m];
        int startX = 0;
        int startY = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 2){
                    startX = i;
                    startY = j;
                }
            }
        }

        bfs(startX, startY);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(graph[i][j] == 0){
                    sb.append(0).append(" ");
                }
                else {
                    sb.append(visited[i][j]-1).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}