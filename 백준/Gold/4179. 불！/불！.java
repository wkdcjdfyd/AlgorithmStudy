import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-01-09
@see            https://www.acmicpc.net/problem/4179
@performance
@category       #BFS
@note
*/

public class Main {

    static int R, C;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int bfs(int x, int y){
        Deque<int[]> q = new ArrayDeque<>();
        Deque<int[]> fireQ = new ArrayDeque<>();
        boolean[][] fireVisited = new boolean[R][C];
        q.offer(new int[]{x, y});

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(graph[i][j] == -2){
                    fireVisited[i][j] = true;
                    fireQ.offer(new int[]{i, j});
                }
            }
        }

        while(!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                int[] now = q.poll();
                if(graph[now[0]][now[1]] < 1) continue;

                if(now[0] == 0 || now[0] == R-1 || now[1] == 0 || now[1] == C-1){
                    return graph[now[0]][now[1]];
                }

                for (int d = 0; d < dx.length; d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(graph[nx][ny] != 0) continue;
                    graph[nx][ny] = graph[now[0]][now[1]] + 1;

                    q.offer(new int[]{nx, ny});
                }
            }

            n = fireQ.size();
            for(int i = 0; i < n; i++){
                int[] now = fireQ.poll();

                for (int d = 0; d < dx.length; d++) {
                    int nx = now[0] + dx[d];
                    int ny = now[1] + dy[d];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(fireVisited[nx][ny]) continue;
                    if(graph[nx][ny] == -1) continue;

                    fireVisited[nx][ny] = true;
                    graph[nx][ny] = -2;
                    fireQ.offer(new int[]{nx, ny});
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new int[R][C];

        int startX = 0;
        int startY = 0;

        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                char c = s.charAt(j);
                if(c == '#') {
                    graph[i][j] = -1;
                }
                else if(c == 'J'){
                    startX = i;
                    startY = j;
                    graph[i][j] = 1;
                }
                else if(c == 'F'){
                    graph[i][j] = -2;
                }
            }
        }

        int ans = bfs(startX, startY);
        System.out.println(ans != 0 ? ans : "IMPOSSIBLE");
        br.close();
    }
}