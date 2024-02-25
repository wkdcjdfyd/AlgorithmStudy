import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2024-02-26
@see            
@performance    https://www.acmicpc.net/problem/5558
@category       #BFS
@note          
*/

public class Main {

    static int H, W, N;
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Factory[] factories;
    static Mouse mouse;

    static class Mouse{
        int x, y, cnt;

        public Mouse(int x, int y) {
            this.x = x;
            this.y = y;
            this.cnt = 0;
        }
    }

    static class Factory{
        int x, y;

        public Factory(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int target){
        Deque<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[H][W];
        visited[mouse.x][mouse.y] = 1;
        q.offer(new int[]{mouse.x, mouse.y});

        while(!q.isEmpty()){
            int[] now = q.poll();

            for(int d = 0; d < dx.length; d++){
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(graph[nx][ny] == -1 || visited[nx][ny] != 0) continue;
                visited[nx][ny] = visited[now[0]][now[1]] + 1;

                if(nx == factories[target].x && ny == factories[target].y){
                    mouse.cnt += visited[nx][ny] - 1;
                    mouse.x = nx;
                    mouse.y = ny;
                    return;
                }

                q.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new int[H][W];
        factories = new Factory[N + 1];

        for(int i = 0; i < H; i++){
            String s = br.readLine();
            for(int j = 0; j < W; j++){
                if(s.charAt(j) == '.') continue;
                if(s.charAt(j) == 'S'){
                    mouse = new Mouse(i, j);
                }
                else if(s.charAt(j) == 'X'){
                    graph[i][j] = -1;
                }
                else{
                    graph[i][j] = s.charAt(j) - '0';
                    factories[graph[i][j]] = new Factory(i, j);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            bfs(i);
        }

        System.out.println(mouse.cnt);
        br.close();
    }
}