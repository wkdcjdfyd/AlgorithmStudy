import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
@author         Ryong
@since          2023-11-1
@see            https://www.acmicpc.net/problem/1986
@performance
@category       #구현
@note
*/

public class Main {

    static int[] queenX = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] queenY = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] knightX = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] knightY = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int n, m;
    static int[][] graph;
    static boolean[][] visited;
    static int cnt;

    public static boolean isIn(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void checkQueen(int x, int y){
        for(int d = 0; d < queenX.length; d++){
            int scale = 1;

            while(true){
                int nx = x + queenX[d] * scale;
                int ny = y + queenY[d] * scale;

                if(!isIn(nx, ny) || graph[nx][ny] != 0) break;
                if(!visited[nx][ny]){
                    visited[nx][ny] = true;
                    cnt--;
                }
                scale++;
            }
        }
    }

    public static void checkKnight(int x, int y){
        for(int d = 0; d < knightX.length; d++){
            int nx = x + knightX[d];
            int ny = y + knightY[d];

            if(!isIn(nx, ny) || graph[nx][ny] != 0) continue;
            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                cnt--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];
        cnt = n * m;

        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        for(int q = 1; q <= Q; q++){
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            graph[x][y] = 1;
            visited[x][y] = true;
            cnt--;
        }

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int k = 1; k <= K; k++){
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            graph[x][y] = 2;
            visited[x][y] = true;
            cnt--;
        }

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());
        for(int p = 1; p <= P; p++){
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            graph[x][y] = 3;
            visited[x][y] = true;
            cnt--;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                switch (graph[i][j]){
                    case 1:
                        checkQueen(i, j);
                        break;
                    case 2:
                        checkKnight(i, j);
                        break;
                }
            }
        }

        System.out.println(cnt);
        br.close();
    }
}