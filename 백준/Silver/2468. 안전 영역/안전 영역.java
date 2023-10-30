import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int[][] graph;
    static int maxHeight;

    public static int counter(int height){
        int num = 0;
        int[][] visited = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] == 0 && graph[i][j] > height){
                    bfs(i, j, visited, ++num, height);
                }
            }
        }
        return num;
    }

    public static void bfs(int x, int y, int[][] visited, int num, int height) {
        Deque<int[]> q = new ArrayDeque<>();
        visited[x][y] = num;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < dx.length; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] != 0 || graph[nx][ny] <= height) continue;
                visited[nx][ny] = num;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, graph[i][j]);
            }
        }

        int ans = 0;
        for(int i = 0; i <= maxHeight; i++){
            ans = Math.max(ans, counter(i));
        }

        System.out.println(ans);
        br.close();
    }
}