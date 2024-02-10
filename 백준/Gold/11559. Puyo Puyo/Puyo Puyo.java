import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
@author         Ryong
@since          2024-02-10
@see            https://www.acmicpc.net/problem/11559
@performance
@category       #구현
@note
*/

public class Main {

    static final int H = 12;
    static final int W = 6;
    static char[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static List<int[]> checker() {
        boolean[][] visited = new boolean[H][W];
        List<int[]> toBeDeleted = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (graph[i][j] != '.' && !visited[i][j]) {
                    counter(i, j, visited, toBeDeleted);
                }
            }
        }

        return toBeDeleted;
    }

    public static void counter(int x, int y, boolean[][] visited, List<int[]> toBeDeleted) {
        int[] start = {x, y};

        List<int[]> candi = new ArrayList<>();
        candi.add(start);
        visited[x][y] = true;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < dx.length; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (!isValid(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (graph[x][y] != graph[nx][ny]) continue;

                visited[nx][ny] = true;
                int[] nxt = {nx, ny};
                candi.add(nxt);
                q.offer(nxt);
            }
        }

        if (candi.size() >= 4) {
            toBeDeleted.addAll(candi);
        }
    }

    public static void delete(List<int[]> toBeDeleted){
        for (int[] now : toBeDeleted) {
            graph[now[0]][now[1]] = '.';
        }
    }

    public static void goDown(){
        for(int j = 0; j < W; j++){
            int x = H-1;
            for(int i = H-1; i >= 0; i--){
                if(graph[i][j] != '.'){
                    graph[x--][j] = graph[i][j];
                }
            }

            for(int i = x; i >= 0; i--){
                graph[i][j] = '.';
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        graph = new char[H][];
        int ans = 0;

        for (int i = 0; i < H; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        while(true){
            List<int[]> toBeDeleted = checker();
            if(toBeDeleted.isEmpty()) break;

            delete(toBeDeleted);
            ans++;
            goDown();
        }

        System.out.println(ans);
        br.close();
    }
}