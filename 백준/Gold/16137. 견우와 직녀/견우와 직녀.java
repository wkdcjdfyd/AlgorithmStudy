import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author 			Ryong
 * @since 			2023-10-1
 * @see  			https://www.acmicpc.net/problem/16137
 * @performance 	
 * @category 		#구현
 * @note 			
 */

public class Main {
    static int N, M;
    static int[][] graph;
    static ArrayList<Pos> canBuild;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean validBridge(Pos p){
        int outCnt = 0;
        int landCnt = 0;
        boolean[] isLand = new boolean[dx.length];

        for(int d = 0; d < dx.length; d++){
            int nx = p.x + dx[d];
            int ny = p.y + dy[d];

            if(!isValid(nx, ny)) outCnt++;
            else if(graph[nx][ny] == 1){
                landCnt++;
                isLand[d] = true;
            }
        }

        if(isLand[0] && isLand[2]) return true;
        if(isLand[1] && isLand[3]) return true;
        if(outCnt == 2 && landCnt == 2) return true;
        return false;
    }

    public static boolean isValid(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static int bfs(){
        Deque<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;
        q.offer(new Pos(0, 0));

        int time = 0;
        while(!q.isEmpty()){
            time++;
            int size = q.size();

            while(--size >= 0){
                Pos now = q.poll();

                for(int d = 0; d < dx.length; d++){
                    int nx = now.x + dx[d];
                    int ny = now.y + dy[d];

                    if(!isValid(nx, ny)) continue;
                    if(graph[nx][ny] == 0 || visited[nx][ny]) continue;

                    if(nx == N-1 && ny == N-1) return time;

                    if(graph[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                    else if(graph[now.x][now.y] == 1){
                        if(time % graph[nx][ny] == 0){
                            visited[nx][ny] = true;
                            q.offer(new Pos(nx, ny));
                        }
                        else{
                            q.offer(now);
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        canBuild = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 0){
                    canBuild.add(new Pos(i, j));
                }
            }
        }

        int ans = bfs();
        if(ans == -1) {
            ans = Integer.MAX_VALUE;
        }

        for(Pos p : canBuild){
            if(!validBridge(p)) continue;

            graph[p.x][p.y] = M;
            int time = bfs();

            if(time != -1){
                ans = Math.min(ans, time);
            }

            graph[p.x][p.y] = 0;
        }

        System.out.println(ans);
        br.close();
    }
}